
package drivingInterface;
import java.util.*;



public class Driver extends Person {
		
		private int milesTraveled;
		private double TotalTipsCollected;
		private int deliveriesTaken;

    	private String email;
        private String password;
        private Job DriverJob;
        private LinkedList<IndividualDelivery> Transactions;
        private boolean IsActive;
	
	public Driver(String name,String Email,String Password,String workPlace,int workHours,int payRate)
	{
		super(name);
		milesTraveled = 0;
		TotalTipsCollected = 0;
		deliveriesTaken = 0;
		email = Email;
        password = Password;
        DriverJob = new Job(workPlace,workHours,payRate);
        Transactions = new LinkedList<IndividualDelivery>();
        
		
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
    public void AddTips(double Tip) throws IllegalArgumentException 
    {
    	if (Tip > 0) {
        this.TotalTipsCollected += Tip;
    	
    	}
    	else
    		throw new IllegalArgumentException("Tips cannot be negative");
    }
    public double GetLastTip() 
    {
    	return this.Transactions.getLast().getIndividualTip();
    }
    public Date LastDeliveryDate()
    {
    	return this.Transactions.getLast().getToday();
    }
	
	

}






