package Service;

import java.math.BigInteger;
import java.util.List;

import Models.Income;
import Db.MySql.Tables.IncomeDB;

public class IncomeService {
    private IncomeDB database;

    public IncomeService(){
        this.database = new IncomeDB();
    };

    public void add(String name, String category, BigInteger value) {
        database.insert(name, category, value);
    }

    public List<Income> getAll(){
        return database.getAll();
    }

    public void update(String name, BigInteger value, String category, int id){
        database.update(name, value, category, id);
    }

    public void delete(int id){
        database.delete(id);
    }

    public List<Income> getOne(int id){
        return database.getOne(id);
    }

    public BigInteger getTotal(){
        return database.getTotal();
    }
}
