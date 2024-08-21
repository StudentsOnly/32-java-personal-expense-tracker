import java.util.HashSet;

public class Manager {
    HashSet<Expense> expenses;

    public Manager() {
        this.expenses = new HashSet<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void printAllExpenses() {
        for (var expense : expenses) {
            System.out.println(expense);
        }
    }

    public void printExpensesByCategory(String category) {
        boolean flag = false;
        for (var expense : expenses) {
            if (expense.getCategory().equals(category)) {
                flag = true;
                System.out.println(expense);
            }
        }
        if (!flag) System.err.println("There are no expenses");
    }

    public void printCategoryDetails(String category) {
        int amountOfExpenses = 0;
        float totalSpend = 0;
        for (var expense : expenses) {
            if (expense.getCategory().equals(category)) {
                amountOfExpenses++;
                totalSpend += expense.getAmount();
            }
        }
        if (amountOfExpenses > 0)
            System.out.println("Category: " + category + ", Expenses: " + amountOfExpenses + ", total spend: $" + totalSpend);
        else System.err.println("There are no expenses");
    }

    public void printMonthlyReport(int year, int month) {
        int amountOfExpenses = 0;
        float totalSpend = 0;
        for (var expense : expenses) {
            if (expense.getDateAndTime().getYear() == year && month == expense.getDateAndTime().getMonthValue()) {
                amountOfExpenses++;
                totalSpend += expense.getAmount();
            }
        }
        if (amountOfExpenses > 0)
            System.out.println(year + "." + month + ", Expenses: " + amountOfExpenses + ", total spend: $" + totalSpend);
        else System.err.println("There are no expenses");
    }

    public void printYearlyReport(int year) {
        int amountOfExpenses = 0;
        float totalSpend = 0;
        for (var expense : expenses) {
            if (expense.getDateAndTime().getYear() == year) {
                amountOfExpenses++;
                totalSpend += expense.getAmount();
            }
        }
        if (amountOfExpenses > 0)
            System.out.println(year + " " + "Expenses: " + amountOfExpenses + ", total spend: $" + totalSpend);
        else System.err.println("There are no expenses");
    }

    public boolean isEmpty() {
        return expenses.isEmpty();
    }
}
