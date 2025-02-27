package Db.LocalDatabase;

import java.util.ArrayList;

public class Budget {

    public ArrayList<Budget> budgets;

    public ArrayList<Budget> get () {
       return budgets;
    }

    public void insert (Budget budget) {
        budgets.add(budget);
    }
}
