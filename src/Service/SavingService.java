package Service;
import java.math.BigInteger;
import Db.MySql.Tables.SavingDB;

public class SavingService {
    private SavingDB database;

    public SavingService(){
        this.database = new SavingDB();
    };

    public SavingDB getDatabase() {
        return database;
    }

    public void addsaving(String name, String category, BigInteger value) {
        database.insertSaving(name, category, value);
    }

    public void getAllSaving(){
        database.getAllSaving();
    }
    
    public void updateSaving(String name, BigInteger value, String category, int id){
        database.updateSaving(name, value, category, id);
    }

    public void deleteSaving(int id){
        database.deleteSaving(id);
    }

}
