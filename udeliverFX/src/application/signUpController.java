package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class signUpController  implements Initializable{
	@FXML
	private AnchorPane rootPane;
	private int userID;
	@FXML
	public TextField FNTxt;
	@FXML
	public TextField LNTxt;
	@FXML 
	public TextField EmTxt;
	@FXML 
	public TextField PWF;	
	@FXML 
	public TableView<ModelRestaurantTable> restaurants;
	@FXML 
	public TableColumn<ModelRestaurantTable,String> col_Name;
	@FXML
	public TableColumn<ModelRestaurantTable,String> col_wage;
	@FXML
	public TableColumn<ModelRestaurantTable,String> col_weeklyHours;
	@FXML 
	public TableColumn<ModelRestaurantTable,String> Col_WeeklyPay;
	@FXML
	public TableColumn<ModelRestaurantTable,String> Col_WeekTip;
	@FXML
	public TableColumn<ModelRestaurantTable,String> Col_TotalinWeek;
	@FXML
	public TableColumn<ModelRestaurantTable,String> Col_AllTimeTotal;
	 
	ObservableList<ModelRestaurantTable> oblist = FXCollections.observableArrayList(); 
	@Override 
	public void initialize(URL url,ResourceBundle rb)
	{
	


	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int user) {
		userID = user;
	}
	@FXML
	private void LoadExpense(MouseEvent event) throws IOException {

			AnchorPane ExpensePane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/ExpensesPane.fxml"));
			rootPane.getChildren().setAll(ExpensePane);
			
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

			AnchorPane CustomerPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/CustomerPane.fxml"));
			rootPane.getChildren().setAll(CustomerPane);
			
	}
	@FXML
	private void LoadSignIn(MouseEvent event) throws IOException {

			AnchorPane SignInPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/signINpage.fxml"));
			rootPane.getChildren().setAll(SignInPane);
			
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
		controller.setUserID(userID);
		rootPane.getChildren().setAll(ADPane);
	}
	
	@FXML
	public void SignUp(ActionEvent event) {

		try {//converting text field input to strings 
			String Fname = null;
			if(!FNTxt.getText().equals("")) {
			Fname = FNTxt.getText();
			}
			else {
				System.out.println("Must enter FN");
			}
			String Lname = LNTxt.getText();
			String email = EmTxt.getText();
			String password = PWF.getText();
			//creating a new Driver Object 
			Driver User = new Driver(Fname, Lname, email, password);
			//connect to database
			Connection con = Connect.getConnection();	
			//creating a query to check if the Database already has the email present
			String CheckQuery = "SELECT FirstName FROM driver WHERE Email = '"+email+"' ";
			PreparedStatement recieve = con.prepareStatement(CheckQuery);
			//creates a list of results 
			ResultSet rs = recieve.executeQuery(CheckQuery);
			//check the list to see if there are matches for email
			if (rs.next())
			{
				System.out.println("That email is already in use.");
				EmTxt.clear();
				
			}
			else {
				//if email nonexistent in  database it stores all the textfields to the driver table in database

			String Query = "INSERT INTO driver (DriverID,FirstName,LastName,Email,Password,TotalTip"
					+ ",AmountOfDeliveries) VALUES('0',?,?,?,?,?,?,?) ";
			PreparedStatement post = con.prepareStatement(Query);
			post.setString(1, Fname);
			post.setString(2, Lname);
			post.setString(3, email);
			post.setString(4, password);
			post.setDouble(5, 0);
			post.setInt(6, 0);
			post.setInt(7, 0);
			post.executeUpdate();
			System.out.println("Done inserting");}
		} catch (Exception e1) {
			System.out.println(e1);
		}

}}
