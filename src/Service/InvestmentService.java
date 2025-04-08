package Service;

import Db.LocalDatabase.InvestmentDatabase;
import Db.MySql.Tables.InvestmentDB;
import Models.Investment;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class InvestmentService {
    private InvestmentDB invDB;
    private InvestmentDatabase invDB2;
    public BigDecimal invest;

    public InvestmentService(){
        this.invDB = new InvestmentDB();
        this.invDB2 = new InvestmentDatabase();
    }

    public void add(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) throws SQLException {
        invDB.insert(concept, investment, interest, months, feeBack);
    }

    public void insertDB(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) throws SQLException {
        invDB.insert(concept, investment, interest, months, feeBack);
    }

    public void update(String concept, long goal) throws SQLException {
        invDB.update(concept, goal);
    }

    public void delete(String concept) throws SQLException {
        invDB.delete(concept);
    }

    public List<Investment> getOne(String concept){
        return invDB.getOne(concept);
    }
    
    public BigDecimal getResult(String concept, BigDecimal investment, 
                          float interest, int months){
    return invDB2.investmentResult2(concept, investment, interest, months);
    }
}
