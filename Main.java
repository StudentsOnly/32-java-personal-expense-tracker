import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner s = new Scanner(System.in);
        while (true) {
            printMainMenu();
            handleMainMenu(s, manager);
        }
    }

    static void printMainMenu() {
        System.out.println("===Main menu===\n" +
                "1 - Add expense\n" +
                "2 - Show all expenses\n" +
                "3 - Show category info\n" +
                "4 - Show expenses by category\n" +
                "5 - Generate monthly report\n" +
                "6 - Generate yearly report\n" +
                "7 - Exit");
    }

    static void handleMainMenu(Scanner s, Manager manager) {
        switch (s.nextLine()) {
            case "1" -> addExpense(s, manager);
            case "2" -> showAllExpenses(manager);
            case "3" -> showCategoryInfo(s, manager);
            case "4" -> showExpensesByCategory(s, manager);
            case "5" -> generateMonthlyReport(s, manager);
            case "6" -> generateYearlyReport(s, manager);
            case "7" -> System.exit(0);
            default -> System.out.println("Wrong input");
        }
    }

    private static void generateYearlyReport(Scanner s, Manager manager) {
        System.out.println("Enter year as a number 1-12");
        int year = Integer.valueOf(s.nextLine());
        manager.printYearlyReport(year);
    }

    private static void generateMonthlyReport(Scanner s, Manager manager) {
        System.out.println("Enter year as a number 1-12");
        int year = Integer.valueOf(s.nextLine());
        System.out.println("Enter month as a number 1-12");
        int month = Integer.valueOf(s.nextLine());
        manager.printMonthlyReport(year, month);
    }

    private static void showExpensesByCategory(Scanner s, Manager manager) {
        if (manager.isEmpty()) {
            System.err.println("Expense tracker is empty");
        }

        System.out.println("Enter category");
        String category = s.nextLine();
        manager.printExpensesByCategory(category);
    }

    private static void showCategoryInfo(Scanner s, Manager manager) {
        if (manager.isEmpty()) {
            System.err.println("Expense tracker is empty");
        }
        System.out.println("Enter category");
        String category = s.nextLine();
        manager.printCategoryDetails(category);
    }

    private static void showAllExpenses(Manager manager) {
        if (manager.isEmpty()) {
            System.err.println("Expense tracker is empty");
        } else manager.printAllExpenses();
    }

    private static void addExpense(Scanner s, Manager manager) {
        System.out.println("Enter expense in $");
        float expense = Float.valueOf(s.nextLine());
        System.out.println("Enter Category (press enter to leave empty)");
        String category = s.nextLine();
        System.out.println("Enter description (press enter to leave empty)");
        String description = s.nextLine();

        manager.addExpense(new Expense(expense, category, description));
    }

}
