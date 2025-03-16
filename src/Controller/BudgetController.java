package Controller;

import java.math.BigInteger;

import Service.BudgetService;

public class BudgetController {
    private BudgetService database;

    public BudgetController(){
        this.database = new BudgetService();
    };

    public void addSaving(String name, String category, BigInteger value) {
        database.addsaving(name, category, value);
    }

    public void getAllSaving(){
        database.getAllSaving();
    } 
}
