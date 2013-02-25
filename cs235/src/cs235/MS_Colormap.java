/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

/**
 *
 * @author Zhenjie Mu
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
    
public class MS_Colormap extends JFrame {
    private Container container;  // container
    private JPanel colorPanel; //panel which display selected color

    public MS_Colormap() {  //constructor
        super( "Color" );  //JFrame constructor is called
        container = getContentPane();  // get container
        colorPanel = new JPanel();  // initialize the panel

        JButton ColormapButton = new JButton( "Colormap" );  // initialize the button called colormap
        ColormapButton.addActionListener(new ActionListener() { //add monitor to Colormap button 

				public void actionPerformed( ActionEvent e ){//and handle the corresponding event
                   JColorChooser chooser = new JColorChooser();  // color chooser
                   Color color = chooser.showDialog
                           (MS_Colormap.this,"Colormap",Color.white);  //get selected color
                   if (color == null){  //if no color selected
						color = Color.white; //then set color to white
					}else{  
						colorPanel.setBackground(color); // change the panel color to the color which selected
				    }
                }
			}
		);
          container.add(ColormapButton,BorderLayout.NORTH); // add button to JFrame on top
          container.add(colorPanel,BorderLayout.CENTER);  // add panel to JFrame in centre
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close JFrame windows and exit program
          setSize( 500, 250 );   //size of windows
          setVisible(true);  //set it visibale
    }
public static void main(String args[]){
    new MS_Colormap();
    }
}
