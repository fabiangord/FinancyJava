package Db.LocalDatabase;

import Models.MonthlyExpense;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthlyExpenseDatabase {
    private List<MonthlyExpense> expenses;

    public MonthlyExpenseDatabase() {
        this.expenses = new ArrayList<>();
    }

    public List<MonthlyExpense> getAll() {
        return expenses;
    }

    public MonthlyExpense getOne(String name) {
        for (MonthlyExpense expense : expenses) {
            if (expense.name.equals(name)) {
                return expense;
            }
        }
        return null;
    }

    public void insert(MonthlyExpense expense) {
        expenses.add(expense);
    }

    public void delete(String name) {
        expenses.removeIf(expense -> expense.name.equals(name));
    }

    public void update(String beforeName, String afterName, BigInteger newValue, String category, LocalDate date) {
        for (MonthlyExpense expense : expenses) {
            if (expense.name.equals(beforeName)) {
                expense.name = afterName;
                expense.value = newValue;
                expense.category = category;
                expense.date = date;
            }
        }
    }

    public BigInteger getTotalExpenseByMonth(int year, int month) {
        return expenses.stream()
                .filter(e -> e.date.getYear() == year && e.date.getMonthValue() == month)
                .map(e -> e.value)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }
}
