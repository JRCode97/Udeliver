package application;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;


public class RestaurantController implements Initializable{
	@FXML
	private AnchorPane rootPane;
	private int userID = 0;
	@FXML 
	private TableView<ModelRestaurantTable> restaurants;
	@FXML 
	private TableColumn<ModelRestaurantTable,String> col_Name;
	@FXML
	private TableColumn<ModelRestaurantTable,String> col_wage;
	@FXML
	private TableColumn<ModelRestaurantTable,String> col_weeklyHours;
	@FXML 
	private TableColumn<ModelRestaurantTable,String> Col_WeeklyPay;
	@FXML
	private TableColumn<ModelRestaurantTable,String> Col_WeekTip;
	@FXML
	private TableColumn<ModelRestaurantTable,String> Col_TotalinWeek;
	@FXML
	private TableColumn<ModelRestaurantTable,String> Col_AllTimeTotal;
	 
	ObservableList<ModelRestaurantTable> oblist = FXCollections.observableArrayList(); 
	public void setUser(int user) {
		userID = user;
		System.out.println("rest user :"+userID);
	}
	public int getUser() {
		return userID;
	}
	@Override 
	public void initialize(URL url,ResourceBundle rb)
	{
		//System.out.println(thisuser.getFirstName());
		//loadData();
	}
	public void loadData() throws SQLException {
		int RestID = 0;
		try {
			Connection con = Connect.getConnection();
			String getID = "SELECT RestaurantID FROM driversrestaurant WHERE DriverID = '"+userID+"' ";
			PreparedStatement get = con.prepareStatement(getID);
			ResultSet id = get.executeQuery();
			while(id.next()){
				System.out.println("");
				RestID = id.getInt("RestaurantID");
				
					String Query = "SELECT * FROM restaurant WHERE ID = '"+RestID+"'";
					PreparedStatement ps = con.prepareStatement(Query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						System.out.println("found info");
						Float payforWeek =  rs.getFloat("WeekHoursWorked") * rs.getFloat("Wage");
						Float DayTip = rs.getFloat("TipInDay");
						Float totalWeek = payforWeek +DayTip;
						String payForWeek = Float.toString(payforWeek);
						String weekTip = Float.toString(payforWeek);
						String TotalWeek = Float.toString(totalWeek);				
						System.out.println(payForWeek);
						System.out.println(weekTip);
						oblist.add(new ModelRestaurantTable(rs.getString("name"),Float.toString(rs.getFloat("Wage")),Float.toString(rs.getFloat("WeekHoursWorked")),payForWeek,Float.toString(rs.getFloat("TipInDay")),TotalWeek,Float.toString(rs.getFloat("AllTimeTotal"))));
						}
					
		
			}
				
			}
		catch(SQLException ex) {
			System.out.println("driverrest failed");

		}
		col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_wage.setCellValueFactory(new PropertyValueFactory<>("wage"));
		col_weeklyHours.setCellValueFactory(new PropertyValueFactory<>("weeklyHours"));
		Col_WeeklyPay.setCellValueFactory(new PropertyValueFactory<>("WeekPay"));
		Col_WeekTip.setCellValueFactory(new PropertyValueFactory<>("WeekTip"));
		Col_TotalinWeek.setCellValueFactory(new PropertyValueFactory<>("TotalinWeek"));
		Col_AllTimeTotal.setCellValueFactory(new PropertyValueFactory<>("AllTime"));
		restaurants.setItems(oblist);
		
	}
	@FXML
	private void LoadDash(MouseEvent event) throws IOException, SQLException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/newUI.fxml"));
		AnchorPane DashPane = (AnchorPane) loader.load();
		DashController controller = loader.getController();
		controller.setUser(userID);
		controller.UpdateHighestTip();
		controller.UpdateAverage();
		controller.UpdateTopCust();
		controller.loadDashData();
		System.out.println(userID);
		//AnchorPane RestaurantPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/RestaurantPane.fxml"));
		rootPane.getChildren().setAll(DashPane);
			
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
	private void LoadExpense(MouseEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/ExpensesPane.fxml"));
		AnchorPane ExpensePane = (AnchorPane) loader.load();
		ExpenseController controller = loader.getController();
		controller.setUser(userID);
		rootPane.getChildren().setAll(ExpensePane);
		System.out.println("Expense running");
			
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