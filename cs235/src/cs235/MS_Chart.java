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

/**
 *
 * @author vampie
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
    
    boolean setTimeDate(Date currentDate){
    
        return true;
    };
    
    boolean setAuthor(String description){
        
        return true;
    };
    
    boolean setDescription(String description){
        return true;
    };
    
    boolean setChartTitle(String chartTitle){
        return true;
    };
    
    boolean setHeight(int height){
        return true;
    };
    
    boolean setWidth(int width){
        return true;
    };
    
    boolean setPostion(int x, int y){
        return true;
    };
    
    boolean convertDataSet(int[] dataSet){
        return true;
    };
    
    boolean convertDataSet(double[] dataSet){
        return true;
    };
    
    boolean convertDataSet(String[] dataSet){
        return true;
    };
    
    boolean createChart(){
        return true;
    };
    
    private ChartPanel createPanel(){
        ChartPanel myChart = new ChartPanel(createChart());
           myChart.setMouseWheelEnabled(true);
        return myChart;
    }
}
