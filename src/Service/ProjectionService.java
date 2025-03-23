package Service;

import java.util.List;

import Db.MySql.Tables.ProjectionDB;
import Models.Budget;
import Models.Expense;
import Models.Income;
import Models.Projection;
import Models.Saving;

public class ProjectionService {
    public ProjectionDB database;

    private Projection projections;

    private BudgetService budgetService;
    private ExpenseService expenseService;
    private IncomeService incomeService;
    private SavingService savingService;


    public ProjectionService(int months){

        this.database = new ProjectionDB();

        this.budgetService = new BudgetService();
        this.expenseService = new ExpenseService();
        this.incomeService = new IncomeService();
        this.savingService = new SavingService();
    
        List<Budget> budgets = budgetService.getAll();
        List<Expense> expenses = expenseService.getAll();
        List<Income> incomes = incomeService.getAll();
        List<Saving> savings  = savingService.getAll();

        this.projections  = new Projection(budgets.get(0).value, expenses.get(0).value, incomes.get(0).value, savings.get(0).value, months);
    
    }

    public void insert(){
        database.insert(projections);
    }

    public List<Projection> getAll(){
        List<Projection> projections = database.getAll();
        return projections;
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
