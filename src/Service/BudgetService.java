package Service;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.opencsv.CSVWriter;

import Db.MySql.Tables.BudgetDB;
import Models.Budget;

public class BudgetService {

    private BudgetDB database;

    public BudgetService(){
        this.database = new BudgetDB();
    };

    public void add(String name, BigInteger value) {
        database.insert(name, value);
    }

    public List<Budget> getAll(){
        return database.getAll();
    }

    public void update(String name, BigInteger value, String category, int id){
        database.update(name, value, category, id);
    }

    public void delete(int id){
        database.delete(id);
    }

    public List<Budget> getOne(int id){
        return database.getOne(id);
    }

    public BigInteger total(){
        return database.getTotal();
    }

    public void exportCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar reporte CSV");
        fileChooser.setSelectedFile(new java.io.File("reportbudget.csv"));
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        List<Budget> budgets = database.getAll();

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {

            writer.writeNext(new String[]{"ID", "Nombre", "Valor"});
            for (Budget budget : budgets) {
                writer.writeNext(new String[]{
                    String.valueOf(budget.getId()),
                    budget.name,
                    budget.value.toString()
                });
            }
            JOptionPane.showMessageDialog(null, "Reporte guardado en:\n" + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
