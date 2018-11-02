import org.jfree.data.xy.XYSeries;

public class Euler implements InterfaceMethods {

    public Euler(){}

    XYSeries series1;
    XYSeries series2;

    public void funct(Float step, Float x, Float y, Float xf){
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
