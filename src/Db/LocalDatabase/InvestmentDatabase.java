package Db.LocalDatabase;

import Models.Investment;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class InvestmentDatabase {
    public List<Investment> investments;
    public BigDecimal ordInterest = BigDecimal.ONE;
    
    public List<Investment> getAll () {
       return investments;
    }

    public long investmentResult(String concept, int goal, 
                                             long investment, float interest, 
                                             int months){
        float realInterest = interest/100;
        long ordInterest = (long) ((investment * months * realInterest) / 12);
       
        return ordInterest;
    }
        public BigDecimal investmentResult2(String concept, 
                                             BigDecimal investment, float interest, 
                                             int months){
        float realInterest = interest/100;
        BigDecimal percent = new BigDecimal(realInterest);
        BigDecimal months_int = new BigDecimal(months);
        BigDecimal months_year = new BigDecimal(12);
        ordInterest = (investment.multiply(months_int).multiply(percent)).divide(months_year, RoundingMode.HALF_DOWN);
        return ordInterest.setScale(6, RoundingMode.HALF_DOWN);
    }
}
