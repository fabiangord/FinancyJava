package Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Db.MySql.Tables.ProjectionDB;
import Models.*;

public class ProjectionService {
    public ProjectionDB database;
    private BudgetService budgetService;
    private ExpenseService expenseService;
    private IncomeService incomeService;
    private SavingService savingService;

    public ProjectionService(){
        this.database = new ProjectionDB();
        this.budgetService = new BudgetService();
        this.expenseService = new ExpenseService();
        this.incomeService = new IncomeService();
        this.savingService = new SavingService();
    }

    public Map<String, Object> calculateBudget(int months, BigInteger goal) {
        List<Budget> budgets = this.budgetService.getAll();
        List<BigInteger> historicalValues = new ArrayList<>();

        for (Budget budget : budgets) {
            historicalValues.add(budget.value);
        }

        Projection projection = new Projection( budgets.get(0).getId(), historicalValues, months, goal);

        Map<String, Object> calculateProjection = calculateProjection(projection, "Budgets");

        return calculateProjection;

    }

    public Map<String, Object> calculateExpense(int months, BigInteger goal) {
        List<Expense> expenses = this.expenseService.getAll();
        List<BigInteger> historicalValues = new ArrayList<>();

        for (Expense expense : expenses) {
            historicalValues.add(expense.value);
        }

        Projection projection = new Projection( expenses.get(0).getId(), historicalValues, months, goal);

        Map<String, Object> calculateProjection = calculateProjection(projection, "Expenses");

        return calculateProjection;

    }

    public Map<String, Object> calculateIncome(int months, BigInteger goal) {
        List<Income> incomes = this.incomeService.getAll();
        List<BigInteger> historicalValues = new ArrayList<>();

        for (Income income : incomes) {
            historicalValues.add(income.value);
        }

        Projection projection = new Projection( incomes.get(0).getId(), historicalValues, months, goal);

        return calculateProjection(projection, "Incomes");

    }

    public Map<String, Object> calculateSaving(int months, BigInteger goal) {
        List<Saving> savings = this.savingService.getAll();
        List<BigInteger> historicalValues = new ArrayList<>();

        for (Saving saving : savings) {
            historicalValues.add(saving.value);
        }

        Projection projection = new Projection( savings.get(0).getId(), historicalValues, months, goal);

        return calculateProjection(projection, "Savings");

    }

    public Map<String, Object> calculateProjection(Projection projection, String type) {
        List<BigInteger> historicalValues = projection.historicalValues;
        BigInteger goal = projection.goal;
        int months = projection.months;

        if (historicalValues.size() < 2) {
            throw new IllegalArgumentException("Se necesitan al menos 2 valores históricos para proyectar.");
        }

        BigDecimal totalChange = BigDecimal.ZERO;
        for (int i = 1; i < historicalValues.size(); i++) {
            BigInteger current = historicalValues.get(i);
            BigInteger previous = historicalValues.get(i - 1);
            totalChange = totalChange.add(new BigDecimal(current.subtract(previous)));
        }

        BigDecimal avgChange = totalChange.divide(
            new BigDecimal(historicalValues.size() - 1), 2, RoundingMode.HALF_UP
        );

        BigDecimal lastValue = new BigDecimal(historicalValues.get(historicalValues.size() - 1));
        List<BigInteger> projectedValues = new ArrayList<>();

        for (int i = 0; i < months; i++) {
            lastValue = lastValue.add(avgChange);
            projectedValues.add(lastValue.toBigInteger());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("months", months);
        result.put("goal", goal);
        result.put("projection", projectedValues);
        result.put("type", type);

        return result;
    }

}
