import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ImprovedEuler {

    XYSeries series1;
    XYSeries series2;

    public ImprovedEuler(String step, String x, String y, String xf){
        series1 = new XYSeries("Improved Euler's Method");
        series1.add(Float.valueOf(x),Float.valueOf(y));
        series2 = new XYSeries("Improved Euler's Method Error");
        series2.add(Float.valueOf(x),Float.valueOf(y));
        float hf = Float.valueOf(y);
        float shag = Float.valueOf(step);
        for(float i = Float.valueOf(x) + shag; i < Float.valueOf(xf); i+=shag){
            series1.add(i, hf+shag*(-2*(hf+shag*0.5*(-2*hf+4*(i-shag)))+4*(i-shag*0.5)));
            series2.add(i, Math.abs((2*i-1+Math.exp(-2*i))-(hf+shag*(-2*(hf+shag*0.5*(-2*hf+4*(i-shag)))+4*(i-shag*0.5)))));
            hf += shag*(-2*(hf+shag*0.5*(-2*hf+4*(i-shag)))+4*(i-shag*0.5));
        }
    }
}
