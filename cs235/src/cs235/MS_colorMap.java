/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.Color;

/**
 *
 * @author Johnson
 */
public class MS_colorMap implements MS_ColorScheme{
    private Color[] colorarray = new Color[5];
    public MS_colorMap(Color[] colorarray){
        this.colorarray = colorarray;
    }
    
        @Override
    public void setColourArray(Color[] carray) {
        colorarray = carray;
    }

    @Override
    public void setColour(Color c, int i) {
        colorarray[i] = c;
    
    }

    @Override
    public Color[] getColourArray() {
        return colorarray;
    }

    @Override
    public Color getColour(int i) {
        return colorarray[i];
    }

    @Override
    public int getNumberOfColours() {
        return colorarray.length;
    }
    
}