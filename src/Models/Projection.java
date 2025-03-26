package Models;

import java.math.BigInteger;

public class Projection {
    public BigInteger projectedBudgets;
    public BigInteger projectedExpenses;
    public BigInteger projectedIncomes;
    public BigInteger projectedSavings;
    public int months;

    public Projection (BigInteger budgets, BigInteger expenses, BigInteger incomes, BigInteger savings, int months){
        this.projectedBudgets = budgets;
        this.projectedExpenses = expenses;
        this.projectedIncomes = incomes;
        this.projectedSavings = savings;
        this.months = months;
    }

}
