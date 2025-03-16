package Controller;
import java.math.BigInteger;
import Service.SavingService;

public class SavingController {
    private SavingService database;

    public SavingController(){
        this.database = new SavingService();
    };

    public void addSaving(String name, String category, BigInteger value) {
        database.addsaving(name, category, value);
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
