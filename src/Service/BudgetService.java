package Service;

import java.math.BigInteger;
import java.util.List;

import Db.MySql.Tables.BudgetDB;
import Models.Budget;

public class BudgetService {

    private BudgetDB database;

    public BudgetService(){
        this.database = new BudgetDB();
    };

    public void add(String name, String category, BigInteger value) {
        database.insert(name, category, value);
    }

    public List<Budget> getAll(){
        return database.getAll();
    }

    public void update(String name, BigInteger value, String category, int id){
        database.update(name, value, category, id);
    }

    public void delete(int id){
        database.delete(id);
    }

    public List<Budget> getOne(int id){
        return database.getOne(id);
    }

    public BigInteger total(){
        return database.getTotal();
    }
}
