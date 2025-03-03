package Controller;

import Service.IncomeService;

public class IncomeController {
private IncomeService database;

    IncomeController(IncomeService database){
        this.database = database;
    };
}
