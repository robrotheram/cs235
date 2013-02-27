/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.Rectangle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
*@author William Bray
* The barchart class inherits from the chart class.
* It constructs a barchart from a data set to be displayed in the GUI
*
*/

public class MS_Barchart extends MS_Chart{
    
     /**
     * constructor setting all class variables needed to create a chart
     * @param db - the data for the chart
     * @param xColumnPos - the column data for the x axis from the dataset
     * @param yColPos - the column data for the y axis from the dataset
     * @param title - chart title
     * @param r - to display the chart in the windows native size
     */
    public MS_Barchart(MS_DataSet db,int xColumnPos, int yColPos, String title, Rectangle r, MS_ColourMap cm){
        super(db, xColumnPos, yColPos, title, r, cm);
        
        
    }
    /**
     * The class for setting the chart title. 
     * @param title - the charts title
     */
    public boolean setChartTitle(String title){
        super.setChartTitle(title);
        return true;
    }
    
    /**
     * Abstract class for converting the program dataset to the format needed to
     * make a chart from it.
     */
    public DefaultCategoryDataset convertDataSet(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        int sum = 0;
        int preval = super.getDataSet().getAtribute(super.getXColumnPosition(), 0).getIntDataAttribute();
        
        for(int i= 0; i < super.getDataSet().getNumOfRows()-1; i++ ){
            if(preval == super.getDataSet().getAtribute(super.getXColumnPosition(), i).getIntDataAttribute()){
                
                sum += super.getDataSet().getAtribute(super.getYColumnPosition(), i).getIntDataAttribute();
                
            }else{
                /**
                 * adds to the dataset the sum value, String value of the number 
                 * in the xaxis column of the MS_DataSet
                 * */
                dataset.addValue(sum, super.getTitle(),Integer.toString(super.getDataSet().getAtribute(
                        super.getXColumnPosition(), (i-1)).getIntDataAttribute()));
                
                sum = super.getDataSet().getAtribute(super.getYColumnPosition(), i).getIntDataAttribute();
                preval = super.getDataSet().getAtribute(super.getXColumnPosition(), i).getIntDataAttribute();
            
            }
        }
        return dataset;
    }
    
    /**
     * Abstract class theat returns the array of the current colour map 
     */
    public MS_ColourMap getColourMap(){
        return super.getColourMap();
        
    }
    
    /**
     * Abstract class that sets the current colour map and carries out any 
     * processing to change the colour of the chart elements
     */
    public boolean setColourMap(MS_ColourMap cm){
        super.setColourMap(cm);
        return true;
    }
    
    /**
     * Abstract class that creates the actual chart 
     */
    public JFreeChart createChart(){
        final JFreeChart CHART = ChartFactory.createBarChart(
            super.getTitle(),         // chart title
            super.getDataSet().getAColumnName(super.getXColumnPosition()),               // domain axis label
            super.getDataSet().getAColumnName(super.getYColumnPosition()),                  // range axis label
            convertDataSet(),            
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );
        
        
        return CHART;
    }
    
    /**
     * Creates a chart panel for the chart
     * @return myChart 
     */
    private ChartPanel createPanel(){
        ChartPanel myChart = new ChartPanel(createChart());
        myChart.setMouseWheelEnabled(true);

        return myChart;
    }
    
}


