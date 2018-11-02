import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.*;
import java.util.LinkedList;

public class Chart{

    XYSeriesCollection dataset;
    JFreeChart chart;
    ChartPanel chartPanel;

    public Chart(){}

    public void createChart(LinkedList<XYSeries> linkedList, String title){
        //Adding each series of points into the series collection:
        dataset = new XYSeriesCollection();
        while(linkedList.isEmpty()==false)
            dataset.addSeries(linkedList.pop());
        //Creating chart using series collection:
        chart = ChartFactory
                .createXYLineChart(title, "x", "y",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        //Creating chart panel using chart;
        chartPanel = new ChartPanel(chart);
        //Tuning chart panel settings:
        Zoom zoom = new Zoom();
        zoom.addListener(chartPanel);
        chartPanel.setSize(new Dimension(500, 220));
    }
}
