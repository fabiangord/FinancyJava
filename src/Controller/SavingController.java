package Controller;

import Service.SavingService;

public class SavingController {
private SavingService database;

    SavingController(SavingService database){
        this.database = database;
    };
}
