package application;
import java.util.*;
public class IndividualDelivery {
	private static CustomerTable CurrentCustomer;
	private static float IndividualTip;
	private static float MilesTraveled;
	private Job CorrespondingRestaurant;
	private static Date Today;
	public IndividualDelivery(CustomerTable thisCustomer,float thisTip,Job Rest)
	{
		CurrentCustomer = thisCustomer;
		IndividualTip = thisTip;
		CorrespondingRestaurant = Rest;
		Today = new Date();
	}
	public CustomerTable getCurrentCustomer() {
		return CurrentCustomer;
	}
	public double getIndividualTip() {
		return IndividualTip;
	}
	public Date getToday() {
		return Today;
	}


	
	
}
