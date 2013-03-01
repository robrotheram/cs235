/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.BorderLayout;
import java.awt.Rectangle;
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
    
    private MS_DataSet m_db;
    private int m_xAxisDataPosition;
    private int m_yAxisDataPosition;
    private String m_title;
    private MS_ColourMap m_cm;
    
    /**
     * Allows access for setting the dataset
     * 
     * @param db dataset to be set
     * @return returns true is set is successful
     */
    boolean setData(MS_DataSet db){
        m_db = db;
        return true;
    }
    
    /**
     * Allows access for setting the x axis data to be used for making charts
     * 
     * @param xData
     * @return 
     */
    boolean setXData(int xData){
        m_xAxisDataPosition = xData;
        return true;
    }
    
    /**
     * Allows access for setting the y axis data to be used for making charts
     * 
     * @param yData
     * @return 
     */
    boolean setYData(int yData){
        m_yAxisDataPosition = yData;
        return true;
    }
    
    /**
     * Allows setting the chart title. 
     */
    boolean setChartTitle(String newTitle){
        m_title = newTitle;
        return true;
    };
    
    /**
     * Abstract class that sets the current colour map and carries out any 
     * processing to change the colour of the chart elements
     */
    boolean setColourMap(MS_ColourMap colourMap){
        m_cm = colourMap;
        
        return true;
    };
    
    /**
     * allows access to the dataset if needed
     * @return M_DB
     */
    MS_DataSet getDataSet(){
        return m_db;
    }
    
    /**
     * allows access to the data for x axis if needed
     * @return X_AXISDATAPOSITION
     */
    int getXColumnPosition(){
        return m_xAxisDataPosition;
    }
    
    /**
     * allows access to the data for y axis if needed
     * @return Y_AXISDATAPOSITION
     */
    int getYColumnPosition(){
        return m_yAxisDataPosition;
    }
    
    /**
     * allows access to the chart title if needed
     * @return m_title
     */
    String getTitle(){
        return m_title;
    }
    
    /**
     * Abstract class theat returns the array of the current colour map 
     */
    MS_ColourMap getColourMap(){
        return m_cm;
    };
    
    /**
     * constructor setting all class variables needed to create a chart
     * @param db - the data for the chart
     * @param xColumnPos - the column data for the x axis from the dataset
     * @param yColPos - the column data for the y axis from the dataset
     * @param title - chart title
     * @param r - to display the chart in the windows native size
     */
    public MS_Chart(MS_DataSet db,int xColumnPos, int yColumnPos, String title, 
            Rectangle r, MS_ColourMap cm){
       if(setData(db)){
           System.out.println("MS_Chart().setData(): Successful");
       } else {
           System.err.println("MS_Chart().setData(): Failed");
       }
       
       if(setXData(xColumnPos)){
           System.out.println("MS_Chart().setXData(): Successful");
       } else {
           System.err.println("MS_Chart().setXData(): Failed");
       }
       
       if(setYData(yColumnPos)){
           System.out.println("MS_Chart().setYData(): Successful");
       } else {
           System.err.println("MS_Chart().setYData(): Failed");
       }
       
       if(setChartTitle(title)){
           System.out.println("MS_Chart().setChartTitle(): Successful");
       } else {
           System.err.println("MS_Chart().setChartTitle(): Failed");
       }
       
       if(setData(db)){
           System.out.println("MS_Chart().setData(): Successful");
       } else {
           System.err.println("MS_Chart().setData(): Failed");
       }

       this.setBounds(r);
       this.setLayout(new java.awt.BorderLayout());
       this.add(createPanel(),BorderLayout.CENTER);
       this.setVisible(true);
    };
    
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
