package Controller;

import Service.ExpenseService;

public class ExpensesController {
private ExpenseService database;

    ExpensesController(ExpenseService database){
        this.database = database;
    };
}
