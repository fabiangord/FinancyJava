package Controller;

import java.math.BigInteger;

import Service.BudgetService;

public class BudgetController {
    private BudgetService database;

    public BudgetController(){
        this.database = new BudgetService();
    };

    public void addBudget(String name, String category, BigInteger value) {
        database.addBudget(name, category, value);
    }

    public void getAllBudget(){
        database.getAllBudget();
    } 
}
