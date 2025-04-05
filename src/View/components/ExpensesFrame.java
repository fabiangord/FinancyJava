package View.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Models.Expense;
import Controller.ExpensesController;
import View.MainFrame;
import java.util.List;

import java.math.BigInteger;

public class ExpensesFrame extends JFrame{
    private JTextField nameField;
    private JTextField valueField;
    private JTextField categoryField;
    private ExpensesController expensesController;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        new ExpensesFrame();
    }
    public ExpensesFrame() {
        this.expensesController = new ExpensesController();
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

        JLabel titleLabel = new JLabel("Expenses");
        titleLabel.setBounds(180, 30, 130, 50);
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
                String category = categoryField.getText().trim();
                String valueText = valueField.getText().trim();
                BigInteger value = new BigInteger(valueText);
                
                expensesController.add(name, category, value);
                JOptionPane.showMessageDialog(null, "Gasto guardado con éxito!");
            }
        });
        add(saveButton);

            JButton allExpense = new JButton("Ver Ahorros");
        allExpense.setBounds(150, 300, 200, 50);
        allExpense.setFont(new Font("Arial", Font.BOLD, 16));
        allExpense.setForeground(Color.WHITE);
        allExpense.setBackground(new Color(52, 152, 219));
        allExpense.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        allExpense.setFocusPainted(false);
        allExpense.setCursor(new Cursor(Cursor.HAND_CURSOR));
        allExpense.addActionListener(e -> allExpense());
        add(allExpense);

        String[] columns = {"ID", "Nombre", "Valor", "Categoria"};
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
            expensesController.exportCSV();
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 380, 400, 200);
        add(scrollPane);

        setVisible(true);
    }

    private void allExpense() {
        List<Expense> results = expensesController.getAll();
        tableModel.setRowCount(0);
    
        for (Expense expense : results) {
            Object[] row = {expense.getId(), expense.name, expense.value, expense.category};
            tableModel.addRow(row);
        }
    }
}
