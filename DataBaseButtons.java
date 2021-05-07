/*Data Base Buttons Class
This is the class that has codes of the buttons in the customer database */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DataBaseButtons implements ActionListener
{
    //buttons declaration
    protected JButton[] feature = new JButton [7];
    private JButton[] addOnFt = new JButton [15];
    private JPanel panel, inputPanel;
    private JTextField[] txtField = new JTextField [8];
    private JFrame inputBox;
    private CustomerDataBase db = new CustomerDataBase ("record.txt");

    public DataBaseButtons (JPanel panel, String title0, String title1, String title2, String title3, String title4, String title5)
    { //comstructor
	//introduce a new panel

	this.panel = panel;
	inputPanel = new JPanel ();
	inputBox = new JFrame ();

	for (int i = 0 ; i < 7 ; i++)
	{ //adding features button to action listener
	    feature [i] = new JButton ();
	    feature [i].addActionListener (this);
	}
	//setting text for buttons
	feature [0].setText (title0);
	feature [1].setText (title1);
	feature [2].setText (title2);
	feature [3].setText (title3);
	feature [4].setText (title4);
	feature [5].setText (title5);
	feature [6].setText ("Points");
	addOnFt [0] = new JButton ("Sort by Income");
	addOnFt [1] = new JButton ("Sort by Age");
	addOnFt [2] = new JButton ("Search");
	addOnFt [3] = new JButton ("Add");
	addOnFt [4] = new JButton ("Delete");
	addOnFt [5] = new JButton ("Exit");
	addOnFt [6] = new JButton ("Child");
	addOnFt [7] = new JButton ("Adult");
	addOnFt [8] = new JButton ("Senior");
	addOnFt [9] = new JButton ("Sort by Points");
	addOnFt [10] = new JButton ("+ Points");
	addOnFt [11] = new JButton ("-- Points");
	addOnFt [12] = new JButton ("Member");
	addOnFt [13] = new JButton ("Insider");
	addOnFt [14] = new JButton ("VIP");


	for (int i = 0 ; i < 15 ; i++)
	{ //adding the extra feature to action listener
	    addOnFt [i].addActionListener (this);
	}

	//assinging textfields
	inputBoxesAssign ();

	//setting background color
	Color c = new Color (59, 89, 152);
	panel.setBackground (c);
	inputPanel.setBackground (c);
    } //constructor


    private void inputBoxesAssign ()
    { //asigning the variables
	for (int i = 0 ; i < 8 ; i++)
	{
	    txtField [i] = new JTextField ();

	}
	///made a new frame and panel for input
	inputPanel.setLayout (null);
	inputBox.setContentPane (inputPanel);
	inputBox.setTitle ("Input");
	inputPanel.add (addOnFt [5]);
	inputBox.setVisible (false);
	inputBox.setResizable (false);
	inputBox.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
    } //ending of assigning to input frame


    private void inputBoxesClear ()
    { //clearing textfield
	for (int i = 0 ; i < 8 ; i++)
	{
	    txtField [i].setText ("");
	}
    } //end of clear textfield


    protected void buttonAdding ()
    { //adding buttons to panel
	for (int i = 0 ; i < 7 ; i++)
	{
	    panel.add (feature [i]);
	}

	JLabel title = new JLabel ();
	ImageIcon img = new ImageIcon ("Customer Database.jpg");
	title.setIcon (img);
	panel.add (title);
	title.setBounds (0, 5, 375, 50);
    } //emd of adding buttons to panel


    private void displayingScrollBar (String records)
    { //made a scrollbar to display all the information
	//puting the info on the text area and put into the scroll
	JTextArea txt = new JTextArea ();
	JScrollPane sp = new JScrollPane (txt);

	//adding a label for info
	JLabel info = new JLabel ("Template = First Name, Last Name, Address, Phone No, Age, Annual Income, Points");
	panel.add (info);
	//assigning the spots
	info.setBounds (20, 125, 750, 20);
	Color fc = new Color (247, 247, 247);
	Font f = new Font ("Times New Romans", Font.ITALIC, 13);
	info.setFont (f);
	info.setForeground (fc);

	for (int i = 0 ; i < db.getRecord () ; i++)
	{ //set the infromation to the textarea
	    txt.setText (records);
	}
	//adding to panel
	panel.add (sp);
	//place everything to the panel

	txt.setBounds (20, 150, 750, 400);
	sp.setBounds (20, 150, 750, 400);
	sp.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    } //end of displaying scroll bar



    protected void placeButton (int x, int y, int h, int w)
    { //place a button method
	//set the background color
	Color c = new Color (223, 227, 238);

	for (int i = 0 ; i < 5 ; i++)
	{ //set
	    feature [i].setBounds (x, y, h, w);
	    feature [i].setBackground (c);
	    x += 160;
	}
	//set pictures for buttons
	ImageIcon displayIcon = new ImageIcon ("notes.jpg");
	feature [0].setIcon (displayIcon);
	displayIcon = new ImageIcon ("magnify.jpg");
	feature [1].setIcon (displayIcon);
	displayIcon = new ImageIcon ("levels.jpg");
	feature [2].setIcon (displayIcon);
	displayIcon = new ImageIcon ("plus.jpg");
	feature [3].setIcon (displayIcon);
	displayIcon = new ImageIcon ("minus.jpg");
	feature [4].setIcon (displayIcon);
	displayIcon = new ImageIcon ("exiting.jpg");
	feature [5].setIcon (displayIcon);
	feature [5].setBounds (645, 0, 140, 40);
	feature [5].setBackground (Color.white);
	feature [6].setBounds (505, 0, 140, 40);
	feature [6].setBackground (c);
    } //end of placing buttons


    public void actionPerformed (ActionEvent e)
    { //action performed for buttons
	//assign variables
	String lName, fName, address, telphone, age, income, recordList = "", profilePicFile, points;
	int option, x = 0, y = 0;
	//for eror trap
	int num, error1 = 0, error2 = 0, error3 = 0, counter = 0;
	double num1;
	//for labels
	JLabel[] infoLabel = new JLabel [9];

	for (int i = 0 ; i < 9 ; i++)
	{
	    infoLabel [i] = new JLabel ();
	}
	//fname, lastname, address, tel, age, income, profilepic, title

	if (e.getSource () == feature [0])
	{ //DISPLAY
	    panel.removeAll ();

	    recordList = db.displayRecord ();
	    displayingScrollBar (recordList);

	    buttonAdding ();
	    panel.repaint ();
	} //end of display

	else if (e.getSource () == feature [1])
	{ //SEARCH
	    //clear all
	    txtField [0].setBackground (Color.white);
	    txtField [1].setBackground (Color.white);
	    txtField [7].setBackground (Color.white);

	    panel.removeAll ();
	    panel.repaint ();
	    inputPanel.removeAll ();
	    inputPanel.repaint ();
	    inputBoxesClear ();
	    //add regular buttons
	    buttonAdding ();
	    inputBox.setSize (300, 180);
	    //set labels
	    Font f = new Font (("Sigmar One"), Font.BOLD, 15);
	    infoLabel [0].setText ("Enter First Name");
	    infoLabel [1].setText ("Enter Last Name");
	    infoLabel [8].setText ("Enter the required information");
	    infoLabel [8].setFont (f);

	    //adding all the labels and buttons to the input panel
	    inputPanel.add (infoLabel [8]);
	    infoLabel [8].setBounds (50, 0, 375, 50);
	    infoLabel [8].setForeground (Color.white);
	    for (int i = 0 ; i < 2 ; i++)
	    {
		infoLabel [i].setForeground (Color.white);
		inputPanel.add (infoLabel [i]);
		inputPanel.add (txtField [i]);
		infoLabel [i].setBounds (10, 50 + y, 100, 20);
		txtField [i].setBounds (120, 50 + y, 100, 20);
		y += 30;
	    }
	    inputPanel.add (addOnFt [2]);
	    inputPanel.add (addOnFt [5]);
	    inputBox.setVisible (true);
	    inputBox.setLocationRelativeTo (null);
	    addOnFt [2].setBounds (60, 115, 100, 20);
	    addOnFt [5].setBounds (160, 115, 100, 20);
	} //end of search

	else if (e.getSource () == feature [2])
	{ //SORT
	    //remove all in panel
	    JLabel[] LabelSort = new JLabel [3];
	    panel.removeAll ();
	    panel.repaint ();
	    buttonAdding ();
	    //add buttons to panel
	    panel.add (addOnFt [0]);
	    panel.add (addOnFt [1]);
	    for (int i = 6 ; i < 10 ; i++)
	    {
		panel.add (addOnFt [i]);
	    }
	    for (int i = 12 ; i < 15 ; i++)
	    {
		panel.add (addOnFt [i]);
	    }

	    Color fc = new Color (223, 227, 238);
	    Font f = new Font ("Sigmar One ", Font.BOLD, 36);
	    //set images to buttons
	    ImageIcon pic = new ImageIcon ("baby.jpg");
	    addOnFt [6].setIcon (pic);
	    pic = new ImageIcon ("adult.jpg");
	    addOnFt [7].setIcon (pic);
	    pic = new ImageIcon ("old.jpg");
	    addOnFt [8].setIcon (pic);
	    pic = new ImageIcon ("age.jpg");
	    addOnFt [1].setIcon (pic);
	    pic = new ImageIcon ("points.jpg");
	    addOnFt [9].setIcon (pic);
	    pic = new ImageIcon ("money.jpg");
	    addOnFt [0].setIcon (pic);
	    pic = new ImageIcon ("bronze.jpg");
	    addOnFt [12].setIcon (pic);
	    pic = new ImageIcon ("silver.jpg");
	    addOnFt [13].setIcon (pic);
	    pic = new ImageIcon ("gold.jpg");
	    addOnFt [14].setIcon (pic);
	    //set labels and buttons to panel
	    for (int w = 0 ; w < 3 ; w++)
	    {
		x += 110;

		LabelSort [w] = new JLabel ();
		panel.add (LabelSort [w]);
		LabelSort [w].setFont (f);
		LabelSort [w].setForeground (fc);
		LabelSort [w].setBounds (x, 175, 200, 50);
		x += 120;
	    }

	    LabelSort [0].setText ("Sort");
	    LabelSort [1].setText ("View");
	    LabelSort [2].setText ("Tiers");
	    x = 0;

	    for (int i = 0 ; i < 2 ; i++)
	    {
		addOnFt [i].setBounds (70, 225 + x, 180, 50);

		addOnFt [i].setBackground (fc);
		x += 75;
	    }
	    addOnFt [9].setBounds (70, 375, 180, 50);
	    addOnFt [9].setBackground (fc);
	    x = 0;
	    for (int s = 6 ; s < 9 ; s++)
	    {
		addOnFt [s].setBounds (300, 225 + x, 180, 50);
		addOnFt [s].setBackground (fc);
		x += 75;
	    }
	    x = 0;
	    for (int s = 12 ; s < 15 ; s++)
	    {
		addOnFt [s].setBounds (540, 225 + x, 180, 50);
		addOnFt [s].setBackground (fc);
		x += 75;
	    }
	} //end of sort

	else if (e.getSource () == feature [3])
	{ //ADDING
	    //clear all
	    panel.removeAll ();
	    panel.repaint ();
	    buttonAdding ();
	    inputPanel.removeAll ();
	    inputPanel.repaint ();
	    inputBoxesClear ();
	    //creating labels for search bar
	    inputBox.setSize (400, 250);
	    for (int i = 0 ; i < 9 ; i++)
	    {
		infoLabel [i] = new JLabel ();
	    }

	    Font f = new Font (("Sigmar One"), Font.BOLD, 20);
	    //setting labels
	    infoLabel [8].setFont (f);
	    infoLabel [8].setText ("Enter Required Information");
	    infoLabel [0].setText ("First Name");
	    infoLabel [1].setText ("Last Name");
	    infoLabel [2].setText ("Address");
	    infoLabel [3].setText ("Telephone");
	    infoLabel [4].setText ("Age");
	    infoLabel [5].setText ("Income");
	    infoLabel [6].setText ("Profile File");
	    infoLabel [7].setText ("Points");

	    for (int i = 0 ; i < 8 ; i++)
	    {
		infoLabel [i].setForeground (Color.white);
		inputPanel.add (infoLabel [i]);
		inputPanel.add (txtField [i]);
		txtField [i].setBackground (Color.white);

	    }

	    inputPanel.add (infoLabel [8]);
	    infoLabel [8].setForeground (Color.white);
	    inputPanel.add (addOnFt [3]);
	    inputPanel.add (addOnFt [5]);

	    inputBox.setVisible (true);
	    inputBox.setLocationRelativeTo (null);


	    infoLabel [8].setBounds (70, 0, 375, 50);
	    //First Name
	    infoLabel [0].setBounds (10, 40, 250, 50);
	    txtField [0].setBounds (80, 55, 100, 20);
	    //Last name
	    infoLabel [1].setBounds (10, 70, 250, 50);
	    txtField [1].setBounds (80, 85, 100, 20);
	    //Age
	    infoLabel [4].setBounds (30, 100, 250, 50);
	    txtField [4].setBounds (80, 115, 100, 20);
	    //Telephone
	    infoLabel [3].setBounds (200, 40, 250, 50);
	    txtField [3].setBounds (265, 55, 100, 20);
	    //Address
	    infoLabel [2].setBounds (205, 70, 250, 50);
	    txtField [2].setBounds (265, 85, 100, 20);
	    //Income
	    infoLabel [5].setBounds (208, 100, 250, 50);
	    txtField [5].setBounds (265, 115, 100, 20);
	    //Profile name
	    infoLabel [6].setBounds (10, 130, 250, 50);
	    txtField [6].setBounds (80, 145, 100, 20);
	    txtField [6].setText ("profilepic.png");
	    //Points
	    infoLabel [7].setBounds (210, 130, 250, 50);
	    txtField [7].setBounds (265, 145, 100, 20);
	    //Buttons
	    addOnFt [3].setBounds (100, 180, 100, 30);
	    addOnFt [5].setBounds (200, 180, 100, 30);

	} //end of adding

	else if (e.getSource () == feature [4])
	{ //DELETE
	    //removing all
	    panel.removeAll ();
	    panel.repaint ();
	    buttonAdding ();
	    inputBoxesClear ();
	    inputPanel.removeAll ();
	    inputPanel.repaint ();
	    inputBox.setSize (300, 180);
	    //setting text
	    Font f = new Font (("Sigmar One"), Font.BOLD, 15);
	    infoLabel [0].setText ("Enter First Name");
	    infoLabel [1].setText ("Enter Last Name");
	    infoLabel [8].setText ("Enter the required information");
	    infoLabel [8].setFont (f);
	    //placing labels and buttons
	    inputPanel.add (infoLabel [8]);
	    infoLabel [8].setBounds (50, 0, 375, 50);
	    infoLabel [8].setForeground (Color.white);
	    for (int i = 0 ; i < 2 ; i++)
	    {
		infoLabel [i].setForeground (Color.white);
		infoLabel [i].setBounds (10, 50 + y, 100, 20);
		txtField [i].setBounds (120, 50 + y, 100, 20);
		y += 30;
		inputPanel.add (infoLabel [i]);
		inputPanel.add (txtField [i]);
	    }
	    JButton ok = new JButton ("Enter");
	    inputPanel.add (addOnFt [4]);
	    inputPanel.add (addOnFt [5]);
	    inputBox.setVisible (true);
	    inputBox.setLocationRelativeTo (null);
	    addOnFt [4].setBounds (60, 115, 100, 20);
	    addOnFt [5].setBounds (160, 115, 100, 20);
	} //end of deleting

	else if (e.getSource () == feature [6])
	{ //POINTS
	    //clear all
	    txtField [0].setBackground (Color.white);
	    txtField [1].setBackground (Color.white);

	    txtField [7].setBackground (Color.white);
	    panel.removeAll ();
	    panel.repaint ();
	    buttonAdding ();
	    inputBoxesClear ();
	    inputPanel.removeAll ();
	    inputPanel.repaint ();
	    inputBox.setSize (300, 200);
	    //set text
	    Font f = new Font (("Sigmar One"), Font.BOLD, 15);
	    infoLabel [0].setText ("Enter First Name");
	    infoLabel [1].setText ("Enter Last Name");
	    infoLabel [7].setText ("Points");
	    infoLabel [8].setText ("Enter the required information");
	    infoLabel [8].setFont (f);
	    //placing buttons and textfield to panel
	    inputPanel.add (infoLabel [8]);
	    infoLabel [8].setBounds (50, 0, 375, 50);
	    infoLabel [8].setForeground (Color.white);
	    for (int i = 0 ; i < 2 ; i++)
	    {
		infoLabel [i].setForeground (Color.white);
		infoLabel [i].setBounds (10, 45 + y, 100, 20);
		txtField [i].setBounds (120, 45 + y, 100, 20);
		y += 30;
		inputPanel.add (infoLabel [i]);
		inputPanel.add (txtField [i]);
	    }
	    infoLabel [7].setBounds (10, 105, 100, 20);
	    txtField [7].setBounds (120, 105, 100, 20);
	    infoLabel [7].setForeground (Color.white);
	    inputPanel.add (infoLabel [7]);
	    inputPanel.add (txtField [7]);
	    inputPanel.add (addOnFt [10]);
	    inputPanel.add (addOnFt [11]);
	    inputPanel.add (addOnFt [5]);
	    inputBox.setVisible (true);
	    inputBox.setLocationRelativeTo (null);
	    addOnFt [10].setBounds (30, 135, 90, 20);
	    addOnFt [11].setBounds (110, 135, 90, 20);
	    addOnFt [5].setBounds (190, 135, 80, 20);
	} //end of adding or minus points

	else if (e.getSource () == addOnFt [0])
	{ //sort age
	    panel.removeAll ();
	    panel.repaint ();
	    buttonAdding ();
	    recordList = db.sortCustomer (1);
	    displayingScrollBar (recordList);
	    inputBox.setVisible (false);
	} //end of sort age

	else if (e.getSource () == addOnFt [1])
	{ //sort income
	    panel.removeAll ();
	    panel.repaint ();
	    buttonAdding ();
	    recordList = db.sortCustomer (0);
	    displayingScrollBar (recordList);
	    inputBox.setVisible (false);
	} //end of sort income

	else if (e.getSource () == addOnFt [2])
	{ //search

	    for (int i = 0 ; i < 2 ; i++)
	    { //setting everything white
		txtField [i].setBackground (Color.white);
	    }

	    if (txtField [0].getText ().equals (""))
	    { //if the first name is blank, error trap
		JOptionPane.showMessageDialog (null, "Please fill in the required fields!", "Inavlid data!", JOptionPane.ERROR_MESSAGE);
		counter++; //To prevent from messageDialog to print a second time

		txtField [0].requestFocus ();
		txtField [0].setBackground (Color.red);
	    }

	    if (txtField [1].getText ().equals (""))
	    { //if last name is blank, error trap

		if (counter != 1) //To see if MessageDialog was printed before
		{
		    JOptionPane.showMessageDialog (null, "Please fill in the required fields!", "Inavlid data!", JOptionPane.ERROR_MESSAGE);
		    counter++;
		}

		txtField [1].requestFocus ();
		txtField [1].setBackground (Color.red);
	    }

	    if (counter == 0)
	    { //if everything is good, then it is searched
		profile (txtField [0].getText (), txtField [1].getText ());
		inputBox.setVisible (false);
	    }
	} //end of searching

	else if (e.getSource () == addOnFt [3])
	{ //add
	    for (int i = 4 ; i < 6 ; i++)
	    {
		txtField [i].setBackground (Color.white);

	    }
	    txtField [7].setBackground (Color.white);
	    //getting all the required info
	    fName = txtField [0].getText ();
	    lName = txtField [1].getText ();
	    address = txtField [2].getText ();
	    telphone = txtField [3].getText ();
	    age = txtField [4].getText ();
	    income = txtField [5].getText ();
	    profilePicFile = txtField [6].getText ();
	    points = txtField [7].getText ();

	    try
	    { //error trap for num if they put alphabets on age
		num = Integer.parseInt (txtField [4].getText ());
		error1++;
	    }
	    catch (NumberFormatException n)
	    {
		JOptionPane.showMessageDialog (null, "Please fill in the required fields!", "Inavlid data!", JOptionPane.ERROR_MESSAGE);
		counter++; //To prevent from messageDialog to print a second time
		if (error1 != 1)
		{
		    txtField [4].requestFocus ();
		    txtField [4].setBackground (Color.red);
		}
	    }

	    try
	    { //error trap if they put alphabets on income
		num1 = Double.parseDouble (txtField [5].getText ());
		error2++;
	    }
	    catch (NumberFormatException C)
	    {
		if (counter != 1) //To see if MessageDialog was printed before
		{
		    JOptionPane.showMessageDialog (null, "Please fill in the required fields!", "Inavlid data!", JOptionPane.ERROR_MESSAGE);
		    counter++;
		}
		if (error2 != 1)
		{
		    txtField [5].requestFocus ();
		    txtField [5].setBackground (Color.red);
		}
	    }

	    try
	    { //error trap if they put alphabets on points
		num = Integer.parseInt (txtField [7].getText ());
		error3++;
	    }
	    catch (NumberFormatException C)
	    {
		if (counter != 1) // To see if MessageDialog was printed before
		{
		    JOptionPane.showMessageDialog (null, "Please fill in the required fields!", "Inavlid data!", JOptionPane.ERROR_MESSAGE);
		    counter++;
		}
		if (error3 != 1)
		{
		    txtField [7].requestFocus ();
		    txtField [7].setBackground (Color.red);
		}
	    }


	    if (counter == 0)
	    { //to confirm if you want to add
		int confirm = JOptionPane.showConfirmDialog (null, "Do you want add this name?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		db.insertCustomer (fName, lName, address, telphone, age, income, profilePicFile, points);
		inputBox.setVisible (false);
	    }
	} //end of points


	else if (e.getSource () == addOnFt [4])
	{ //delete

	    fName = txtField [0].getText ();
	    lName = txtField [1].getText ();

	    db.deleteCustomer (lName, fName);

	    inputBox.setVisible (false);
	} //end of deleting


	else if (e.getSource () == addOnFt [5])
	{ //exit
	    inputBox.setVisible (false);
	} //closes the input box


	else if (e.getSource () == addOnFt [6])
	{ //Child
	    panel.removeAll ();
	    displayingScrollBar (db.displayAgeRange (0));
	    buttonAdding ();
	    panel.repaint ();
	} //end of child range

	else if (e.getSource () == addOnFt [7])
	{ //Adult
	    panel.removeAll ();
	    displayingScrollBar (db.displayAgeRange (1));
	    buttonAdding ();
	    panel.repaint ();
	} //end of adult range

	else if (e.getSource () == addOnFt [8])
	{ //Senior
	    panel.removeAll ();
	    displayingScrollBar (db.displayAgeRange (2));
	    buttonAdding ();
	    panel.repaint ();
	} //end of senior range


	else if (e.getSource () == addOnFt [9])
	{ //Sort Points
	    panel.removeAll ();
	    panel.repaint ();
	    buttonAdding ();
	    recordList = db.sortCustomer (2);
	    displayingScrollBar (recordList);
	    inputBox.setVisible (false);
	} //sort by points

	else if (e.getSource () == addOnFt [10])
	{ //Add points

	    txtField [7].setBackground (Color.white);
	    try
	    {
		num = Integer.parseInt (txtField [7].getText ());
		error1++;
	    }
	    catch (NumberFormatException l)
	    { //error trap if they do not enter points in number
		JOptionPane.showMessageDialog (null, "Please input correct Value", "Inavlid data!", JOptionPane.ERROR_MESSAGE);
		counter++; //To prevent from messageDialog to print a second time
		if (error1 != 1)
		{
		    txtField [7].requestFocus ();
		    txtField [7].setBackground (Color.red);
		}
	    }
	    if (counter == 0)
	    { //if everything is correct
		db.customerPoints (txtField [1].getText (), txtField [0].getText (), txtField [7].getText (), 0);
		inputBox.setVisible (false);
	    }
	} //end of addingpoints


	else if (e.getSource () == addOnFt [11])
	{ //Minus points

	    txtField [7].setBackground (Color.white);
	    try
	    {
		num = Integer.parseInt (txtField [7].getText ());
		error1++;
	    }
	    catch (NumberFormatException l)
	    { //error trap if they do not enter points in number
		JOptionPane.showMessageDialog (null, "Please input correct value", "Inavlid data!", JOptionPane.ERROR_MESSAGE);
		counter++; //To prevent from messageDialog to print a second time
		if (error1 != 1)
		{
		    txtField [7].requestFocus ();
		    txtField [7].setBackground (Color.red);
		}
	    }
	    if (counter == 0)
	    { //if everything is correct
		db.customerPoints (txtField [1].getText (), txtField [0].getText (), txtField [7].getText (), 1);
		inputBox.setVisible (false);
	    }
	} //end of minusing points


	else if (e.getSource () == addOnFt [12])
	{ //Bronze
	    panel.removeAll ();
	    displayingScrollBar (db.displayPointsRange (0));
	    buttonAdding ();
	    panel.repaint ();
	} //end of Bronze



	else if (e.getSource () == addOnFt [13])
	{ //Silver
	    panel.removeAll ();
	    displayingScrollBar (db.displayPointsRange (1));
	    buttonAdding ();
	    panel.repaint ();
	} //end of silver

	else if (e.getSource () == addOnFt [14])
	{ //Gold
	    panel.removeAll ();
	    displayingScrollBar (db.displayPointsRange (2));
	    buttonAdding ();
	    panel.repaint ();
	} //end of gold

	else
	{ //log out button
	    //require the password so not everyone can log out
	    String pass = readString ("Please enter the password to log out.");

	    if (pass.equals ("CarlLevinaBYE"))
	    {
		JOptionPane.showMessageDialog (null, "CL Airlines Closing", "BYE", JOptionPane.INFORMATION_MESSAGE);

		db.exit ();
		System.exit (0);
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "Wrong Password", "INPUT", JOptionPane.ERROR_MESSAGE);

	    }

	} //end of log out button
    } //end of actionlistener


    private static String readString (String action)
    { //Read String method to get user input
	String input;
	input = JOptionPane.showInputDialog (null, action);

	if (input == null)
	{
	    input = "cancelled";
	}
	while (input.equals (""))
	{ //error traps
	    JOptionPane.showMessageDialog (null, "No Input", "INPUT", JOptionPane.ERROR_MESSAGE);
	    input = JOptionPane.showInputDialog (null, action);
	    if (input == null)
	    {
		input = "cancelled";
	    }
	}
	return input;
    } //end of read string method


    private void profile (String fName, String lName)
    { //profile for search method
	int y = 0;

	//searched the customer
	String[] searchedCom = db.searchCustomer (lName, fName);

	//making the labels
	JLabel[] myProfile = new JLabel [8];
	ImageIcon theProfilePic = new ImageIcon (searchedCom [6]);
	Font words = new Font ("Dialog", Font.PLAIN, 25);
	if (searchedCom [1].equals (""))
	{
	    JOptionPane.showMessageDialog (null, "Customer not found");
	}
	else
	{
	    for (int i = 0 ; i < 8 ; i++)
	    { //assigning all the labels
		myProfile [i] = new JLabel ();
	    }
	    myProfile [0].setIcon (theProfilePic);
	    myProfile [1].setText ("First Name : " + searchedCom [0]);
	    myProfile [2].setText ("Last Name : " + searchedCom [1]);
	    myProfile [3].setText ("Address : " + searchedCom [2]);
	    myProfile [4].setText ("Phone Number : " + searchedCom [3]);
	    myProfile [5].setText ("Age : " + searchedCom [4]);
	    myProfile [6].setText ("Income : " + searchedCom [5]);
	    myProfile [7].setText ("Points: " + searchedCom [7]);
	    for (int i = 0 ; i < 8 ; i++)
	    { //adding the label array to the panel
		panel.add (myProfile [i]);

	    }
	    for (int i = 1 ; i < 8 ; i++)
	    { //placing the labels
		myProfile [i].setFont (words);
		myProfile [i].setBounds (40, 120 + y, 500, 50);
		myProfile [i].setForeground (Color.white);
		y += 55;
	    }
	    myProfile [0].setBounds (465, 150, 300, 350);
	}
    } //end of profile method
} //end of class


