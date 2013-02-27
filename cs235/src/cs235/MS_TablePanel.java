package cs235;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * Creates a new JPanel containing a JTable with all the data form the 
 * MS_dataset
 * @author Alex
 * \date 26.02.2013
 */
    public class MS_TablePanel extends JPanel {
    private MS_DataSet m_DB;
    
    private final int X_AXISDATAPOSITION;
    private final int Y_AXISDATAPOSITION;
    private final String TITLE;
    
    
    
    /**
     *Constructor that makes the chart
     * @param MS_DataSet the Dataset 
     * @param int xColPos - postion  in the colum in the MS_DataSet where the x 
     * axis data is
     * @param iny yColPos - postion  in the colum in the MS_DataSet where the y 
     * axis data is
     * @param String title - Title of the chart
     * @param Rectangle r - the size of this Panel 
     */

    
    public MS_TablePanel(
            MS_DataSet db,
            int xColumnPos, 
            int yColPos, 
            String title,
            Rectangle r){
        
       m_DB = db;
       X_AXISDATAPOSITION = xColumnPos;
       Y_AXISDATAPOSITION = yColPos;
       TITLE = title;

       this.setBounds(r);
       this.setLayout(new java.awt.BorderLayout());
       this.add(getTable(),BorderLayout.CENTER);
       this.setVisible(true);
       
    }
    
    
 /**
 * Creates a JScrollPanel containing the table with the data already added           
 *
 * @param  null       
 * @return A Constructed JTable ready to be added to this JPanel
 * 
 * 
 */
    private JScrollPane getTable(){
            JTable t = new JTable(m_DB.getDataSet(),m_DB.getColumnNames());
            return  new JScrollPane(t);
 
 }
 

 }
 
