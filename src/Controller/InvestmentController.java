package Controller;

import Models.Investment;
import Service.InvestmentService;
import java.math.BigDecimal;
import java.util.List;

public class InvestmentController {
    private final InvestmentService invService;

    public InvestmentController(){
        this.invService = new InvestmentService();
    };

    public void add(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) {
        invService.add(concept, investment, interest, months, feeBack);
    }

    public void insertDB(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) {
        invService.insertDB(concept, investment, interest, months, feeBack);
    }
    
    public List<Investment> getAll(){
        return invService.getAll();
    }

    public void update(String concept, long goal){
        invService.update(concept, goal);
    }

    public void delete(String concept){
        invService.delete(concept);
    }

    public List<Investment> getOne(String concept){
        return invService.getOne(concept);
    }

    public long getTotal(){
        return invService.getTotal();
    }
    
    public long getResult(String concept, int goal, long investment, 
                         float interest, int months){
        return invService.getResult(concept, goal, investment, interest, months);
    }
    
    public BigDecimal getResult2(String concept, BigDecimal investment, 
                         float interest, int months){
    return invService.getResult(concept, investment, interest, months);
    }
}
