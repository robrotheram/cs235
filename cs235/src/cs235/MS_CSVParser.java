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
    private File m_file;
    private MS_DataSet m_DB; 
    private String m_delimitor; 
    private final String CLASS = "MS_CSVParser";    
    /**
     * Class Constructure 
     * @param ds
     * @param file 
     */
    public  MS_CSVParser(MS_DataSet db, File file,String delimitor){
        m_file = file;
        m_delimitor = delimitor;
        m_DB = db;
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
            Scanner in = new Scanner(m_file);
            LineNumberReader  lnr = new LineNumberReader
                                   (new FileReader(m_file));
            
            
            String[] names  = in.nextLine().split(m_delimitor);
            lnr.skip(Long.MAX_VALUE);
            m_DB.setMS_DataSet(names.length, lnr.getLineNumber());
            System.err.println(CLASS+".PArseFile(): File Line vlaue = "+lnr.getLineNumber());
            
            m_DB.setColNames(names);
            int i = 0;
            while(in.hasNextLine()){
                String[] tempData = in.nextLine().split(",");
                if(checkArray(tempData)){
                    int newPos =  0; 
                    for(int j =0; j < tempData.length; j++ ){
                        
                       if(!tempData[j].equals("")){
                          
                           m_DB.setMS_DataAtribute
                              (new MS_DataAtribute(tempData[j]), newPos, i);
                           newPos++;
                        }
                       
                    }
                   
                    i++;
                }
            }
           
        } catch (Exception e) {
           isError = false;
           System.err.println("MS_CSVParser.ParseFile() Error :"+e);
        }
       return isError;
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
}
 