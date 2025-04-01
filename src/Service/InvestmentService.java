package Service;

import Db.LocalDatabase.InvestmentDatabase;
import Db.MySql.Tables.InvestmentDB;
import Models.Investment;
import java.math.BigDecimal;
import java.util.List;

public class InvestmentService {
    private InvestmentDB invDB;
    private InvestmentDatabase invDB2;
    public BigDecimal invest;

    public InvestmentService(){
        this.invDB = new InvestmentDB();
        this.invDB2 = new InvestmentDatabase();
    }

    public void add(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) {
        invDB.insert(concept, investment, interest, months, feeBack);
    }

    public void insertDB(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) {
        invDB.insert(concept, investment, interest, months, feeBack);
    }
    
    public List<Investment> getAll(){
        return invDB.getAll();
    }

    public void update(String concept, long goal){
        invDB.update(concept, goal);
    }

    public void delete(String concept){
        invDB.delete(concept);
    }

    public List<Investment> getOne(String concept){
        return invDB.getOne(concept);
    }

    public long getTotal(){
        return invDB.getTotal();
    }
    
    public long getResult(String concept, int goal, long investment, 
                                             float interest, int months){
        return invDB2.investmentResult(concept, goal, investment, interest, months);
    }
    public BigDecimal getResult(String concept, BigDecimal investment, 
                          float interest, int months){
    return invDB2.investmentResult2(concept, investment, interest, months);
    }
}
