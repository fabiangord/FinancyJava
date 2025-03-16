import java.math.BigInteger;

import Controller.BudgetController;
import Controller.IncomeController;
import Controller.SavingController;
public class App {    public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");

    //Saving
    SavingController controllerSaving = new SavingController();
    //controllerSaving.addSaving("Juan Pepito", "TRANSPORTE", BigInteger.valueOf(10000));
    //controllerSaving.updateSaving("Juan perez", BigInteger.valueOf(10000), "TRANSPORTE", 4);
    //controllerSaving.deleteSaving(4);
    controllerSaving.getAllSaving();
    
    BudgetController controllerBudget = new BudgetController();
    //controllerBudget.addBudget("Juan Presupuestos", "TRANSPORTE", BigInteger.valueOf(10000));
    controllerBudget.getAllBudget();
    
    
    // IncomeController controllerIncome = new IncomeController();
    // controllerIncome.addsaving("Juan Ingresos", "TRANSPORTE", BigInteger.valueOf(10000));
    // controllerIncome.getAllSaving();
}
}
