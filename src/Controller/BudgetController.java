package Controller;

import java.math.BigInteger;
import java.util.List;

import Models.Budget;
import Service.BudgetService;
import Service.IncomeService;

public class BudgetController {
    private BudgetService service;
    private IncomeService serviceIncome;

    public BudgetController(){
        this.service = new BudgetService();
        this.serviceIncome = new IncomeService();
    };

    public void add(String name, String category, BigInteger value) {
        service.add(name, category, value);
        serviceIncome.add(name, category, value);
    }

    public List<Budget> getAll(){
        return service.getAll();
    } 

    public void update(String name, BigInteger value, String category, int id){
        service.update(name, value, category, id);
    }

    public void delete(int id){
        service.delete(id);
    }

    public List<Budget> getOne(int id){
        return service.getOne(id);
    }

    public BigInteger getTotal(){
        return service.total();
    }
}
