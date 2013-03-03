package GroupMSApplication;

import java.awt.Color;
import java.awt.Rectangle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

 /**
 *
 * @author William Jones
 * The Line chart class inherits from the chart class.
 * The Line chart class will construct a barchart from a data set to be displayed in the GUI
 */
public class MS_LineChart extends MS_Chart {
    
    /**
     * The constructor will set all class variables for the Line Chart
     * @param db - the data (dataset to be used) for the Line chart
     * @param xColumnPos - the column data for the x axis from the dataset
     * @param yColPos - the column data for the y axis from the dataset
     * @param r - to display the chart in a window of its native size
     * @param cm - to choose the colour of the line
     */
    public MS_LineChart(MS_DataSet db,int xColumnPos, int yColPos, String title, Rectangle r, MS_ColourMap cm){
            super(db, xColumnPos, yColPos, title, r, cm);
                
    }
    
      /**
     * setChartTitle method is used to set the title of the graph
     * @param title - the charts title 
     */   
       
    
    @Override
    public boolean setChartTitle(String title){
            super.setChartTitle(title);
            return true;
    }
  
    
    /**
     * 
     * Abstract class for converting the program dataset to the format needed 
     * to make a chart from it 
     */  
           
    public DefaultCategoryDataset convertDataSet(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        int sum = 0;
        int preval = super.getDataSet().getAtribute(super.getXColumnPosition(), 0).getIntDataAttribute();
        
        for(int i= 0; i < super.getDataSet().getNumOfRows() -1; i++ ){
            if(preval == super.getDataSet().getAtribute(super.getXColumnPosition(), i).getIntDataAttribute()){
                
                 sum += super.getDataSet().getAtribute(super.getYColumnPosition(), i).getIntDataAttribute();
                
            }else{
                
                /**
                 * adds to the dataset the sum value, String value of the number
                 * in the xaxis column of the MS_Dataset
                 */
                
                dataset.addValue(sum, super.getTitle(),Integer.toString(super.getDataSet().getAtribute(
                        super.getXColumnPosition(), (i-1)).getIntDataAttribute()));
                
                sum = super.getDataSet().getAtribute(super.getYColumnPosition(), i).getIntDataAttribute();
                preval = super.getDataSet().getAtribute(super.getXColumnPosition(), i).getIntDataAttribute();
            
            }
        }
        return dataset;
    }
    
    /**
     * Abstract class that returns the array of the current colour map
     */
    @Override
    public MS_ColourMap getColourMap() {
        return super.getColourMap();
       
    }   
       
    /**
     * Abstract class that sets the current colour map and carries out any 
     * processing to change the colour of the chart elements
     */
    @Override
    public boolean setColourMap (MS_ColourMap cm) {
         super.setColourMap(cm);

         return true;
     }    
       
       
 
    /**
     * Abstract class that creates the actual chart 
     */   
    @Override
    public JFreeChart createChart(){
        final JFreeChart chart = ChartFactory.createLineChart(
            super.getTitle(),                                                     // chart title
            super.getDataSet().getAColumnName(super.getXColumnPosition()),        // domain axis label
            super.getDataSet().getAColumnName(super.getYColumnPosition()),        // range axis label
            convertDataSet(),            
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );
        MS_ColourMap mappedColours = getColourMap();
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // customise the renderer...
        LineAndShapeRenderer renderer
        = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setSeriesPaint(0, mappedColours.getColour(0));
        renderer.setFillPaint(Color.WHITE);
    
                    
     /*   final PositionRender rend = chart.getPositionRend;
        PositionItemRenderer renderer = new UniqueRenderer();
        renderer.setRenderer(renderer); */
        return chart; 
    }

 
   
     
        
     /*  class UniqueRenderer extends AbstractCategoryItemRenderer { 
       private Paint[] colors;
       public UniqueRenderer(){ 
       MS_ColourMap mappedColours = getColourMap();
       this.colors = new Paint[] {
       mappedColours.getColour(1), mappedColours.getColour(2), mappedColours.getColour(3), 
       mappedColours.getColour(4), mappedColours.getColour(5)}; 
       } */
        
    
      
    /**
     * Creates a chart panel for the chart
     * @return myChart 
     */
    private  ChartPanel createPanel(){      
         ChartPanel myChart = new ChartPanel(createChart());
         myChart.setMouseWheelEnabled(true);
            
            return myChart;
    }
    
    
    
    
}      
