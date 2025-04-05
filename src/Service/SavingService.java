package Service;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import com.opencsv.CSVWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Db.MySql.Tables.SavingDB;
import Models.Saving;

public class SavingService {
    private SavingDB database;

    public SavingService(){
        this.database = new SavingDB();
    }

    public SavingDB getDatabase() {
        return database;
    }

    public void add(String name, String category, BigInteger value) {
        database.insert(name, category, value);
    }

    public List<Saving> getAll(){
        return database.getAll();
    }
    
    public void update(String name, BigInteger value, String category, int id){
        database.update(name, value, category, id);
    }

    public void delete(int id){
        database.delete(id);
    }

    public List<Saving> getOne(int id){
        return database.getOne(id);
    }

    public BigInteger getTotal(){
        return database.getTotal();
    }

    public void exportCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar reporte CSV");
        fileChooser.setSelectedFile(new java.io.File("reportSaving.csv"));
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        List<Saving> savings = database.getAll();

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {

            writer.writeNext(new String[]{"ID", "Nombre", "Categoria", "Valor"});
            for (Saving saving : savings) {
                writer.writeNext(new String[]{
                    String.valueOf(saving.getId()),
                    saving.getName(),
                    saving.getCategory().toString(),
                    saving.getValue().toString()
                });
            }
            JOptionPane.showMessageDialog(null, "Reporte guardado en:\n" + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}