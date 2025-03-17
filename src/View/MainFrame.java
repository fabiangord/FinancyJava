package View;
import Controller.SavingController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.BigInteger;


public class MainFrame extends JFrame {
    private JTextField nameField;
    private JTextField valueField;
    private JTextField categoryField;
    private SavingController controllerSaving;

    public static void main(String[] args) {
        new MainFrame();
    }
    public MainFrame() {
        initialize();
    }

    public void initialize() {
        setTitle("FinancyJava");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 400));
        setLayout(null);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(30, 110, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(110, 110, 200, 25);
        add(nameField);

        JLabel valueLabel = new JLabel("Valor:");
        valueLabel.setBounds(30, 140, 80, 25);
        add(valueLabel);

        valueField = new JTextField();
        valueField.setBounds(110, 140, 200, 25);
        add(valueField);

        JLabel categoryLabel = new JLabel("Categoria:");
        categoryLabel.setBounds(30, 170, 80, 25);
        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(110, 170, 200, 25);
        add(categoryField);

        JButton saveButton = new JButton("Guardar");
        saveButton.setBounds(150, 300, 200, 50);
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(52, 152, 219));
        saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        saveButton.setFocusPainted(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String category = categoryField.getText().trim();
                String valueText = valueField.getText().trim();
                BigInteger value = new BigInteger(valueText);

                controllerSaving = new SavingController();
                controllerSaving.addSaving(name, category, value);
                JOptionPane.showMessageDialog(null, "Ahorro guardado con éxito!");
            }
        });

        setVisible(true);
    }
}