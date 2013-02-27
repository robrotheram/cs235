package cs235;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 * MS_CsvFileDialog creates a JFrame where the user can browser for their csv 
 * file and choose the delimitor in the csv file
 * @author Robert
 */
public class MS_CsvFileDialog extends JFrame {
        
  
	private JTextField m_delimiterFeild, m_FilePath;
	private JButton m_ButtonAppy, m_ButtonCancel,m_ButtonBrowse;
	private JPanel m_BrowsePanel, m_ButtonPannel, m_ChoosePannel, m_Sample;
	private EventHandler handle;
	private JLabel m_Description;
	private JTextArea m_SampleData;
        private final String FILLER = "Sample text are";
        private File m_File;
	private final String CLASS = "MS_CsvFileDialog()";
        private JScrollPane m_ScrollText;
        private MS_DataSet m_db;
        private final String NEWLINE = "\n";
	
        /**
         * 
         * @param MS_DataSet db - the refernce the the dataset stored in the 
         * progrma
         */
	public MS_CsvFileDialog (MS_DataSet db){
                m_db = db;
		init();
	}
/**
 * This function creates the objects sets the layout of the frame and the 
 * JPanels and adds the objects to them
 * 
 * 
 */
	public void init(){
		handle = new EventHandler();
		this.setBounds(400,400,500,280);
                this.setResizable(false);
		this.setLayout(new BorderLayout());
		JPanel mainContainer = new JPanel();
                mainContainer.setLayout(new FlowLayout((FlowLayout.LEFT)));
		m_BrowsePanel = new JPanel();
		m_BrowsePanel.setBounds(0,0,400,120);
		m_BrowsePanel.setLayout(new FlowLayout((FlowLayout.LEFT)));
		
		m_ChoosePannel = new JPanel();
		m_ChoosePannel.setBounds(0,0,400,120);
		m_ChoosePannel.setLayout(new FlowLayout((FlowLayout.LEFT)));
		
		m_ButtonPannel = new JPanel();
		m_ButtonPannel.setBounds(0,0,400,120);
		m_ButtonPannel.setLayout(new FlowLayout());
                
              
                
		m_Sample = new JPanel();
		m_Sample.setBounds(0,0,400,120);
                
               
                m_SampleData = new JTextArea(FILLER, 10,40);
                m_SampleData.setBounds(0,0,400,400);
                
                m_ScrollText = new JScrollPane(m_SampleData, 
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                
                m_Sample.add(m_ScrollText,BorderLayout.CENTER);
		
                
		m_Description = new JLabel();
		m_Description.setText("Choose Delimiter:");	
		
		m_delimiterFeild = new JTextField();
		m_delimiterFeild.setColumns(10);
		m_delimiterFeild.setText(",");
		
		m_ChoosePannel.add(m_Description);
		m_ChoosePannel.add(m_delimiterFeild);
               
		
		m_FilePath = new JTextField();
		m_FilePath.setColumns(20);
		
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
                        System.out.println
                                (CLASS+"EventHandler.actionPerformed():"
                                + " Event been performed ");

			if(event.getSource() == m_ButtonBrowse){
                             System.out.println
                                     (CLASS+"EventHandler.actionPerformed(): "
                                     + " m_ButtonBrowse been pressed ");
				
				m_FilePath.setText((getFile()));
                                if(diplayFile()){
                                    System.out.println
                        (CLASS+".displayFile(): File Has been read no errors");
                                }else{
                                     System.out.println
                         (CLASS+".displayFile(): Error with file");
                                }
                                
			}else if(event.getSource() == m_ButtonCancel){
                            System.out.println
                                    (CLASS+"EventHandler.actionPerformed():"
                                    + " m_ButtonCancel been pressed");
                            
                            dispose(); 
                        }else if(event.getSource() == m_ButtonAppy){
                           System.out.println
                                   (CLASS+"EventHandler.actionPerformed():"
                                   + " m_ButtonAppy been pressed");
                           
                           if(parseFile()){
                                System.out.println
                                        (CLASS+"EventHandler.actionPerformed():"
                                        + " parseFile() been successful");
                                
                           }else{
                               System.out.println
                                       (CLASS+"EventHandler.actionPerformed():"
                                       + " parseFile() has faild");
                           }
                            dispose(); 
                            
                        }
			
		}
		
	}
        
        /**
         * calls the JFilechooser and when user has selected a file stores it.
         * @return String absolute path to the selected file
         */	
	  private String getFile(){
	        JFileChooser fileChooser = new JFileChooser();
	        int returnValue = fileChooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	          m_File = fileChooser.getSelectedFile();
	          
	        }
	        return m_File.getAbsolutePath();
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
              MS_CSVParser csv = 
                      new MS_CSVParser(m_db,m_File,m_delimiterFeild.getText());
              return csv.ParseFile();
          }
          
 /**
  * Main Method for testing this file
  * @param String[] args command Line arguments
  */       
	public static void main(String[] args){
		MS_CsvFileDialog t = new MS_CsvFileDialog(new MS_DataSet());
		t.setVisible(true);
                
                
               
		
	}

}