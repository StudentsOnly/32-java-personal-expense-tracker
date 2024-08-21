import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Expense {
    private float amount;
    private String category;
    private String description;
    private LocalDateTime dateAndTime;

    public Expense(float amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.dateAndTime = LocalDateTime.now();
    }

    public float getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(dateAndTime, expense.dateAndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateAndTime);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "amount=$" + amount +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", dateAndTime=" + dateAndTime.format(DateTimeFormatter.ofPattern("yyyy:MM:dd hh:mm")) +
                '}';
    }
}
