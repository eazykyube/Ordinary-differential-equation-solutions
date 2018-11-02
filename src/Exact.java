import org.jfree.data.xy.XYSeries;

public class Exact implements InterfaceMethods {

    public Exact(){}

    //Series of exact solution
    XYSeries series1;

    public void funct(Float step, Float x, Float y, Float xf){
        //Calculating points that satisfy to Exact solution and adding them to the series:
        series1 = new XYSeries("Exact");
        for(float i = x; i < xf; i += step)
            series1.add(i, (2*i*Math.exp(2*i)-Math.exp(2*i)+(y*Math.exp(2*x)
                    -2*x*Math.exp(2*x)+Math.exp(2*x)))/Math.exp(2*i));
    }
}
