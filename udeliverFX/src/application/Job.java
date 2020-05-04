package application;
public class Job {
    private String nameOfEmployer;
    private int HoursWorked;
    private int DeliveriesTaken;
    private float DeliveryAmount;
    private float HourlyWage;
    
    public Job(String NameOfEmployer,int hoursWorked)
    {
       nameOfEmployer = NameOfEmployer;
       HoursWorked = hoursWorked;
       HourlyWage = 0;
       DeliveriesTaken = 0;
       DeliveryAmount = 0;
   
    }

    public String getNameOfEmployer() {
        return nameOfEmployer;
    }

    public void setNameOfEmployer(String nameOfEmployer) {
        this.nameOfEmployer = nameOfEmployer;
    }

    public int getHoursWorked() {
        return HoursWorked;
    }
    
    public void setHoursWorked(int weekHrs) {
        this.HoursWorked = weekHrs;
    }
    public float getAverage()
    {
    	return DeliveryAmount/DeliveriesTaken;
    }
    
  
}





