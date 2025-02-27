package Db.LocalDatabase;

import java.util.ArrayList;

public class Expense {
    public ArrayList<Expense> expenses;

    public ArrayList<Expense> get () {
       return expenses;
    }

    public void insert (Expense expense) {
        expenses.add(expense);
    }
}
