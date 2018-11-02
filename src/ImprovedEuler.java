import org.jfree.data.xy.XYSeries;

public class ImprovedEuler implements InterfaceMethods {

    public ImprovedEuler(){}

    //Series of method
    XYSeries series1;
    //Series of method's error
    XYSeries series2;

    public void funct(Float step, Float x, Float y, Float xf){
        //Calculating points that satisfy to Improved Euler's Method and adding them to the series1
        //and points that satisfy to Improved Euler's Method error and adding them to the series2:
        series1 = new XYSeries("Improved Euler's Method");
        series1.add(x, y);
        series2 = new XYSeries("Improved Euler's Method Error");
        series2.add(x, y);
        for(float i = x+step; i < xf; i += step){
            series1.add(i, y+step*(-2*(y+step/2*(-2*y+4*(i-step)))+4*(i-step/2)));
            series2.add(i, Math.abs((2*i-1+Math.exp(-2*i))-(y+step*(-2*(y+step/2*(-2*y+4*(i-step)))+4*(i-step/2)))));
            y += step*(-2*(y+step/2*(-2*y+4*(i-step)))+4*(i-step/2));
        }
    }
}
