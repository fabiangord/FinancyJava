package Db.LocalDatabase;

import java.util.ArrayList;

public class Income {
public ArrayList<Income> incomes;

    public ArrayList<Income> get () {
       return incomes;
    }

    public void insert (Income income) {
        incomes.add(income);
    }
}
