package View.components;

import Controller.ProjectionsController;
import Controller.SavingController;
import Models.Saving;
import View.MainFrame;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public class SavingFrame extends JFrame {
    private JTextField nameField;
    private JTextField valueField;
    private SavingController controllerSaving;
    private JTable table;
    private DefaultTableModel tableModel;
    private ProjectionChartFrame projectionChartFrame;

    public static void main(String[] args) {
        new SavingFrame();
    }

    public SavingFrame() {
        this.controllerSaving = new SavingController();
        initialize();
        setVisible(true);

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

        backButton.addActionListener(_ -> {
            new MainFrame();
            dispose();
        });

        JLabel titleLabel = new JLabel("Saving");
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

        String[] categories = {"", "VIVIENDA", "TRANSPORTE", "ALIMENTOS", "SUELDO"};
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

        saveButton.addActionListener(_ -> {
            try {
                String name = nameField.getText().trim();
                String category = (String) categoryComboBox.getSelectedItem();
                String valueText = valueField.getText().trim();
                BigInteger value = new BigInteger(valueText);

                controllerSaving.add(name, category, value);
                JOptionPane.showMessageDialog(null, "Ahorro guardado con éxito!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido en 'Value'.");
            }
        });

        add(saveButton);

        JButton allSavings = new JButton("Ver Ahorros");
        allSavings.setBounds(150, 300, 200, 50);
        allSavings.setFont(new Font("Arial", Font.BOLD, 16));
        allSavings.setForeground(Color.WHITE);
        allSavings.setBackground(new Color(52, 152, 219));
        allSavings.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        allSavings.setFocusPainted(false);
        allSavings.setCursor(new Cursor(Cursor.HAND_CURSOR));
        allSavings.addActionListener(_ -> allSavings());
        add(allSavings);

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

        csvButton.addActionListener(_ -> {
            controllerSaving.exportCSV();
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 380, 400, 200);
        add(scrollPane);

        JButton projectionButton = new JButton("Proyectar 📈");
        projectionButton.setBounds(150, 26, 200, 15);
        projectionButton.setFont(new Font("Arial", Font.BOLD, 10));
        projectionButton.setForeground(Color.WHITE);
        projectionButton.setBackground(new Color(14, 162, 33));
        projectionButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        projectionButton.setFocusPainted(false);
        projectionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(projectionButton);

        projectionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               showDialogFrame();
            }
        });

    }

    private void showDialogFrame() {
        JDialog dialog = new JDialog((Frame) null, "Ingresar Datos de Proyección", true);
        dialog.setSize(360, 220);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(Color.WHITE);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
    
        JLabel monthsLabel = new JLabel("Meses:");
        monthsLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
    
        JTextField monthsField = new JTextField();
        monthsField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        monthsField.setPreferredSize(new Dimension(180, 30));
        monthsField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    
        JLabel goalLabel = new JLabel("Meta:");
        goalLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
    
        JTextField goalField = new JTextField();
        goalField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        monthsField.setPreferredSize(new Dimension(180, 30));
        goalField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    
        JButton confirmButton = new JButton("Calcular");
        confirmButton.setBackground(new Color(34, 197, 94));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        confirmButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    
        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
    
        // Layout
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(monthsLabel, gbc);
        gbc.gridx = 1;
        dialog.add(monthsField, gbc);
    
        gbc.gridx = 0; gbc.gridy = 1;
        dialog.add(goalLabel, gbc);
        gbc.gridx = 1;
        dialog.add(goalField, gbc);
    
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        dialog.add(errorLabel, gbc);
    
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(confirmButton, gbc);
    
        confirmButton.addActionListener(_ -> {
            try {
                errorLabel.setText(""); 
                String monthsText = monthsField.getText().trim();
                String goalText = goalField.getText().trim();
    
                if (monthsText.isEmpty() || goalText.isEmpty()) {
                    errorLabel.setText("Todos los campos son obligatorios.");
                    return;
                }
    
                int months = Integer.parseInt(monthsText);
                BigInteger goal = new BigInteger(goalText);
    
                ProjectionsController projectionsController = new ProjectionsController();
                Map<String, Object> projectedSavings = projectionsController.calculateSaving(months, goal);
    
                projectionChartFrame = new ProjectionChartFrame(projectedSavings);
                projectionChartFrame.setVisible(true);
                dialog.dispose();
                dispose();
            } catch (NumberFormatException ex) {
                errorLabel.setText("Por favor ingrese valores numéricos válidos.");
            }
        });
    
        dialog.getRootPane().setDefaultButton(confirmButton);
        FocusListener selector = new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                ((JTextField) e.getComponent()).selectAll();
            }
        };
        monthsField.addFocusListener(selector);
        goalField.addFocusListener(selector);
    
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void allSavings() {
        controllerSaving = new SavingController();
        List<Saving> results = controllerSaving.getAll();
        tableModel.setRowCount(0);
    
        for (Saving saving : results) {
            Object[] row = {saving.getId(), saving.getName(), saving.getValue(), saving.getCategory()};
            tableModel.addRow(row);
        }
    }
}
