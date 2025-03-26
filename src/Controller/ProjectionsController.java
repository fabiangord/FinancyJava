package Controller;

import Models.Projection;
import Service.*;

public class ProjectionsController {
    private final ProjectionService projectionService;

    public ProjectionsController() {
        BudgetService budgetService = new BudgetService();
        ExpenseService expenseService = new ExpenseService();
        IncomeService incomeService = new IncomeService();
        SavingService savingService = new SavingService();

        this.projectionService = new ProjectionService(budgetService, expenseService, incomeService, savingService);
    }

    public void insert(){
        projectionService.insert();
    }

    public Projection getAll(){ 
        return projectionService.getAll();
    }


}