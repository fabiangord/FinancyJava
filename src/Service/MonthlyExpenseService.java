package Service;

import Db.LocalDatabase.MonthlyExpenseDatabase;
import java.math.BigInteger;

public class MonthlyExpenseService {
    private MonthlyExpenseDatabase database;

    public MonthlyExpenseService(MonthlyExpenseDatabase database) {
        this.database = database;
    }

    public BigInteger getTotalExpenseByMonth(int year, int month) {
        return database.getTotalExpenseByMonth(year, month);
    }
}
