package View.components;

import Controller.ExpensesController;
import Models.Expense;
import View.MainFrame;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class MonthlyExpenseFrame extends JFrame {
    private JTextField yearField;
    private JTextField monthField;
    private JLabel resultLabel;
    private ExpensesController controller;

    public MonthlyExpenseFrame() {

        setLocation(550, 60);
        setTitle("Gasto Mensual");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 400));
        setLayout(new BorderLayout());

        JButton backButton = new JButton("Volver");
        backButton.setBounds(15, 26, 50, 15);
        backButton.setFont(new Font("Arial", Font.BOLD, 10));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(52, 152, 219));
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(backButton);

        backButton.addActionListener(e -> {
            new MainFrame();
            dispose();
        });

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
                    controller = new ExpensesController();
                    List<Expense> monthlyExpenses = controller.getMonthlyExpenses(year, month);

                    BigInteger total = BigInteger.ZERO;
                    for (Expense exp : monthlyExpenses) {
                        total = total.add(exp.value);
                    }
                    resultLabel.setText("Gasto total: $" + total);

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
