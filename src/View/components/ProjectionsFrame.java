package View.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Map;
import java.util.List;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ui.Layer;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

import View.MainFrame;

public class ProjectionsFrame extends JFrame {

    private JPanel chartContainer;

    public ProjectionsFrame() {
        initialize();
    }

    public void initialize() {
        setTitle("FinancyJava - Dashboard de Proyecciones");
        setSize(1500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Volver");
        backButton.setFont(new Font("Arial", Font.BOLD, 10));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(52, 152, 219));
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(_ -> {
            new MainFrame();
            dispose();
        });
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        chartContainer = new JPanel();
        chartContainer.setLayout(new GridLayout(2, 2, 8, 8));
        add(chartContainer, BorderLayout.CENTER);
        setVisible(true);
    }
    
    public void showProjections(Map<String, Map<String, Object>> projections) {
        chartContainer.removeAll();
    
        for (Map.Entry<String, Map<String, Object>> entry : projections.entrySet()) {
            String type = entry.getKey();
            Map<String, Object> data = entry.getValue();
    
            int months = (int) data.get("months");
            BigInteger goal = (BigInteger) data.get("goal");
            List<BigInteger> values = (List<BigInteger>) data.get("projection");
    
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            createAnimatedChart(dataset, values, months, type, goal);
        }
    
        revalidate();
        repaint();
    }
    
    private void createAnimatedChart(DefaultCategoryDataset dataset, List<BigInteger> values, int months, String type, BigInteger goal) {
        // Crear el gráfico según el tipo
        JFreeChart chart;
        if (type.equals("Expenses") || type.equals("Incomes")) {
            chart = ChartFactory.createBarChart(
                "Proyección de " + type, "Meses", "Valores", dataset,
                PlotOrientation.VERTICAL, true, true, false
            );
        } else {
            chart = ChartFactory.createLineChart(
                "Proyección de " + type, "Meses", "Valores", dataset,
                PlotOrientation.VERTICAL, true, true, false
            );
        }
    
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new GradientPaint(0f, 0f, Color.WHITE, 0f, 600f, new Color(230, 240, 255)));
        plot.setRangeGridlinePaint(new Color(180, 180, 180));
        plot.setDomainGridlinePaint(new Color(180, 180, 180));
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
    
        // Configurar el renderer según el tipo de gráfico
        if (type.equals("Expenses") || type.equals("Incomes")) {
            // Renderer para gráfico de barras
            BarRenderer barRenderer = new BarRenderer() {
                @Override
                public Paint getItemPaint(int row, int column) {
                    if (column < values.size()) {
                        BigInteger val = values.get(column);
                        return val.compareTo(goal) >= 0 ? new Color(34, 197, 94) : new Color(239, 68, 68);
                    }
                    return super.getItemPaint(row, column);
                }
            };
            barRenderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator("Mes: {1} - Valor: {2}", NumberFormat.getInstance()));
            plot.setRenderer(barRenderer);
        } else {
            // Renderer para gráfico de líneas
            LineAndShapeRenderer renderer = new LineAndShapeRenderer() {
                @Override
                public Paint getItemPaint(int row, int column) {
                    if (column < values.size()) {
                        BigInteger val = values.get(column);
                        return val.compareTo(goal) >= 0 ? new Color(34, 197, 94) : new Color(239, 68, 68);
                    }
                    return super.getItemPaint(row, column);
                }
            };
            renderer.setSeriesStroke(0, new BasicStroke(3f));
            renderer.setSeriesShapesVisible(0, true);
            renderer.setSeriesShape(0, new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
            renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator("Mes: {1} - Valor: {2}", NumberFormat.getInstance()));
            plot.setRenderer(renderer);
        }
    
        // Agregar la línea de meta
        ValueMarker marker = new ValueMarker(goal.doubleValue());
        marker.setPaint(new Color(34, 197, 94));
        marker.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] {5.0f, 5.0f}, 0.0f));
        marker.setLabel("Meta: " + goal);
        marker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        marker.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
        marker.setLabelFont(new Font("SansSerif", Font.PLAIN, 10));
        plot.addRangeMarker(marker);
    
        // Panel del gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 300));
        chartPanel.setMouseWheelEnabled(true);
        chartContainer.add(chartPanel);
    
        // Animación: se agregan los datos uno a uno
        Timer timer = new Timer(150, new ActionListener() {
            int index = 0;
            public void actionPerformed(ActionEvent e) {
                if (index < values.size()) {
                    dataset.addValue(values.get(index).doubleValue(), type, "Mes: " + (index + 1));
                    index++;
                } 
                
                if (index == values.size()) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });        
        timer.start();
    }
}
