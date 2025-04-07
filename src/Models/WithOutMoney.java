package Models;

import java.math.BigInteger;
import java.time.LocalDate;

import Helpers.Category;

public abstract class WithOutMoney {
    private int id;
    public String name;
    public BigInteger value;
    public Category category;
    public LocalDate date;

    WithOutMoney(String name, BigInteger value, Category category) {
        this.name = name;
        this.value = value;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
