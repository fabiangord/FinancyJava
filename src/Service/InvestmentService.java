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
    
    public List<Investment> getAll() throws SQLException {
        return invDB.getAll();
    }

    public void update(String concept, long goal) throws SQLException {
        invDB.update(concept, goal);
    }

    public void delete(String concept) throws SQLException {
        invDB.delete(concept);
    }

    public List<Investment> getOne(String concept) throws SQLException {
        return invDB.getOne(concept);
    }

    public long getTotal() throws SQLException {
        return invDB.getTotal();
    }
    
    public BigDecimal getResult(String concept, int goal, BigDecimal investment, 
                                             float interest, int months){
        return invDB2.investmentResult(concept, goal, investment, interest, months);
    }
    public BigDecimal getResult(String concept, BigDecimal investment, 
                          float interest, int months){
    return invDB2.investmentResult2(concept, investment, interest, months);
    }
}
