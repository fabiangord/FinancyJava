package View.components;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.ui.*;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import View.MainFrame;

public class ProjectionChartFrame extends JFrame{

    private int months;
    private BigInteger goal;
    private List<BigInteger> projectedValues;
    private String type;

    private int currentIndex = 0;


    private DefaultCategoryDataset dataset;

    public ProjectionChartFrame(Map<String, Object> projectedData){

        this.months = (int) projectedData.get("months");
        this.goal = (BigInteger) projectedData.get("goal");
        this.projectedValues = (List<BigInteger>) projectedData.get("projection");
        this.type = (String) projectedData.get("type");
        this.dataset = new DefaultCategoryDataset();


        initialize();
    }

    public void initialize(){
        setLocation(550, 60);
        setTitle(type);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        projectionChart();
    }

    private void projectionChart(){ 
        BigInteger valueMin = projectedValues.stream().min(BigInteger::compareTo).get();
        BigInteger valueMax = projectedValues.stream().max(BigInteger::compareTo).get();
        double margen = (valueMax.doubleValue() - valueMin.doubleValue()) * 0.1;

        Timer animTimer = new Timer(200, e -> {
            if (currentIndex < projectedValues.size()) {
                dataset.addValue(projectedValues.get(currentIndex), type, "Mes: "+(currentIndex + 1));
                currentIndex++;
            } else {
                ((Timer)e.getSource()).stop();
            }
        });
        animTimer.start();
         

        JFreeChart chart = ChartFactory.createLineChart(
            "Proyección de " + type, "Meses", "Valores", dataset,
            PlotOrientation.VERTICAL, true, true, false
        );

        chart.setAntiAlias(true);
        chart.setTextAntiAlias(true);
        chart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));

        ValueMarker marker = new ValueMarker(goal.doubleValue());
        marker.setPaint(Color.GREEN);
        marker.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] {5.0f, 5.0f}, 0.0f)); // Línea punteada
        marker.setLabel("Meta: " + goal);
        marker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        marker.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
        marker.setLabelFont(new Font("SanSerif", Font.PLAIN, 10));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.addRangeMarker(marker);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                BigInteger value = projectedValues.get(column);
                return value.compareTo(goal) >= 0 ? Color.GREEN : Color.RED;
            }
        };

        renderer.setSeriesStroke(0, new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        renderer.setSeriesShape(0, new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
        renderer.setSeriesShapesVisible(0, true);

        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelFont(new Font("SanSerif", Font.PLAIN, 10));
        renderer.setSeriesPaint(0, new GradientPaint(0f, 0f, new Color(50, 150, 255), 0f, 0f, new Color(100, 255, 100), true));
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator("Mes {1}: {2} (" + type + ")", NumberFormat.getInstance()));
        
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);
        plot.setBackgroundPaint(new GradientPaint(0f, 0f, Color.WHITE, 0f, 600f, new Color(235, 245, 255)));
        plot.setRangeZeroBaselineVisible(true);
        plot.setRangeZeroBaselinePaint(Color.GRAY);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.PLAIN, 12));        
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();

        yAxis.setLowerBound(valueMin.doubleValue() - margen);
        yAxis.setUpperBound(valueMax.doubleValue() + margen);


        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        panel.setDomainZoomable(true);
        panel.setPreferredSize(new Dimension(800, 400));
        panel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.setPreferredSize(new Dimension(getWidth() - 50, getHeight() - 100));

        add(panel);

        saveButton(chart);

        return;
    }

    private void saveButton(JFreeChart chart){
        JButton saveButton = new JButton("Guardar como PNG 📂");

        saveButton.addActionListener(_ ->{
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Guardar grafica de "+type);
                fileChooser.setSelectedFile(new File(type+"_proyeccion.png"));
                int userSelection = fileChooser.showSaveDialog(this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    ChartUtils.saveChartAsPNG(file, chart, 800, 400);
                    JOptionPane.showMessageDialog(this, "Gráfica guardada en:\n" + file.getAbsolutePath(), "Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar la imagen:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        saveButton.setBackground(new Color(31, 212, 49));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 12));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton backButton = new JButton("<-- Volver");

        backButton.setBackground(new Color(31, 212, 49));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 12));

        backButton.addActionListener(_ ->{
            new MainFrame();
            dispose();
        });
        
        bottomPanel.add(saveButton);
        bottomPanel.add(backButton);
        bottomPanel.setBackground(Color.WHITE);
        add(bottomPanel, BorderLayout.SOUTH);

    }
}

