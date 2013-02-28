package cs235;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * 
 * @author Zhenjie Mu
 */
public class MS_ColourMap implements MS_ColorScheme{
    private Color[] m_colorArray = new Color[5];
    private JPanel m_colourPanel, m_defaultC2
            , m_defaultC3, m_defaultC4, m_defaultC5;
    private JPanel[] panels = {m_colourPanel, m_defaultC2
            , m_defaultC3, m_defaultC4, m_defaultC5};
            
   /**
    * Constructor sets the color array for constructor
    * 
    * @param colorArray 
    */
    public MS_ColourMap(Color[] colorArray){
        if(setColourArray(colorArray)){
            System.out.println("Colours set in the colour map in MS_ColourMap");
        } else if(!setColourArray(colorArray)){
            System.err.println("Unable to set colours in MS_ColourMap");
        }
        if(setPanels(panels)){
            System.out.println("Colour panels set in MS_ColourMap");
        }
        else if(!setPanels(panels)){
            System.err.println("Colour panels not set in MS_ColourMap");
        }
    }
    
    /**
    * 
    * The setColourArray class stores structure of entire color data
    * 
    * @param cArray 
    */
    @Override
    public boolean setColourArray(Color[] cArray) {
        m_colorArray = cArray;
        return true;
    }

    /**
    * set in the color array a Color c at position i
    * 
    * @param c the colour
    * @param i the position
    */
    @Override
    public boolean setColour(Color c, int i) {
        m_colorArray[i] = c;
        return true;
    }
    
    /**
     * Sets 5 panels sizes and colours to match the array for viewing colour
     * map colours in an interface
     * 
     * @param panels array of panels
     * @return true if set correctly
     */
    public boolean setPanels(JPanel[] panels){
        for(int i = 0; i < panels.length; i++){
            panels[i].setPreferredSize(new Dimension(30,30));
            panels[i].setBackground(m_colorArray[i]);
        }
        return true;
    }
    
    /**
     * Returns the coloured panels for colourmaps
     * 
     * @return panels coloured panels matching the colour array
     */
    public JPanel[] getPanels(){
        return panels;
    }
    
    /**
     * Get the colour array
     * @return m_colorArray
     */
    @Override
    public Color[] getColourArray() {
        return m_colorArray;
    }

    /**
    * Get individual colours from the colour array
    * 
    * @param i an int for the location of colour in array
    * @return m_colorArray[i] the colour at position a
    */
    @Override
    public Color getColour(int i) {
        return m_colorArray[i];
    }
    
    /**
     * Returns the number of colours stored in array for calculations etc
     * 
     * @return int
     */
    @Override
    public int getNumberOfColours() {
        return m_colorArray.length;
    }
    
}



