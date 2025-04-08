package Models;

import java.math.BigInteger;
import java.util.List;

public class Projection {
    private int id;
    public List<BigInteger> historicalValues;
    public int months;
    public BigInteger goal;

    public Projection (int id, List<BigInteger> historicalValues, int months, BigInteger goal){
        this.id = id;
        this.historicalValues = historicalValues;
        this.months = months;
        this.goal = goal;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
