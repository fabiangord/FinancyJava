package Service;
import java.math.BigInteger;
import java.util.List;

import Db.MySql.Tables.SavingDB;
import Models.Saving;

public class SavingService {
    private SavingDB database;

    public SavingService(){
        this.database = new SavingDB();
    };

    public SavingDB getDatabase() {
        return database;
    }

    public void add(String name, String category, BigInteger value) {
        database.insert(name, category, value);
    }

    public List<Saving> getAll(){
        return database.getAll();
    }
    
    public void update(String name, BigInteger value, String category, int id){
        database.update(name, value, category, id);
    }

    public void delete(int id){
        database.delete(id);
    }

    public List<Saving> getOne(int id){
        return database.getOne(id);
    }

    public BigInteger getTotal(){
        return database.getTotal();
    }

}
