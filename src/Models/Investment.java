package Models;

import java.math.BigDecimal;

public class Investment{
    public String concept;
    public BigDecimal investment;
    public float interest;
    public int months;
    public BigDecimal feeBack;

    public Investment(String concept, BigDecimal investment) {
        this.concept = concept;
        this.investment = investment;
    }

    public Investment(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) {
        this.concept = concept;
        this.investment = investment;
        this.interest = interest;
        this.months = months;
        this.feeBack = feeBack;
    }

    public String getConcept(){
        return concept;
    }

    public BigDecimal getinvestment(){
        return investment;
    }
}
