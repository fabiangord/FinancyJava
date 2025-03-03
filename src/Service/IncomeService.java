package Service;

import Db.LocalDatabase.IncomeDatabase;

public class IncomeService {
    private IncomeDatabase database;

    IncomeService(IncomeDatabase database){
        this.database = database;
    };
}
