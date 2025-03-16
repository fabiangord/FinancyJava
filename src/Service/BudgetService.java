package Service;

import java.math.BigInteger;

import Db.MySql.Tables.BudgetDB;

public class BudgetService {

    private BudgetDB database;

    public BudgetService(){
        this.database = new BudgetDB();
    };

    public void addsaving(String name, String category, BigInteger value) {
        database.insertSaving(name, category, value);
    }

    public void getAllSaving(){
        database.getAllSaving();
    }
}
