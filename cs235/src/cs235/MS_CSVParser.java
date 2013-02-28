package cs235;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

/**
 * This Class Opens and reads through the Csv file and for each new element it
 * Makes a new MS_DataAtribute object and adds that to the M_DataSet Class
 * @author Robert Fletcher
 */

public class MS_CSVParser {
    private File m_File;
    private MS_DataSet m_DB; 
    private String m_delimitor; 
    private final String CLASS = "MS_CSVParser";    
    /**
     * Class Constructure 
     * @param ds
     * @param file 
     */
    public  MS_CSVParser(MS_DataSet db, File file,String delimitor){
         if(setDataset(db)){
                System.out.println(CLASS+".setDataset():Dataset set Correctly"); 
            }else{
                System.out.println(CLASS+".setDataset():Failed to add");
            }
            if(setDelimiter(delimitor)){
                System.out.println(
                        CLASS+".setDelimiter():Delimitor set Correctly");
            }else{
                System.out.println(CLASS+".setDelimiter():Failed");
            }
            if(setFile(file)){
                System.out.println(CLASS+".setFile():File Set Correctly");
            }else{
                System.out.println(CLASS+".setFile():Failed");
            }
    }

    /**
     * ParseFile runs through the file the first line it takes the Column names
     * The rest of the file it make a new MS_DataAtribute and adds it with 
     * Position values to the MS_DataSet class  Returns a Boolean if True no   
     * Errors have Occurred
     * 
     * @return Boolean isError 
     */
    public Boolean ParseFile(){
        boolean isError = true;
        try {
            Scanner in = new Scanner(m_File);
            LineNumberReader  lnr = new LineNumberReader
                                   (new FileReader(m_File));
            
            
            String[] names  = in.nextLine().split(m_delimitor);
            lnr.skip(Long.MAX_VALUE);
            m_DB.setMS_DataSet(names.length, lnr.getLineNumber());
            System.err.println(CLASS+".ParseFile(): File Line vlaue = "+
                    lnr.getLineNumber());
            
            m_DB.setColNames(names);
            int i = 0;
            while(in.hasNextLine()){
                String[] tempData = in.nextLine().split(",");
       
                if(insertData(tempData , i)){
                    i++;
                    System.out.println(CLASS+".insertData():Complete");
                }else{
                    System.out.println(CLASS+".insertData():Failed");
                }
            }
           
        } catch (Exception e) {
           isError = false;
           System.err.println("MS_CSVParser.ParseFile() Error :"+e);
        }
       return isError;
    }
    
    /**
     * The function checks the array to see if it is null if not it inserts it
     * to the MS_DataSet
     * @param String[] the row of data from the csv parser
     * @param int the row position in the array
     * @return boolean True if successful  
     */
    private boolean insertData(String[] tempData, int i){
        if(checkArray(tempData)){
            int newPos =  0; 
            for(int j =0; j < tempData.length; j++ ){
                if(!tempData[j].equals("")){
                    m_DB.setMS_DataAtribute(new MS_DataAtribute(tempData[j]),
                            newPos, i);
                    newPos++;
                }       
            }
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Method to check if line is empty Requires a the String[] and returns true
     * if it is <b>not </b> empty
     * @param check 
     * @return isEmpty
     */
    private Boolean checkArray(String[] check){
        Boolean isEmpty = false;
        for(int i = 0;i<check.length;i++){
            
            if(!check[i].equals("")){
                isEmpty = true;
                break;
            }
            
        }
        return isEmpty;
    }
    
     public boolean setFile(File f){
        m_File = f;
        return true;
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
    public boolean setDataset(MS_DataSet db){
        m_DB = db;
        return true;
    }

    /**
    * Gets the Dataset of this class
    * @return MS_BasicGUI 
    */
    public MS_DataSet getDataSet(){
        return m_DB;
    }
    /** 
    * Sets the Delimitor used in parsing the file;
    * @param  String  the file's delimitor;
    * @return boolean true if set correctly
    */
    public boolean setDelimiter(String del){
        m_delimitor = del;
        return true;
    }

    /**
    * Gets the delimitor of this class
    * @return String 
    */
    public String getDelimitor(){
        return m_delimitor;
    }
    
}
 