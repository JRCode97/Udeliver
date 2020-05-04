package application;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;


public class ADController implements Initializable{
	@FXML
	private AnchorPane rootPane;
	private int UserID;
	@FXML
	public TextField ATF;
	@FXML
	public TextField CTF;
	@FXML
	public TextField STF;
	@FXML
	public TextField ZTF;
	@FXML 
	public ComboBox<String> RCB;
	@FXML
	private TextField TATF;
	@Override 
	public void initialize(URL url,ResourceBundle rb)
	{	
		
	}
	public void LoadRestaurantList(int User) {
		System.out.println("Restlist running");
		LinkedList<String> restaurantNames = new LinkedList<String>();
		Connection con = Connect.getConnection();
		String getRest = "SELECT Name FROM restaurant,driversrestaurant,driver WHERE  driversrestaurant.RestaurantID = restaurant.ID AND driversrestaurant.DriverID = driver.DriverID";
		try {
			PreparedStatement ps = con.prepareStatement(getRest);
			ResultSet rs = ps.executeQuery();


			while(rs.next())
			{
				System.out.println("found one");
				String name = rs.getString("Name");
				restaurantNames.add(name);
				System.out.println("went through");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<String> RestList  = FXCollections.observableArrayList(restaurantNames); 
		RCB.setItems(RestList);
	}
	@FXML
	private void LoadDash(MouseEvent event) throws IOException {

			AnchorPane DashPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/newUI.fxml"));
			rootPane.getChildren().setAll(DashPane);
			
	}
	@FXML
	private void LoadUser(MouseEvent event) throws IOException {

			AnchorPane UserPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/UserPanel.fxml"));
			rootPane.getChildren().setAll(UserPane);
			
	}
	@FXML
	private void LoadExpense(MouseEvent event) throws IOException {

			AnchorPane ExpensePane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/ExpensesPane.fxml"));
			rootPane.getChildren().setAll(ExpensePane);
			
	}
	@FXML
	private void LoadRestaurant(MouseEvent event) throws IOException {

			AnchorPane RestaurantPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/RestaurantPane.fxml"));
			rootPane.getChildren().setAll(RestaurantPane);
			
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int user) {
		UserID = user;
		System.out.println("set AD ID: "+ UserID );
	}
	@FXML 
	private void AddDelivery(ActionEvent event) throws SQLException
	{	Connection con = Connect.getConnection();
		String Address = ATF.getText();
		String City = CTF.getText();
		String State = STF.getText();
		String zipCode = ZTF.getText();
		int zip = Integer.parseInt(zipCode);
		String name =RCB.getValue();
		System.out.println("Starting");
		String findRestID = "SELECT restaurant.ID,AllTimeTotal FROM restaurant,driversrestaurant WHERE Name = ? AND driversrestaurant.DriverID = '"+UserID+"'AND driversrestaurant.RestaurantID = restaurant.ID";
		PreparedStatement pStatement = con.prepareStatement(findRestID);
		pStatement.setString(1, name);
		ResultSet result = pStatement.executeQuery();
		while(result.next()) {
		int Restid = result.getInt("ID");
		float alltime = result.getFloat("AllTimeTotal");
		String TipAmount = TATF.getText();
		String Check = "SELECT TotalTip,visits,customerID FROM customer,delivery WHERE customer.ID = delivery.CustomerID AND delivery.DriverID = '"+UserID+"' AND delivery.Address = customer.Address AND delivery.Zip = customer.Zip AND delivery.Address = '"+Address+"'";
		PreparedStatement CheckExisting = con.prepareStatement(Check);
		ResultSet rs = CheckExisting.executeQuery();
		if(rs.next()){
			System.out.println("found");
			float totalTip = rs.getFloat("TotalTip");
			float Tip = Float.parseFloat(TipAmount);
			float TipTotal = totalTip + Tip;
			int amountOfVisits = rs.getInt("visits") + 1;
			int CustomerID = rs.getInt("customerID");
			System.out.println(TipTotal);
			System.out.println(CustomerID);
			String update = "UPDATE customer SET TotalTip = '"+TipTotal+"',visits = '"+amountOfVisits+"' WHERE ID = '"+CustomerID+"'";
			PreparedStatement post = con.prepareStatement(update);
			
			post.executeUpdate();
			
			
			String add = "INSERT INTO delivery(ID,Address,City,State,Zip,Tip,DriverID,RestaurantID,CustomerID)VALUES(0,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(add);
			ps.setString(1,Address);
			ps.setString(2,City);
			ps.setString(3,State);
			ps.setInt(4,zip);
			ps.setFloat(5,Tip);
			ps.setInt(6, UserID);
			ps.setInt(7, Restid);
			ps.setInt(8, CustomerID);
			ps.executeUpdate();
			
			String updateTotalTip = "UPDATE restaurant SET AllTimeTotal = ? WHERE ID = '"+Restid+"'";
			PreparedStatement updateTT = con.prepareStatement(updateTotalTip);
			updateTT.setFloat(1, TipTotal);
			updateTT.executeUpdate();
			String DriverCustomerADD = "INSERT INTO driverscustomer(ID,DriverID,CustomerID) VALUES(0,'"+UserID+"','"+CustomerID+"')";
			PreparedStatement AddDC = con.prepareStatement(DriverCustomerADD);
			AddDC.executeUpdate();
			
		}
		
		else if(!rs.next()) {
			
			System.out.println("not found");
			float Tip = Float.parseFloat(TipAmount);
			String makeCustomer = "INSERT INTO customer(ID,Address,Zip,TotalTip,visits,RestaurantID) VALUES(0,?,?,?,1,'"+Restid+"')";
			PreparedStatement make = con.prepareStatement(makeCustomer);
			make.setString(1,Address);
			make.setInt(2, zip);
			make.setFloat(3, Tip);
			make.executeUpdate();
			
			String getCust = "SELECT customer.ID FROM customer,driversrestaurant WHERE customer.restaurantID = '"+Restid+"' AND driversrestaurant.RestaurantID = customer.RestaurantID AND driversrestaurant.DriverID = '"+UserID+"' AND customer.Address = '"+Address+"'";
			PreparedStatement getCustomerID = con.prepareStatement(getCust);
			ResultSet custIDList = getCustomerID.executeQuery();
			while(custIDList.next()) {
			int customerID = custIDList.getInt("ID");
			
			String addDel = "INSERT INTO delivery(ID,Address,City,State,Zip,Tip,DriverID,RestaurantID,CustomerID)VALUES(0,?,?,?,?,?,'"+UserID+"','"+Restid+"','"+customerID+"')";
			PreparedStatement ps = con.prepareStatement(addDel);
			ps.setString(1,Address);
			ps.setString(2,City);
			ps.setString(3,State);
			ps.setInt(4,zip);
			ps.setFloat(5,Tip);
			ps.executeUpdate();
			
			String updateTotalTip = "UPDATE restaurant SET AllTimeTotal = ? WHERE ID = '"+Restid+"'";
			float newTotal = Tip + alltime;
			PreparedStatement updateTT = con.prepareStatement(updateTotalTip);
			updateTT.setFloat(1, newTotal);
			updateTT.executeUpdate();
			String DriverCustomerADD = "INSERT INTO driverscustomer(ID,DriverID,CustomerID) VALUES(0,'"+UserID+"','"+customerID+"')";
			PreparedStatement AddDC = con.prepareStatement(DriverCustomerADD);
			AddDC.executeUpdate();
			}}
		}
		
		}
	}

