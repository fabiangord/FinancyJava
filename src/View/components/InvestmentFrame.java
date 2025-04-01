package View.components;

import Controller.InvestmentController;
import View.MainFrame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class InvestmentFrame extends JFrame{
    private JLabel conceptLabel;
    private JTextField conceptField;
    private JSeparator split;
    private JLabel goalLabel;
    private JTextField goalField;
    private JLabel investmentLabel;
    private JTextField investmentField;
    private JLabel interestLabel;
    private JTextField interestField;
    private JLabel monthsLabel;
    private JTextField monthsField;
    private JButton calcInvestment;
    private JLabel allSavingLabel;
    private InvestmentController controllerInvestment;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> categoryComboBox;

    public static void main(String[] args) {
        new InvestmentFrame();
    }

    public InvestmentFrame() {
        this.controllerInvestment = new InvestmentController();
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

        backButton.addActionListener(e -> {
            new MainFrame();
            dispose();
        });

        JLabel titleLabel = new JLabel("Investment");
        titleLabel.setBounds(182, 30, 160, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(titleLabel);

        conceptLabel = new JLabel("Concept:");
        conceptLabel.setBounds(90, 110, 80, 25);
        conceptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(conceptLabel);
        
        conceptField = new JTextField();
        conceptField.setBounds(168, 110, 200, 25);
        conceptField.setHorizontalAlignment(SwingConstants.CENTER);
        add(conceptField);

        goalLabel = new JLabel("👇");
        goalLabel.setBounds(225, 140, 80, 25);
        goalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        goalLabel.setVisible(true);
        add(goalLabel);

        /*goalField = new JTextField();
        goalField.setBounds(168, 140, 200, 25);
        goalField.setHorizontalAlignment(SwingConstants.CENTER);
        goalField.setVisible(false);
        add(goalField);*/
        
        investmentLabel = new JLabel("investment:");
        investmentLabel.setBounds(90, 170, 80, 25);
        investmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(investmentLabel);
        
        investmentField = new JTextField();
        investmentField.setBounds(168, 170, 200, 25);
        investmentField.setHorizontalAlignment(SwingConstants.CENTER);
        add(investmentField);

        interestLabel = new JLabel("Interest (%):");
        interestLabel.setHorizontalAlignment(SwingConstants.CENTER);
        interestLabel.setBounds(90, 200, 80, 25);
        add(interestLabel);

        interestField = new JTextField();
        interestField.setBounds(168, 200, 200, 25);
        interestField.setHorizontalAlignment(SwingConstants.CENTER);
        add(interestField);
        
        monthsLabel = new JLabel("Months:");
        monthsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthsLabel.setBounds(90, 230, 80, 25);
        add(monthsLabel);

        monthsField = new JTextField();
        monthsField.setBounds(168, 230, 200, 25);
        monthsField.setHorizontalAlignment(SwingConstants.CENTER);
        add(monthsField);

        calcInvestment = new JButton("How much will I get?");
        calcInvestment.setBounds(150, 265, 200, 50);
        calcInvestment.setFont(new Font("Arial", Font.BOLD, 16));
        calcInvestment.setForeground(Color.WHITE);
        calcInvestment.setBackground(new Color(52, 152, 219));
        calcInvestment.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        calcInvestment.setFocusPainted(false);
        calcInvestment.setCursor(new Cursor(Cursor.HAND_CURSOR));
        calcInvestment.setHorizontalAlignment(SwingConstants.CENTER);
        calcInvestment.addActionListener(e -> {
            try {
                String conceptF = conceptField.getText().trim();
                //String goalF = goalField.getText().trim();
                //int prueba2 = Integer.parseInt(goalF);
                String investmentF = investmentField.getText().trim();
                BigDecimal investment = new BigDecimal(investmentF);
                String interestF = interestField.getText().trim();
                long interest = Long.valueOf(interestF);
                String monthsF = monthsField.getText().trim();
                int prueba = Integer.parseInt(monthsF);

                controllerInvestment.getResult2(conceptF, investment, interest, prueba);
                BigDecimal result = controllerInvestment.getResult2(conceptF, investment, interest, prueba);
                controllerInvestment.add(conceptF, investment, interest, prueba, result);
                JOptionPane.showMessageDialog(null, "Your fee back is: $" +result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please verify that all the fields are filled"  +ex);
            } catch (Exception e2){
                JOptionPane.showMessageDialog(null, e2);
                e2.printStackTrace();
            }
        });

        add(calcInvestment);
        setVisible(true);
/*
        JButton getInvestment = new JButton("Ver Ahorros");
        getInvestment.setBounds(150, 320, 200, 50);
        getInvestment.setFont(new Font("Arial", Font.BOLD, 16));
        getInvestment.setForeground(Color.WHITE);
        getInvestment.setBackground(new Color(52, 152, 219));
        getInvestment.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        getInvestment.setFocusPainted(false);
        getInvestment.setCursor(new Cursor(Cursor.HAND_CURSOR));
        getInvestment.addActionListener(e -> allSavings());
        add(getInvestment);

        String[] columns = {"ID", "Nombre", "Valor", "Categoria"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 380, 400, 200);
        add(scrollPane);

        setVisible(true);
    }

    private void allSavings() {
        controllerInvestment = new InvestmentController();
        List<Investment> results = controllerInvestment.getAll();
        tableModel.setRowCount(0);
    
        for (Investment investment : results) {
            Object[] row = {investment.getConcept(), investment.getGoal()};
            tableModel.addRow(row);
        }
*/
    }
    
}
