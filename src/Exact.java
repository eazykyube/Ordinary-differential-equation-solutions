import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Exact {

    XYSeries series;

    public Exact(String step, String x, String xf){
        series = new XYSeries("Exact");
        for(float i=Float.valueOf(x); i<Float.valueOf(xf); i+=Float.valueOf(step))
            series.add(i, 2*i-1+Math.exp(-2*i));
    }
}
