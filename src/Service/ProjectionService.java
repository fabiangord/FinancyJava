package Service;
import java.math.BigInteger;
import java.util.List;

import Db.MySql.Tables.ProjectionDB;
import Models.*;

public class ProjectionService {
    public ProjectionDB database;
    private Projection projections;

    public ProjectionService(BudgetService budgetService, ExpenseService expenseService, IncomeService incomeService, SavingService savingService){

        this.database = new ProjectionDB();
    
        List<Budget> budgetList = budgetService.getAll();
        List<Expense> expenseList = expenseService.getAll();
        List<Income> incomeList = incomeService.getAll();
        List<Saving> savingList  = savingService.getAll();

        this.projections  = new Projection
        (
            calculateProjection(budgetList.get(budgetList.size() - 1).value, 12), 
            calculateProjection(expenseList.get(expenseList.size() - 1).value, 12),
            calculateProjection(incomeList.get(incomeList.size() - 1).value, 12),
            calculateProjection(savingList.get(savingList.size() - 1).value,12), 
            12
        );
    }

    public void insert(){
        database.insert(projections);
    }

    public Projection getAll(){
        return this.projections;
    }

    private BigInteger calculateProjection(BigInteger value, int months) {
        return value.multiply(BigInteger.valueOf(months));
    }

    public void update(){
        database.update();
    }

    public void delete(){
        database.delete();
    }

    public Projection getOne(){
        Projection projection = database.getOne();
        return projection;
    }
}
