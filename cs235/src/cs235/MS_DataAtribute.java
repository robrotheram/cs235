/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;


import java.util.regex.Pattern;

/**
 *
 * @author Robert
 * The MS_DataAttribute Class stores one piece of data form the csv file
 * 
 */
public class MS_DataAtribute {
    /** 
     * First 3 private variables stores the different types of data 
     * 
     * 
     */
    
    private int m_AttributeInt; 
    private double m_AttributeDouble; 
    private String m_AttributeString; 
    
    /// pattern to determin if a string is a double.
    private final String DOUBLEPATTERN = "([0-9]*)\\.([0-9]*)";  
    
    // pattern to determin id String is a integer
    private final String INTPATTERN = "([0-9]*)";
    
   
    
 /**
 * Returns the data stored in the class of type int                         

 *
 * @param  null         
 * @return interger of the data stored
 */
    public int getIntDataAttribute(){
        return m_AttributeInt;  
    }
    
    
     /**
 * Returns the data stored in the class of type Double                         

 *
 * @param  null         
 * @return Double of the data stored
 */
    public double getDoubleDataAttribute(){
        return m_AttributeDouble;  
    }
    
    
     /**
 * Returns the data stored in the class of type Str                        

 *
 * @param  null         
 * @return String of the data stored
 */
    public String getStringDataAttribute(){
        return m_AttributeString;  
    }
    
    
     /**
 * Returns True if the program successfully decides where the string
 * input is actually a int double or string                        

 *
 * @param  String the input from field of file.        
 * @return Boolean if the data stored
 * 
 * 
 */
   public boolean setDataAtribute(String input){
        
       if(Pattern.matches(DOUBLEPATTERN, input)){
            m_AttributeDouble = Double.parseDouble(input);
            return true;
       }else if(Pattern.matches(INTPATTERN, input)){
           m_AttributeInt = Integer.parseInt(input);
            return true;
       }else{
           m_AttributeString = input;
            return true;
       }
       

   } 
    
}
