package Controller;

import Models.Investment;
import Service.InvestmentService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class InvestmentController {
    private final InvestmentService invService;

    public InvestmentController(){
        this.invService = new InvestmentService();
    };

    public void add(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) throws SQLException {
        invService.add(concept, investment, interest, months, feeBack);
    }

    public void insertDB(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) throws SQLException {
        invService.insertDB(concept, investment, interest, months, feeBack);
    }
    
    public List<Investment> getAll() throws SQLException {
        return invService.getAll();
    }

    public void update(String concept, long goal) throws SQLException {
        invService.update(concept, goal);
    }

    public void delete(String concept) throws SQLException {
        invService.delete(concept);
    }

    public List<Investment> getOne(String concept) throws SQLException {
        return invService.getOne(concept);
    }

    public long getTotal() throws SQLException {
        return invService.getTotal();
    }
    
    public BigDecimal getResult2(String concept, BigDecimal investment, 
                         float interest, int months){
    return invService.getResult(concept, investment, interest, months);
    }
}
