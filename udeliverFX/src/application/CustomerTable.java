package application;
import java.util.*;

import javafx.scene.control.Button;

public class CustomerTable {

   public CustomerTable(String address, String totalTip, String visits, String average, Button add, Button delete) {
		super();
		Address = address;
		TotalTip = totalTip;
		this.visits = visits;
		Average = average;
		this.add = add;
		Delete = delete;
	}
String Address;
   String TotalTip;
   String visits;
   String Average;
   Button add;
   Button Delete;
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getTotalTip() {
	return TotalTip;
}
public void setTotalTip(String totalTip) {
	TotalTip = totalTip;
}
public String getVisits() {
	return visits;
}
public void setVisits(String visits) {
	this.visits = visits;
}
public String getAverage() {
	return Average;
}
public void setAverage(String average) {
	Average = average;
}
public Button getAdd() {
	return add;
}
public void setAdd(Button add) {
	this.add = add;
}
public Button getDelete() {
	return Delete;
}
public void setDelete(Button delete) {
	Delete = delete;
}

}


