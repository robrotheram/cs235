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
    
    public MS_DataSet(int width, int height){
        M_numCols = width;
        M_numRows = height;
        M_dataSet = new MS_DataAtribute [height][width];
    }
     
    public void setColNames(String[] colNames ){
        M_colNames = colNames;
    }
    
    public void setMS_DataAtribute(MS_DataAtribute mDA, int x, int y){
        M_dataSet[y][x] = mDA;
    }
    
    public MS_DataAtribute getAtribute(int x, int y){
        return M_dataSet[y][x];
    }

    public MS_DataAtribute[] getAtributeRow(int y){
        return M_dataSet[y];
    }
    
    public String[] getColumnNames(){
        return M_colNames;
    }
    
    public String getAColumnName(int i){
        return M_colNames[i];
    }
    
    public int getNumOfColumns(){
        return M_numCols;
    }
    public int getNumOfRows(){
        return M_numRows;
    }
    
}
