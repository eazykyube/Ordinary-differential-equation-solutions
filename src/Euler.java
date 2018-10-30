import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Euler {

    XYSeries series1;
    XYSeries series2;

    public Euler(String step, String x, String y, String xf){
        series1 = new XYSeries("Euler's Method");
        series1.add(Float.valueOf(x),Float.valueOf(y));
        series2 = new XYSeries("Euler's Method Error");
        series2.add(Float.valueOf(x),Float.valueOf(y));
        float hf = Float.valueOf(y);
        for(float i = Float.valueOf(x) + Float.valueOf(step); i < Float.valueOf(xf); i+=Float.valueOf(step)){
            series1.add(i, hf+Float.valueOf(step)*(-2*hf+4*(i-Float.valueOf(step))));
            series2.add(i, Math.abs((2*i-1+Math.exp(-2*i))-(hf+Float.valueOf(step)*(-2*hf+4*(i-Float.valueOf(step))))));
            hf += Float.valueOf(step)*(-2*hf+4*(i-Float.valueOf(step)));
        }
    }
}
