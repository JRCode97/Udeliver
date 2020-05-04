package application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;


public class MainController implements Initializable{
	@FXML
	private AnchorPane rootPane;
	private int userID;
	@FXML 
	public TextField EmailTxt;
	@FXML 
	public TextField PasswordTxt;	
	@FXML
	public Button SignUpBtn;
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
			controller.loadData();
			controller.setUser(userID);
			System.out.println(userID);
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
	private void LoadSignUp(ActionEvent event) throws IOException {

			AnchorPane SignupPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/SignUPpage.fxml"));
			rootPane.getChildren().setAll(SignupPane);
			
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
	public void SignIn(ActionEvent event) {

		try {
			String email = EmailTxt.getText();
			String password = PasswordTxt.getText();
			//getting connection to DB and checking if the email and password combo is existent 
			
			Connection con = Connect.getConnection();
			String Query = "SELECT DriverID FROM driver WHERE Email = '"+email+"' AND Password = '"+password+"'";
			
			PreparedStatement recieve = con.prepareStatement(Query);
			//makes resultset list and if it is 
			
			ResultSet rs = recieve.executeQuery(Query);
			if(rs.next())
			{//TODO:: add new scene which displays stats and options once access is granted 
				setUserID(rs.getInt("DriverID"));
				
							
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/application/newUI.fxml"));
				AnchorPane DashPane = (AnchorPane) loader.load();
				DashController controller = loader.getController();
				controller.setUser(userID);
				controller.UpdateHighestTip();
				controller.UpdateAverage();
				controller.UpdateTopCust();
				controller.loadDashData();
				controller.loadMonthTIps();
			
				System.out.println(userID);
				//AnchorPane RestaurantPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/RestaurantPane.fxml"));
				rootPane.getChildren().setAll(DashPane);
				System.out.println("dash running");
			}
			else 
			{
				//User nonexistent in database
				EmailTxt.clear();
				PasswordTxt.clear();
				System.out.println("User not found");
				
				
			}
			System.out.println("Done Checking");
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

}
