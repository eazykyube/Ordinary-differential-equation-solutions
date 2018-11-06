import org.jfree.data.xy.XYSeries;

public class Exact implements InterfaceMethods {

    public Exact(){}

    //Series of exact solution
    XYSeries series1;

    public void funct(Double step, Double x, Double y, Double xf){
        //Calculating points that satisfy to Exact solution and adding them to the series:
        series1 = new XYSeries("Exact");
        for(double i = x; i < xf; i += step)
            series1.add(i, (2*i*Math.exp(2*i)-Math.exp(2*i)+(y*Math.exp(2*x)
                    -2*x*Math.exp(2*x)+Math.exp(2*x)))/Math.exp(2*i));
    }

    public void apprFunct(Double step, Double x, Double y, Double xf, Double n0, Double nf){}
}
