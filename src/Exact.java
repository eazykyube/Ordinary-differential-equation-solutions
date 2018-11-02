import org.jfree.data.xy.XYSeries;

public class Exact implements InterfaceMethods {

    public Exact(){}

    XYSeries series1;
    XYSeries series2;

    public void funct(Float step, Float x, Float y, Float xf){
        series1 = new XYSeries("Exact");
        for(float i = x; i < xf; i += step)
            series1.add(i, 2*i-1+Math.exp(-2*i));
    }
}
