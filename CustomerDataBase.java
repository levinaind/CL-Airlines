/*Customer Data Base Class
This program is coded for the main features of the data base
such as adding, deleting, displaying, etc*/

import java.io.*;
import java.util.*;
import javax.swing.*;
public class CustomerDataBase
{
    //variable declaration
    protected CustomerRecord[] customerList = new CustomerRecord [1000];
    private static int recordNum = 0;

    public CustomerDataBase (String fileName)  //passparameter here)
    { //Constructor
	//getting inputs from customer record text file to customer object
	String input;
	try
	{

	    BufferedReader br = new BufferedReader (new FileReader (fileName));
	    StringTokenizer st;
	    input = br.readLine ();

	    while (input != null)
	    {
		st = new StringTokenizer (input, "/");
		customerList [recordNum] = new CustomerRecord (st.nextToken (), st.nextToken (), st.nextToken (), st.nextToken (), st.nextToken (), st.nextToken (), st.nextToken (), st.nextToken ());
		recordNum++;
		input = br.readLine ();
	    }
	    br.close ();
	}
	catch (IOException e)
	{
	    System.out.println ("File is not found.");

	} //end of try and catch
    } //end of constructor


    protected static int getRecord ()
    { //getter method for recordnum
	return recordNum;
    }


    protected String displayRecord ()
    { //getting customer list in alphabetical order
	String Output = new String ();

	for (int i = 0 ; i < recordNum ; i++)
	{
	    Output += (customerList [i].getFirstName () + ", " + customerList [i].getLastName () + ", " + customerList [i].getAdress () + ", " + customerList [i].getTelephone () + ", " + customerList [i].getAge () + ", " + customerList [i].getIncome () + ", " + customerList [i].getPoints () + "\n" + "");
	}
	return Output;
    } //end of display record


    protected String[] searchCustomer (String LastName, String FirstName)  //Find where it needs to be place (ie if 15 is the location move 15- max one to the right)
    { //search customer record
	//putting all the elements in a string array

	String[] searched = new String [8];

	for (int s = 0 ; s < recordNum ; s++)
	{
	    if (LastName.compareToIgnoreCase (customerList [s].getLastName ()) == 0 && FirstName.compareToIgnoreCase (customerList [s].getFirstName ()) == 0)
	    { //if found, then do this
		searched [0] = customerList [s].getFirstName ();
		searched [1] = customerList [s].getLastName ();
		searched [2] = customerList [s].getAdress ();
		searched [3] = customerList [s].getTelephone ();
		searched [4] = customerList [s].getAge ();
		searched [5] = customerList [s].getIncome ();
		searched [6] = (customerList [s].getPicName ());
		searched [7] = customerList [s].getPoints ();
		break;
	    }
	}
	if (searched [0] == null)
	{ //if not found, then filled in with this
	    for (int i = 0 ; i < 8 ; i++)
	    {
		searched[i] = "";
	    }
	} //end of if
	return searched;
    } //end of searching


    protected void customerPoints (String LastName, String FirstName, String points, int calculation)    //Find where it needs to be place (ie if 15 is the location move 15- max one to the right)
    { //method to calculate points from customers
	try
	{
	    int originalP, newPoint = 0, location = -1;
	    for (int s = 0 ; s < recordNum ; s++)
	    {
		if (LastName.compareToIgnoreCase (customerList [s].getLastName ()) == 0 && FirstName.compareToIgnoreCase (customerList [s].getFirstName ()) == 0)
		{ //if customer is found
		    location = 0;

		    originalP = Integer.parseInt (customerList [s].getPoints ());
		    newPoint = Integer.parseInt (points);

		    if (calculation == 0)
		    { //if they want to add points
			originalP += newPoint;
		    }
		    else
		    { //they will minus the original points
			originalP -= newPoint;
		    }
		    //using setter method in the previous class
		    customerList [s].setPoints (originalP);
		    break;
		}
	    }
	    if (location == -1)
	    { //if customer is not found
		JOptionPane.showMessageDialog (null, "Customer is not found!", "Invalid!", JOptionPane.WARNING_MESSAGE);

	    }
	}
	catch (NumberFormatException e)
	{ //exception if values point is not a number
	    JOptionPane.showMessageDialog (null, "Please enter a value!", "Invalid!", JOptionPane.WARNING_MESSAGE);

	} //end of try and catch

    } //end of points method


    protected void insertCustomer (String fName, String lName, String address, String number, String age, String income, String profileFile, String points)
    { //adding customers
	if (fName.equals ("") || lName.equals ("") || address.equals ("") || number.equals ("") || age.equals ("") || income.equals ("") || points.equals (""))
	{ //if user input is nothing
	    JOptionPane.showMessageDialog (null, "Please enter the required fields!", "Missing values!", JOptionPane.WARNING_MESSAGE);
	}
	else
	{
	    int location = 0;
	    for (int i = 0 ; i < recordNum ; i++)
	    {
		if (lName.compareToIgnoreCase (customerList [i].getLastName ()) > 0)
		{ //finding customers thorugh last name
		    location++;
		}
		else if (lName.compareToIgnoreCase (customerList [i].getLastName ()) == 0)
		{ //if last name is the same, they find first name
		    if (fName.compareToIgnoreCase (customerList [i].getFirstName ()) > 0)
		    {
			location++;
		    }

		}
	    }
	    //ading record num
	    recordNum++;

	    for (int m = recordNum - 1 ; m > location ; m--)
	    { //placing the customer list positions
		customerList [m] = customerList [m - 1];
	    }
	    //new object
	    customerList [location] = new CustomerRecord (fName, lName, address, number, age, income, profileFile, points);
	} //end of if
    } //end of adding customers method


    protected void deleteCustomer (String LastName, String FirstName)
    {//deleting customer method
	int location = -1;

	for (int s = 0 ; s < recordNum ; s++)
	{//trying to find the customers
	    if (LastName.compareToIgnoreCase (customerList [s].getLastName ()) == 0 && FirstName.compareToIgnoreCase (customerList [s].getFirstName ()) == 0)
	    {
		location = s;
		break;
	    }

	}
	if (location == -1)
	{//if customer is not found
	    JOptionPane.showMessageDialog (null, "Customer is not found!", "Invalid!", JOptionPane.WARNING_MESSAGE);
	}
	else
	{//if customer is found
	    //asked to make sure
	    int confirm = JOptionPane.showConfirmDialog (null, "Do you want to delete this name?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if (confirm == 0)
	    {
		for (int i = location ; i < recordNum - 1 ; i++)
		{//customer gets deleted
		    customerList [i] = customerList [i + 1];
		}

		recordNum--;
	    }
	}//end of if
    }//end of class


    protected String sortCustomer (int num)
    {//sort customer
	CustomerRecord[] temporaryList = new CustomerRecord [recordNum];
	for (int i = 0 ; i < recordNum ; i++)
	{
	    temporaryList [i] = customerList [i];
	}
	if (num == 0)
	{//sort based on age
	    quickSortAge (temporaryList, 0, recordNum - 1);
	}
	else if (num == 1)
	{//sort based on income
	    quickSortIncome (temporaryList, 0, recordNum - 1);
	}
	else if (num == 2)
	{//sort based on points
	    quickSortPoints (temporaryList, 0, recordNum - 1);
	}
	return displayRecordForSort (temporaryList);
    }//end of sort


    private void quickSortIncome (CustomerRecord[] tempList, int low, int high)
    { //quick sort for age
	int left, right;
	CustomerRecord pivot = new CustomerRecord ("", "", "", "", "", "", "", "");
	if (low < high)
	{ //quick sort
	    pivot = tempList [low];
	    left = low;
	    right = high;
	    while (left < right)
	    {
		while (Integer.parseInt (pivot.getIncome ()) < Integer.parseInt (tempList [right].getIncome ()) && left < right)
		{
		    right--;
		}
		tempList [left] = tempList [right];
		while (Integer.parseInt (pivot.getIncome ()) >= Integer.parseInt (tempList [left].getIncome ()) && left < right)
		{
		    left++;
		}
		tempList [right] = tempList [left];

	    }
	    tempList [left] = pivot;
	    quickSortIncome (tempList, low, left - 1);
	    quickSortIncome (tempList, right + 1, high);
	}
    } //end of quick sort for income


    private void quickSortAge (CustomerRecord[] tempList, int low, int high)
    { //quick sort for age
	int left, right;
	CustomerRecord pivot = new CustomerRecord ("", "", "", "", "", "", "", "");
	if (low < high)
	{ //quick sort
	    pivot = tempList [low];
	    left = low;
	    right = high;
	    while (left < right)
	    {
		while (Integer.parseInt (pivot.getAge ()) < Integer.parseInt (tempList [right].getAge ()) && left < right)
		{
		    right--;
		}
		tempList [left] = tempList [right];
		while (Integer.parseInt (pivot.getAge ()) >= Integer.parseInt (tempList [left].getAge ()) && left < right)
		{
		    left++;
		}
		tempList [right] = tempList [left];

	    }
	    tempList [left] = pivot;
	    quickSortAge (tempList, low, left - 1);
	    quickSortAge (tempList, right + 1, high);
	}
    } //end of quick sort for age


    private void quickSortPoints (CustomerRecord[] tempList, int low, int high)
    { //quick sort for points in order

	int left, right;
	CustomerRecord pivot = new CustomerRecord ("", "", "", "", "", "", "", "");
	if (low < high)
	{ //quick sort
	    pivot = tempList [low];
	    left = low;
	    right = high;
	    while (left < right)
	    {
		while (Integer.parseInt (pivot.getPoints ()) < Integer.parseInt (tempList [right].getPoints ()) && left < right)
		{
		    right--;
		}
		tempList [left] = tempList [right];
		while (Integer.parseInt (pivot.getPoints ()) >= Integer.parseInt (tempList [left].getPoints ()) && left < right)
		{
		    left++;
		}
		tempList [right] = tempList [left];

	    }
	    tempList [left] = pivot;
	    quickSortPoints (tempList, low, left - 1);
	    quickSortPoints (tempList, right + 1, high);
	}
    } //end of quick sorting points



    private String displayRecordForSort (CustomerRecord[] tempList)
    { //display record for the sort
	String Output = new String ();
	//String[] Output = new String [recordNum];

	for (int i = 0 ; i < recordNum ; i++)
	{
	    Output += (tempList [i].getFirstName () + ", " + tempList [i].getLastName () + ", " + tempList [i].getAdress () + ", " + tempList [i].getTelephone () + ", " + tempList [i].getAge () + ", " + tempList [i].getIncome () + ", " + tempList [i].getPoints () + "\n" + "");
	}
	return Output;
    } //end of display record


    protected String displayAgeRange (int ageRange)
    { //method to determine the age range

	String Output = "";
	if (ageRange == 0)
	{ //age range is 0-18
	    for (int i = 0 ; i < recordNum ; i++)
	    { //add ppl
		if (Integer.parseInt (customerList [i].getAge ()) <= 18)
		{
		    Output += (customerList [i].getFirstName () + ", " + customerList [i].getLastName () + ", " + customerList [i].getAdress () + ", " + customerList [i].getTelephone () + ", " + customerList [i].getAge () + ", " + customerList [i].getIncome () + ", " + customerList [i].getPoints () + "\n" + "");
		}
	    }
	}
	else if (ageRange == 1)
	{ //age range is 19-64
	    for (int i = 0 ; i < recordNum ; i++)
	    { //add ppl
		if (Integer.parseInt (customerList [i].getAge ()) > 18 && Integer.parseInt (customerList [i].getAge ()) <= 64)
		{
		    Output += (customerList [i].getFirstName () + ", " + customerList [i].getLastName () + ", " + customerList [i].getAdress () + ", " + customerList [i].getTelephone () + ", " + customerList [i].getAge () + ", " + customerList [i].getIncome () + ", " + customerList [i].getPoints () + "\n" + "");
		}
	    }
	}
	else
	{ //age Range is 65++
	    for (int i = 0 ; i < recordNum ; i++)
	    { //add ppl
		if (Integer.parseInt (customerList [i].getAge ()) > 64)
		{
		    Output += (customerList [i].getFirstName () + ", " + customerList [i].getLastName () + ", " + customerList [i].getAdress () + ", " + customerList [i].getTelephone () + ", " + customerList [i].getAge () + ", " + customerList [i].getIncome () + ", " + customerList [i].getPoints () + "\n" + "");
		}
	    }
	}
	return Output;

    } //end of age range


    protected String displayPointsRange (int pointsRange)
    { //method to determine the points range
	String Output = "";
	if (pointsRange == 0)
	{ //if points range is 0-10000
	    for (int i = 0 ; i < recordNum ; i++)
	    { //adding people
		if (Integer.parseInt (customerList [i].getPoints ()) <= 10000)
		{
		    Output += (customerList [i].getFirstName () + ", " + customerList [i].getLastName () + ", " + customerList [i].getAdress () + ", " + customerList [i].getTelephone () + ", " + customerList [i].getAge () + ", " + customerList [i].getIncome () + ", " + customerList [i].getPoints () + "\n" + "");
		}
	    }
	}
	else if (pointsRange == 1)
	{ //points range is 10000-100000
	    for (int i = 0 ; i < recordNum ; i++)
	    { //adding people
		if (Integer.parseInt (customerList [i].getPoints ()) > 10000 && Integer.parseInt (customerList [i].getPoints ()) <= 100000)
		{
		    Output += (customerList [i].getFirstName () + ", " + customerList [i].getLastName () + ", " + customerList [i].getAdress () + ", " + customerList [i].getTelephone () + ", " + customerList [i].getAge () + ", " + customerList [i].getIncome () + ", " + customerList [i].getPoints () + "\n" + "");
		}
	    }
	}
	else
	{ //points Range is 100000+
	    for (int i = 0 ; i < recordNum ; i++)
	    { //adding people
		if (Integer.parseInt (customerList [i].getPoints ()) > 100000)
		{
		    Output += (customerList [i].getFirstName () + ", " + customerList [i].getLastName () + ", " + customerList [i].getAdress () + ", " + customerList [i].getTelephone () + ", " + customerList [i].getAge () + ", " + customerList [i].getIncome () + ", " + customerList [i].getPoints () + "\n" + "");
		}
	    }
	}
	return Output;

    } //end of points range


    protected void exit ()
    { //if customer presses log out
	try
	{ //everything is added back to record text file
	    PrintWriter pw = new PrintWriter (new FileWriter ("record.txt"));
	    for (int i = 0 ; i < recordNum ; i++)
	    {
		pw.println (customerList [i].getFirstName () + "/" + customerList [i].getLastName () + "/" + customerList [i].getAdress () + "/" + customerList [i].getTelephone () + "/" + customerList [i].getAge () + "/" + customerList [i].getIncome () + "/" + customerList [i].getPicName () + "/" + customerList [i].getPoints ());
	    }
	    pw.close ();
	}
	catch (IOException e)
	{
	    System.out.println ("Unable to open the file.");
	} //end of try catch
    } //end of exit method
} //end of class


