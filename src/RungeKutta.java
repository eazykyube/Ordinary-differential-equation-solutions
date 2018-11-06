import org.jfree.data.xy.XYSeries;

public class RungeKutta implements InterfaceMethods {

    public RungeKutta(){}

    //Series of method
    XYSeries series1;
    //Series of method's error
    XYSeries series2;
    //Series of method's approximation error
    XYSeries series3;

    public void funct(Double step, Double x, Double y, Double xf){
        //Calculating points that satisfy to Runge-Kutta's Method and adding them to the series1
        //and points that satisfy to Runge-Kutta's Method error and adding them to the series2:
        series1 = new XYSeries("Runge-Kutta's Method");
        series1.add(x, y);
        series2 = new XYSeries("Runge-Kutta's Method Error");
        series2.add(x, y);
        double k1, k2, k3, k4;
        double y0 = y;
        for(double i = x+step; i < xf; i += step){
            k1 = -2*y+4*(i-step);
            k2 = -2*(y+step*k1/2)+4*(i-step/2);
            k3 = -2*(y+step*k2/2)+4*(i-step/2);
            k4 = -2*(y+step*k3)+4*i;
            series1.add(i, y+step/6*(k1+2*k2+2*k3+k4));
            series2.add(i, Math.abs((2*i*Math.exp(2*i)-Math.exp(2*i)+(y0*Math.exp(2*x)
                    -2*x*Math.exp(2*x)+Math.exp(2*x)))/Math.exp(2*i)-
                    (y+step/6*(k1+2*k2+2*k3+k4))));
            y += step/6*(k1+2*k2+2*k3+k4);
        }
    }

    public void apprFunct(Double step, Double x, Double y, Double xf, Double n0, Double nf){
        series3 = new XYSeries("Improved Euler's Method TA Error");
        double n = n0;
        double y0 = y;
        double k1, k2, k3, k4;
        while(n <= nf){
            double max = -1;
            double h = (xf-x)/n;
            y = y0;
            for(double i = x+h; i < xf; i += h){
                k1 = -2*y+4*(i-h);
                k2 = -2*(y+h*k1/2)+4*(i-h/2);
                k3 = -2*(y+h*k2/2)+4*(i-h/2);
                k4 = -2*(y+h*k3)+4*i;
                if(max < (Math.abs((2*i*Math.exp(2*i)-Math.exp(2*i)+(y0*Math.exp(2*x)
                        -2*x*Math.exp(2*x)+Math.exp(2*x)))/Math.exp(2*i)-
                        (y+h/6*(k1+2*k2+2*k3+k4)))))
                    max = Math.abs((2*i*Math.exp(2*i)-Math.exp(2*i)+(y0*Math.exp(2*x)
                            -2*x*Math.exp(2*x)+Math.exp(2*x)))/Math.exp(2*i)-
                            (y+h/6*(k1+2*k2+2*k3+k4)));
                y += h/6*(k1+2*k2+2*k3+k4);
            }
            series3.add(n, max);
            n++;
        }
    }
}
