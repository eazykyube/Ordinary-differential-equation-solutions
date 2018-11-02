import org.jfree.chart.ChartPanel;
import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Zoom {

    public Zoom(){}

    //Function that works when mouse wheel makes an action:
    public void addListener(ChartPanel chartPanel) {
        chartPanel.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getScrollType() != MouseWheelEvent.WHEEL_UNIT_SCROLL) return;
                if (e.getWheelRotation()< 0)
                    increaseZoom((ChartPanel)e.getComponent(), true);
                else
                    decreaseZoom((ChartPanel)e.getComponent(), true);
            }
        });
    }

    //Function for increasing zoom:
    public synchronized void increaseZoom(JComponent chart, boolean saveAction){
        ChartPanel ch = (ChartPanel)chart;
        zoomChartAxis(ch, true);
    }

    //Function for decreasing zoom:
    public synchronized void decreaseZoom(JComponent chart, boolean saveAction){
        ChartPanel ch = (ChartPanel)chart;
        zoomChartAxis(ch, false);
    }

    //Function used by two previous functions:
    private void zoomChartAxis(ChartPanel chartP, boolean increase){
        int width = chartP.getMaximumDrawWidth() - chartP.getMinimumDrawWidth();
        int height = chartP.getMaximumDrawHeight() - chartP.getMinimumDrawWidth();
        if(increase){
            chartP.zoomInBoth(width/2, height/2);
        }else{
            chartP.zoomOutBoth(width/2, height/2);
        }
    }
}
