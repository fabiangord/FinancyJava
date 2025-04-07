package View.components;

import Controller.InvestmentController;
import Models.Investment;
import View.MainFrame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class InvestmentFrame extends JFrame{
    private JLabel conceptLabel;
    private JTextField conceptField;
    private JLabel goalLabel;
    private JLabel investmentLabel;
    private JTextField investmentField;
    private JLabel interestLabel;
    private JTextField interestField;
    private JLabel monthsLabel;
    private JTextField monthsField;
    private JButton calcInvestment;
    private InvestmentController controllerInvestment;
    private JButton findConcept;
    private JTable table;
    private DefaultTableModel tableModel;
    List<Investment> results;

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
        getContentPane().setBackground(Color.decode("#48A6A7"));

        JButton backButton = new JButton("Back");
        backButton.setBounds(18, 26, 60, 20);
        backButton.setFont(new Font("Arial", Font.BOLD, 10));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#006A71"));
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

        JLabel titleLabel = new JLabel("Investment");
        titleLabel.setBounds(170, 40, 160, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        conceptLabel = new JLabel("Concept:");
        conceptLabel.setBounds(70, 110, 80, 25);
        conceptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(conceptLabel);
        
        conceptField = new JTextField();
        conceptField.setBounds(150, 110, 200, 25);
        conceptField.setHorizontalAlignment(SwingConstants.CENTER);
        add(conceptField);

        findConcept = new JButton("🔎");
        findConcept.setBounds(360, 110, 48, 25);
        findConcept.setHorizontalAlignment(SwingConstants.CENTER);
        findConcept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            controllerInvestment = new InvestmentController();
            if(!conceptField.getText().isEmpty()){
                try {
                    results = controllerInvestment.getOne(conceptField.getText());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }    
            tableModel.setRowCount(0);
    
            for (Investment investment : results) {
                Object[] row = {investment.getConcept(), investment.investment, investment.interest, investment.months, investment.feeBack};
                tableModel.addRow(row);
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please put something into the concept field");
            }
        }
        });
        add(findConcept);

        calcInvestment = new JButton("How much will I get?");
        calcInvestment.setBounds(150, 265, 200, 50);
        calcInvestment.setFont(new Font("Arial", Font.BOLD, 16));
        calcInvestment.setForeground(Color.WHITE);
        calcInvestment.setBackground(Color.decode("#006A71"));
        calcInvestment.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        calcInvestment.setFocusPainted(false);
        calcInvestment.setCursor(new Cursor(Cursor.HAND_CURSOR));
        calcInvestment.setHorizontalAlignment(SwingConstants.CENTER);
        calcInvestment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String conceptF = conceptField.getText().trim();
                    String investmentF = investmentField.getText().trim();
                    BigDecimal investment = new BigDecimal(investmentF);
                    String interestF = interestField.getText().trim();
                    long interest = Long.valueOf(interestF);
                    String monthsF = monthsField.getText().trim();
                    int months = Integer.parseInt(monthsF);
                    
                    controllerInvestment.getResult2(conceptF, investment, interest, months);
                    BigDecimal result = controllerInvestment.getResult2(conceptF, investment, interest, months);
                    controllerInvestment.add(conceptF, investment, interest, months, result);
                    JOptionPane.showMessageDialog(null, "Your feedback is: $" +result);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please verify that all the fields are filled"  +ex);
                } catch (Exception e2){
                    JOptionPane.showMessageDialog(null, e2);
                    e2.printStackTrace();
                }
            }
        });

        add(calcInvestment);

        investmentLabel = new JLabel("Investment:");
        investmentLabel.setBounds(70, 140, 80, 25);
        investmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(investmentLabel);
        
        investmentField = new JTextField();
        investmentField.setBounds(150, 140, 200, 25);
        investmentField.setHorizontalAlignment(SwingConstants.CENTER);
        add(investmentField);

        interestLabel = new JLabel("Interest (%):");
        interestLabel.setHorizontalAlignment(SwingConstants.CENTER);
        interestLabel.setBounds(70, 170, 80, 25);
        add(interestLabel);

        interestField = new JTextField();
        interestField.setBounds(150, 170, 200, 25);
        interestField.setHorizontalAlignment(SwingConstants.CENTER);
        add(interestField);
        
        monthsLabel = new JLabel("Months:");
        monthsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthsLabel.setBounds(70, 200, 80, 25);
        add(monthsLabel);

        monthsField = new JTextField();
        monthsField.setBounds(150, 200, 200, 25);
        monthsField.setHorizontalAlignment(SwingConstants.CENTER);
        add(monthsField);

        String[] columns = {"Concept", "Investment", "Interest", "Categoria"};
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
        scrollPane.setBounds(50, 350, 400, 200);
        scrollPane.getViewport().setBackground(Color.decode("#48A6A7"));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#48A6A7")));
        add(scrollPane);

        JButton allIncome = new JButton("Get Savings");
        allIncome.setBounds(150, 570, 200, 50);
        allIncome.setFont(new Font("Arial", Font.BOLD, 16));
        allIncome.setForeground(Color.WHITE);
        allIncome.setBackground(Color.decode("#006A71"));
        allIncome.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        allIncome.setFocusPainted(false);
        allIncome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        allIncome.addActionListener(e -> allInvestments());
        add(allIncome);

        setVisible(true);
    }

    private void allInvestments() {
        final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CO"));

        try {
            controllerInvestment = new InvestmentController();
            List<Investment> results = controllerInvestment.getOne(conceptField.getText());
            tableModel.setRowCount(0);
        
            for (Investment investment : results) {
                Object[] row = {investment.getConcept(), currencyFormatter.format(investment.investment), investment.interest, investment.months, investment.feeBack};
                tableModel.addRow(row);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
