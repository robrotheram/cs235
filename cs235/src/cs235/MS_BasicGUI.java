/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.BorderLayout;
import java.awt.Container;
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
    JPanel toolPanel, displayArea, optionPane;
    JFrame chartOptions;
    JTabbedPane displayTabs;
    JButton importData, createChart, saveButton;
    Container container;
    
    MS_BasicGUI(){
        //create container for GUI
        container = getContentPane();
        container.setLayout(new BorderLayout());
        
        //create display area for data and charts
        displayArea = new JPanel();
        displayTabs = new JTabbedPane();
        displayArea.add(displayTabs);
        
        //create area for tools
        toolPanel = new JPanel();
        
        //create import button
        importData = new JButton("Import");
        
        //create create chart button
        createChart = new JButton("Create Chart");
        
        //add buttons to the tool panel
        toolPanel.add(importData);
        toolPanel.add(createChart);
        
        //add components to the main container
        container.add(displayArea);
        container.add(toolPanel);
        
        //create chart option window
        chartOptions = new JFrame();
        optionPane = new JPanel();
        chartOptions.add(optionPane);
        
        //add handlers 
        MS_GUIHandler handler = new MS_GUIHandler();
        importData.addActionListener(handler);
        createChart.addActionListener(handler);
        
    }
    
}
