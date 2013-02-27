/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

import java.awt.Color;

/**
 *
 * @author Wyler
 */
interface MS_ColorScheme {
    void setColourArray(Color[] carray);
    void setColour(Color c , int i);
    Color[] getColourArray();
    Color getColour(int i);
    int getNumberOfColours();
     
    
}
