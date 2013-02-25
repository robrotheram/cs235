/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author Kerryanne Tolhurst
 */
public abstract class MS_Chart extends JPanel {
    private MS_DataSet M_DB;
    
    private final int X_AXISDATAPOSITION;
    private final int Y_AXISDATAPOSITION;
    private final String TITLE;
    
    public MS_Chart(MS_DataSet db,int xColumnPos, int yColPos, String title, Rectangle r){
       M_DB = db;
       X_AXISDATAPOSITION = xColumnPos;
       Y_AXISDATAPOSITION = yColPos;
       TITLE = title;

       this.setBounds(r);
       this.setLayout(new java.awt.BorderLayout());
       this.add(createPanel(),BorderLayout.CENTER);
       this.setVisible(true);
    };
    
    abstract boolean setChartTitle();
    
    abstract boolean convertDataSet();
    
    abstract boolean setColourMap();
    
    abstract public JFreeChart createChart();
    
    private ChartPanel createPanel(){
        ChartPanel myChart = new ChartPanel(createChart());
           myChart.setMouseWheelEnabled(true);
        return myChart;
    }
    
}
