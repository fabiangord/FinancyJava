package View.components;

import java.awt.*;
import java.math.BigInteger;
import java.util.Map;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Models.*;
import View.MainFrame;

public class ProjectionsFrame extends JFrame{

    private JPanel chartContainer;

    public ProjectionsFrame(){
        initialize();
    }

    public void initialize(){
        setLocation(550, 60);
        setTitle("FinancyJava - Dashboard de Proyecciones");
        setSize(1500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Volver");
        backButton.setFont(new Font("Arial", Font.BOLD, 10));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(52, 152, 219));
        backButton.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addActionListener(e -> {
            new MainFrame();
            dispose();
        });

        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        chartContainer = new JPanel();
        chartContainer.setLayout(new GridLayout(2,2,8,8));
        add(chartContainer, BorderLayout.CENTER);
        setVisible(true);
    }

    public void showProjections(Map<String, Object>  projections){

        chartContainer.removeAll();

        System.out.println(projections);

        /*Object[][] chartInfo = {
            {"Budgets", 1, projections.months},
            {"Expenses", 1, projections.months},
            {"Incomes", 1, projections.months},
            {"Savings", 1, projections.months}
        };

        for(Object[] info : projections){
            String title = (String) info[0];
            BigInteger value = (BigInteger) info[1];    
            int months = (int) info[2];

            DefaultCategoryDataset dataset = crearteDataSet(value, months, title);
            projectionChart(dataset, title);
        }*/

        revalidate();
        repaint();
    }

    private DefaultCategoryDataset crearteDataSet(BigInteger projectedValue, int months, String title){

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int month = 1; month <= months; month++) {
            BigInteger value = projectedValue.multiply(BigInteger.valueOf(month)).divide(BigInteger.valueOf(months));
            dataset.addValue(value.doubleValue(), title, "Mes " + month);
        }
        return dataset;
    }

    private void projectionChart(DefaultCategoryDataset dataset, String title){

        JFreeChart chart = ChartFactory.createStackedBarChart(
            "Grafica de " + title, "Meses", "Valores", dataset, 
            PlotOrientation.VERTICAL, true, true, false
        );

        ChartPanel panel = new ChartPanel(chart);
        chartContainer.add(panel);

    }
    
}
