/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Kerryanne Tolhurst
 * Creates a basic GUI 
 */
public class MS_BasicGUI extends JFrame{
    private Container m_container = getContentPane();
    
    private final Toolkit KIT = m_container.getToolkit();
    private final Dimension SCREENSIZE = KIT.getScreenSize();
    private double m_height = SCREENSIZE.getHeight();
    private double m_width = SCREENSIZE.getWidth();
    private final int PERCENTAGEHEIGHT = (int)(Math.round(m_height * 0.90));
    private final int PERCENTAGEWIDTH = (int) (Math.round(m_width * 0.70));
    private final int PERCENTAGEWIDTH2 = (int) (Math.round(m_width * 0.20));
    private final Dimension DISPLAYSIZE = new Dimension(PERCENTAGEWIDTH, 
            PERCENTAGEHEIGHT);
    private final Dimension TOOLPANELSIZE = new Dimension(PERCENTAGEWIDTH2, 
            PERCENTAGEHEIGHT);
    private final Dimension SPACER = new Dimension(300,20);
    private final Dimension OPTIONPANELSIZE = new Dimension (500,400);
    private final Dimension FIELDSIZE = new Dimension(200,30);
    private final Dimension COMBOSIZE = new Dimension(110,30);
    private final Dimension OPTIONPANESIZE = new Dimension(200,500);
    private final Dimension BUTTONPANESIZE = new Dimension(100,500);
    private final Dimension COLOURMAPSIZE = new Dimension(250,30);
    private final Color WINDOWCOLOUR = new Color(87,87,87);
    private MS_DataSet m_db;
    private JLabel m_chartTypeLabel, m_titleLabel, m_xAxisLabel, m_yAxisLabel,
            m_defaultColourLabel, m_colourMap1Label;
    private JPanel m_toolPanel, m_displayArea, m_chartOptionContainer,
            m_optionPane, m_optionButtonPane, m_defaultC1, m_defaultC2, 
            m_defaultC3, m_defaultC4, m_defaultC5, m_colour1, m_colour2, 
            m_colour3, m_colour4, m_colour5, m_defaultCPanel, m_colourPanel;
    private JFrame m_chartOptions, m_warning;
    private JTabbedPane m_displayTabs;
    private JButton m_importData, m_optionsOpen, m_createChart, m_cancelChart, 
            m_saveButton;
    private JRadioButton m_defaultColourRadio, m_colourMap1Radio;
    private JComboBox m_chartType, m_xAxisData, m_yAxisData;
    private JTextField m_chartTitle;
    private int i;
    private Rectangle r = new Rectangle();
    private MS_BasicGUI m_Context;
    private Color[] m_defaultColours = {new Color(2,89,89), new Color(0,175,181), 
        new Color(189,51,103), new Color(242,206,22), new Color(204,135,4)};
    private Color[] m_colourMap1 = {new Color(104,186,45), new Color(0,145,63), 
        new Color(0,147,142), new Color(0,147,221), new Color(204,135,4)};
    private MS_ColourMap m_defaultColour;
    private MS_ColourMap m_colourMaps;
    private MS_GUIHandler m_handler = new MS_GUIHandler();
    private ButtonGroup m_colourScheme;
    private String m_chartTypeState;
    private String[] m_cols;
    
    /**
     * constructor that sets up the GUI
     */
    public MS_BasicGUI(){
        //sets program to exit if GUI is closed
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //create empty dataset
        m_db = new MS_DataSet();
        m_Context = this;      
        
        //create container for GUI
        m_container.setPreferredSize(SCREENSIZE);
        m_container.setLayout(new BoxLayout(m_container, BoxLayout.LINE_AXIS));
        
        //create display area for data and charts
        m_displayArea = new JPanel();
        m_displayArea.setBackground(WINDOWCOLOUR);
        m_displayArea.setPreferredSize(DISPLAYSIZE);
        m_displayTabs = new JTabbedPane();
        m_displayTabs.setPreferredSize(DISPLAYSIZE);
        m_displayArea.add(m_displayTabs);
        m_displayArea.validate();
        
        //create area for tools
        m_toolPanel = new JPanel();
        
        //create import button
        m_importData = new JButton("Import");
        
        //create create chart button
        m_optionsOpen = new JButton("Create Chart");
        
        //add buttons to the tool panel
        m_toolPanel.setPreferredSize(TOOLPANELSIZE);
        m_toolPanel.setBackground(WINDOWCOLOUR);
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
    /**
     * Option pane builder
     */
    private void InitOptionPane() {
        //create and set frame size
        m_chartOptions = new JFrame();
        m_chartOptions.setSize(OPTIONPANELSIZE);
        m_chartOptions.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //create main container, set size and layout
        m_chartOptionContainer = new JPanel();
        m_chartOptionContainer.setPreferredSize(OPTIONPANELSIZE);
        m_chartOptionContainer.setLayout(new BoxLayout(m_chartOptionContainer,
                BoxLayout.LINE_AXIS));
        m_chartOptionContainer.setBackground(WINDOWCOLOUR);
        
        
        //create panel to contain options, set size, and layout
        m_optionPane = new JPanel();
        m_optionPane.setBackground(WINDOWCOLOUR);
        m_optionPane.setPreferredSize(OPTIONPANESIZE);
        m_optionPane.setMinimumSize(OPTIONPANESIZE);
        m_optionPane.setLayout(new BoxLayout(m_optionPane, BoxLayout.Y_AXIS));
        
        //create panel to contain buttons
        m_optionButtonPane = new JPanel();
        m_optionButtonPane.setBackground( WINDOWCOLOUR);
        m_optionButtonPane.setLayout(new BoxLayout(m_optionButtonPane, BoxLayout.Y_AXIS));
        m_optionButtonPane.setPreferredSize(BUTTONPANESIZE);
        m_optionButtonPane.setSize(BUTTONPANESIZE);
        m_optionButtonPane.setMinimumSize(BUTTONPANESIZE);
        
        //create buttons
        m_createChart = new JButton("Create");
        m_createChart.setAlignmentX(Component.RIGHT_ALIGNMENT);
        m_cancelChart = new JButton("Cancel");
        m_cancelChart.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        //create chart type combo box
        m_chartType = new JComboBox();
        m_chartType.setMaximumSize(COMBOSIZE);
        m_chartType.addItem("Barchart");
        m_chartType.addItem("Linechart");
        
        //create chart title input field
        m_chartTitle = new JTextField();
        m_chartTitle.setPreferredSize(FIELDSIZE);
        m_chartTitle.setMaximumSize(FIELDSIZE);
        m_chartTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //create x and y axis combo boxes
        m_xAxisData = new JComboBox();
        m_xAxisData.setMaximumSize(COMBOSIZE);
        m_xAxisData.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_yAxisData = new JComboBox();
        m_yAxisData.setMaximumSize(COMBOSIZE);
        
        m_yAxisData.setAlignmentX(Component.CENTER_ALIGNMENT);
        //populate combo boxes with values from the table data
        m_cols = m_db.getColumnNames();
        for(i =0; i < m_cols.length; i++){
            m_xAxisData.addItem(m_cols[i]);
            m_yAxisData.addItem(m_cols[i]);
        }
        
        //create colour maps
        m_defaultColour = new MS_ColourMap(m_defaultColours);
        m_defaultCPanel = new JPanel();
        m_defaultCPanel.setMaximumSize(COLOURMAPSIZE);
        m_defaultCPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_defaultCPanel.setLayout(new BoxLayout(m_defaultCPanel, BoxLayout.LINE_AXIS));
        m_defaultColourRadio = new JRadioButton();
        m_defaultColourRadio.setActionCommand("DefaultColour");
        m_defaultCPanel.add(m_defaultColourRadio);
        for (i = 0; i < m_defaultColour.getNumberOfColours(); i++ ){
            //m_defaultColour.getPanels()[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            m_defaultCPanel.add(m_defaultColour.getPanels()[i]);
            
        }
        
        m_colourMaps = new MS_ColourMap(m_colourMap1);
        m_colourPanel = new JPanel();
        m_colourPanel.setMaximumSize(COLOURMAPSIZE);
        m_colourPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_colourPanel.setLayout(new BoxLayout(m_colourPanel, BoxLayout.LINE_AXIS));
        m_colourMap1Radio = new JRadioButton();
        m_colourMap1Radio.setActionCommand("ColourMap1");
        m_colourPanel.add(m_colourMap1Radio);
        //adds the colourmap panels do the interface
        for (i = 0; i < m_colourMaps.getNumberOfColours(); i++ ){
            
            m_colourPanel.add(m_colourMaps.getPanels()[i]);
            
        }
        
        //create button group for radio buttons
        m_colourScheme = new ButtonGroup();
        m_colourScheme.add(m_defaultColourRadio);
        m_colourScheme.add(m_colourMap1Radio);
        
        //add components to relevant panels with labels
        m_chartTypeLabel = new JLabel("Chart Type");
        m_chartTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_chartTypeLabel);
        m_optionPane.add(m_chartType);
        m_optionPane.add(Box.createRigidArea(SPACER));
        m_titleLabel = new JLabel("Chart Title");
        m_titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_titleLabel);
        m_optionPane.add(m_chartTitle);
        m_optionPane.add(Box.createRigidArea(SPACER));
        m_xAxisLabel = new JLabel("Choose X Axis Data: ");
        m_xAxisLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_xAxisLabel);
        m_optionPane.add(m_xAxisData);
        m_optionPane.add(Box.createRigidArea(SPACER));
        m_yAxisLabel = new JLabel("Choose Y Axis Data: ");
        m_yAxisLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_yAxisLabel);
        m_optionPane.add(m_yAxisData);
        m_optionPane.add(Box.createRigidArea(SPACER));
        m_defaultColourLabel = new JLabel("Default Colour Map: ");
        m_defaultColourLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_defaultColourLabel);
        m_optionPane.add(m_defaultCPanel);
        m_optionPane.add(Box.createRigidArea(SPACER));
        m_colourMap1Label = new JLabel("Colour Map 1: ");
        m_colourMap1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        m_optionPane.add(m_colourMap1Label);
        m_optionPane.add(m_colourPanel);
        m_optionPane.add(Box.createRigidArea(SPACER));
        
        //create button pane
        m_optionButtonPane.add(m_createChart);
        m_optionButtonPane.add(m_cancelChart);
        //add the components to the window
        m_chartOptionContainer.add(m_optionPane);
        m_chartOptionContainer.add(m_optionButtonPane);
        m_chartOptions.add(m_chartOptionContainer);
        
        //add handlers
        m_createChart.addActionListener(m_handler);
        m_cancelChart.addActionListener(m_handler);
        m_defaultColourRadio.addActionListener(m_handler);
        m_colourMap1Radio.addActionListener(m_handler);
    }
    /**
     * Custom handler for the GUI
     */
    public class MS_GUIHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == m_importData){
                //creates a new CSVFileDialog for opening CSV files
                JFrame getFile = new MS_CsvFileDialog(m_db , m_Context);
                getFile.setVisible(true);
                
            }else if(event.getSource() == m_optionsOpen){
                //checks if there is data to be read from before making a chart
                if(m_db.getDataSet() == null){
                    //displays a warning if there is no data
                    JOptionPane.showMessageDialog(m_warning,
                    "No data has been loaded. Please use the import button to "
                            + "to load your data.",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
                } else {
                    //intialises the components incase the data has changed
                    InitOptionPane();
                    m_chartOptions.setVisible(true);
                }   
                
            } else if( event.getSource() == m_createChart){
                // stores the selected indexes of the x and y boxes
                int x = m_xAxisData.getSelectedIndex();
                int y = m_yAxisData.getSelectedIndex();
                //checks for the selected chart type
                if (m_chartType.getSelectedIndex() == 0){
                    //checks which colour scheme is selected
                    if(m_chartTypeState.equals("DefaultColour")){
                        //creates a barchart with the selected colour scheme
                        MS_Barchart chart = new MS_Barchart(m_db, x, y,
                        m_chartTitle.getText(),r,m_defaultColour);
                        m_displayTabs.addTab( m_chartTitle.getText(), chart);
                        m_chartOptions.setVisible(false);
                        
                    } else if(m_chartTypeState.equals("ColourMap1")){
                        //creates a barchart with the selected colour scheme
                        MS_Barchart chart = new MS_Barchart(m_db, x, y,
                        m_chartTitle.getText(),r,m_colourMaps);
                        m_displayTabs.addTab( m_chartTitle.getText(), chart);
                        m_chartOptions.setVisible(false);
                    }
                } else if(m_chartType.getSelectedIndex() == 1){
                    //checks which colour scheme is selected
                    if(m_chartTypeState.equals("DefaultColour")){
                        //creates a barchart with the selected colour scheme
                        MS_LineChart chart = new MS_LineChart(m_db, x, y,
                        m_chartTitle.getText(),r,m_defaultColour);
                        m_displayTabs.addTab( m_chartTitle.getText(), chart);
                        m_chartOptions.setVisible(false);
                        
                    } else if(m_chartTypeState.equals("ColourMap1")){
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
                m_chartTypeState = event.getActionCommand();
            } else if (event.getSource() == m_colourMap1Radio){
                //sets which radio button is checked
                m_chartTypeState = event.getActionCommand();
            }
        }
    }
    
    public boolean displayTable(){
       r =  new Rectangle(0,0,m_displayArea.getWidth(),m_displayArea.getWidth());
       System.out.println(m_displayArea.getWidth() + " " +m_displayArea.getWidth());
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
    
    /**
     * for running tests on the class
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
 
       MS_BasicGUI example1 = new MS_BasicGUI();
       example1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       example1.setVisible(true);
       
       /**
        * Tests can't really be written as the GUI requires button presses and 
        * such. 
        * Tested the GUI by:
        * Importing a test csv.
        * Trying to import something that wasn't a csv file.
        * Created a bar chart with the csv with title and default colour scheme
        * selected using every y and x field combination.
        * Created a bar chart with the csv with no title and default colour 
        * scheme using every y and x field combination.
        * Created line chart with the csv with no title and default colour
        * scheme using every y and x field combination.
        * Created line chart with the csv with title and default colour
        * scheme using every y and x field combination.
        * Tried to create a chart when no data had been loaded.
        * 
        */
    }
}
