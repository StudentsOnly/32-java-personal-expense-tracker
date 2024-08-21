import java.util.Comparator;

public class Expense implements Comparable<Expense> {
    private String name;
    private double amount;
    private String category;
    private String description;
    private String date;

    public Expense(String name, double amount, String category, String description, String date) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "%-10s %-20s %-6s %-20s %-40s".formatted(
                date, name, String.format("$%.2f", amount), category, description);
    }

    @Override
    public int compareTo(Expense expense) {
        int result = - date.compareTo(expense.date);
        if (result == 0) {
            result = -Double.compare(amount, expense.amount);
        }
        if (result == 0) {
            result = category.compareTo(expense.category);
        }
        if(result == 0) {
            result = name.compareTo(expense.name);
        }
        return result;
    }

    public static class ExpenseComparator implements Comparator<Expense> {

        String sortedBy;

        public ExpenseComparator() {
            this.sortedBy = "N";
        }

        public ExpenseComparator(String sortedBy) {
            this.sortedBy = sortedBy.trim().toUpperCase();
        }

        @Override
        public int compare(Expense expense, Expense expense2) {
            return switch(sortedBy.charAt(0)) {
                default -> expense.name.compareTo(expense2.name);
                case 'A' -> Double.compare(expense.amount, expense2.amount);
                case 'C' -> expense.category.compareTo(expense2.category);
                case 'D' -> expense.description.compareTo(expense2.description);
                case 'T' -> - expense.date.compareTo(expense2.date);
            };
        }
    }
}
