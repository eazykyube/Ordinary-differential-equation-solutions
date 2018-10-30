import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class RungeKutta {

    XYSeries series1;
    XYSeries series2;

    public RungeKutta(String step, String x, String y, String xf){
        series1 = new XYSeries("Runge-Kutta's Method");
        series1.add(Float.valueOf(x),Float.valueOf(y));
        series2 = new XYSeries("Runge-Kutta's Method Error");
        series2.add(Float.valueOf(x),Float.valueOf(y));
        float hf = Float.valueOf(y);
        float shag = Float.valueOf(step);
        float k1, k2, k3, k4;
        for(float i = Float.valueOf(x) + shag; i < Float.valueOf(xf); i+=shag){
            k1 = -2*hf+4*(i-shag);
            k2 = -2*(hf+shag*k1/2)+4*(i-shag/2);
            k3 = -2*(hf+shag*k2/2)+4*(i-shag/2);
            k4 = -2*(hf+shag*k3)+4*i;
            series1.add(i, hf+shag/6*(k1+2*k2+2*k3+k4));
            series2.add(i, Math.abs((2*i-1+Math.exp(-2*i))-(hf+shag/6*(k1+2*k2+2*k3+k4))));
            hf += shag/6*(k1+2*k2+2*k3+k4);
        }
    }
}
