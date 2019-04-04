package drivingInterface;
import java.util.*;
public class IndividualDelivery {
	private static Customer CurrentCustomer;
	private static double IndividualTip;
	private static Date Today;
	public IndividualDelivery(Customer thisCustomer,double thisTip)
	{
		CurrentCustomer = thisCustomer;
		IndividualTip = thisTip;
		Today = new Date();
	}
	public Customer getCurrentCustomer() {
		return CurrentCustomer;
	}
	public double getIndividualTip() {
		return IndividualTip;
	}
	public Date getToday() {
		return Today;
	}


	
	
}
