package cs235;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


    /**
     * Creates a new JPanel containing a JTable with all the data form the 
     * MS_dataset
     * @author Alex
     * @date 26.02.2013
     * 
     */
    public class MS_TablePanel extends JPanel {
     
    private MS_DataSet m_DB;

    /**
     *Constructor that makes the chart
     * @param MS_DataSet the Dataset 
     * @param Rectangle r - the size of this Panel 
     */


    public MS_TablePanel(MS_DataSet db, Rectangle r){
       m_DB = db;
       this.setBounds(r);
       this.setLayout(new java.awt.BorderLayout());
       this.add(getTable(),BorderLayout.CENTER);
       this.setVisible(true);
    }
    /**
     * Set the dataset in this class
     * @param MS_DataSet db the dataset reference
     * @return Boolean True if it has been set
     */
    public boolean setDataSet(MS_DataSet db){ 
        m_DB = db;
        return true;
    }
    /**
     * Gets the data set in this class
     * @return  MS_DataSet
     */
    public MS_DataSet getDataSet(){ 
        return m_DB;
    }


    /**
    * Creates a JScrollPanel containing the table with the data already added           
    *
    * @param  null       
    * @return A Constructed JTable ready to be added to this JPanel
    * 
    * 
    */
    public JScrollPane getTable(){
        JTable t = new JTable(m_DB.getDataSet(),m_DB.getColumnNames());
        return  new JScrollPane(t);

    }
    
    
    /**
     * Main Method for testing this class
     * @param String[] Arguments 
     */
   

    public static void main (String[] args){
        
        // check if panel loads with corrct data
        
        try{
            System.out.println("MS_TablePannel.main():Test Load Panel with table");
            final Rectangle FRAMESIZE = new Rectangle(0, 0, 500, 350); 
            // Setting up the frame
            JFrame view = new JFrame("Change me");
            view.setBounds(FRAMESIZE);
            JPanel container = new JPanel();
            container.setBounds(view.getBounds());
            view.add(container);
            File f = new File("/Users/Robert/Desktop/coal.csv");
            MS_DataSet db = new MS_DataSet();
            MS_CSVParser csv = new MS_CSVParser(db,f,",");
            csv.ParseFile();
            MS_TablePanel tP = new MS_TablePanel(db,view.getBounds() );
            container.add(tP);
            container.validate();
            container.repaint();
            view.repaint();
            view.setVisible(true);
             System.out.println("MS_TablePannel.main(): Load Pannel with table"
                    + " failed : "+ e);
        }catch(Exception e){
            System.err.println("MS_TablePannel.main(): Load Pannel with table"
                    + " failed : "+ e);
        }
        
        
        

    }
 

 }
 
