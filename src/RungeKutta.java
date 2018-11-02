import org.jfree.data.xy.XYSeries;

public class RungeKutta implements InterfaceMethods {

    public RungeKutta(){}

    XYSeries series1;
    XYSeries series2;

    public void funct(Float step, Float x, Float y, Float xf){
        series1 = new XYSeries("Runge-Kutta's Method");
        series1.add(x, y);
        series2 = new XYSeries("Runge-Kutta's Method Error");
        series2.add(x, y);
        float k1, k2, k3, k4;
        for(float i = x+step; i < xf; i += step){
            k1 = -2*y+4*(i-step);
            k2 = -2*(y+step*k1/2)+4*(i-step/2);
            k3 = -2*(y+step*k2/2)+4*(i-step/2);
            k4 = -2*(y+step*k3)+4*i;
            series1.add(i, y+step/6*(k1+2*k2+2*k3+k4));
            series2.add(i, Math.abs((2*i-1+Math.exp(-2*i))-(y+step/6*(k1+2*k2+2*k3+k4))));
            y += step/6*(k1+2*k2+2*k3+k4);
        }
    }
}
