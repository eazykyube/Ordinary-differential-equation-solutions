import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Exact {

    XYSeries series;

    public Exact(Float step, Float x, Float xf){
        series = new XYSeries("Exact");
        for(float i = x; i < xf; i += step)
            series.add(i, 2*i-1+Math.exp(-2*i));
    }
}
