package Controller;

import java.math.BigInteger;

import Service.IncomeService;

public class IncomeController {
private IncomeService database;

    public IncomeController(){
        this.database = new IncomeService();
    };

    public void addsaving(String name, String category, BigInteger value) {
        database.addsaving(name, category, value);
    }

    public void getAllSaving(){
        database.getAllSaving();
    }
}
