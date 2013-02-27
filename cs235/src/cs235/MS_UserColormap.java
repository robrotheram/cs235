package cs235;

/**
 *This Class aloud the user to choose their own custom color map
 * @author Zhenjie Mu
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
    
public class MS_UserColormap extends JFrame implements MS_ColorScheme{
    private Container container;  // container
    private JPanel colorPanel, color1,color2,color3,color4,color5; //panel which display selected color
    private Color[] colorarray = new Color[5];
/**
 * Constructor makes the User interface and sets up the variables
 * @param  null;
 */    
    public MS_UserColormap() {  //constructor
        super( "Color" );  //JFrame constructor is called
        container = getContentPane();  // get container
        colorPanel = new JPanel(new FlowLayout());  // initialize the panel
        color1 = new JPanel();
        color2 =  new JPanel();
        color3 = new JPanel();
        color4 = new JPanel();
        color5 = new JPanel();
        
        // adding panels to colorPanel
        colorPanel.add(color1);
        colorPanel.add(color2);
        colorPanel.add(color3);
        colorPanel.add(color4);
        colorPanel.add(color5);
        
        JButton ColormapButton = new JButton( "Colormap" );  // initialize the button called colormap
        ColormapButton.addActionListener(new ActionListener() { //add monitor to Colormap button 
            public void actionPerformed( ActionEvent e ){//and handle the corresponding event
                   JColorChooser chooser = new JColorChooser();  // color chooser
                    for(int i = 0; i < colorarray.length ; i++){
                       Color color = chooser.showDialog
                               (MS_UserColormap.this,"Colormap",Color.white);
                        if (color != null){  //if no color selected
                            switch(i){
                                case 0: color1.setBackground(color); 
                                colorarray[i] = color;
                                break;
                                case 1: color2.setBackground(color);
                                colorarray[i] = color;
                                break;
                                case 2: color3.setBackground(color);
                                colorarray[i] = color;
                                break;
                                case 3: color4.setBackground(color); 
                                colorarray[i] = color;
                                break;
                                case 4: color5.setBackground(color); 
                                colorarray[i] = color;
                                break;
                        };
                        }			
                    }
        
            }
        });

       
        
         container.add(ColormapButton,BorderLayout.NORTH); // add button to JFrame on top
         container.add(colorPanel,BorderLayout.CENTER);  // add panel to JFrame in centre
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close JFrame windows and exit program
         setSize( 500, 250 );   //size of windows
         setVisible(true);  //set it visibale
    }
    
    public static void main(String args[]){
        new MS_UserColormap();
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