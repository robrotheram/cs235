/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Kerryanne Tolhurst
 * Creates a basic GUI 
 */
public class MS_BasicGUI extends JFrame{
    private MS_DataSet m_db;
    JPanel m_toolPanel, m_displayArea, m_optionPane;
    JFrame m_chartOptions;
    JTabbedPane m_displayTabs;
    JButton m_importData, m_createChart, m_saveButton;
    Container m_container;
    
    MS_BasicGUI(){
        //create empty dataset
        m_db = new MS_DataSet();
        
        //create container for GUI
        m_container = getContentPane();
        m_container.setLayout(new BorderLayout());
        
        //create display area for data and charts
        m_displayArea = new JPanel();
        m_displayTabs = new JTabbedPane();
        m_displayArea.add(m_displayTabs);
        
        //create area for tools
        m_toolPanel = new JPanel();
        
        //create import button
        m_importData = new JButton("Import");
        
        //create create chart button
        m_createChart = new JButton("Create Chart");
        
        //add buttons to the tool panel
        m_toolPanel.add(m_importData);
        m_toolPanel.add(m_createChart);
        
        //add components to the main container
        m_container.add(m_displayArea);
        m_container.add(m_toolPanel);
        
        //create chart option window
        m_chartOptions = new JFrame();
        m_optionPane = new JPanel();
        m_chartOptions.add(m_optionPane);
        
        //add handlers 
        MS_GUIHandler m_handler = new MS_GUIHandler();
        m_importData.addActionListener(m_handler);
        m_createChart.addActionListener(m_handler);
        
    }
    
    public class MS_GUIHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == m_importData){
                JFrame getFile = new MS_CsvFileDialog(m_db);
                getFile.setVisible(true);
                System.out.println("button pressed");
            }else if(event.getSource() == m_createChart){
                m_chartOptions.setVisible(true);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
 
       MS_BasicGUI example1 = new MS_BasicGUI();
       example1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       example1.setVisible(true);
    }
}
