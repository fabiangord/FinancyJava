package Controller;

import java.math.BigInteger;
import java.util.List;

import Models.Budget;
import Service.BudgetService;

public class BudgetController {
    private BudgetService service;

    public BudgetController(){
        this.service = new BudgetService();
    };

    public void add(String name, BigInteger value) {
        service.add(name, value);
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
