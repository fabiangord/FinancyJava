package Models;
import java.math.BigInteger;
import java.util.UUID;

public class Budget {
    private String id = UUID.randomUUID().toString();
    public String name;
    public BigInteger value;
    
    Budget(String name, BigInteger value){
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }
}
