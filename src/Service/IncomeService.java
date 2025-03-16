package Service;

import java.math.BigInteger;

import Db.MySql.Tables.IncomeDB;

public class IncomeService {
    private IncomeDB database;

    public IncomeService(){
        this.database = new IncomeDB();
    };

    public void addsaving(String name, String category, BigInteger value) {
        database.insertSaving(name, category, value);
    }

    public void getAllSaving(){
        database.getAllSaving();
    }
}
