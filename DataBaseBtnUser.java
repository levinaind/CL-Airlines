/* Customer Database Airlines CPT 
Programmed by Carl Reina and Levina Indrawan from December 12 - January 14, 2019
This program is used to run the Customer Data Base for CL Airlines. There are a lot of features
such as adding, deleting, displaying customers, and etc */


import javax.swing.*;
import java.awt.*;

public class DataBaseBtnUser
{//Data Base User Class

    //main method
    public static void main (String[] arqs)
    {
	//make Frames & Panel
	JFrame myFrame = new JFrame ();
	JPanel panel = new JPanel ();
	panel.setLayout (null);

	//Calling the log in class
	LogInScreen log = new LogInScreen (panel);
	log.screen ();

	//Making the display of the window
	myFrame.setContentPane (panel);
	myFrame.setTitle ("Customer DataBase");
	myFrame.setSize (800, 600);
	myFrame.setVisible (true);
	myFrame.setLocationRelativeTo(null); 
	myFrame.setResizable (false);
	myFrame.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
    }//end of main
    
}//end of class

