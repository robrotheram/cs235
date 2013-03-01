package cs235;

import java.util.ArrayList;

/**
 * The MS_DataSet Class stores a structure of the entire data 
 * @author Robert
 */
public class MS_DataSet {
   
    /**
     * Creates the 2 dimentional array of MS_DataAtributes and sets the Width
     * and Height
     * @param int width
     * @param int height
     * @return boolean true if set correctly 
     */
    
    public boolean setMS_DataSet(int width, int height){
        m_numCols = width;
        m_numRows = height;
        m_dataSet = new MS_DataAtribute [height][width];
        return true;
    }
    /**
     * Set the Column names in this class
     * @param String[] colNames 
     * @return boolean true
     * 
     */
     
    public boolean setColNames(String[] colNames ){
        m_colNames = colNames;
        return true;
    }
    /**
     * sets in the data array a MS_DataAtributs at position x ,y 
     * @param setMS_DataAtribute mDA the cell of data
     * @param int x:  Position x in the 2 dimentional array;
     * @param int y:  Position y in the 2 dimentional array;
     * @return Boolean true
     */
    public boolean setMS_DataAtribute(MS_DataAtribute mDA, int x, int y){
        m_dataSet[y][x] = mDA;
        return true;
    }
    /**
     * set the entire dataset
     * @param MS_DataAtribute[][] the representation of the table data
     * @return Boolean true if run. 
     */
    public boolean setDataSet(MS_DataAtribute[][] data){
        m_dataSet = data;
        return true;
    }
    
    /**
     * Reterns the dataAtribute and position x,y
     * @param int x:  Position x in the 2 dimentional array;
     * @param int y:  Position y in the 2 dimentional array;
     * @return MS_DataSet single cell of data
     */
    
    public MS_DataAtribute getAtribute(int x, int y){
        return m_dataSet[y][x];
    }
    
    /**
     * Outputs the entire dataset
     * @return MS_DataSet[][]  The entire dataset
     */
    public MS_DataAtribute[][] getDataSet(){
        return m_dataSet;
    }
    /**
     * Returns an Array representing a row in the Set at position y
     * 
     * @param y
     * @return MS_DataSet[]
     */
    public MS_DataAtribute[] getAtributeRow(int y){
        return m_dataSet[y];
    }
    
    /**
     * Returns the array of the column names
     * @return String[]
     */
    public String[] getColumnNames(){
        return m_colNames;
    }
    /**
     * Gets the a single column name at position i in the array
     * @param i
     * @return 
     */
    public String getAColumnName(int i){
        return m_colNames[i];
    }
    /**
     * Returns the number of columns in the dataset
     * @return int
     */
    public int getNumOfColumns(){
        return m_numCols;
    }
    /**
     * Returns the number of rows that the dataset represents. 
     * @return Int
     */
    public int getNumOfRows(){
        return m_numRows;
    }
    
    private MS_DataAtribute[][] m_dataSet;
    private String[] m_colNames;
    private int m_numCols, m_numRows;
    
    /**
     * Main Method for testing this class
     * @param String[] Arguments 
     */
    public static void main (String[] args){
        int passed =0;
        int failed = 0;
        // check if panel loads with corrct data
         MS_DataSet ds = new MS_DataSet();
        // test data
         int width = 5;
         int height = 172;
         String[] colNames ={"these","are","test","Strings"};
         
        //test set methods
         
        System.out.println("MS_DataSet.main():Test setDataSet() ");
        if(ds.setMS_DataSet(width, height)){
            System.out.println("MS_DataSet.main():Test setDataSet() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test setDataSet() failed");
            failed++;
            
        }
        
        System.out.println("MS_DataSet.main():Test setColNames() ");
        if(ds.setColNames(colNames)){
            System.out.println("MS_DataSet.main():Test setColNames() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test setColNames() failed");
            failed++;
            
        }
        System.out.println("MS_DataSet.main():Test setDataSet() ");
        if(ds.setDataSet(new MS_DataAtribute[width][height])){
            System.out.println("MS_DataSet.main():Test setDataSet() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test setDataSete()"
                    + "failed");
            failed++;
            
        }
        
        System.out.println("MS_DataSet.main():Test setMS_DataAtribute() ");
        if(ds.setMS_DataAtribute(new MS_DataAtribute("100"), 0, 0)){
            System.out.println("MS_DataSet.main():Test setMS_DataAtribute() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test setMS_DataAtribute()"
                    + "failed");
            failed++;
            
        }
        
        
        //testing get methods
        
        
         System.out.println("MS_DataSet.main():Test getDataSet() ");
        if(ds.getDataSet()!=null){
            System.out.println("MS_DataSet.main():Test getDataSet() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test getDataSet() failed");
            failed++;
            
        }
        
        System.out.println("MS_DataSet.main():Test getColumnNames()");
        if(ds.getColumnNames().equals(colNames)){
            System.out.println("MS_DataSet.main():Test getColumnNames() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test getColumnNames()"
                    + " failed");
            failed++;
            
        }
        
        System.out.println("MS_DataSet.main():Test getAcolumnName() ");
        if(ds.getAColumnName(0).equals(colNames[0])){
            System.out.println("MS_DataSet.main():Test getAcolumnName() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test getAcolumnName()"
                    + "failed");
            failed++;
            
        }
        
        System.out.println("MS_DataSet.main():Test getAtributet() ");
        if(ds.getAtribute(0,0).toString().equals("100")){
            System.out.println("MS_DataSet.main():Test getAtribute() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test getAtribute()"
                    + "failed");
            failed++;
            
        }
        System.out.println("MS_DataSet.main():Test getAtributeRow() ");
        if(ds.getAtributeRow(0)[0].toString().equals("100")){
            System.out.println("MS_DataSet.main():Test getAtributeRow() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test getAtributeRow()"
                    + "failed");
            failed++;
            
        }
        
        System.out.println("MS_DataSet.main():Test getNumOfColumns() ");
        if(ds.getNumOfColumns()== width){
            System.out.println("MS_DataSet.main():Test getNumOfColumns() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test getNumOfColumns()"
                    + "failed");
            failed++;
            
        }
        
        System.out.println("MS_DataSet.main():Test getNumOfRows() ");
        if(ds.getNumOfRows()== height){
            System.out.println("MS_DataSet.main():Test getNumOfRows() "
                    + "successful");
            passed++;
        }else{
            System.out.println("MS_DataSet.main():Test getNumOfRows()"
                    + "failed");
            failed++;
            
        }
        System.out.println("------------------------------------------------");
        System.out.println("                 Testing Output                 ");
        System.out.println();
        System.out.println();
        System.out.println("Number of test run: "+(passed+failed));
        System.out.println("MS_DataSet Passed:  "+(passed));
        System.out.println("MS_DataSet Failed:  "+(failed));
        System.out.println();
        System.out.println();
        System.out.println("------------------------------------------------");
        
        
        

    }
    
}
