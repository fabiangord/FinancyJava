package Models;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

public abstract class WithOutMoney {
    private String id = UUID.randomUUID().toString();
    public String name;
    public BigInteger value;
    public LocalDate date = LocalDate.now();
    public Category category;

    WithOutMoney(String name, BigInteger value, Category category) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
