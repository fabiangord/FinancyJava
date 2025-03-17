package Models;
import java.math.BigInteger;
import java.util.UUID;

public class Saving {
    private String id = UUID.randomUUID().toString();
    public String name;
    public BigInteger value;

    Saving(String name, BigInteger value) {
        this.name = name;
        this.value = value;

    }

    public String getid(){
        return id;
    }

}
