package cs235;


import java.util.regex.Pattern;

/**
 * The MS_DataAttribute Class stores one piece of data form the csv file
 * @author Robert
 * 
 * 
 */
public class MS_DataAtribute {  
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
            m_AttributeString = input;
            return true;
       }else if(Pattern.matches(INTPATTERN, input)){
           m_AttributeInt = Integer.parseInt(input);
           m_AttributeDouble = Integer.parseInt(input);
            m_AttributeString = input;
            return true;
       }else{
           m_AttributeString = input;
            return true;
       }
       
       
       
   }
    @Override
    public String toString(){
        return m_AttributeString;
    }
    /**
     * uses the setDataAtribute() 
     * @param String data in string format
     */
    public MS_DataAtribute(String input){
        boolean setDataAtribute = setDataAtribute(input);
    }
    /**
     * Basic Constructor for the class
     */
    public MS_DataAtribute(){
        
    }
    
    /**
     * Main Method for testing this class
     * @param String[] Arguments 
     */
    public static void main (String[] args){
        int passed =0;
        int failed = 0;
        // check if panel loads with corrct data
         MS_DataAtribute da = new MS_DataAtribute();
        
        
        System.out.println("MS_DataAtribute.main():Test setDataAtribute() ");
        if(da.setDataAtribute("3.0")){
            System.out.println("MS_DataAtribute.main():Test setDataAtribute() "
                    + " successful");
            passed++;
        }else{
            System.out.println("MS_DataAtribute.main():Test setDataAtribute() "
                    + " failed");
            failed++;
            
        }
        
        // checking data
        System.out.println("MS_DataAtribute.main():Test getIntDataAttribute()");
        da.setDataAtribute(Integer.toString(1));
        if(da.getIntDataAttribute()==1){
            System.out.println("MS_DataAtribute.main():"
                    + "Test getIntDataAttribute() successful");
            passed++;
        }else{
            System.out.println("MS_DataAtribute.main():Test "
                    + "getIntDataAttribute() failed");
            failed++;
            
        }
        
        System.out.println("MS_DataAtribute.main():Test "
                + "getDoubleDataAttribute()");
        da.setDataAtribute(Double.toString(1.0));
        if(da.getDoubleDataAttribute()==1.0){
            System.out.println("MS_DataAtribute.main():"
                    + "Test getDoubleDataAttribute() successful");
            passed++;
        }else{
            System.out.println("MS_DataAtribute.main():Test "
                    + "getIntDoubleAttribute() failed");
            failed++;
            
        }
        
        System.out.println("MS_DataAtribute.main():Test "
                + "getStringDataAttribute()");
        
        da.setDataAtribute("a String");
        if(da.getStringDataAttribute().equals("a String")){
            System.out.println("MS_DataAtribute.main():"
                    + "Test getStringDataAttribute() successful");
            passed++;
        }else{
            System.out.println("MS_DataAtribute.main():Test "
                    + "getStringDataAttribute() failed");
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
    
    
    
    
    
    
    
    
    
    
   
   
    
}
