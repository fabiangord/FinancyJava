package View.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.IncomeController;
import Controller.ProjectionsController;
import View.MainFrame;

import java.math.BigInteger;
import java.util.Map;

public class IncomeFrame extends JFrame{
    private JTextField nameField;
    private JTextField valueField;
    private JTextField categoryField;
    private IncomeController controllerIncome;
    private ProjectionChartFrame projectionChartFrame;


    public static void main(String[] args) {
        new IncomeFrame();
    }
    public IncomeFrame() {
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

        categoryField = new JTextField();
        categoryField.setBounds(150, 170, 200, 25);
        add(categoryField);

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
                
                controllerIncome = new IncomeController();
                controllerIncome.add(name, category, value);
                JOptionPane.showMessageDialog(null, "Ingreso guardado con éxito!");
            }
        });
        add(saveButton);

        JButton projectionButton = new JButton("Proyectar 📈");
        projectionButton.setBounds(150, 300, 200, 50);
        projectionButton.setFont(new Font("Arial", Font.BOLD, 16));
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
                Map<String, Object> projectedExpenses = projectionsController.calculateIncome(months, goal);
    
                projectionChartFrame = new ProjectionChartFrame(projectedExpenses);
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
    
}
