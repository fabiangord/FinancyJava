package Db.LocalDatabase;

import java.math.BigInteger;
import java.util.List;

import Models.Category;
import Models.Expense;

public class ExpenseDatabase {
    public List<Expense> expenses;

    public List<Expense> getAll () {
       return expenses;
    }

    public Expense getOne(String name) {
        for (Expense expense: expenses) {
            if(expense.name.equals(name)){
                return expense;
            }
        }

        return null;
    }

    public void insert (Expense expense) {
        expenses.add(expense);
    }

    public void delete (String name) {
        expenses.removeIf(expense -> expense.name.equals(name));
    }

    public void update (String beforeName, String afterName, BigInteger newValue, Category category) {
        for (Expense expense: expenses) {
            if(expense.name.equals(beforeName)){
                expense.name = afterName;
                expense.value = newValue;
                expense.category = category;
            }
        }
        
    }
}
