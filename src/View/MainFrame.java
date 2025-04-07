package View;
import Controller.*;
import View.components.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;

public class MainFrame extends JFrame {
    public static void main(String[] args) {
        new MainFrame();
    }

    public MainFrame() {
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
        getContentPane().setBackground(Color.decode("#48A6A7"));


        JLabel titleLabel = new JLabel("Current Budget...");
        titleLabel.setBounds(150, 30, 200, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        BigInteger total = new BudgetController().getTotal().subtract(new SavingController().getTotal());
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CO"));
        JLabel valueLabel = new JLabel(currencyFormatter.format(total.subtract(new ExpensesController().getTotal())).toString());
        valueLabel.setBounds(180, 60, 200, 50);
        valueLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        valueLabel.setForeground(Color.WHITE);
        add(valueLabel);

        JButton incomeButton = new JButton("Income");
        incomeButton.setBounds(100, 150, 200, 50);
        incomeButton.setFont(new Font("Arial", Font.BOLD, 16));
        incomeButton.setForeground(Color.WHITE);
        incomeButton.setBackground(Color.decode("#006A71"));
        incomeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        incomeButton.setFocusPainted(false);
        incomeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(incomeButton);

        incomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                new IncomeFrame();
                dispose();
            }
        });

        JButton savingButton = new JButton("Saving");
        savingButton.setBounds(200, 210, 200, 50);
        savingButton.setFont(new Font("Arial", Font.BOLD, 16));
        savingButton.setForeground(Color.WHITE);
        savingButton.setBackground(Color.decode("#006A71"));
        savingButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        savingButton.setFocusPainted(false);
        savingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(savingButton);

        savingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                new SavingFrame();
                dispose();
            }
        });

        JButton expensesButton = new JButton("Expenses");
        expensesButton.setBounds(100, 270, 200, 50);
        expensesButton.setFont(new Font("Arial", Font.BOLD, 16));
        expensesButton.setForeground(Color.WHITE);
        expensesButton.setBackground(Color.decode("#006A71"));
        expensesButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        expensesButton.setFocusPainted(false);
        expensesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(expensesButton);

        expensesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                new ExpensesFrame();
                dispose();
            }
        });

        JButton budgetsButton = new JButton("Budgets");
        budgetsButton.setBounds(200, 330, 200, 50);
        budgetsButton.setFont(new Font("Arial", Font.BOLD, 16));
        budgetsButton.setForeground(Color.WHITE);
        budgetsButton.setBackground(Color.decode("#006A71"));
        budgetsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        budgetsButton.setFocusPainted(false);
        budgetsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(budgetsButton);

        budgetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                new BudgetsFrame();
                dispose();
            }
        });

        JButton projectionsButton = new JButton("Projections");
        projectionsButton.setBounds(100, 390, 200, 50);
        projectionsButton.setFont(new Font("Arial", Font.BOLD, 16));
        projectionsButton.setForeground(Color.WHITE);
        projectionsButton.setBackground(Color.decode("#006A71"));
        projectionsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        projectionsButton.setFocusPainted(false);
        projectionsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(projectionsButton);
        
        
        projectionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectionsController projectionsController = new ProjectionsController();
                new ProjectionDialogFrame(projectionsController, "All");
                dispose();
            }
        });

        JButton investmentButton = new JButton("Investments");
        investmentButton.setBounds(200, 450, 200, 50);
        investmentButton.setFont(new Font("Arial", Font.BOLD, 16));
        investmentButton.setForeground(Color.WHITE);
        investmentButton.setBackground(Color.decode("#006A71"));
        investmentButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        investmentButton.setFocusPainted(false);
        investmentButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(investmentButton);

        investmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                new InvestmentFrame();
                dispose();
            }
        });

        
    }
}