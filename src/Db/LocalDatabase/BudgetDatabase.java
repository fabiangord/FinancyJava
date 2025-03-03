package Db.LocalDatabase;

import java.math.BigInteger;
import java.util.List;

import Models.Budget;

public class BudgetDatabase {

    public List<Budget> budgets;

    public List<Budget> getAll() {
        return budgets;
    }

    public Budget getOne(String name) {
        for (Budget budget : budgets) {
            if (budget.name.equals(name)) {
                return budget;
            }
        }

        return null;
    }

    public void insert(Budget budget) {
        budgets.add(budget);
    }

    public void delete(String name) {
        budgets.removeIf(budget -> budget.name.equals(name));
    }

    public void update(String beforeName, String afterName, BigInteger newValue) {
        for (Budget budget : budgets) {
            if (budget.name.equals(beforeName)) {
                budget.name = afterName;
                budget.value = newValue;
            }
        }

    }
}
