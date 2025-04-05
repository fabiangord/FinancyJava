package View.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import Controller.IncomeController;
import Models.Income;
import View.MainFrame;

import java.math.BigInteger;

public class IncomeFrame extends JFrame{
    private JTextField nameField;
    private JTextField valueField;
    private IncomeController controllerIncome;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        new IncomeFrame();
    }
    public IncomeFrame() {
        this.controllerIncome = new IncomeController();
        initialize();
    }

    public void initialize() {
        setLocation(550, 60);
        setTitle("FinancyJava");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 400));
        setLayout(null);

        JButton backButton = new JButton("Volver");
        backButton.setBounds(15, 26, 50, 15);
        backButton.setFont(new Font("Arial", Font.BOLD, 10));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(52, 152, 219));
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                new MainFrame();
                dispose();
            }
        });

        JLabel titleLabel = new JLabel("Income");
        titleLabel.setBounds(200, 30, 100, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(90, 110, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 110, 200, 25);
        add(nameField);

        JLabel valueLabel = new JLabel("Value:");
        valueLabel.setBounds(90, 140, 80, 25);
        add(valueLabel);

        valueField = new JTextField();
        valueField.setBounds(150, 140, 200, 25);
        add(valueField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(90, 170, 80, 25);
        add(categoryLabel);

        String[] categories = {"", "VIVIENDA", "TRANSPORTE", "ALIMENTOS", "SUELDO", "INVERSION", "AHORRO", "OTRO"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setBounds(150, 170, 200, 25);
        add(categoryComboBox);

        JButton saveButton = new JButton("Guardar");
        saveButton.setBounds(150, 230, 200, 50);
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(52, 152, 219));
        saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        saveButton.setFocusPainted(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String category = (String) categoryComboBox.getSelectedItem();
                String valueText = valueField.getText().trim();
                BigInteger value = new BigInteger(valueText);
                
                controllerIncome = new IncomeController();
                controllerIncome.add(name, category, value);
                JOptionPane.showMessageDialog(null, "Ingreso guardado con éxito!");
            }
        });
        add(saveButton);

            JButton allIncome = new JButton("Ver Ahorros");
        allIncome.setBounds(150, 300, 200, 50);
        allIncome.setFont(new Font("Arial", Font.BOLD, 16));
        allIncome.setForeground(Color.WHITE);
        allIncome.setBackground(new Color(52, 152, 219));
        allIncome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        allIncome.setFocusPainted(false);
        allIncome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        allIncome.addActionListener(e -> allIncome());
        add(allIncome);

        String[] columns = {"ID", "Nombre", "Valor"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton csvButton = new JButton("Descargar csv");
        csvButton.setBounds(380, 26, 90, 15);
        csvButton.setFont(new Font("Arial", Font.BOLD, 10));
        csvButton.setForeground(Color.WHITE);
        csvButton.setBackground(new Color(52, 152, 219));
        csvButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        csvButton.setFocusPainted(false);
        csvButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(csvButton);

        csvButton.addActionListener(e -> {
            controllerIncome.exportCSV();
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 380, 400, 200);
        add(scrollPane);

        setVisible(true);
    }

    private void allIncome() {
        List<Income> results = controllerIncome.getAll();
        tableModel.setRowCount(0);
    
        for (Income income : results) {
            Object[] row = {income.getId(), income.name, income.value};
            tableModel.addRow(row);
        }
    }
}
