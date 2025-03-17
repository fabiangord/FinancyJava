package View.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.IncomeController;
import View.MainFrame;

import java.math.BigInteger;

public class IncomeFrame extends JFrame{
    private JTextField nameField;
    private JTextField valueField;
    private JTextField categoryField;
    private IncomeController controllerIncome;

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

        setVisible(true);
    }
}
