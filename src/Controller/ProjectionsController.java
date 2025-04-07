package Controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import Service.*;

public class ProjectionsController {
    private final ProjectionService projectionService;

    public ProjectionsController() {
        this.projectionService = new ProjectionService();
    }

    public Map<String, Object> calculateBudget(int months, BigInteger goal) {
        return this.projectionService.calculateBudget(months, goal);
    }

    public Map<String, Object> calculateExpense(int months, BigInteger goal) {
        return this.projectionService.calculateExpense(months, goal);
    }

    public Map<String, Object> calculateIncome(int months, BigInteger goal) {
        return this.projectionService.calculateIncome(months, goal);
    }

    public Map<String, Object> calculateSaving(int months, BigInteger goal) {
        return this.projectionService.calculateSaving(months, goal);
    }

    public Map<String, Map<String, Object>> calculateAllProjections(int months, BigInteger budgetGoal, BigInteger expenseGoal, BigInteger incomeGoal, BigInteger savingGoal) {
        Map<String, Map<String, Object>> allProjections = new HashMap<>();

        allProjections.put("Budgets", this.projectionService.calculateBudget(months, budgetGoal));
        allProjections.put("Expenses", this.projectionService.calculateExpense(months, expenseGoal));
        allProjections.put("Incomes", this.projectionService.calculateIncome(months, incomeGoal));
        allProjections.put("Savings", this.projectionService.calculateSaving(months, savingGoal));

        return allProjections;
    }

}