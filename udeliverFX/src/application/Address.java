package application;
public class Address {
	private String Address;
	private String City;
	private String State;
	private int zipCode;
	public Address(String address,String city,String state,int zip) {
		Address = address;
		City = city;
		State = state;
		zipCode = zip;
	}
	public void setAddress(String newAddress)
	{
		Address = newAddress;
	}
	public String getAddress()
	{
		return Address;
	}
	public void setCity(String newCity)
	{
		City = newCity;
	}
	public String getCity()
	{
		return City;
	}
	public void setState(String newState)
	{
		State = newState;
	}
	public String getState()
	{
		return State;
	}
	public void setZip(int newZip)
	{
		zipCode = newZip;
	}
	public int getZip()
	{
		return zipCode;
	}
	public String getWholeAddress()
	{
		String WholeAddress = Address +" "+ City +" "+ State +" "+ zipCode;
		return WholeAddress;
	}
	
}

