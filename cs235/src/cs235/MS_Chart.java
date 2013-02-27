/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Kerryanne Tolhurst
 * The chart class is the abstract super class for all charts.
 * It sets the constructor for all classes and sets all methods needed to be
 * implemented by all subclasses.
 * 
 */
public abstract class MS_Chart extends JPanel {
    
    private MS_DataSet M_DB;
    private final int X_AXISDATAPOSITION;
    private final int Y_AXISDATAPOSITION;
    private String TITLE;
    
    /**
     * constructor setting all class variables needed to create a chart
     * @param db - the data for the chart
     * @param xColumnPos - the column data for the x axis from the dataset
     * @param yColPos - the column data for the y axis from the dataset
     * @param title - chart title
     * @param r - to display the chart in the windows native size
     */
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
    
    /**
     * Abstract class for setting the chart title. 
     */
    boolean setChartTitle(String newTitle){
        TITLE = newTitle;
        return true;
    };
    
    MS_DataSet getDataSet(){
        return M_DB;
    }
    
    int getXColumnPosition(){
        return X_AXISDATAPOSITION;
    }
    
    int getYColumnPosition(){
        return Y_AXISDATAPOSITION;
    }
    
    String getTitle(){
        return TITLE;
    }
    /**
     * Abstract class for converting the program dataset to the format needed to
     * make a chart from it.
     */
    abstract boolean convertDataSet();
    
    /**
     * Abstract class theat returns the array of the current colour map 
     */
    abstract boolean getColourMap();
    
    /**
     * Abstract class that sets the current colour map and carries out any 
     * processing to change the colour of the chart elements
     */
    abstract boolean setColourMap();
    
    /**
     * Abstract class that creates the actual chart 
     */
    abstract public JFreeChart createChart();
    
    /**
     * Creates a chart panel from the chart
     * @return myChart 
     */
    private ChartPanel createPanel(){
        ChartPanel myChart = new ChartPanel(createChart());
           myChart.setMouseWheelEnabled(true);
        return myChart;
    }
    
}
