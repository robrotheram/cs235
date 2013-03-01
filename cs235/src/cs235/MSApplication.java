/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs235;

/**
 *
 * @author Robert
 */
public class MSApplication {
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MS_BasicGUI().setVisible(true);
            }
        });
    }
}
