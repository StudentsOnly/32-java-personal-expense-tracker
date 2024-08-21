import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense implements Comparable<Expense>{
    double amount;
    String description;
    LocalDate date;
    DateTimeFormatter usFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public Expense(double amount){
        this.amount = amount;
        this.description = " ";
        this.date = LocalDate.now();
    }
    public Expense(double amount, String description){
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.now();
    }
    public Expense(double amount, String description, int year, int month, int day){
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.of(year, month, day);
    }

    @Override
    public String toString() {
        return amount + "$ (" + description + ") [" + date.format(usFormat) + "]";
    }

    @Override
    public int compareTo(Expense expense) {
        int result = date.compareTo(expense.date);
        if(result == 0){
            result = Double.compare(amount, expense.amount);
        }
        return result;
    }


}
