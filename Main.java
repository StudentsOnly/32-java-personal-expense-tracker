import java.time.LocalDate;
import java.util.*;

public class Main{

    private static Scanner scanner = new Scanner(System.in);
    private static List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {

        boolean flag = true;
        while(flag){

            printMenu();

            String operation = scanner.nextLine().trim().toUpperCase();
            System.out.println();

            switch(operation.charAt(0)){
                case 'A' -> addExpenses();
                case 'V' -> {
                    expenses.sort(null);
                    viewExpenses(expenses);
                }
                case 'S' -> expensesByCategory();
                case 'G' -> generateReport();
                case 'E' -> flag = false;
                default -> System.out.println("Invalid input.");
            }
        }

        scanner.close();
    }

    private static void addExpenses() {
        try {
            System.out.println("To add an expense enter a comma-separated list\n" +
                    "e.g. name, 3.24, category, description, 2024-07-31");
            String expense[] = scanner.nextLine().split(",");

            String name = expense[0].trim();
            String category = expense[2].trim();
            String description = expense[3].trim();
            String date = expense[4].trim();

            double amount = 0;
            try {
                amount = Double.parseDouble(expense[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Item amount could not be parsed as double: " + e.getMessage());
            }

            expenses.add(new Expense(name, amount, category, description, date));
            System.out.println("Expense added successfully.");

        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding an expense: "
                    + e.getMessage());
        }
    }

    private static void viewExpenses(List<Expense> expenses) {
        try {
            System.out.printf("%-10s %-20s %-6s %-20s %-40s %n", "DATE", "EXPENSES", "AMOUNT",
                    "CATEGORY", "DESCRIPTION");
            printLine();
            for (var expense : expenses) {
                System.out.println(expense);
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while printing expense view: " + e.getMessage());
        }
    }

    private static void expensesByCategory() {

        Map<String, Double> categories = new TreeMap<>();
        for (Expense expense : expenses) {
            String category = expense.getCategory().toUpperCase();
            Double amountCategory = categories.get(category);
            if (amountCategory == null) {
                amountCategory = 0.0;
            }
            categories.put(category, amountCategory + expense.getAmount());
        }
        System.out.printf("%-20s %-20s %n","CATEGORY" ,"AMOUNT");
        printLine();
        for (Map.Entry<String, Double> entry : categories.entrySet()) {
            System.out.printf("%-20s %-20s %n",
                    entry.getKey(), String.format("$%.2f", entry.getValue()));
        }
        System.out.println();

        List<Expense> expensesCopy = new ArrayList<>(expenses);
        expensesCopy.sort(new Expense.ExpenseComparator("CATEGORY"));
        viewExpenses(expensesCopy);

    }

    private static void generateReport() {

        expenses.sort(null); // sort by date, newest first

        System.out.println("YEARLY REPORT");
        printLine();
        System.out.printf("%-10s %-20s %-6s %-20s %-40s %n", "DATE", "EXPENSES", "AMOUNT",
                "CATEGORY", "DESCRIPTION");
        printLine();
        double yearlyTotal = 0;
        for (Expense expense : expenses) {
            if (LocalDate.now().getYear() == LocalDate.parse(expense.getDate()).getYear()) {
                System.out.println(expense);
                yearlyTotal += expense.getAmount();
            }
        }
        printLine();
        System.out.printf("YEARLY TOTAL: $%-20.2f %n", yearlyTotal);
        System.out.println();

        System.out.println("MONTHLY REPORT");
        printLine();
        System.out.printf("%-10s %-20s %-6s %-20s %-40s %n", "DATE", "EXPENSES", "AMOUNT",
                "CATEGORY", "DESCRIPTION");
        printLine();

        double monthlyTotal = 0;
        for (Expense expense : expenses) {
            if (LocalDate.now().getYear() == LocalDate.parse(expense.getDate()).getYear()
                    && LocalDate.now().getMonthValue() == LocalDate.parse(expense.getDate())
                    .getMonthValue()) {
                System.out.println(expense);
                monthlyTotal += expense.getAmount();
            }
        }
        printLine();
        System.out.printf("MONTHLY TOTAL: $%-20.2f %n", monthlyTotal);
        System.out.println();

    }

    static void printLine() {
        System.out.println("-".repeat(60));
    }

    private static void printMenu(){
        String menu = """
                
                MENU
                -----------------------------------
                (A)dd Expenses
                (V)iew list of expenses
                (S)mmarize expenses
                (G)enerate monthly/yearly expense reports
                (E)xit
                
                Enter operation:\s""";
        System.out.print(menu);
    }

}
