package Service;

import Db.LocalDatabase.SavingDatabase;

public class SavingService {
    private SavingDatabase database;

    SavingService(SavingDatabase database){
        this.database = database;
    };
}
