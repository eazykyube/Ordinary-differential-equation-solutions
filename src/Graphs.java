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
import java.util.ArrayList;
import java.util.LinkedList;

public class Graphs extends JFrame {

    //Components that we will be added to this window
    JButton back = new JButton("Return to change input data");
    JButton help = new JButton("How to zoom?");
    JButton info = new JButton("Info");

    public Graphs(String step, String x, String y, String xf) {
        //Tuning window settings:
        JFrame graphs = new JFrame("Graphs");
        graphs.setSize(1390, 486);
        graphs.setLocationRelativeTo(null);
        graphs.setResizable(false);
        graphs.setDefaultCloseOperation(EXIT_ON_CLOSE);
        graphs.setVisible(true);
        Container container = graphs.getContentPane();
        container.setLayout(new FlowLayout());
        //Creating objects to get series of points of each method:
        Euler euler = new Euler();
        euler.funct(Float.valueOf(step), Float.valueOf(x), Float.valueOf(y), Float.valueOf(xf));
        ImprovedEuler improvedEuler = new ImprovedEuler();
        improvedEuler.funct(Float.valueOf(step), Float.valueOf(x), Float.valueOf(y), Float.valueOf(xf));
        RungeKutta rungeKutta = new RungeKutta();
        rungeKutta.funct(Float.valueOf(step), Float.valueOf(x), Float.valueOf(y), Float.valueOf(xf));
        Exact exact = new Exact();
        exact.funct(Float.valueOf(step), Float.valueOf(x), Float.valueOf(y), Float.valueOf(xf));
        //Creating LinkedList for series of points of each method:
        LinkedList<XYSeries> linkedListMethods = new LinkedList();
        linkedListMethods.addLast(euler.series1);
        linkedListMethods.addLast(improvedEuler.series1);
        linkedListMethods.addLast(rungeKutta.series1);
        linkedListMethods.addLast(exact.series1);
        //Creating chart using series of points of each method and adding to the current window:
        Chart chartMethods = new Chart();
        chartMethods.createChart(linkedListMethods, "Methods");
        container.add(chartMethods.chartPanel);
        //Creating LinkedList for series of points of each method's error:
        LinkedList<XYSeries> linkedListErrors = new LinkedList();
        linkedListErrors.addLast(euler.series2);
        linkedListErrors.addLast(improvedEuler.series2);
        linkedListErrors.addLast(rungeKutta.series2);
        //Creating chart using series of points of each method's error and adding to the current window:
        Chart chartErrors = new Chart();
        chartErrors.createChart(linkedListErrors, "Errors");
        container.add(chartErrors.chartPanel);
        //Adding components into the window:
        back.setPreferredSize(new Dimension(200, 20));
        container.add(back);
        help.setPreferredSize(new Dimension(120, 20));
        container.add(help);
        info.setPreferredSize(new Dimension(60, 20));
        container.add(info);
        //Tuning "back" button settings:
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Delete Graphs window:
                graphs.dispose();
                //Create new FirstWindow:
                FirstWindow firstWindow = new FirstWindow();
                firstWindow.setVisible(true);
            }
        });
        //Tuning "info" button settings:
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Simply shows some information:
                String message = "Equation: y'=-2y+4x\nExact solution: y=2x-1+e^(-2x)\n";
                JOptionPane.showMessageDialog(null, message, "INFO", JOptionPane.PLAIN_MESSAGE);
            }
        });
        //Tuning "help" button settings:
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Simply shows some information:
                String message = "You can zoom by using your mouse wheel either by selecting\n" +
                        "the area which you want to see closer(drag the cursor,\n" +
                        "starting from left-up and going right-down). To return to\n" +
                        "initial view drag the cursor from right to left.\n" +
                        "To additional opportunities right-click on coordinate axes.";
                JOptionPane.showMessageDialog(null, message, "INFO", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}
