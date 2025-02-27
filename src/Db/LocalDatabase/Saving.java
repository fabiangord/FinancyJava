package Db.LocalDatabase;

import java.util.ArrayList;

public class Saving {
public ArrayList<Saving> savings;

    public ArrayList<Saving> get () {
       return savings;
    }

    public void insert (Saving saving) {
        savings.add(saving);
    }
}
