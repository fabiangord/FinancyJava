package Controller;

import java.math.BigInteger;
import java.util.List;

import Models.Income;
import Service.BudgetService;
import Service.IncomeService;

public class IncomeController {
private IncomeService service;
private BudgetService serviceBudget;

    public IncomeController(){
        this.service = new IncomeService();
        this.serviceBudget = new BudgetService();
    };

    public void add(String name, String category, BigInteger value) {
        service.add(name, category, value);
        serviceBudget.add(name, value);
    }

    public List<Income> getAll(){
        return service.getAll();
    }

    public void update(String name, BigInteger value, String category, int id){
        service.update(name, value, category, id);
    }

    public void delete(int id){
        service.delete(id);
    }

    public List<Income> getOne(int id){
        return service.getOne(id);
    }

    public BigInteger getTotal(){
        return service.getTotal();
    }
}
