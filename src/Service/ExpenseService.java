package Service;

import java.math.BigInteger;
import java.util.List;

import Db.MySql.Tables.ExpensesDB;
import Models.Expense;

public class ExpenseService {
    private ExpensesDB database;

    public ExpenseService(){
        this.database = new ExpensesDB();
    };

    public void add(String name, String category, BigInteger value) {
        database.insert(name, category, value);
    }

    public List<Expense> getAll(){
        return database.getAll();
    }

    public void update(String name, BigInteger value, String category, int id){
        database.update(name, value, category, id);
    }

    public void delete(int id){
        database.delete(id);
    }

    public List<Expense> getOne(int id){
        return database.getOne(id);
    }

    public BigInteger getTotal(){
        return database.getTotal();
    }
}
