package Controller;

import java.math.BigInteger;
import java.util.List;

import Models.Expense;
import Service.ExpenseService;

public class ExpensesController {
private ExpenseService service;

    public ExpensesController(){
        this.service = new ExpenseService();
    };

     public void add(String name, String category, BigInteger value) {
        service.add(name, category, value);
    }

    public List<Expense> getAll(){
        return service.getAll();
    } 

    public void update(String name, BigInteger value, String category, int id){
        service.update(name, value, category, id);
    }

    public void delete(int id){
        service.delete(id);
    }

    public void deleteAll(){
        service.deleteAll();
    }

    public List<Expense> getOne(int id){
        return service.getOne(id);
    }

    public BigInteger getTotal(){
        return service.getTotal();
    }

    public void exportCSV(){
        service.exportCSV();
    }
}
