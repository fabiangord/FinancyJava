package View.components;

import java.awt.*;
import java.awt.event.*;
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

                switch (type) {
                    case "Budgets":
                        projected = projectionsController.calculateBudget(months, goal);
                        break;
                    case "Expenses":
                        projected = projectionsController.calculateExpense(months, goal);
                        break;
                    case "Incomes":
                        projected = projectionsController.calculateIncome(months, goal);
                        break;
                    case "Savings":
                        projected = projectionsController.calculateSaving(months, goal);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de proyección no válido: " + type);
                }
    
                ProjectionChartFrame projectionChartFrame = new ProjectionChartFrame(projected);
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
