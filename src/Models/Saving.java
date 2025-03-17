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

    public int getid(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
