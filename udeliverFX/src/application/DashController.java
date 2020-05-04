package application;

import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;

public class DashController implements Initializable {
	@FXML
	private AnchorPane rootPane;
	@FXML
	private Button User;
	@FXML 
	private Label HighestTip;
	@FXML 
	private Label AverageTip;
	@FXML 
	private Label TopCustomer;
	@FXML
	private PieChart pieChart;
	@FXML 
	private Label Tipinmonth;
	@FXML
	private Label MonthGross;
	private int userID;
	public void setUser(int user) {
		userID = user;
		System.out.println(userID);
	}
	public int getUser() {
		return userID;
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}
	public void loadMonthTIps() throws SQLException {
		Connection con = Connect.getConnection();
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int Month = cal.get(Calendar.MONTH) + 1;
		int Year = cal.get(Calendar.YEAR);
		String date =Integer.toString(Year)+"-"+ Integer.toString(Month)+"-"+Integer.toString(dayOfMonth) ;
		String datebefore =Integer.toString(Year)+"-"+ Integer.toString(Month -1)+"-"+Integer.toString(dayOfMonth) ;
		
		String getMTQuery = "SELECT SUM(Tip) FROM delivery WHERE DriverID = '"+userID+"' AND CAST(delivery.DateOfDelivery AS Date) BETWEEN '"+datebefore+"'AND '"+date+"' ";
		PreparedStatement getMTPS = (PreparedStatement) con.prepareStatement(getMTQuery);
		ResultSet getMTRS = getMTPS.executeQuery();
		if(getMTRS.next()) {
			float totalTip = getMTRS.getFloat("SUM(Tip)");
			Tipinmonth.setText(Float.toString(totalTip));
		}
				
				
	}
	public void loadMonthExpense() throws SQLException {
		Connection con = Connect.getConnection();
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int Month = cal.get(Calendar.MONTH) + 1;
		int Year = cal.get(Calendar.YEAR);
		String date =Integer.toString(Year)+"-"+ Integer.toString(Month)+"-"+Integer.toString(dayOfMonth) ;
		String datebefore =Integer.toString(Year)+"-"+ Integer.toString(Month -1)+"-"+Integer.toString(dayOfMonth) ;
		
		String getMTQuery = "SELECT SUM(Tip) FROM delivery WHERE DriverID = '"+userID+"' AND CAST(delivery.DateOfDelivery AS Date) BETWEEN '"+datebefore+"'AND '"+date+"' ";
		PreparedStatement getMTPS = (PreparedStatement) con.prepareStatement(getMTQuery);
		ResultSet getMTRS = getMTPS.executeQuery();
		if(getMTRS.next()) {
			float totalTip = getMTRS.getFloat("SUM(Tip)");
			String getRestPay = "SELECT WeekHourWorked,Wage FROM restaurant,driversrestaurant WHERE driversrestauarant.RestaurantID = restaurant.ID AND driversrestaurant.DriverID = '"+userID+"' ";
			PreparedStatement getRPPS = (PreparedStatement) con.prepareStatement(getRestPay);
			ResultSet getRPRS = getRPPS.executeQuery();
			int Madewage=0;
			while(getRPRS.next()) {
				int Hours = getRPRS.getInt("WeekHoursWorked");
				int wage = getRPRS.getInt("Wage");
				int monthwage = Hours * wage;
				Madewage += monthwage;
			}
			float gross = Madewage + totalTip;
			MonthGross.setText(Float.toString(gross));
		}
		
		
	}
	public void loadDashData () {
		Connection con = Connect.getConnection();
		String getRestaurants = "SELECT Name,AllTimeTotal FROM restaurant WHERE DriverID = '"+userID+"'";
		try {
			PreparedStatement retrieveRest = (PreparedStatement) con.prepareStatement(getRestaurants);
			ResultSet RestList = retrieveRest.executeQuery();
			ObservableList<PieChart.Data>pieChartData = FXCollections.observableArrayList();
			int sum = 0;
			while(RestList.next()) {
				String name = RestList.getString("Name");
				float total = RestList.getInt("AllTimeTotal");
				System.out.println(name + " : " + total);
				pieChartData.add(new PieChart.Data(name, total));
			}
			if(!RestList.next()) {
				System.out.println("no rest found");
			}
			pieChart.setData(pieChartData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			

	}
	public void UpdateHighestTip() throws SQLException {
		Connection con = Connect.getConnection();
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int Month = cal.get(Calendar.MONTH) + 1;
		int Year = cal.get(Calendar.YEAR);
		String date =Integer.toString(Year)+"-"+ Integer.toString(Month)+"-"+Integer.toString(dayOfMonth) ;
		System.out.println(date);
		String getTopTip = "SELECT Tip FROM delivery WHERE DriverID = '"+userID+"' AND CAST(delivery.DateOfDelivery AS Date) = '"+date+"' ORDER BY Tip";
		java.sql.PreparedStatement getTT = con.prepareStatement(getTopTip);
		ResultSet TopTipList = getTT.executeQuery();
		System.out.println("tip working");
		ArrayList<Float> TipList = new ArrayList<>();
		int count=0;
		while(TopTipList.next()) {
			float Tip = TopTipList.getFloat("Tip");
			TipList.add(Tip);
			count++;
		}
		if(count > 0) {
		Collections.sort(TipList);
		float highest = TipList.get(TipList.size()-1);
		System.out.println(highest);
		HighestTip.setText("Highest Tip: " + Float.toString(highest));
		}	
	}
	public void UpdateAverage() throws SQLException {
		Connection con = Connect.getConnection();
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int Month = cal.get(Calendar.MONTH) + 1;
		int Year = cal.get(Calendar.YEAR);
		String date =Integer.toString(Year)+"-"+ Integer.toString(Month)+"-"+Integer.toString(dayOfMonth) ;
		System.out.println(date);
		String getAvgTip = "SELECT AVG(Tip) FROM delivery WHERE DriverID = '"+userID+"' AND CAST(delivery.DateOfDelivery AS Date) = '"+date+"' ORDER BY Tip";
		PreparedStatement getAT = (PreparedStatement) con.prepareStatement(getAvgTip);
		ResultSet ATList = getAT.executeQuery();
		if (ATList.next()) {
			float AvgTip = ATList.getFloat(1);
			AverageTip.setText("Average tip: "+Float.toString(AvgTip));
		}
		
	}
	public void UpdateTopCust() throws SQLException {
		Connection con = Connect.getConnection();
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int Month = cal.get(Calendar.MONTH) + 1;
		int Year = cal.get(Calendar.YEAR);
		String date =Integer.toString(Year)+"-"+ Integer.toString(Month)+"-"+Integer.toString(dayOfMonth) ;
		System.out.println(date);
		String getAvgTip = "SELECT Address FROM delivery WHERE DriverID = '"+userID+"' AND CAST(delivery.DateOfDelivery AS Date) = '"+date+"' ORDER BY Tip DESC";
		PreparedStatement getAT = (PreparedStatement) con.prepareStatement(getAvgTip);
		ResultSet ATList = getAT.executeQuery();
		
		if (ATList.next()) {
			String Address = ATList.getString("Address");
			System.out.println(Address);
			TopCustomer.setText("Top Customer: " + Address);
			
		}
		
	}
	@FXML
	public void LoadExpense(MouseEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/ExpensesPane.fxml"));
		AnchorPane ExpensePane = (AnchorPane) loader.load();
		ExpenseController controller = loader.getController();
		controller.setUser(userID);
		rootPane.getChildren().setAll(ExpensePane);
		System.out.println("Expense running");
			
	}
	@FXML
	private void LoadUser(MouseEvent event) throws IOException {
		System.out.println("");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/UserPanel2.fxml"));
		AnchorPane UserPane = (AnchorPane) loader.load();
		UserController controller = loader.getController();
		controller.setUser(userID);
		rootPane.getChildren().setAll(UserPane);
		System.out.println("User running");
	}
	@FXML
	private void LoadRestaurant(MouseEvent event) throws IOException, SQLException {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/RestaurantPane.fxml"));
			AnchorPane RestaurantPane = (AnchorPane) loader.load();
			RestaurantController controller = loader.getController();
			controller.setUser(userID);
			controller.loadData();
			
			//AnchorPane RestaurantPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/RestaurantPane.fxml"));
			rootPane.getChildren().setAll(RestaurantPane);
			System.out.println("restaurant running");
			
		
	}
	@FXML
	private void LoadCustomer(MouseEvent event) throws IOException {

		System.out.println("cust running");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/CustomerPane.fxml"));
		AnchorPane CustPane = (AnchorPane) loader.load();
		CustomerController controller = loader.getController();
		controller.setUser(userID);
		rootPane.getChildren().setAll(CustPane);
			
	}
	@FXML
	private void LoadAR(ActionEvent event) throws IOException {
		System.out.println("AR running");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/AddRestaurant.fxml"));
		AnchorPane ARPane = (AnchorPane) loader.load();
		ARController controller = loader.getController();
		controller.setUserID(userID);
		rootPane.getChildren().setAll(ARPane);
	}
	@FXML
	private void LoadAD(ActionEvent event) throws IOException, SQLException {
		System.out.println("AD running");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/AddDelivery.fxml"));
		AnchorPane ADPane = (AnchorPane) loader.load();
		ADController controller = loader.getController();
		controller.LoadRestaurantList(userID);
		System.out.println("Dash ID:"+ userID);
		controller.setUserID(userID);
		rootPane.getChildren().setAll(ADPane);
	}
	
}
