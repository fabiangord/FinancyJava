package Models;
import java.math.BigInteger;

public class Budget {
    private int id;
    public String name;
    public BigInteger value;
    
    public Budget(String name, BigInteger value){
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
