/*Customer Data Base Log in Screen
Programmed by Carl Reina and Levina Indrawan
This class is used to make a log in screen so it is not accessible by the general public
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LogInScreen implements ActionListener
{
    //variable declaration
    private JButton logIn, BWLogIn;
    private JTextField userName;
    private JPasswordField password;
    private JPanel panel;
    private ReadFile r;

    public LogInScreen (JPanel panel)
    { //constructor

	//variable assignment
	this.panel = panel;
	logIn = new JButton ("LOG IN");
	BWLogIn = new JButton ("DARK MODE LOG IN");
	r = new ReadFile ("Employee Login.txt");
	logIn.addActionListener (this);
	BWLogIn.addActionListener (this);

	Color m = new Color (59, 89, 152);
	panel.setBackground (m);
    } //end of constructor


    protected void screen ()
    { //creative components
	//declaration of label arrays
	JLabel[] logInLables = new JLabel [5];

	//declaration for textfield & password field
	userName = new JTextField ();
	password = new JPasswordField ();


	for (int i = 0 ; i < 4 ; i++)
	{
	    logInLables [i] = new JLabel ();
	}

	//setting icons and texts
	ImageIcon logo = new ImageIcon ("CL Airlines.png");
	logInLables [0].setIcon (logo);
	logInLables [1].setText ("Username: ");
	logInLables [2].setText ("Password: ");
	logInLables [3].setText ("- customer database -");

	for (int i = 0 ; i < 4 ; i++)
	{ //adding the label array to the panel
	    panel.add (logInLables [i]);

	} //adding buttons and textfields and password to the panel
	panel.add (userName);
	panel.add (password);
	panel.add (logIn);
	panel.add (BWLogIn);

	Font tf = new Font (("Sigmar One"), Font.PLAIN, 20);
	for (int i = 1 ; i < 3 ; i++)
	{ //setting colors for labels
	    logInLables [i].setFont (tf);
	    logInLables [i].setForeground (Color.white);
	}
	tf = new Font (("Sigmar One"), Font.BOLD, 30);
	logInLables [3].setFont (tf);

	//placing the labels, buttons, and pictures
	logInLables [0].setBounds (0, 0, 750, 250);
	logInLables [1].setBounds (150, 330, 150, 70);
	logInLables [2].setBounds (510, 330, 150, 70);
	logInLables [3].setBounds (270, 280, 400, 100);
	userName.setBounds (110, 390, 170, 50);
	password.setBounds (470, 390, 170, 50);
	logIn.setBounds (210, 460, 160, 30);
	BWLogIn.setBounds (400, 460, 160, 30);

	//setting background color
	Color subh = new Color (223, 227, 238);
	logIn.setBackground (subh);
	BWLogIn.setBackground (subh);
	logInLables [3].setForeground (subh);
    } //end of screen method


    public void actionPerformed (ActionEvent f)
    { //For buttons
	//variable declaration
	String actualUserName, actualPass, realPass;
	Font er = new Font ("Courier", Font.PLAIN, 17);
	JLabel error = new JLabel ("Username / Password is incorrect.");

	error.setFont (er);
	error.setForeground (Color.red);
	if (f.getSource () == logIn)
	{ //if user choose light mode log in

	    //getting input from textfield & comparing to notes
	    actualUserName = userName.getText ();
	    actualPass = r.getPassword (actualUserName);
	    realPass = password.getText ();
	    if (realPass.equals (actualPass) && !realPass.equals (""))
	    { //if password is correct
		panel.removeAll ();
		//call out the database buttons class
		DataBaseButtons c = new DataBaseButtons (panel, "display", "search", "sort", "adding", "delete", "log out");

		//calling out the place button method
		c.placeButton (0, 60, 160, 60);
		c.buttonAdding ();
		panel.repaint ();
	    }
	    else
	    { //if pass is incorect, show an error message
		panel.add (error);
		error.setBounds (210, 500, 350, 50);
	    }

	} //end of light mode button
	else
	{ //if user chose dark mode log in
	    //getting input from textfield & comparing to note
	    actualUserName = userName.getText ();
	    actualPass = r.getPassword (actualUserName);
	    realPass = password.getText ();

	    if (realPass.equals (actualPass) && !realPass.equals (""))
	    { //if password is correct
		panel.removeAll ();
		//call out the dark mode class
		BWDataBaseButtons m = new BWDataBaseButtons (panel, "display", "search", "sort", "adding", "delete", "log out");
		//calling out the button placing
		m.placeButton (0, 60, 160, 60);
		m.buttonAdding ();
		panel.repaint ();

	    }
	    else
	    { //if password is wrong
		panel.add (error);
		error.setBounds (210, 500, 350, 50);

	    }
	} //end of dark mode log in
    } //end of actionlistener
} //end of class
