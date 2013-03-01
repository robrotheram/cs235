/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.Color; 
import java.awt.Paint;
import java.awt.Rectangle;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
*@author William Bray
* The barchart class inherits from the chart class.
* It constructs a barchart from a data set to be displayed in the GUI
*
*/

public class MS_Barchart extends MS_Chart{
    
    /**
     * Accessor method for returning the current colour map being used
     * @return MS_ColourMap super.getColourMap() 
     */
    @Override
    public MS_ColourMap getColourMap(){
        return super.getColourMap();
        
    }    
    
    /**
     * The class for setting the chart title. 
     * @param title - the charts title
     * @return boolean - to test whether the assignment was successful
     * 
     */
    @Override
    public boolean setChartTitle(String title){
        super.setChartTitle(title);
        return true;
    }

    /**
     * Accessor method for setting the current colour map
     * @param cm - a colour map to be used for this chart
     * @return boolean
     */
    @Override
    public boolean setColourMap(MS_ColourMap cm){
        super.setColourMap(cm);
        return true;
    }
    
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
     * A class which constructs a dataset which is usable for the creation
     * of barcharts.
     * @return DefaultCatagoryDataset dataset - the data that will be used in 
     * the chart creation process.
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
     * Creates the bar chart using the other methods
     * @return JFreeChart - the chart 
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
        CategoryItemRenderer renderer = new MS_CustomRenderer(); 
        plot.setRenderer(renderer);
        return CHART;
    }
    
    
    
    /**
     * A renderer specific for this type of chart. Sets the colours that will 
     * be used when displaying the chart.
     */
    class MS_CustomRenderer extends BarRenderer { 
        private Paint[] colors;
        
        /**
         * The constructor for the custom renderer, which will set the colours
         * of the bars
         */
        public MS_CustomRenderer(){ 
           MS_ColourMap mappedColours = getColourMap();
           this.colors = new Paint[] {
             mappedColours.getColour(0), mappedColours.getColour(1), mappedColours.getColour(2), 
             mappedColours.getColour(3), mappedColours.getColour(4)}; 
        }
        
        /**
         * Accessor method for getting the colours of each column
         * @param row - the identifier of the row it is returning
         * @param column - the identifier of the column it is returning
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
    
    
    
    //TESTING CODE BEYOND THIS POINT
    //TURN AROUND NOW!
    
    //This code will create a basic chart, testing the various methods 
    
    
    public static void main (String[] args){
        // Setting up the frame
        JFrame view = new JFrame("Change me");
        view.setBounds(0, 0, 500, 350);
        JPanel container = new JPanel();
        container.setBounds(view.getBounds());
        view.add(container);
        File f = new File("C:/Users/William/Dropbox/csv.csv");
        MS_DataSet db = new MS_DataSet();
        MS_CSVParser csv = new MS_CSVParser(db,f,",");
        csv.ParseFile();
        Color[] colorarray = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE} ;
        
        MS_ColourMap cm = new MS_ColourMap(colorarray);
        
        // change below for you class
        int xColumn = 1;  
        int yColumn = 4; 
        
        // MS_Barchart(MS_DataSet db,int xColumnPos, int yColPos, String title, Rectangle r, MS_ColourMap cm){
        
        MS_Barchart bc = new MS_Barchart(db,xColumn,yColumn,"title",view.getBounds(), cm);
        container.add(bc);
        
        
        container.validate();
        container.repaint();
        
        view.repaint();
        view.setVisible(true);
        
        //Testing individual methods
        System.out.println("///////////////BEGIN TESTING\\\\\\\\\\\\\\\\");
        
        //Colour map testing
        System.out.println("Testing getColourMap");
        System.out.println("Colour map data: " + bc.getColourMap().toString(bc.getColourMap()));
        System.out.println("New colour map with different colours");
        Color[] ca = {Color.BLACK, Color.RED, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.WHITE};
        MS_ColourMap testCM = new MS_ColourMap(ca);
        bc.setColourMap(testCM);
        System.out.println("New colour map data: " + bc.getColourMap().toString(bc.getColourMap()));
        
        //Title setting and getting test
        System.out.println("Getting default title with getTitle");
        System.out.println("Original title is: " + bc.getTitle());
        System.out.println("Testing setChartTitle with 'New Title'");
        bc.setChartTitle("New Title");
        System.out.println("Checking title set with getTitle");
        System.out.println("New title is: " + bc.getTitle());
        
        
        
    }
    

            
    
}


