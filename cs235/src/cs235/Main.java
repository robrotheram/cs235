/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Robert
 */
public class Main {
    private static MS_CSVParser csv;
    private static MS_DataSet DS;
    
    /**
     * @param args the command line arguments
     * 
     * 
     * The following code is just to test if a csv is stored corectly. and
     * can be outputed correctly use the code ad reference and do not coppy 
     */
    public static void main(String[] args) {
        DS = new MS_DataSet();
        //csv = new MS_CSVParser(DS,new File("C:/test/csv.csv"));
        
        
        File f=  getFile();
        
        if(f !=null){
            csv = new MS_CSVParser(DS,f);
            if(csv.ParseFile()){
            String[] n = DS.getColumnNames() ;
            for(int j = 0; j < DS.getNumOfColumns(); j++ ){
                System.out.print(n[j]+"  ");
            }
            System.out.println();
            for(int i= 0; i < DS.getNumOfRows()-1; i++ ){
                for(int j = 0; j < DS.getNumOfColumns(); j++ ){
                    System.out.print(DS.getAtribute(j, i).getIntDataAttribute()+"       ");                
                }
                System.out.println();
            }
            }
            ChartExample ce = new ChartExample(DS);
            ce.setVisible(true);
        }
        
    }
    
    
    public static File getFile(){
        File f =null;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          f = fileChooser.getSelectedFile();
          System.out.println(f.getName());
        }
        return f;
    }
}