package Models;

import java.math.BigInteger;
import java.time.LocalDate;

public class Income {
    private int id;
    public String name;
    public BigInteger value;
    public LocalDate date = LocalDate.now();

    public Income(String name, BigInteger value) {
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
