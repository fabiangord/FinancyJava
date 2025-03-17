package Models;
import java.math.BigInteger;

import Helpers.Category;

public class Saving {
    private int id;
    public String name;
    public BigInteger value;
    public Category category;

    public Saving(String name, BigInteger value, Category category) {
        this.name = name;
        this.value = value;
        this.category = category;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigInteger getValue() {
        return value;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", value: " + value + ", category: " + category;
    }

    
}
