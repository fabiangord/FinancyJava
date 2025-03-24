package View.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Models.*;
import View.MainFrame;

public class ProjectionsFrame extends JFrame{

    public DefaultTableModel tableModel;
    private JTable table;

    public ProjectionsFrame(){
        initialize();
        setVisible(true);
    }

    public void initialize(){
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
    }

    public void showProjections(Projection projections){
        String[] columns = {"projected_budgets", "projected_expenses", "projected_incomes", "projected_savings", "months"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 380, 400, 200);
        add(scrollPane);

        setVisible(true);


        tableModel.setRowCount(0);
        Object[] row = {projections.projectedBudgets, projections.projectedExpenses, projections.projectedIncomes, projections.projectedSavings, projections.months};
        tableModel.addRow(row);
    }
    
}
