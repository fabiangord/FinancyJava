package Controller;

import Service.BudgetService;

public class BudgetController {
    private BudgetService database;

    BudgetController(BudgetService database){
        this.database = database;
    };
}
