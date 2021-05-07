/*Customer Record Class
This class is used for getter and setter methods for variables that we need*/
public class CustomerRecord
{
    //variable declaration
    protected String firstName, lastName, address, telephone, age, income, picName, points;
    
    public CustomerRecord (String firstName, String lastName, String address, String telephone, String age, String income, String picName, String points)
    {//constructor
	//declare and assign variables
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
	this.telephone = telephone;
	this.age = age;
	this.income = income;
	this.picName = picName;
	this.points = points; 
    }//end of constructor

    protected String getPicName()
    {
	return picName;
    }//getter for picname

    protected String getPoints ()
    {
	return points; 
    }//getter for points
    
    protected String getFirstName ()
    {
	return firstName;
    }//getter for first name


    protected String getLastName ()
    {
	return lastName;
    }//getter for last name


    protected String getAdress ()
    {
	return address;
    }//getter for address


    protected String getTelephone ()
    {
	return telephone;
    }//getter for telephone
    
    protected void setPoints(int newPoint)
    {
	points =""+newPoint;
    }//setter for points

    protected String getAge ()
    {
	return age;
    }//getter for age

    protected String getIncome ()
    {
	return income;
    }//getter for income
}//end of class
