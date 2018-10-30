import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Graphs extends JFrame {

    JButton back = new JButton("Return to change input data");
    ChartPanel chartPanel1;
    ChartPanel chartPanel2;

    public Graphs(String step, String x, String y, String xf) {
        JFrame graphs = new JFrame("Graphs");
        graphs.setSize(1390, 486);
        graphs.setLocationRelativeTo(null);
        this.setResizable(false);
        graphs.setDefaultCloseOperation(EXIT_ON_CLOSE);
        graphs.setVisible(true);

        Euler euler = new Euler(step, x, y, xf);
        ImprovedEuler improvedEuler = new ImprovedEuler(step, x, y, xf);
        RungeKutta rungeKutta = new RungeKutta(step, x, y, xf);
        Exact exact = new Exact(step, x, xf);
        XYSeriesCollection dataset1 = new XYSeriesCollection();
        dataset1.addSeries(euler.series1);
        dataset1.addSeries(improvedEuler.series1);
        dataset1.addSeries(rungeKutta.series1);
        dataset1.addSeries(exact.series);
        JFreeChart chart1 = ChartFactory
                .createXYLineChart("y'=-2y+4x", "x", "y",
                        dataset1,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        XYSeriesCollection dataset2 = new XYSeriesCollection();
        dataset2.addSeries(euler.series2);
        dataset2.addSeries(improvedEuler.series2);
        dataset2.addSeries(rungeKutta.series2);
        JFreeChart chart2 = ChartFactory
                .createXYLineChart("y'=-2y+4x", "x", "y",
                        dataset2,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        Container container = graphs.getContentPane();
        Zoom zoom = new Zoom();
        container.setLayout(new FlowLayout());
        chartPanel1 = new ChartPanel(chart1);
        zoom.addListener(chartPanel1);
        chartPanel1.setSize(new Dimension(500, 220));
        container.add(chartPanel1);
        chartPanel2 = new ChartPanel(chart2);
        zoom.addListener(chartPanel2);
        chartPanel2.setSize(new Dimension(500, 220));
        container.add(chartPanel2);
        back.setPreferredSize(new Dimension(200, 20));
        container.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstWindow firstWindow = new FirstWindow();
                firstWindow.setVisible(true);
                graphs.dispose();
            }
        });
    }
}
