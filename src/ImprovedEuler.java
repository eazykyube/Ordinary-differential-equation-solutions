import org.jfree.data.xy.XYSeries;

public class ImprovedEuler implements InterfaceMethods {

    public ImprovedEuler(){}

    //Series of method
    XYSeries series1;
    //Series of method's error
    XYSeries series2;
    //Series of method's approximation error
    XYSeries series3;

    public void funct(Double step, Double x, Double y, Double xf){
        //Calculating points that satisfy to Improved Euler's Method and adding them to the series1
        //and points that satisfy to Improved Euler's Method error and adding them to the series2:
        series1 = new XYSeries("Improved Euler's Method");
        series1.add(x, y);
        series2 = new XYSeries("Improved Euler's Method Error");
        series2.add(x, y);
        double y0 = y;
        for(double i = x+step; i < xf; i += step){
            series1.add(i, y+step*(-2*(y+step/2*(-2*y+4*(i-step)))+4*(i-step/2)));
            series2.add(i, Math.abs((2*i*Math.exp(2*i)-Math.exp(2*i)+(y0*Math.exp(2*x)
                    -2*x*Math.exp(2*x)+Math.exp(2*x)))/Math.exp(2*i)-
                    (y+step*(-2*(y+step/2*(-2*y+4*(i-step)))+4*(i-step/2)))));
            y += step*(-2*(y+step/2*(-2*y+4*(i-step)))+4*(i-step/2));
        }
    }

    public void apprFunct(Double step, Double x, Double y, Double xf, Double n0, Double nf){
        series3 = new XYSeries("Improved Euler's Method TA Error");
        double n = n0;
        double y0 = y;
        while(n <= nf){
            double max = -1;
            double h = (xf-x)/n;
            y = y0;
            for(double i = x+h; i < xf; i += h){
                if(max < Math.abs((2*i*Math.exp(2*i)-Math.exp(2*i)+(y0*Math.exp(2*x)
                        -2*x*Math.exp(2*x)+Math.exp(2*x)))/Math.exp(2*i)-
                        (y+h*(-2*(y+h/2*(-2*y+4*(i-h)))+4*(i-h/2)))))
                    max = Math.abs((2*i*Math.exp(2*i)-Math.exp(2*i)+(y0*Math.exp(2*x)
                            -2*x*Math.exp(2*x)+Math.exp(2*x)))/Math.exp(2*i)-
                            (y+h*(-2*(y+h/2*(-2*y+4*(i-h)))+4*(i-h/2))));
                y += h*(-2*(y+h/2*(-2*y+4*(i-h)))+4*(i-h/2));
            }
            series3.add(n, max);
            n++;
        }
    }
}
