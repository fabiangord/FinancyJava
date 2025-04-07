package Controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import Service.*;

import View.components.ErrorFrame;

public class ProjectionsController {
    private final ProjectionService projectionService;

    public ProjectionsController() {
        this.projectionService = new ProjectionService();
    }

    public Map<String, Object> calculateBudget(int months, BigInteger goal) {
        try {
            return this.projectionService.calculateBudget(months, goal);
        } catch (Exception e) {
            new ErrorFrame(null,"Error al calcular el presupuesto: " + e.getMessage()).setVisible(true);
            return new HashMap<>();
        }
    }

    public Map<String, Object> calculateExpense(int months, BigInteger goal) {
        try {
            return this.projectionService.calculateExpense(months, goal);
        } catch (Exception e) {
            new ErrorFrame(null, "Error al calcular los gastos: " + e.getMessage()).setVisible(true);
            return new HashMap<>();
        }
    }

    public Map<String, Object> calculateIncome(int months, BigInteger goal) {
        try {
            return this.projectionService.calculateIncome(months, goal);
        } catch (Exception e) {
            new ErrorFrame(null, "Error al calcular los ingresos: " + e.getMessage()).setVisible(true);
            return new HashMap<>();
        }
    }

    public Map<String, Object> calculateSaving(int months, BigInteger goal) {
        try {
            return this.projectionService.calculateSaving(months, goal);
        } catch (Exception e) {
            new ErrorFrame(null, "Error al calcular los ahorros: " + e.getMessage()).setVisible(true);
            return new HashMap<>();
        }
    }

    public Map<String, Map<String, Object>> calculateAllProjections( int months, BigInteger budgetGoal, BigInteger expenseGoal, BigInteger incomeGoal, BigInteger savingGoal) {

        Map<String, Map<String, Object>> allProjections = new HashMap<>();

        try {
            allProjections.put("Budgets", calculateBudget(months, budgetGoal));
            allProjections.put("Expenses", calculateExpense(months, expenseGoal));
            allProjections.put("Incomes", calculateIncome(months, incomeGoal));
            allProjections.put("Savings", calculateSaving(months, savingGoal));
        } catch (Exception e) {
            new ErrorFrame(null, "Error al calcular todas las proyecciones: " + e.getMessage()).setVisible(true);
        }

        return allProjections;
    }
}
