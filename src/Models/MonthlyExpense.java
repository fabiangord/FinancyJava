package Models;

import java.math.BigInteger;
import java.time.LocalDate;

public class MonthlyExpense {
    public String name;
    public BigInteger value;
    public String category;
    public LocalDate date;

    public MonthlyExpense(String name, BigInteger value, String category, LocalDate date) {
        this.name = name;
        this.value = value;
        this.category = category;
        this.date = date;
    }
}
