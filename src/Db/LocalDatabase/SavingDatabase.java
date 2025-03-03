package Db.LocalDatabase;

import java.math.BigInteger;
import java.util.List;

import Models.Saving;

public class SavingDatabase {
public List<Saving> savings;

    public List<Saving> getAll () {
       return savings;
    }

    public Saving getOne(String name) {
        for (Saving saving: savings) {
            if(saving.name.equals(name)){
                return saving;
            }
        }

        return null;
    }

    public void insert (Saving saving) {
        savings.add(saving);
    }

    public void delete (String name) {
        savings.removeIf(saving -> saving.name.equals(name));
    }

    public void update (String beforeName, String afterName, BigInteger newValue) {
        for (Saving saving: savings) {
            if(saving.name.equals(beforeName)){
                saving.name = afterName;
                saving.value = newValue;
            }
        }
        
    }
}
