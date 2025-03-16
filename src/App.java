import java.math.BigInteger;

import Controller.BudgetController;
// import Controller.IncomeController;
// import Controller.SavingController;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        // SavingController controller = new SavingController();

        // controller.addSaving("Juan Pepito", "TRANSPORTE", BigInteger.valueOf(10000));
        
        // controller.getAllSaving();
        
        BudgetController controllerBudget = new BudgetController();
        
        
        controllerBudget.addSaving("Juan Presupuestos", "TRANSPORTE", BigInteger.valueOf(10000));
        controllerBudget.getAllSaving();
        
        
        // IncomeController controllerIncome = new IncomeController();
        // controllerIncome.addsaving("Juan Ingresos", "TRANSPORTE", BigInteger.valueOf(10000));
        // controllerIncome.getAllSaving();
    }
}
