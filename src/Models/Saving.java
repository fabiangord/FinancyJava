package Models;

import java.math.BigInteger;

public class Saving extends WithOutMoney {
    Saving (String name, BigInteger value, Category category) {
        super(name, value, category);
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
