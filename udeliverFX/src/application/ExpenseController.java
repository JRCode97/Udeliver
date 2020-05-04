package application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.*;


public class ExpenseController implements Initializable{
	@FXML
	private AnchorPane rootPane;
	@FXML
	private TextField MGTF;
	@FXML
	private TextField CPTF;	
	@FXML
	private TextField ITF;	
	@FXML
	private TextField MTF;	
	@FXML
	private Label Tipinmonth;
	@FXML 
	private Label MonthExpense;
	@FXML
	private Label TotalTxt;
	private int UserID;
	public void setUser(int user) {
		UserID = user;
	}
	public int getUser() {
		return UserID;
	}
	@Override 
	public void initialize(URL url,ResourceBundle rb)
	{
		
	}
	@FXML
	private void LoadDash(MouseEvent event) throws IOException, SQLException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/newUI.fxml"));
		AnchorPane DashPane = (AnchorPane) loader.load();
		DashController controller = loader.getController();
		controller.setUser(UserID);
		controller.UpdateHighestTip();
		controller.UpdateAverage();
		controller.UpdateTopCust();
		controller.loadDashData();
		System.out.println(UserID);
		//AnchorPane RestaurantPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/RestaurantPane.fxml"));
		rootPane.getChildren().setAll(DashPane);
			
	}
	@FXML
	private void LoadUser(MouseEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/UserPanel2.fxml"));
		AnchorPane UserPane = (AnchorPane) loader.load();
		UserController controller = loader.getController();
		controller.setUser(UserID);
		rootPane.getChildren().setAll(UserPane);
		System.out.println("User running");
			
	}
	@FXML
	private void LoadRestaurant(MouseEvent event) throws IOException, SQLException {

		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/RestaurantPane.fxml"));
		AnchorPane RestaurantPane = (AnchorPane) loader.load();
		RestaurantController controller = loader.getController();
		controller.setUser(UserID);
		controller.loadData();
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
		controller.setUser(UserID);
		rootPane.getChildren().setAll(CustPane);
			
	}
	@FXML
	private void LoadAD(ActionEvent event) throws IOException, SQLException {
		System.out.println("AD running");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/AddDelivery.fxml"));
		AnchorPane ADPane = (AnchorPane) loader.load();
		ADController controller = loader.getController();
		controller.LoadRestaurantList(UserID);
		System.out.println("Dash ID:"+ UserID);
		controller.setUserID(UserID);
		rootPane.getChildren().setAll(ADPane);
	}
	@FXML
	private void TotalUp(ActionEvent event) throws SQLException
	{	Connection con = Connect.getConnection();
		
		float Gas = Float.parseFloat(MGTF.getText());
		float CarPayment = Float.parseFloat(CPTF.getText());
		float Insurance = Float.parseFloat(ITF.getText());
		float Maintenance = Float.parseFloat(MTF.getText());
		float Total = Gas+ CarPayment + Insurance + Maintenance;
		TotalTxt.setText(Float.toString(Total));
		String Query = "INSERT INTO expenses (ExpenseID,Gas,CP,Insurance,Maintenance,DriverID) VALUES(0,?,?,?,?,?) ";
		PreparedStatement ps = con.prepareStatement(Query);
		ps.setFloat(1, Gas);
		ps.setFloat(2, CarPayment);
		ps.setFloat(3, Insurance);
		ps.setFloat(4, Maintenance);
		ps.setInt(5,UserID );
		ps.executeUpdate();
		
	}
}