package Service;

import Db.LocalDatabase.BudgetDatabase;

public class BudgetService {

    private BudgetDatabase database;

    BudgetService(BudgetDatabase database){
        this.database = database;
    };
}
