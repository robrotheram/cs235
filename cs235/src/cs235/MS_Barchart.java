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
import org.jfree.chart.renderer.category.BarRenderer; 
import java.awt.Color; 
import java.awt.Paint;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;

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
     * 
     */
    @Override
    public boolean setChartTitle(String title){
        super.setChartTitle(title);
        return true;
    }
    
    /**
     * A class which constructs a dataset which is usable for the creation
     * of barcharts.
     * @return dataset - the data that will be used in the chart creation
     *  process.
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
     * Accessor method for returning the current colour map being used
     * @return super.getColourMap() 
     */
    @Override
    public MS_ColourMap getColourMap(){
        return super.getColourMap();
        
    }
    
    /**
     * Accessor method for setting the current colour map
     * @param cm - a colour map to be used for this chart
     */
    @Override
    public boolean setColourMap(MS_ColourMap cm){
        super.setColourMap(cm);
        return true;
    }
    
    /**
     * Creates the bar chart
     * @return the chart
     */
    @Override
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
        
        final CategoryPlot plot = CHART.getCategoryPlot(); 
        CategoryItemRenderer renderer = new CustomRenderer(); 
        plot.setRenderer(renderer);
        return CHART;
    }
    
    /**
     * A renderer specific for this type of chart. Sets the colours that will 
     * be used when displaying the chart.
     */
    class CustomRenderer extends BarRenderer { 
        private Paint[] colors;
        public CustomRenderer(){ 
           MS_ColourMap mappedColours = getColourMap();
           this.colors = new Paint[] {
             mappedColours.getColour(1), mappedColours.getColour(2), mappedColours.getColour(3), 
             mappedColours.getColour(4), mappedColours.getColour(5)}; 
        }
        
        /**
         * Accessor method for getting the colours of each column
         * @param row
         * @param column
         * @return the colour of each column 
         */
        @Override
        public Paint getItemPaint(final int row, final int column) { 
           return (this.colors[column % this.colors.length]); 
        } 
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


