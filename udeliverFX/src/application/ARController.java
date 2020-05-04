package application;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;


public class ARController implements Initializable{
	private int UserID;
	@FXML
	private AnchorPane rootPane;
	@FXML
	private TextField NRTF;
	@FXML
	private TextField WTF;
	@FXML
	private TextField HTF;	
	@Override 
	public void initialize(URL url,ResourceBundle rb)
	{
		
	}
	@FXML
	private void LoadDash(MouseEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/newUI.fxml"));
		AnchorPane DashPane = (AnchorPane) loader.load();
		DashController controller = loader.getController();
		controller.setUser(UserID);
		System.out.println(UserID);
		//AnchorPane RestaurantPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/RestaurantPane.fxml"));
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
	private void LoadCustomer(MouseEvent event) throws IOException {

			AnchorPane CustomerPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/CustomerPane.fxml"));
			rootPane.getChildren().setAll(CustomerPane);
			
	}
	@FXML
	private void LoadAD(ActionEvent event) throws IOException {

		System.out.println("AD running");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/AddDelivery.fxml"));
		AnchorPane ADPane = (AnchorPane) loader.load();
		ADController controller = loader.getController();
		controller.LoadRestaurantList(UserID);
		System.out.println("AR ID:"+ UserID);
		controller.setUserID(UserID);
		rootPane.getChildren().setAll(ADPane);
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int user) {
		UserID = user;
	}
	@FXML
	private void AddRest(ActionEvent event) throws SQLException {
		
		
		
		String RestaurantName = NRTF.getText();
		float wage = Float.parseFloat(WTF.getText());
		float weekHours = Float.parseFloat(HTF.getText());
		String Query = "Insert INTO  restaurant(ID,Name,WeekHoursWorked,Wage,AllTimeTotal,DriverID) VALUES(0,?,?,?,0,'"+UserID+"')";
		
		Connection con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement(Query);
		ps.setString(1, RestaurantName);
		ps.setFloat(2, weekHours);
		ps.setFloat(3, wage);
		ps.executeUpdate();
		
		String getRestID = "SELECT ID FROM restaurant WHERE DriverID = '"+UserID+"'";
		PreparedStatement getRestid = con.prepareStatement(getRestID);
		ResultSet restIdList = getRestid.executeQuery();
		while(restIdList.next()) {
			int restID = restIdList.getInt("ID");
			String setDriverRest = "INSERT INTO driversrestaurant(ID,DriverID,RestaurantID) VALUES(0,'"+UserID+"','"+restID+"')";
			PreparedStatement DriverRestps =  con.prepareStatement(setDriverRest);
			DriverRestps.executeUpdate();
		}
	}
}