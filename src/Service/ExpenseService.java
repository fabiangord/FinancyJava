package Service;

import Db.LocalDatabase.ExpenseDatabase;

public class ExpenseService {
private ExpenseDatabase database;

    ExpenseService(ExpenseDatabase database){
        this.database = database;
    };
}
