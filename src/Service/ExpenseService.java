package Service;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.opencsv.CSVWriter;

import Db.MySql.Tables.ExpensesDB;
import Models.Expense;

public class ExpenseService {
    private ExpensesDB database;

    public ExpenseService(){
        this.database = new ExpensesDB();
    };

    public void add(String name, String category, BigInteger value) {
        database.insert(name, category, value);
    }

    public List<Expense> getAll(){
        return database.getAll();
    }

    public void update(String name, BigInteger value, String category, int id){
        database.update(name, value, category, id);
    }

    public void delete(int id){
        database.delete(id);
    }

    public void deleteAll(){
        database.deleteAll();
    }


    public List<Expense> getOne(int id){
        return database.getOne(id);
    }

    public BigInteger getTotal(){
        return database.getTotal();
    }

    public List<String[]> getAllExpensesAsCSVData() {
        List<Expense> expenses = database.getAll();
        List<String[]> data = new ArrayList<>();

        for (Expense expense : expenses) {
            data.add(new String[]{
                String.valueOf(expense.getId()),
                expense.name,
                expense.category.toString(),
                expense.value.toString()
            });
        }
        return data;
    }


    public void exportCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar reporte CSV");
        fileChooser.setSelectedFile(new java.io.File("reportexpense.csv"));
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }
    
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        List<String[]> expensesData = getAllExpensesAsCSVData();
    
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"ID", "Nombre", "Categoria", "Valor"});
            writer.writeAll(expensesData);
            JOptionPane.showMessageDialog(null, "Reporte guardado en:\n" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
