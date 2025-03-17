package Service;

import java.math.BigInteger;
import Db.MySql.Tables.BudgetDB;

public class BudgetService {

    private BudgetDB database;

    public BudgetService(){
        this.database = new BudgetDB();
    };

    public void addBudget(String name, String category, BigInteger value) {
        database.insertBudget(name, category, value);
    }

    public void getAllBudget(){
        database.getAllBudget();
    }
}
