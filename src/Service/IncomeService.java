package Service;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.opencsv.CSVWriter;

import Models.Income;
import Db.MySql.Tables.IncomeDB;

public class IncomeService {
    private IncomeDB database;

    public IncomeService(){
        this.database = new IncomeDB();
    };

    public void add(String name, String category, BigInteger value) {
        database.insert(name, category, value);
    }

    public List<Income> getAll(){
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

    public List<Income> getOne(int id){
        return database.getOne(id);
    }

    public BigInteger getTotal(){
        return database.getTotal();
    }

    public void exportCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar reporte CSV");
        fileChooser.setSelectedFile(new java.io.File("reportincome.csv"));
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        List<Income> incomes = database.getAll();

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {

            writer.writeNext(new String[]{"ID", "Nombre", "Categoria", "Valor"});
            for (Income income : incomes) {
                writer.writeNext(new String[]{
                    String.valueOf(income.getId()),
                    income.name,
                    income.value.toString()
                });
            }
            JOptionPane.showMessageDialog(null, "Reporte guardado en:\n" + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
