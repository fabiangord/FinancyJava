package View.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.util.List;
import java.util.Locale;

import Controller.IncomeController;
import Models.Income;
import View.MainFrame;

import java.math.BigInteger;
import java.text.NumberFormat;

public class IncomeFrame extends JFrame {
    private JTextField nameField;
    private JTextField valueField;
    private IncomeController controllerIncome;
    private JTable table;
    private DefaultTableModel tableModel;
    private int rowSelected;
    private String[] categories = {"", "DWELLING", "TRANSPORT", "FOOD", "SALARY", "INVERSION", "INCOME", "OTHER" };
    private JComboBox<String> categoryComboBox = new JComboBox<>(categories);
    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CO"));


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
        getContentPane().setBackground(Color.decode("#48A6A7"));

        JButton backButton = new JButton("Back");
        backButton.setBounds(18, 26, 60, 20);
        backButton.setFont(new Font("Arial", Font.BOLD, 10));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#006A71"));
        // backButton.setBackground(new Color(52, 152, 219));
        backButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
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

        JButton csvButton = new JButton("Download CSV");
        csvButton.setBounds(370, 26, 100, 20);
        csvButton.setFont(new Font("Arial", Font.BOLD, 10));
        csvButton.setForeground(Color.WHITE);
        csvButton.setBackground(new Color(52, 152, 219));
        csvButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        csvButton.setBackground(Color.decode("#006A71"));
        csvButton.setFocusPainted(false);
        csvButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(csvButton);

        csvButton.addActionListener(e -> {
            controllerIncome.exportCSV();
        });

        JLabel titleLabel = new JLabel("Income");
        titleLabel.setBounds(200, 40, 100, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        BigInteger total = controllerIncome.getTotal();
        JLabel valueLabelTwo = new JLabel(currencyFormatter.format(total));
        valueLabelTwo.setBounds(180, 80, 200, 50);
        valueLabelTwo.setFont(new Font("Arial", Font.ITALIC, 18));
        valueLabelTwo.setForeground(Color.WHITE);
        add(valueLabelTwo);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(90, 140, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 140, 200, 25);
        add(nameField);

        JLabel valueLabel = new JLabel("Value:");
        valueLabel.setBounds(90, 170, 80, 25);
        add(valueLabel);

        valueField = new JTextField();
        valueField.setBounds(150, 170, 200, 25);
        add(valueField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(90, 200, 80, 25);
        add(categoryLabel);

        
        categoryComboBox.setBounds(150, 200, 200, 25);
        add(categoryComboBox);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(150, 240, 200, 50);
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(Color.decode("#006A71"));
        saveButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
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
                JOptionPane.showMessageDialog(null, "Income saved!");
            }
        });
        add(saveButton);

        String[] columns = { "ID", "Name", "Value", "Category" };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setBackground(Color.decode("#9ACBD0"));
        table.setShowGrid(true);
        table.setGridColor(Color.decode("#48A6A7"));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.decode("#006A71"));
        header.setForeground(Color.decode("#F2EFE7"));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 320, 400, 200);
        scrollPane.getViewport().setBackground(Color.decode("#48A6A7"));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#48A6A7")));
        add(scrollPane);

        JButton cleanButton = new JButton("Clean");
        cleanButton.setBounds(60, 550, 80, 25);
        cleanButton.setFont(new Font("Arial", Font.BOLD, 12));
        cleanButton.setForeground(Color.WHITE);
        cleanButton.setBackground(Color.decode("#006A71"));
        cleanButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        cleanButton.setFocusPainted(false);
        cleanButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanTable();
                JOptionPane.showMessageDialog(null, "All data deleted!");
            }
        });
        add(cleanButton);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(160, 550, 80, 25);
        editButton.setFont(new Font("Arial", Font.BOLD, 12));
        editButton.setForeground(Color.WHITE);
        editButton.setBackground(Color.decode("#006A71"));
        editButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        editButton.setFocusPainted(false);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDataInTable();
            }
        });
        add(editButton);

        JButton modifyButton = new JButton("Modify");
        modifyButton.setBounds(260, 550, 80, 25);
        modifyButton.setFont(new Font("Arial", Font.BOLD, 12));
        modifyButton.setForeground(Color.WHITE);
        modifyButton.setBackground(Color.decode("#006A71"));
        modifyButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        modifyButton.setFocusPainted(false);
        modifyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyTable();
                JOptionPane.showMessageDialog(null, "Data selected for edit!");
            }
        });
        add(modifyButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(360, 550, 80, 25);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.decode("#006A71"));
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        deleteButton.setFocusPainted(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteIncome(e);
                JOptionPane.showMessageDialog(null, "Income deleted!");
            }
        });
        add(deleteButton);

        JButton allIncome = new JButton("Get Savings");
        allIncome.setBounds(150, 600, 200, 50);
        allIncome.setFont(new Font("Arial", Font.BOLD, 16));
        allIncome.setForeground(Color.WHITE);
        allIncome.setBackground(Color.decode("#006A71"));
        allIncome.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        allIncome.setFocusPainted(false);
        allIncome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        allIncome.addActionListener(e -> allIncome());
        add(allIncome);

        setVisible(true);
    }

    private void allIncome() {
        List<Income> results = controllerIncome.getAll();
        tableModel.setRowCount(0);

        for (Income income : results) {
            Object[] row = { income.getId(), income.name, currencyFormatter.format(income.value), income.category };
            tableModel.addRow(row);
        }
    }

    private void deleteIncome(java.awt.event.ActionEvent evt){
        int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
        controllerIncome.delete(id);
        tableModel.removeRow(table.getSelectedRow());
    }

    private void cleanTable(){
        controllerIncome.deleteAll();
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
    }

    private void editDataInTable(){
        if(table.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Register not selected", "Error modify registry", JOptionPane.ERROR_MESSAGE);
        }
        try {
            rowSelected = table.getSelectedRow();
            nameField.setText(tableModel.getValueAt(rowSelected, 1).toString());
            valueField.setText(currencyFormatter.parse(tableModel.getValueAt(rowSelected, 2).toString()).toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modifyTable(){
        try {
            String nameUpdate = nameField.getText().trim();
            BigInteger valueUpdate = new BigInteger(valueField.getText());
            int id = (int) table.getValueAt(rowSelected, 0);
            
            System.out.println(valueUpdate);
            
            Object categoriesUpdate = categoryComboBox.getSelectedItem();
            
            tableModel.setValueAt(nameUpdate, rowSelected, 1);
            tableModel.setValueAt(valueUpdate, rowSelected, 2);
            tableModel.setValueAt(categoriesUpdate, rowSelected, 3);

            controllerIncome.update(nameUpdate, valueUpdate, categoriesUpdate.toString(), id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
