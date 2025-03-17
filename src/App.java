import java.math.BigInteger;
import View.MainFrame;
import Controller.BudgetController;
import Controller.ExpensesController;
import Controller.IncomeController;
import Controller.SavingController;
public class App {    public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");

    MainFrame ventana = new MainFrame();
    ventana.initialize();

    //Saving
    SavingController controllerSaving = new SavingController();
    //controllerSaving.add("Juan Pepito", "TRANSPORTE", BigInteger.valueOf(10000));
    //controllerSaving.update("Juan perez", BigInteger.valueOf(10000), "TRANSPORTE", 4);
    //controllerSaving.delete(4);
    //controllerSaving.getAll();
    controllerSaving.getOne(1);
    
    //Budget
    BudgetController controllerBudget = new BudgetController();
    //controllerBudget.addBudget("Juan Presupuestos", "TRANSPORTE", BigInteger.valueOf(10000));
    //controllerBudget.update("Sueldo", BigInteger.valueOf(1000000), "SUELDO", 0);
    // controllerBudget.delete(4);
    // controllerBudget.getAll();
    controllerBudget.getOne(1);
    
    // Income
    IncomeController controllerIncome = new IncomeController();
    // controllerIncome.add("Juan Ingresos", "TRANSPORTE", BigInteger.valueOf(10000));
    // controllerIncome.update("Subsidio", BigInteger.valueOf(200000), "VIVIENDA", 0);
    // controllerIncome.delete(3);
    // controllerIncome.getAll();
    controllerIncome.getOne(2);

    ExpensesController controllerExpense = new ExpensesController();
    // controllerExpense.add("Recibo 1", "ALIMENTOS", BigInteger.valueOf(300000));
    // controllerExpense.update("Recibo 2", BigInteger.valueOf(40000), "TRANSPORTE", 0);
    // controllerExpense.delete(1);
    // controllerExpense.getAll();
    controllerExpense.getOne(2);
}
}
