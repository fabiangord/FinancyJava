package Models;

import java.math.BigInteger;

import Helpers.Category;

public class Expense extends WithOutMoney {   
    public Expense(String name, BigInteger value, Category category) {
        super(name, value,category);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) { 
        super.setId(id);
    }
}
