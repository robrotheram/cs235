/*! \mainpage CS235 Visuliser Documnetation
 *
 * \section intro_sec Introduction
 *
 * This Program will beable to Load data from a CSV File and display it in a 
 * table. You will also be able to make a number of charts from the dataset
 * <br>
 * Project Authors
 * <ul>
 * <li>Robert Fletcher</li>
 * <li>Kerry Tolhurst</li>
 * <li>William Bray</li>
 * <li>William Jones</li>
 * <li>Alex McDonough</li>
 * <li>Wyler Gu</li>
 * <li>Zhenjie Mu</li>
 * </ul>
 *
 * \section install_sec Installation
 *
 * \subsection step1 Step 1: Download the zip file
 * \subsection step2 Step 2: Extract the zip file
 * \subsection step1 Step 3: Run the GroupMSAplication.jar
 *  
 *
 */

package cs235;

/**
 *
 * @author Robert
 */
public class GroupMSApplication {
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MS_BasicGUI().setVisible(true);
            }
        });
    }
}
