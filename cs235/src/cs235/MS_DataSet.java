package cs235;

import java.util.ArrayList;

/**
 * The MS_DataSet Class stores a structure of the entire data 
 * @author Robert
 */
public class MS_DataSet {
    private MS_DataAtribute[][] m_dataSet;
    private String[] m_colNames;
    private int m_numCols, m_numRows;
    
    
    
/**
 * The MS_DataSet Class stores a structure of the entire data 
 * @author Robert
 */
    
    public void setMS_DataSet(int width, int height){
        m_numCols = width;
        m_numRows = height;
        m_dataSet = new MS_DataAtribute [height][width];
    }
    /**
     * Set the Column names in this class
     * @param colNames 
     */
     
    public void setColNames(String[] colNames ){
        m_colNames = colNames;
    }
    /**
     * sets in the data array a MS_DataAtributs at position x ,y 
     * @param mDA
     * @param x
     * @param y 
     */
    public void setMS_DataAtribute(MS_DataAtribute mDA, int x, int y){
        m_dataSet[y][x] = mDA;
    }
    /**
     * Reterns the dataAtribute and position x,y
     * @param x
     * @param y
     * @return MS_DataAtribute 
     */
    public MS_DataAtribute getAtribute(int x, int y){
        return m_dataSet[y][x];
    }
    
     public MS_DataAtribute[][] getDataSet(){
        return m_dataSet;
    }
    /**
     * Returns an Array representing a row in the Set at position y
     * 
     * @param y
     * @return MS_DataAtribute[]
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
    
}
