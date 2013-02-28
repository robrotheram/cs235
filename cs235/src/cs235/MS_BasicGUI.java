/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

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
    JComboBox m_xAxisData, m_yAxisData;
    JTextField m_chartTitle;
    Container m_container = getContentPane();;
    double m_height, m_width;
    int m_percentageHeight, m_percentageWidth;
    Rectangle r = new Rectangle();
    private MS_BasicGUI m_Context;
    
    public void MS_BasicGUI(){
        //create empty dataset
        m_db = new MS_DataSet();
        m_Context = this;      
        //create container for GUI
        
        //creates a toolkit for our container
        Toolkit m_kit = m_container.getToolkit(); 
        //saves the dimension of users screen size
        Dimension m_screenSize = m_kit.getScreenSize();
        System.out.println(m_screenSize);
        m_container.setPreferredSize(m_screenSize);
        m_container.setLayout(new BoxLayout(m_container, BoxLayout.LINE_AXIS));
        m_height = m_screenSize.getHeight();
        m_width = m_screenSize.getWidth();
        System.out.println(m_width + " " + m_height);
        
        
        //create display area for data and charts
        m_percentageHeight = (int)(Math.round(m_height * 0.90));
        m_percentageWidth = (int) (Math.round(m_width * 0.70));
        System.out.println(m_percentageWidth);
        m_displayArea = new JPanel();
        m_displayArea.setBackground(Color.yellow);
        m_displayArea.setPreferredSize(new Dimension(m_percentageWidth, m_percentageHeight));
        System.out.println(m_displayArea.getHeight() + " " +  m_displayArea.getWidth());
        m_displayTabs = new JTabbedPane();
        m_displayTabs.setPreferredSize(new Dimension(m_percentageWidth, m_percentageHeight));
        m_displayArea.add(m_displayTabs);
        m_displayArea.validate();
        
        //create area for tools
        m_toolPanel = new JPanel();
        
        //create import button
        m_importData = new JButton("Import");
        
        //create create chart button
        m_createChart = new JButton("Create Chart");
        
        //add buttons to the tool panel
        int m_percentageHeight2 = (int)(Math.round(m_height * 0.90));
        int m_percentageWidth2 = (int) (Math.round(m_width * 0.20));
        m_toolPanel.setPreferredSize(new Dimension(m_percentageWidth2, m_percentageHeight2));
        m_toolPanel.setBackground(Color.RED);
        System.out.println(m_toolPanel.getHeight() + " " + m_toolPanel.getWidth());
        m_toolPanel.add(m_importData);
        m_toolPanel.add(m_createChart);
        
        //add components to the main container
        m_container.add(m_displayArea);
        m_container.add(m_toolPanel);
        
        //create chart option window
        m_chartOptions = new JFrame();
        m_optionPane = new JPanel();
        m_chartTitle = new JTextField();
        m_xAxisData = new JComboBox();
        m_yAxisData = new JComboBox();
        
        m_chartOptions.add(m_optionPane);
        
        //add handlers 
        MS_GUIHandler m_handler = new MS_GUIHandler();
        m_importData.addActionListener(m_handler);
        m_createChart.addActionListener(m_handler);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    public class MS_GUIHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == m_importData){
                //creates a new CSVFileDialog for opening CSV files
                JFrame getFile = new MS_CsvFileDialog(m_db ,m_Context );
                getFile.setVisible(true);
                
            }else if(event.getSource() == m_createChart){
                m_chartOptions.setVisible(true);
            }
        }
    }
    public boolean displayTable(){
                r =  new Rectangle(0,0,m_displayArea.getWidth(),m_displayArea.getWidth());
                JPanel tp  = new MS_TablePanel(m_db, r);
                m_displayTabs.addTab("Data", tp);
                return true;
    }
    /** 
     * Sets the context of this class to a context
     * @param  MS_BasicGUI context of this class
     */
    public void setContext(MS_BasicGUI con){
        m_Context = con;
    }
    
    /**
     * Gets the Context of this class
     * @return MS_BasicGUI 
     */
    public MS_BasicGUI getContext(){
        return this;
    }
    
    public static void main(String[] args) throws IOException {
 
       MS_BasicGUI example1 = new MS_BasicGUI();
       example1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       example1.MS_BasicGUI();
    }
}
