package cs235;

import java.awt.Color;

/**
 * 
 * @author Zhenjie Mu
 */
public class MS_ColourMap implements MS_ColorScheme{
    private Color[] colorarray = new Color[5];
            
   /**
    * constructor takes from color array
    * @param colorarray 
    */
    public MS_ColourMap(Color[] colorarray){
        this.colorarray = colorarray;
    }
    
 /**
 * 
 * The setColourArray class stores structure of entire color data
 * 
 * @param carray 
 */
    
        @Override
    public boolean  setColourArray(Color[] carray) {
        colorarray = carray;
        return true;
    }

     /**
     * set in the color array a Color c at position i
     * 
     * @param color
     * @param int  
     */
    @Override
    public boolean setColour(Color c, int i) {
        colorarray[i] = c;
        return true;
    
    }
    
/**
 * get color from color array
 * @return color array
 */
    @Override
    public Color[] getColourArray() {
        return colorarray;
    }

     /**
     * get individual color from color array
     * @param int
     * @return int
     */
    @Override
    public Color getColour(int i) {
        return colorarray[i];
    }
    
/**
 * returns the number of color stored in array
 * 
 * @return int
 */
    @Override
    public int getNumberOfColours() {
        return colorarray.length;
    }
    
}


