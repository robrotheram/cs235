/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *An Example Class that extends from JPanel that makes only a barchart
 * @author Robert
 */
public class exampleChartPaneClass extends JPanel {
    private MS_DataSet M_DB;
    
    private final int X_AXISDATAPOSITION;
    private final int Y_AXISDATAPOSITION;
    private final String TITLE;
    
    
    
    /**
     *Constructor that makes the chart
     * @param MS_DataSet the Dataset 
     * @param int xColPos - postion  in the colum in the MS_DataSet where the x axis data is
     * @param iny yColPos - postion  in the colum in the MS_DataSet where the y axis data is
     * @param String title - Title of the chart
     * @param Rectangle r - the size of this Panel 
     */
    
    public exampleChartPaneClass(MS_DataSet db,int xColumnPos, int yColPos, String title, Rectangle r){
       M_DB = db;
       X_AXISDATAPOSITION = xColumnPos;
       Y_AXISDATAPOSITION = yColPos;
       TITLE = title;

       this.setBounds(r);
       this.setLayout(new java.awt.BorderLayout());
       this.add(createPanel(),BorderLayout.CENTER);
       this.setVisible(true);
       
       System.out.println("stuff has happend");
    }
    
    
    /**
 * Returns a Chart Panel              

 *
 * @param  null       
 * @return A Constructed chartPanel ready to be added to this JPanel
 * 
 * 
 */
    private  ChartPanel createPanel(){
            
           ChartPanel myChart = new ChartPanel(createBarChart());
           myChart.setMouseWheelEnabled(true);
            
            return myChart;
    }
    
    
/**
 * Returns a completed chart with all the fields filed out.       

 *
 * @param  null       
 * @return JFreeChart char - A Constructed Bar Chart
 * 
 **/
    
    public JFreeChart createBarChart(){
        final JFreeChart chart = ChartFactory.createBarChart(
            TITLE,         // chart title
            M_DB.getAColumnName(X_AXISDATAPOSITION),               // domain axis label
            M_DB.getAColumnName(Y_AXISDATAPOSITION),                  // range axis label
            createDataSet(),                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );
        return chart;
    }
    
    
/**
 * Function that loops through the MS_DataSet adding up all values that 
 * Correspond to the xaxisfeild and adds it to the dataset
 *
 * @param  null       
 * @return DefaultCategoryDataset createDataSet 
 * A Constructed chartPanel ready to be added to this JPanel
 * 
 * */
 
    
    
    public DefaultCategoryDataset createDataSet(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        int sum = 0;
        int preval = M_DB.getAtribute(X_AXISDATAPOSITION, 0).getIntDataAttribute();
        
        for(int i= 0; i < M_DB.getNumOfRows()-1; i++ ){
            if(preval == M_DB.getAtribute(X_AXISDATAPOSITION, i).getIntDataAttribute()){
                
                sum += M_DB.getAtribute(Y_AXISDATAPOSITION, i).getIntDataAttribute();
                
            }else{
                /**
                 * adds to the dataset the sum value, String value of the number 
                 * in the xaxis column of the MS_DataSet
                 * */
                dataset.addValue(sum, TITLE,Integer.toString(M_DB.getAtribute(
                        X_AXISDATAPOSITION, (i-1)).getIntDataAttribute()));
                
                sum = M_DB.getAtribute(Y_AXISDATAPOSITION, i).getIntDataAttribute();
                preval = M_DB.getAtribute(X_AXISDATAPOSITION, i).getIntDataAttribute();
            
            }
        }
        
        
        return dataset;
    }
     
    
    
            
     
    
    
}
