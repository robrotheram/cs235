/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Scanner;
/**
 *
 * @author Robert Fletcher
 */
public class MS_CSVParser {
    private File M_file;
    private MS_DataSet M_DB; 
    
    public  MS_CSVParser( MS_DataSet db, File file){
        M_file = file;
        M_DB = db;
    }
    public Boolean ParseFile(){
        try {
            Scanner in = new Scanner(M_file);
            LineNumberReader  lnr = new LineNumberReader(new FileReader(M_file));
            String[] names  = in.nextLine().split(",");
            M_DB = new MS_DataSet(names.length, lnr.getLineNumber());
            M_DB.setColNames(names);
            int i = 0;
            while(in.hasNextLine()){
                String[] tempData = in.nextLine().split(",");
                for(int j =0; j < tempData.length; j++ ){
                    M_DB.setMS_DataAtribute
                            (new MS_DataAtribute(tempData[j]), j, i);
                }
                i++;
            }
           
        } catch (FileNotFoundException ex) {
           
        }
       return true;
    }
    
}
