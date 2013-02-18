package cs235;

import java.util.ArrayList;

/**
 * The MS_DataSet Class stores a structure of the entire data 
 * @author Robert
 */
public class MS_DataSet {
    private MS_DataAtribute[][] M_dataSet;
    private String[] M_colNames;
    private int M_numCols, M_numRows;
    
    
    
/**
 * The MS_DataSet Class stores a structure of the entire data 
 * @author Robert
 */
    
    public void setMS_DataSet(int width, int height){
        M_numCols = width;
        M_numRows = height;
        M_dataSet = new MS_DataAtribute [height][width];
    }
    /**
     * Set the Column names in this class
     * @param colNames 
     */
     
    public void setColNames(String[] colNames ){
        M_colNames = colNames;
    }
    /**
     * sets in the data array a MS_DataAtributs at position x ,y 
     * @param mDA
     * @param x
     * @param y 
     */
    public void setMS_DataAtribute(MS_DataAtribute mDA, int x, int y){
        M_dataSet[y][x] = mDA;
    }
    /**
     * Reterns the dataAtribute and position x,y
     * @param x
     * @param y
     * @return MS_DataAtribute 
     */
    public MS_DataAtribute getAtribute(int x, int y){
        return M_dataSet[y][x];
    }
    /**
     * Returns an Array representing a row in the Set at position y
     * 
     * @param y
     * @return MS_DataAtribute[]
     */
    public MS_DataAtribute[] getAtributeRow(int y){
        return M_dataSet[y];
    }
    
    /**
     * Returns the array of the column names
     * @return String[]
     */
    public String[] getColumnNames(){
        return M_colNames;
    }
    /**
     * Gets the a single column name at position i in the array
     * @param i
     * @return 
     */
    public String getAColumnName(int i){
        return M_colNames[i];
    }
    /**
     * Returns the number of columns in the dataset
     * @return int
     */
    public int getNumOfColumns(){
        return M_numCols;
    }
    /**
     * Returns the number of rows that the dataset represents. 
     * @return Int
     */
    public int getNumOfRows(){
        return M_numRows;
    }
    
}
