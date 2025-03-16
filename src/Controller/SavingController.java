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
}
