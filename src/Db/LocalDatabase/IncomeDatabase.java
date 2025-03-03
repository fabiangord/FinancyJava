package Db.LocalDatabase;
import java.math.BigInteger;
import java.util.List;

import Models.Income;

public class IncomeDatabase {
public List<Income> incomes;

    public List<Income> getAll () {
       return incomes;
    }

    public Income getOne(String name) {
        for (Income income: incomes) {
            if(income.budget.name.equals(name)){
                return income;
            }
        }

        return null;
    }

    public void insert (Income income) {
        incomes.add(income);
    }

    public void delete (String name) {
        incomes.removeIf(income -> income.budget.name.equals(name));
    }

    public void update (String beforeName, String afterName, BigInteger newValue) {
        for (Income income: incomes) {
            if(income.budget.name.equals(beforeName)){
                income.budget.name = afterName;
                income.budget.value = newValue;
            }
        }
        
    }
}
