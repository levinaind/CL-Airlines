// Read File Program
//This program is used to read file from the notes for the username and password
import java.io.*;
import java.util.*;
public class ReadFile
{
    //variable declaration
    protected String[] userName = new String [5];
    protected String[] password = new String [5];
    public ReadFile (String fileName)
    {//constructor
	//pass filename in the user class
	String input;

	try
	{//getting the user name and password from the textfile
	    BufferedReader br = new BufferedReader (new FileReader (fileName));
	    StringTokenizer st;
	    for (int i = 0 ; i < 5 ; i++)
	    {
		input = br.readLine ();
		 st = new StringTokenizer(input, "/");
		userName[i]=st.nextToken();
		password[i]=st.nextToken();

	    }
	    br.close();

	}
	catch (IOException e)
	{
	    System.out.println ("Unable to open the file.");
	}//end of try catch method
    }//end of readfile
    
    protected String getPassword(String usernameInput)
    {//getting the password from userInput so we can compare
	String thepass="";
	for(int i=0;i<10;i++)
	{
	    if(usernameInput.equals(userName[i]) )
	    {//if username is found, you get the password and pass it
		thepass= password[i];
		break;
		//end of if
	    }
	}
	return thepass;
    }//end of get pass
}//end of class
