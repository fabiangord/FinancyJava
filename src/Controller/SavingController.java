package Controller;
import java.math.BigInteger;
import java.util.List;

import Models.Saving;
import Service.SavingService;

public class SavingController {
    private SavingService service;

    public SavingController(){
        this.service = new SavingService();
    };

    public void add(String name, String category, BigInteger value) {
        service.add(name, category, value);
    }

    public List<Saving> getAll(){
        return service.getAll();
    } 

    public void update(String name, BigInteger value, String category, int id){
        service.update(name, value, category, id);
    }

    public void delete(int id){
        service.delete(id);
    }

    public List<Saving> getOne(int id){
        return service.getOne(id);
    }

    public BigInteger getTotal(){
        return service.getTotal();
    }

    public void exportCSV(){
        service.exportCSV();
    }
}
