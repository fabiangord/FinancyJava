package Controller;

import Service.MonthlyExpenseService;
import java.math.BigInteger;

public class MonthlyExpenseController {
    private MonthlyExpenseService service;

    public MonthlyExpenseController(MonthlyExpenseService service) {
        this.service = service;
    }

    public void showMonthlyExpense(int year, int month) {
        BigInteger total = service.getTotalExpenseByMonth(year, month);
        System.out.println("Gasto total en " + month + "/" + year + ": $" + total);
    }
}
