package application;
import java.util.*;



public class Driver {
		
		private int milesTraveled;
		private double TotalTipsCollected;
		private int deliveriesTaken;
		private String Fname;
		private String Lname;
    	private String email;
        private String password;

        
	
	public Driver(String FName,String LName,String Email,String Password)
	{
		
		Fname = FName;
		Lname = LName;
		milesTraveled = 0;
		TotalTipsCollected = 0;
		deliveriesTaken = 0;
		email = Email;
        password = Password;

        
		
	}
	public String getFirstName()
	{
		return Fname;
	}
    public int getTotalMilesTraveled() 
    {
        return milesTraveled;
    }     


    public double getTipsCollected() {
        return TotalTipsCollected;
    }



    public int getDeliveriesTaken() {
    	
        return deliveriesTaken;
    }

    public void AddToDeliveryCount()
    {
    	this.deliveriesTaken += 1;
    }


    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }
    public void AddTips(double Tip) 
    {
    	if (Tip > 0) {
        this.TotalTipsCollected += Tip;
    	
    	}
    }

}






