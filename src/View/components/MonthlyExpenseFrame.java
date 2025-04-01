package View.components;

import Controller.MonthlyExpenseController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonthlyExpenseFrame extends JFrame {
    private JTextField yearField;
    private JTextField monthField;
    private JLabel resultLabel;
    private MonthlyExpenseController controller;

    public MonthlyExpenseFrame() {
        this.controller = new MonthlyExpenseController(null);

        setTitle("Gasto Mensual");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal con márgenes
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 10, 5, 10); // Espaciado entre elementos
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Etiqueta y campo de Año
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Año:"), gbc);

        gbc.gridx = 1;
        yearField = new JTextField(10);
        mainPanel.add(yearField, gbc);

        // Etiqueta y campo de Mes
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Mes:"), gbc);

        gbc.gridx = 1;
        monthField = new JTextField(10);
        mainPanel.add(monthField, gbc);

        // Botón Calcular
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa dos columnas
        JButton calculateButton = new JButton("Calcular");
        mainPanel.add(calculateButton, gbc);

        // Etiqueta de resultado
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        resultLabel = new JLabel("Gasto total: $0.00", SwingConstants.CENTER);
        mainPanel.add(resultLabel, gbc);

        // Agregar panel a la ventana
        add(mainPanel, BorderLayout.CENTER);

        // Acción del botón
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int year = Integer.parseInt(yearField.getText());
                    int month = Integer.parseInt(monthField.getText());
                    controller.showMonthlyExpense(year, month);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa valores numéricos válidos.");
                }
            }
        });

        // Ajustar tamaño óptimo de la ventana
        pack();
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);
    }
}
