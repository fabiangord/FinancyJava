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
import java.util.List;
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

        findConcept = new JButton("🔎");
        findConcept.setBounds(380, 110, 48, 25);
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
        calcInvestment.setBackground(new Color(52, 152, 219));
        calcInvestment.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
                    JOptionPane.showMessageDialog(null, "Your fee back is: $" +result);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please verify that all the fields are filled"  +ex);
                } catch (Exception e2){
                    JOptionPane.showMessageDialog(null, e2);
                    e2.printStackTrace();
                }
            }
        });

        add(calcInvestment);

        investmentLabel = new JLabel("investment:");
        investmentLabel.setBounds(90, 140, 80, 25);
        investmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(investmentLabel);
        
        investmentField = new JTextField();
        investmentField.setBounds(168, 140, 200, 25);
        investmentField.setHorizontalAlignment(SwingConstants.CENTER);
        add(investmentField);

        interestLabel = new JLabel("Interest (%):");
        interestLabel.setHorizontalAlignment(SwingConstants.CENTER);
        interestLabel.setBounds(90, 170, 80, 25);
        add(interestLabel);

        interestField = new JTextField();
        interestField.setBounds(168, 170, 200, 25);
        interestField.setHorizontalAlignment(SwingConstants.CENTER);
        add(interestField);
        
        monthsLabel = new JLabel("Months:");
        monthsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthsLabel.setBounds(90, 200, 80, 25);
        add(monthsLabel);

        monthsField = new JTextField();
        monthsField.setBounds(168, 200, 200, 25);
        monthsField.setHorizontalAlignment(SwingConstants.CENTER);
        add(monthsField);

        String[] columns = {"Concept", "Investment", "Interest", "Categoria"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 380, 400, 200);
        add(scrollPane);

        setVisible(true);
    }

    private void allInvestments() throws SQLException {
        controllerInvestment = new InvestmentController();
        List<Investment> results = controllerInvestment.getOne(conceptField.getText());
        tableModel.setRowCount(0);
    
        for (Investment investment : results) {
            Object[] row = {investment.getConcept(), investment.investment, investment.interest, investment.months, investment.feeBack};
            tableModel.addRow(row);
        }
    }
    
}
