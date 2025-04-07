package View.components;

import java.awt.*;
import javax.swing.*;

import Controller.ProjectionsController;

import java.math.BigInteger;
import java.util.Map;

public class ProjectionDialogFrame extends JFrame{

    private ProjectionsController projectionsController;
    private Map<String, Object> projected;
    private String type;

    public ProjectionDialogFrame(ProjectionsController projectionsController, String type){
        this.projectionsController = projectionsController;
        this.type = type;

        initialize();
    }
    private void initialize() {
        JDialog dialog = new JDialog((Frame) null, "Enter data for projection", true);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(Color.decode("#48A6A7"));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
    
        // Comunes
        JLabel monthsLabel = new JLabel("Months:");
        JTextField monthsField = new JTextField();
        monthsField.setPreferredSize(new Dimension(180, 30));
    
        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
    
        JButton confirmButton = new JButton("Calculate");
        confirmButton.setBackground(Color.decode("#006A71"));
        confirmButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        confirmButton.setForeground(Color.WHITE);
    
        // Campos dinámicos
        JTextField goalField = new JTextField(); // Para caso simple
        JTextField budgetGoalField = new JTextField();
        JTextField expenseGoalField = new JTextField();
        JTextField incomeGoalField = new JTextField();
        JTextField savingGoalField = new JTextField();
    
        int row = 0;
    
        // Layout fijo
        gbc.gridx = 0; gbc.gridy = row;
        dialog.add(monthsLabel, gbc);
        gbc.gridx = 1;
        dialog.add(monthsField, gbc);
        row++;
    
        if (type.equals("All")) {
            dialog.setSize(400, 380); // Redimensionado
    
            gbc.gridx = 0; gbc.gridy = row;
            dialog.add(new JLabel("Meta Budget:"), gbc);
            gbc.gridx = 1;
            dialog.add(budgetGoalField, gbc);
            row++;
    
            gbc.gridx = 0; gbc.gridy = row;
            dialog.add(new JLabel("Meta Expense:"), gbc);
            gbc.gridx = 1;
            dialog.add(expenseGoalField, gbc);
            row++;
    
            gbc.gridx = 0; gbc.gridy = row;
            dialog.add(new JLabel("Meta Income:"), gbc);
            gbc.gridx = 1;
            dialog.add(incomeGoalField, gbc);
            row++;
    
            gbc.gridx = 0; gbc.gridy = row;
            dialog.add(new JLabel("Meta Saving:"), gbc);
            gbc.gridx = 1;
            dialog.add(savingGoalField, gbc);
            row++;
    
        } else {
            dialog.setSize(360, 220); // Tamaño normal
    
            gbc.gridx = 0; gbc.gridy = row;
            dialog.add(new JLabel("Meta:"), gbc);
            gbc.gridx = 1;
            dialog.add(goalField, gbc);
            row++;
        }
    
        // Error + botón
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        dialog.add(errorLabel, gbc);
        row++;
    
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(confirmButton, gbc);
    
        confirmButton.addActionListener(_ -> {
            try {
                errorLabel.setText(""); 
    
                int months = Integer.parseInt(monthsField.getText().trim());
    
                if (type.equals("All")) {
                    Map<String, Map<String, Object>> projected = projectionsController.calculateAllProjections(
                        months,
                        new BigInteger(budgetGoalField.getText().trim()),
                        new BigInteger(expenseGoalField.getText().trim()),
                        new BigInteger(incomeGoalField.getText().trim()),
                        new BigInteger(savingGoalField.getText().trim())
                    );
                    new ProjectionsFrame().showProjections(projected);
                } else {
                    BigInteger goal = new BigInteger(goalField.getText().trim());
                    switch (type) {
                        case "Budgets": projected = projectionsController.calculateBudget(months, goal); break;
                        case "Expenses": projected = projectionsController.calculateExpense(months, goal); break;
                        case "Incomes": projected = projectionsController.calculateIncome(months, goal); break;
                        case "Savings": projected = projectionsController.calculateSaving(months, goal); break;
                        default: throw new IllegalArgumentException("Tipo de proyección no válido: " + type);
                    }
                    new ProjectionChartFrame(projected).setVisible(true);
                }
    
                dialog.dispose();
                dispose();
            } catch (NumberFormatException ex) {
                errorLabel.setText("Por favor ingrese valores numéricos válidos.");
            }
        });
    
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
}
