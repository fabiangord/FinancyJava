package View;
import Controller.BudgetController;
import Controller.ExpensesController;
import Controller.ProjectionsController;
import Controller.SavingController;
import View.components.BudgetsFrame;
import View.components.ExpensesFrame;
import View.components.IncomeFrame;
import View.components.InvestmentFrame;
import View.components.ProjectionsFrame;
import View.components.SavingFrame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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

        setLocation(550, 130);
        setTitle("FinancyJava");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 400));
        setLayout(null);

        JLabel titleLabel = new JLabel("Budget Actually...");
        titleLabel.setBounds(150, 30, 200, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);

        BigInteger total = new BudgetController().getTotal().subtract(new SavingController().getTotal());
        System.out.println(total);
        System.out.println(new SavingController().getTotal());
        System.out.println(new ExpensesController().getTotal());

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CO"));
        JLabel valueLabel = new JLabel(currencyFormatter.format(total.subtract(new ExpensesController().getTotal())).toString());
        valueLabel.setBounds(180, 60, 200, 50);
        valueLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        add(valueLabel);

        JButton incomeButton = new JButton("Income");
        incomeButton.setBounds(150, 150, 200, 50);
        incomeButton.setFont(new Font("Arial", Font.BOLD, 16));
        incomeButton.setForeground(Color.WHITE);
        incomeButton.setBackground(new Color(52, 152, 219));
        incomeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        savingButton.setBounds(150, 210, 200, 50);
        savingButton.setFont(new Font("Arial", Font.BOLD, 16));
        savingButton.setForeground(Color.WHITE);
        savingButton.setBackground(new Color(52, 152, 219));
        savingButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        expensesButton.setBounds(150, 270, 200, 50);
        expensesButton.setFont(new Font("Arial", Font.BOLD, 16));
        expensesButton.setForeground(Color.WHITE);
        expensesButton.setBackground(new Color(52, 152, 219));
        expensesButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        budgetsButton.setBounds(150, 330, 200, 50);
        budgetsButton.setFont(new Font("Arial", Font.BOLD, 16));
        budgetsButton.setForeground(Color.WHITE);
        budgetsButton.setBackground(new Color(52, 152, 219));
        budgetsButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        projectionsButton.setBounds(150, 390, 200, 50);
        projectionsButton.setFont(new Font("Arial", Font.BOLD, 16));
        projectionsButton.setForeground(Color.WHITE);
        projectionsButton.setBackground(new Color(52,152,219));
        projectionsButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        projectionsButton.setFocusPainted(false);
        projectionsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(projectionsButton);
        
        
        projectionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectionsFrame projectionsFrame = new ProjectionsFrame();
                ProjectionsController projectionsController = new ProjectionsController();
                projectionsFrame.showProjections(projectionsController.getAll());
                dispose();
            }
        });

        JButton investmentButton = new JButton("Investments");
        investmentButton.setBounds(150, 450, 200, 50);
        investmentButton.setFont(new Font("Arial", Font.BOLD, 16));
        investmentButton.setForeground(Color.WHITE);
        investmentButton.setBackground(new Color(52, 152, 219));
        investmentButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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