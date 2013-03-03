package cs235;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * MS_CsvFileDialog creates a JFrame where the user can browser for their csv 
 * file and choose the delimitor in the csv file
 * @author Robert
 */
public final class MS_CsvFileDialog extends JFrame {

    /** 
    * Sets the context of this class to a context
    * @param  MS_BasicGUI context of this class
    * @return boolean true if set correctly
    */
    public boolean setContext(MS_BasicGUI con){
        m_Context = con;
        return true;
    }

    /**
    * Gets the Context of this class
    * @return MS_BasicGUI 
    */
    public MS_BasicGUI getContext(){
        return m_Context;
    }
    /** 
    * Set the file used in the program
    * @param  FIle File used in the MS_CSVParser
    * @return boolean true if set correctly
    */
    public boolean setFile(File f){
        if(f.exists()){
            m_File = f;  
            return true;
	  }else{
            return false;  
	  }
        
        
    }

    /**
    * Gets the File used
    * @return MS_BasicGUI 
    */
    public File getFile(){
        return m_File;
    }
    /** 
    * Sets the dataset used in the class
    * @param  MS_DataSet the dataset of this class
    * @return boolean true if set correctly
    */
    public boolean setDataSet(MS_DataSet db){
        m_db = db;
        return true;
    }

    /**
    * Gets the Dataset of this class
    * @return MS_BasicGUI 
    */
    public MS_DataSet getDataSet(){
        return m_db;
    }

    /**
     * calls the JFilechooser and when user has selected a file stores it.
     * @return String absolute path to the selected file
     */	
    private String getFileDialog(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(
        "CSV Files Only", "CSV"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          m_File = fileChooser.getSelectedFile();
          return m_File.getAbsolutePath();
        }else{
            return null;
        }
        
    }

    /**
     * Reads the file and outputs it in a JTextArea
     * @return boolean true if there are no errors
     */	
    private Boolean diplayFile(){
        m_SampleData.setText("");
        Boolean test = true;
        try{
            Scanner in = new Scanner(m_File);
            while(in.hasNextLine()){
                m_SampleData.append(in.nextLine()+NEWLINE);
            }
        }catch(IOException e){
            System.err.println(CLASS+".displayFile(): "+e);
            test = false;
        }
        return true;
    }
    /**
    * Instantiates the MS_CSVParser and parses the file;
    * @return boolean  true if file is parsed into the MS_DataSet correctly
    */         
    private boolean parseFile(){
        MS_CSVParser csv = new MS_CSVParser
                (m_db,m_File,m_delimiterFeild.getText());
        return csv.ParseFile();
    }

    /**
     * This function creates the objects sets the layout of the frame and the 
     * JPanels and adds the objects to them
     * @return boolean true if swing elements are created and displayed 
     */
    public boolean init(){
        handle = new EventHandler();
        this.setBounds(FRAMESIZE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new FlowLayout((FlowLayout.LEFT)));
        m_BrowsePanel = new JPanel();
        m_BrowsePanel.setBounds(PANNELSIZE);
        m_BrowsePanel.setLayout(new FlowLayout((FlowLayout.LEFT)));

        m_ChoosePannel = new JPanel();
        m_ChoosePannel.setBounds(PANNELSIZE);
        m_ChoosePannel.setLayout(new FlowLayout((FlowLayout.LEFT)));

        m_ButtonPannel = new JPanel();
        m_ButtonPannel.setBounds(PANNELSIZE);
        m_ButtonPannel.setLayout(new FlowLayout());



        m_Sample = new JPanel();
        m_Sample.setBounds(PANNELSIZE);


        m_SampleData = new JTextArea(FILLER, TEXTAREACOLUMN,TEXTAREAROW);
        m_SampleData.setBounds(SAMPLEDATASIZE);

        m_ScrollText = new JScrollPane(m_SampleData, 
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        m_Sample.add(m_ScrollText,BorderLayout.CENTER);


        m_Description = new JLabel();
        m_Description.setText("Choose Delimiter:");	

        m_delimiterFeild = new JTextField();
        m_delimiterFeild.setColumns(COLUMNCOUNTDEL);
        m_delimiterFeild.setText(",");

        m_ChoosePannel.add(m_Description);
        m_ChoosePannel.add(m_delimiterFeild);


        m_FilePath = new JTextField();
        m_FilePath.setColumns(COLUMNCOUNTFILE);

        m_ButtonBrowse = new JButton("Browser for csv file");
        m_ButtonBrowse.addActionListener(handle);

        m_BrowsePanel.add(m_FilePath);
        m_BrowsePanel.add(m_ButtonBrowse);

        m_ButtonAppy = new JButton("Apply");
        m_ButtonAppy.addActionListener(handle);

        m_ButtonCancel = new JButton("Cancel");
        m_ButtonCancel.addActionListener(handle);



        m_ButtonPannel.add(m_ButtonCancel);
        m_ButtonPannel.add(m_ButtonAppy);

        mainContainer.add(m_BrowsePanel, BorderLayout.NORTH);
        mainContainer.add(m_ChoosePannel, BorderLayout.CENTER);
        mainContainer.add(m_Sample, BorderLayout.SOUTH);


        this.add(mainContainer,BorderLayout.CENTER);
        this.add(m_ButtonPannel,BorderLayout.SOUTH);

        return  true;
    }
	
    /**
     * EventHandler class handles the just the buttons actions in this 
     * class
     * 
     */
    private class EventHandler implements ActionListener {
            
        /**
         * Overrides the actionPerformed event to do custom events 
         * depending on which button is pressed
         * @param ActionEvent event the Buttons action event
         * called when the button is pressed 
         */                   
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println(CLASS+"EventHandler.actionPerformed(): Event "
                    + "been performed ");

            if(event.getSource() == m_ButtonBrowse){
                System.out.println(CLASS+"EventHandler.actionPerformed(): "
                         + " m_ButtonBrowse been pressed ");

                if(getFileDialog()!= null){
                     m_FilePath.setText(m_File.getAbsolutePath());
                    if(diplayFile()){
                            System.out.println(CLASS+".displayFile(): File Has been"
                                    + " read no errors");
                    }else{
                        System.out.println(CLASS+".displayFile(): Error with file");
                    }
                }

            }else if(event.getSource() == m_ButtonCancel){
                System.out.println(CLASS+"EventHandler.actionPerformed():"
                        + " m_ButtonCancel been pressed");
                dispose(); 
                
            }else if(event.getSource() == m_ButtonAppy){
               System.out.println(CLASS+"EventHandler.actionPerformed():"
                       + " m_ButtonAppy been pressed");

               if(parseFile()){
                    System.out.println(CLASS+"EventHandler.actionPerformed():"
                            + " parseFile() been successful");
                    m_Context.displayTable();

               }else{
                   System.out.println(CLASS+"EventHandler.actionPerformed():"
                           + " parseFile() has faild");
               }
                dispose(); 

            }
        }
    }
    
    /**
     * Main Method for testing this class
     * @param String[] Arguments 
     */
    
    public static void main (String[] args){
        int passed =0;
        int failed = 0;
        // check if panel loads with corrct data
         MS_CsvFileDialog fd = null;
        try{
            System.out.println("MS_CsvFileDialog.main():Test launch GUI ");
            final Rectangle FRAMESIZE = new Rectangle(0, 0, 500, 350); 
            fd = new MS_CsvFileDialog(new MS_DataSet(), new MS_BasicGUI());
            fd.setVisible(true);
            System.out.println("MS_CsvFileDialog.main():"
                    + "Gui launched sucsseful");
             passed++;
        }catch(Exception e){
            System.err.println("MS_CsvFileDialog.main():Gui "
                    + "launched Failed with Error : "+ e);
            failed++;
        }
        System.out.println("MS_CsvFileDialog.main():Test setFile() ");
        
        if(fd.setFile(new File("/Users/Robert/Desktop/coal.csv"))){
            System.out.println("MS_CsvFileDialog.main():Test setFile()"
                    + " successful");
            passed++;
        }else{
            System.out.println("MS_CsvFileDialog.main():Test setFile()"
                    + " failed");
            failed++;
            
        }
        
        System.out.println("MS_CsvFileDialog.main():Test setFile() ");
        
        if(fd.setFile(new File("/this/path/des/notexist/csv.csv"))==false){
            System.out.println("MS_CsvFileDialog.main():Test setFile()"
                    + " successful returned could not set file");
            passed++;
        }else{
            System.out.println("MS_CsvFileDialog.main():Test setFile()"
                    + " failed");
            failed++;
            
        }
        
        System.out.println("MS_CsvFileDialog.main():Test setContext() ");
        if(fd.setDataSet(new MS_DataSet())){
            System.out.println("MS_CsvFileDialog.main():Test setContext()"
                    + " successful");
            passed++;
        }else{
            System.out.println("MS_CsvFileDialog.main():Test setContext()"
                    + " failed");
            failed++;
            
        }
        
        System.out.println("MS_CsvFileDialog.main():Test setContext");
        if(fd.setContext(new MS_BasicGUI())){
            System.out.println("MS_CsvFileDialog.main():Test setContext()"
                    + " successful");
            passed++;
        }else{
            System.out.println("MS_CsvFileDialog.main():Test setContext()"
                    + " failed");
            failed++;
            
        }

        System.out.println("------------------------------------------------");
        System.out.println("                 Testing Output                 ");
        System.out.println();
        System.out.println();
        System.out.println("Number of test run: "+(passed+failed));
        System.out.println("Ms_TablePanel() Passed:  "+(passed));
        System.out.println("Ms_TablePanel() Failed:  "+(failed));
        System.out.println();
        System.out.println();
        System.out.println("------------------------------------------------");
        
        
        

    }

    /**
     * 
     * @param MS_DataSet db - the refernce the the dataset stored in the 
     * progrma
     */
    public MS_CsvFileDialog (MS_DataSet db, MS_BasicGUI context){
           
            if(setDataSet(db)){
                System.out.println(CLASS+".setDataset():Dataset set Correctly"); 
            }else{
                System.out.println(CLASS+".setDataset():Failed to add");
            }
            if(setContext(context)){
                System.out.println(CLASS+".setContext():Context set Correctly");
            }else{
                System.out.println(CLASS+".setContext():Failed");
            }
            if(init()){
                System.out.println(CLASS+".init():Gui initiated Correctly");
            }else{
                System.out.println(CLASS+".init():Failed");
            }
            
            
    }
    
            
    // gui objects
    private JTextField m_delimiterFeild, m_FilePath;
    private JButton m_ButtonAppy, m_ButtonCancel,m_ButtonBrowse;
    private JPanel m_BrowsePanel, m_ButtonPannel, m_ChoosePannel, m_Sample;
    private EventHandler handle;
    private JLabel m_Description;
    private JTextArea m_SampleData;
    private JScrollPane m_ScrollText;

    //data objects
    private File m_File;
    private MS_DataSet m_db;
    private MS_BasicGUI m_Context;
    

    //Constants
    private final Rectangle PANNELSIZE = new Rectangle(0,0,400,120);
    private final Rectangle FRAMESIZE = new Rectangle(400,400,500,280);
    private final Rectangle SAMPLEDATASIZE = new Rectangle(0,0,400,400);
    private final int COLUMNCOUNTFILE = 20;
    private final int COLUMNCOUNTDEL = 20;
    private final int TEXTAREACOLUMN = 10;
    private final int TEXTAREAROW = 40;
    private final boolean TESTING = true;
    private final String FILLER = "Sample text are";
    private final String NEWLINE = "\n";
    private final String CLASS = "MS_CsvFileDialog()";

}
