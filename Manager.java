
import java.time.Month;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Manager {

    TreeMap<String, ArrayList<Expense>> transactions;

    public Manager(){
        transactions = new TreeMap<>();
        transactions.put("Groceries", new ArrayList<>());
        transactions.put("Clothing",  new ArrayList<>());
        transactions.put("Internet",  new ArrayList<>());
        transactions.put("Meal delivery",  new ArrayList<>());
        transactions.put("Rent",  new ArrayList<>());
        transactions.put("Travel",  new ArrayList<>());
    }

    public ArrayList<Expense> findCategory(String category){
        if(!transactions.containsKey(category)){
            transactions.put(category, new ArrayList<>());
        }
        return transactions.get(category);
    }

    public void addCategory(String category){
        if(!transactions.containsKey(category)){
            transactions.put(category, new ArrayList<>());
        }
    }

    public String chooseCategory(Scanner scan){
        System.out.println("\nEnter the category:");
        for(String key: transactions.keySet()){
            System.out.println("*\t" + key);
        }
        System.out.println("Add your category:");
        return scan.nextLine();
    }
    /*
    public void displayTransactions(String category){
        displayExpenses(findCategory(category));
    }
    */

    public double summarizeByCategory(Scanner scan){
        String category = chooseCategory(scan);
        double sum = 0;
        for(Expense el: findCategory(category)){
            if(el != null) {
                sum += el.amount;
            }
        }
        return sum;
    }

    public void displayExpenses(ArrayList<Expense> expenses){
        for(Expense el: expenses){
            if(el != null) {
                System.out.println("\t\t- " + el);
            }else{
                System.out.println("\t\t- ");
            }
        }
    }
    public void displayExpenses(ArrayList<Expense> expenses, String month, int year){
        for(Expense el: expenses){
            if(el != null && el.date.getMonth() == Month.valueOf(month) && el.date.getYear() == year) {
                System.out.println("\t\t- " + el);
            }
        }
    }

    public void displayExpenses(ArrayList<Expense> expenses, int year){
        for(Expense el: expenses){
            if(el != null && el.date.getYear() == year) {
                System.out.println("\t\t- " + el);
            }
        }
    }

    public void displayTransactions(){

            System.out.println("\nTransactions: ");
            for(Map.Entry<String, ArrayList<Expense>> entry: transactions.entrySet()){
                System.out.println("*\t" + entry.getKey() + ":");
                displayExpenses(entry.getValue());
            }
    }

    public void addTransaction(Scanner scan){
        String category = chooseCategory(scan);
        addCategory(category);
        System.out.println("Enter amount");
        double amount = Double.valueOf(scan.nextLine());
        System.out.println("Enter description:");
        String description = scan.nextLine();
        System.out.println("Do you want to enter date: Y/N");
        String input = scan.nextLine();
        if(input.equals("Y")){
            System.out.println("Enter year:");
            int year = Integer.valueOf(scan.nextLine());
            System.out.println("Enter month (1 -12):");
            int month = Integer.valueOf(scan.nextLine());
            System.out.println("Enter day:");
            int day = Integer.valueOf(scan.nextLine());
            transactions.get(category).add(new Expense(amount, description, year, month, day));
        }else {
            transactions.get(category).add(new Expense(amount, description));
        }
        System.out.println("\nTransaction was added!");
    }

    public void monthReport(Scanner scan){
        System.out.println("Enter month (january - december):");
        String month = scan.nextLine().toUpperCase();
        System.out.println("Enter year:");
        int year = Integer.valueOf(scan.nextLine());

        System.out.println("\nMonthly report for '" + month + " - " + year + "':");
        for(Map.Entry<String, ArrayList<Expense>> entry: transactions.entrySet()){
            System.out.println("*\t" + entry.getKey() + ":");
            displayExpenses(entry.getValue(), month, year);

        }

    }

    public void yearReport(Scanner scan){
        System.out.println("Enter year:");
        int year = Integer.valueOf(scan.nextLine());

        System.out.println("\nYearly report for '" + year + "':");
        for(Map.Entry<String, ArrayList<Expense>> entry: transactions.entrySet()){
            System.out.println("*\t" + entry.getKey() + ":");
            displayExpenses(entry.getValue(), year);

        }

    }


    public void menu(){
        Scanner scan = new Scanner(System.in);

        while(true) {
            displayMenu();
            switch(Integer.valueOf(scan.nextLine())){
                case 1:
                    addTransaction(scan);
                    break;
                case 2:
                    displayTransactions();
                    break;
                case 3:
                    System.out.println("Total sum = " + summarizeByCategory(scan) + "$");
                    break;
                case 4:
                    monthReport(scan);
                    break;
                case 5:
                    yearReport(scan);
                    break;
                case 6:
                    scan.close();
                    System.exit(0);
                default:
                    System.err.println("\n Invalid input");
                    break;
            }

        }

    }

    public void displayMenu(){
        System.out.println("\nMenu: ");
        System.out.println("* Add transaction:              -> '1'");
        System.out.println("* Display all transactions:     -> '2'");
        System.out.println("* Summarize by category:        -> '3'");
        System.out.println("* Monthly report:               -> '4'");
        System.out.println("* Yearly report:                -> '5'");
        System.out.println("* Exit:                         -> '6'");
    }
}
