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
    JButton next = new JButton("Next");
    JButton previous = new JButton("Previous");
    ChartPanel[] chartPanels = new ChartPanel[3];
    int currentChart = 0;
    JFrame graphs;
    Container container;
    JPanel jPanel = new JPanel();

    public Graphs(String step, String x, String y, String xf, String n0, String nf) {
        //Tuning window settings:
        graphs = new JFrame("Graphs");
        graphs.setSize(800, 493);
        graphs.setLocationRelativeTo(null);
        graphs.setResizable(false);
        graphs.setDefaultCloseOperation(EXIT_ON_CLOSE);
        graphs.setVisible(true);
        container = graphs.getContentPane();
        container.setLayout(new FlowLayout());
        jPanel.setLayout(new BorderLayout());
        jPanel.setSize(new Dimension(680, 420));
        //Creating objects to get series of points of each method:
        Euler euler = new Euler();
        euler.funct(Double.valueOf(step), Double.valueOf(x), Double.valueOf(y), Double.valueOf(xf));
        euler.apprFunct(Double.valueOf(step), Double.valueOf(x), Double.valueOf(y), Double.valueOf(xf), Double.valueOf(n0), Double.valueOf(nf));
        ImprovedEuler improvedEuler = new ImprovedEuler();
        improvedEuler.funct(Double.valueOf(step), Double.valueOf(x), Double.valueOf(y), Double.valueOf(xf));
        improvedEuler.apprFunct(Double.valueOf(step), Double.valueOf(x), Double.valueOf(y), Double.valueOf(xf), Double.valueOf(n0), Double.valueOf(nf));
        RungeKutta rungeKutta = new RungeKutta();
        rungeKutta.funct(Double.valueOf(step), Double.valueOf(x), Double.valueOf(y), Double.valueOf(xf));
        rungeKutta.apprFunct(Double.valueOf(step), Double.valueOf(x), Double.valueOf(y), Double.valueOf(xf), Double.valueOf(n0), Double.valueOf(nf));
        Exact exact = new Exact();
        exact.funct(Double.valueOf(step), Double.valueOf(x), Double.valueOf(y), Double.valueOf(xf));
        //Creating LinkedList for series of points of each method:
        LinkedList<XYSeries> linkedListMethods = new LinkedList();
        linkedListMethods.addLast(euler.series1);
        linkedListMethods.addLast(improvedEuler.series1);
        linkedListMethods.addLast(rungeKutta.series1);
        linkedListMethods.addLast(exact.series1);
        //Creating chart using series of points of each method and adding to the current window:
        Chart chartMethods = new Chart();
        chartMethods.createChart(linkedListMethods, "Methods", "x", "y");
        chartPanels[0] = chartMethods.chartPanel;
        //Creating LinkedList for series of points of each method's error:
        LinkedList<XYSeries> linkedListErrors = new LinkedList();
        linkedListErrors.addLast(euler.series2);
        linkedListErrors.addLast(improvedEuler.series2);
        linkedListErrors.addLast(rungeKutta.series2);
        //Creating chart using series of points of each method's error and adding to the current window:
        Chart chartErrors = new Chart();
        chartErrors.createChart(linkedListErrors, "Errors", "x", "y");
        chartPanels[1] = chartErrors.chartPanel;
        //Creating chart using series of points of each method's ta error and adding to the current window:
        LinkedList<XYSeries> linkedListTAErrors = new LinkedList();
        linkedListTAErrors.addLast(euler.series3);
        linkedListTAErrors.addLast(improvedEuler.series3);
        linkedListTAErrors.addLast(rungeKutta.series3);
        //Creating chart using series of points of each method's error and adding to the current window:
        Chart chartTAErrors = new Chart();
        chartTAErrors.createChart(linkedListTAErrors, "TA Errors", "n", "y");
        chartPanels[2] = chartTAErrors.chartPanel;
        //Adding components into the window:
        jPanel.add(chartPanels[0], BorderLayout.CENTER);
        container.add(jPanel);
        back.setPreferredSize(new Dimension(200, 20));
        container.add(back);
        help.setPreferredSize(new Dimension(120, 20));
        container.add(help);
        info.setPreferredSize(new Dimension(60, 20));
        container.add(info);
        previous.setPreferredSize(new Dimension(90, 20));
        container.add(previous);
        next.setPreferredSize(new Dimension(60, 20));
        container.add(next);
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
                String message = "Equation: y'=-2y+4x\nExact solution: y=(2xe^(2x)-e^(2x)+C)*e^(-2x),\n" +
                        "where C=y0e^(2x0)-2x0e^(2x0)+e^(2x0)";
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

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decrease();
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increase();
            }
        });
    }

    public void decrease(){
        jPanel.remove(chartPanels[currentChart]);
        if(currentChart-1<0)
            currentChart = 2;
        else
            --currentChart;
        jPanel.add(chartPanels[currentChart], BorderLayout.CENTER);
        graphs.repaint();
    }

    public void increase(){
        jPanel.remove(chartPanels[currentChart]);
        if(currentChart+1>2)
            currentChart = 0;
        else
            ++currentChart;
        jPanel.add(chartPanels[currentChart], BorderLayout.CENTER);
        graphs.repaint();
    }
}
