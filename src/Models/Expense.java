package Models;

import java.math.BigInteger;

public class Expense extends WithOutMoney {   
    Expense(String name, BigInteger value, Category category) {
        super(name, value,category);
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
