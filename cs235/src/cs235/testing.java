/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Robert Fletcher
 */
public class testing {
    public static void main (String[] args){
        // Setting up the frame
        JFrame view = new JFrame("Change me");
        view.setBounds(0, 0, 500, 350);
        JPanel container = new JPanel();
        container.setBounds(view.getBounds());
        view.add(container);
        File f = new File("C://test/csv.csv");
        MS_DataSet db = new MS_DataSet();
        MS_CSVParser csv = new MS_CSVParser(db,f,",");
        csv.ParseFile();
        
        // change below for you class
        int xColumn = 1;  
        int yColumn = 4; 
        container.add(new exampleChartPaneClass(db,xColumn,yColumn,"title",view.getBounds()));
        
        
        container.validate();
        container.repaint();
        
        view.repaint();
        view.setVisible(true);
        
        
        
        
        
        
        
    }
}
