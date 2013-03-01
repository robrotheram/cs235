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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author Kerryanne Tolhurst
 * Creates a basic GUI 
 */
public class MS_BasicGUI extends JFrame{
    private MS_DataSet m_db;
    private JPanel m_toolPanel, m_displayArea, m_chartOptionContainer,
            m_optionPane, m_optionButtonPane, m_defaultC1, m_defaultC2, 
            m_defaultC3, m_defaultC4, m_defaultC5, m_colour1, m_colour2, 
            m_colour3, m_colour4, m_colour5, m_defaultCPanel, m_colourPanel;
    private JFrame m_chartOptions;
    private JTabbedPane m_displayTabs;
    private JButton m_importData, m_optionsOpen, m_createChart, m_cancelChart, m_saveButton;
    private JRadioButton m_defaultColourRadio, m_colourMap1Radio;
    private JComboBox m_chartType, m_xAxisData, m_yAxisData;
    private JTextField m_chartTitle;
    private Container m_container = getContentPane();;
    private double m_height, m_width;
    private int m_percentageHeight, m_percentageWidth;
    private Rectangle r = new Rectangle();
    private MS_BasicGUI m_Context;
    private Color[] m_defaultColours = {new Color(2,89,89), new Color(0,175,181), 
        new Color(189,51,103), new Color(242,206,22), new Color(204,135,4)};
    private Color[] m_colourMap1 = {new Color(104,186,45), new Color(0,145,63), 
        new Color(0,147,142), new Color(0,147,221), new Color(204,135,4)};
    private MS_ColourMap m_defaultColour;
    private MS_ColourMap m_colourMaps;
    private MS_GUIHandler m_handler = new MS_GUIHandler();
    private String chartTypeState;
    
    public void MS_BasicGUI(){
        //create empty dataset
        m_db = new MS_DataSet();
        m_Context = this;      
        //create container for GUI
        
        //creates a toolkit for our container
        Toolkit m_kit = m_container.getToolkit(); 
        //saves the dimension of users screen size
        Dimension m_screenSize = m_kit.getScreenSize();
        m_container.setPreferredSize(m_screenSize);
        m_container.setLayout(new BoxLayout(m_container, BoxLayout.LINE_AXIS));
        m_height = m_screenSize.getHeight();
        m_width = m_screenSize.getWidth();
        
        //create display area for data and charts
        m_percentageHeight = (int)(Math.round(m_height * 0.90));
        m_percentageWidth = (int) (Math.round(m_width * 0.70));
        m_displayArea = new JPanel();
        m_displayArea.setBackground(new Color(87,87,87));
        m_displayArea.setPreferredSize(new Dimension(m_percentageWidth, m_percentageHeight));
        m_displayTabs = new JTabbedPane();
        m_displayTabs.setPreferredSize(new Dimension(m_percentageWidth, m_percentageHeight));
        m_displayArea.add(m_displayTabs);
        m_displayArea.validate();
        
        //create area for tools
        m_toolPanel = new JPanel();
        
        //create import button
        m_importData = new JButton("Import");
        
        //create create chart button
        m_optionsOpen = new JButton("Create Chart");
        
        //add buttons to the tool panel
        int m_percentageHeight2 = (int)(Math.round(m_height * 0.90));
        int m_percentageWidth2 = (int) (Math.round(m_width * 0.20));
        m_toolPanel.setPreferredSize(new Dimension(m_percentageWidth2, m_percentageHeight2));
        m_toolPanel.setBackground(new Color(87,87,87));
        System.out.println(m_toolPanel.getHeight() + " " + m_toolPanel.getWidth());
        m_toolPanel.add(m_importData);
        m_toolPanel.add(m_optionsOpen);
        
        //add components to the main container
        m_container.add(m_displayArea);
        m_container.add(m_toolPanel);
        
        //add handlers 
        m_importData.addActionListener(m_handler);
        m_optionsOpen.addActionListener(m_handler);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    
    }
    
    private void initOptionPane() {
        //create and set frame size
        m_chartOptions = new JFrame();
        m_chartOptions.setSize(500,400);
        m_chartOptions.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //create main container, set size and layout
        m_chartOptionContainer = new JPanel();
        m_chartOptionContainer.setPreferredSize(new Dimension(500,400));
        m_chartOptionContainer.setLayout(new BoxLayout(m_chartOptionContainer,
                BoxLayout.LINE_AXIS));
        
        //create panel to contain options, set size, and layout
        m_optionPane = new JPanel();
        m_optionPane.setBackground(new Color(87,87,87));
        m_optionPane.setPreferredSize(new Dimension(200,500));
        m_optionPane.setMinimumSize(new Dimension(200,500));
        m_optionPane.setLayout(new BoxLayout(m_optionPane, BoxLayout.Y_AXIS));
        
        //create panel to contain buttons
        m_optionButtonPane = new JPanel();
        m_optionButtonPane.setBackground( new Color(87,87,87));
        m_optionButtonPane.setLayout(new BoxLayout(m_optionButtonPane, BoxLayout.Y_AXIS));
        m_optionButtonPane.setPreferredSize(new Dimension(100,500));
        m_optionButtonPane.setSize(new Dimension(100,500));
        m_optionButtonPane.setMinimumSize(new Dimension(100,500));
        
        //create buttons
        m_createChart = new JButton("Create");
        m_createChart.setAlignmentX(Component.RIGHT_ALIGNMENT);
        m_cancelChart = new JButton("Cancel");
        m_cancelChart.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        //create chart type combo box
        m_chartType = new JComboBox();
        m_chartType.setMaximumSize(new Dimension(110,30));
        m_chartType.addItem("Barchart");
        m_chartType.addItem("Linechart");
        
        //create chart title input field
        m_chartTitle = new JTextField();
        m_chartTitle.setPreferredSize(new Dimension(200,30));
        m_chartTitle.setMaximumSize(new Dimension(200,30));
        m_chartTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //create x and y axis combo boxes
        m_xAxisData = new JComboBox();
        m_xAxisData.setMaximumSize(new Dimension(110,30));
        m_xAxisData.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_yAxisData = new JComboBox();
        m_yAxisData.setMaximumSize(new Dimension(110,30));
        m_yAxisData.setAlignmentX(Component.CENTER_ALIGNMENT);
        //populate combo boxes with values from the table data
        String[] cols = m_db.getColumnNames();
        for(int i =0; i<cols.length; i++){
            m_xAxisData.addItem(cols[i]);
            m_yAxisData.addItem(cols[i]);
        }
        
        //create colour maps
        m_defaultColour = new MS_ColourMap(m_defaultColours);
        m_defaultCPanel = new JPanel();
        m_defaultCPanel.setMaximumSize(new Dimension(250,30));
        m_defaultCPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_defaultCPanel.setLayout(new BoxLayout(m_defaultCPanel, BoxLayout.LINE_AXIS));
        m_defaultColourRadio = new JRadioButton();
        m_defaultColourRadio.setActionCommand("DefaultColour");
        m_defaultCPanel.add(m_defaultColourRadio);
        for (int i = 0; i < m_defaultColour.getNumberOfColours(); i++ ){
            //m_defaultColour.getPanels()[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            m_defaultCPanel.add(m_defaultColour.getPanels()[i]);
            
        }
        
        m_colourMaps = new MS_ColourMap(m_colourMap1);
        m_colourPanel = new JPanel();
        m_colourPanel.setMaximumSize(new Dimension(250,30));
        m_colourPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_colourPanel.setLayout(new BoxLayout(m_colourPanel, BoxLayout.LINE_AXIS));
        m_colourMap1Radio = new JRadioButton();
        m_colourMap1Radio.setActionCommand("ColourMap1");
        m_colourPanel.add(m_colourMap1Radio);
        for (int i = 0; i < m_colourMaps.getNumberOfColours(); i++ ){
            //m_colourMaps.getPanels()[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            m_colourPanel.add(m_colourMaps.getPanels()[i]);
            
        }
        ButtonGroup m_colourScheme = new ButtonGroup();
        m_colourScheme.add(m_defaultColourRadio);
        m_colourScheme.add(m_colourMap1Radio);
        //add components to relevant panels with labels
        JLabel m_chartTypeLabel = new JLabel("Chart Type");
        m_chartTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_chartTypeLabel);
        m_optionPane.add(m_chartType);
        m_optionPane.add(Box.createRigidArea(new Dimension(300, 20)));
        JLabel m_titleLabel = new JLabel("Chart Title");
        m_titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_titleLabel);
        m_optionPane.add(m_chartTitle);
        m_optionPane.add(Box.createRigidArea(new Dimension(300, 20)));
        JLabel m_xAxisLabel = new JLabel("Choose X Axis Data: ");
        m_xAxisLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_xAxisLabel);
        m_optionPane.add(m_xAxisData);
        m_optionPane.add(Box.createRigidArea(new Dimension(300, 20)));
        JLabel m_yAxisLabel = new JLabel("Choose Y Axis Data: ");
        m_yAxisLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_yAxisLabel);
        m_optionPane.add(m_yAxisData);
        m_optionPane.add(Box.createRigidArea(new Dimension(300, 20)));
        JLabel m_defaultColourLabel = new JLabel("Default Colour Map: ");
        m_defaultColourLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_defaultColourLabel);
        m_optionPane.add(m_defaultCPanel);
        m_optionPane.add(Box.createRigidArea(new Dimension(300, 20)));
        JLabel m_colourMap1Label = new JLabel("Colour Map 1: ");
        m_colourMap1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_colourMap1Label);
        m_optionPane.add(m_colourPanel);
        m_optionPane.add(Box.createRigidArea(new Dimension(300, 20)));
        
        //create button pane
        m_optionButtonPane.setBackground(new Color(87,87,87));
        m_optionButtonPane.add(m_createChart);
        m_optionButtonPane.add(m_cancelChart);
        //add the components to the window
        m_chartOptionContainer.add(m_optionPane);
        m_chartOptionContainer.add(m_optionButtonPane);
        m_chartOptions.add(m_chartOptionContainer);
        //pack();
        //setLocationRelativeTo(null);
        //setVisible(true);
        
        m_createChart.addActionListener(m_handler);
        m_cancelChart.addActionListener(m_handler);
        m_defaultColourRadio.addActionListener(m_handler);
        m_colourMap1Radio.addActionListener(m_handler);
    }
    
    public class MS_GUIHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == m_importData){
                //creates a new CSVFileDialog for opening CSV files
                JFrame getFile = new MS_CsvFileDialog(m_db , m_Context);
                getFile.setVisible(true);
                
            }else if(event.getSource() == m_optionsOpen){
                //intialises the components incase the data has changed
                initOptionPane();
                m_chartOptions.setVisible(true);
                    
                
            } else if( event.getSource() == m_createChart){
                // stores the selected indexes of the x and y boxes
                int x = m_xAxisData.getSelectedIndex();
                int y = m_yAxisData.getSelectedIndex();
                //checks for the selected chart type
                if (m_chartType.getSelectedIndex() == 0){
                    //checks which colour scheme is selected
                    if(chartTypeState.equals("DefaultColour")){
                        //creates a barchart with the selected colour scheme
                        MS_Barchart chart = new MS_Barchart(m_db, x, y,
                        m_chartTitle.getText(),r,m_defaultColour);
                        m_displayTabs.addTab( m_chartTitle.getText(), chart);
                        m_chartOptions.setVisible(false);
                        
                    } else if(chartTypeState.equals("ColourMap1")){
                        //creates a barchart with the selected colour scheme
                        MS_Barchart chart = new MS_Barchart(m_db, x, y,
                        m_chartTitle.getText(),r,m_colourMaps);
                        m_displayTabs.addTab( m_chartTitle.getText(), chart);
                        m_chartOptions.setVisible(false);
                    }
                } else if(m_chartType.getSelectedIndex() == 1){
                    //checks which colour scheme is selected
                    if(chartTypeState.equals("DefaultColour")){
                        //creates a barchart with the selected colour scheme
                        MS_LineChart chart = new MS_LineChart(m_db, x, y,
                        m_chartTitle.getText(),r,m_defaultColour);
                        m_displayTabs.addTab( m_chartTitle.getText(), chart);
                        m_chartOptions.setVisible(false);
                        
                    } else if(chartTypeState.equals("ColourMap1")){
                        //creates a barchart with the selected colour scheme
                        MS_LineChart chart = new MS_LineChart(m_db, x, y,
                        m_chartTitle.getText(),r,m_colourMaps);
                        m_displayTabs.addTab(m_chartTitle.getText(), chart);
                        m_chartOptions.setVisible(false);
                    }
                }
                
                
            } else if(event.getSource() == m_cancelChart){
                
            } else if (event.getSource() == m_defaultColourRadio){
                //sets which radio button is checked
                chartTypeState = event.getActionCommand();
            } else if (event.getSource() == m_colourMap1Radio){
                //sets which radio button is checked
                chartTypeState = event.getActionCommand();
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
