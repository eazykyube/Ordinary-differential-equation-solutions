import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Euler {

    XYSeries series1;
    XYSeries series2;

    public Euler(Float step, Float x, Float y, Float xf){
        series1 = new XYSeries("Euler's Method");
        series1.add(x, y);
        series2 = new XYSeries("Euler's Method Error");
        series2.add(x, y);
        for(float i = x+step; i < xf; i += step){
            series1.add(i, y+step*(-2*y+4*(i-step)));
            series2.add(i, Math.abs((2*i-1+Math.exp(-2*i))-(y+step*(-2*y+4*(i-step)))));
            y += step*(-2*y+4*(i-step));
        }
    }
}
