package application;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.*;

public class UserController implements Initializable{
		@FXML
		private AnchorPane rootPane;
		@FXML
		private Button EditBtn;
		@FXML
		private TextField FNTF;
		@FXML 
		private TextField LNTF;
		@FXML
		private TextField ETF;
		@FXML
		private TextField PTF;

		private int UserID;
		public void setUser(int userID) {
			UserID = userID;
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
		private void LoadExpense(MouseEvent event) throws IOException {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/ExpensesPane.fxml"));
			AnchorPane ExpensePane = (AnchorPane) loader.load();
			ExpenseController controller = loader.getController();
			controller.setUser(UserID);
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
			controller.setUser(UserID);
			rootPane.getChildren().setAll(CustPane);
				
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
		private void EditUser(ActionEvent event) throws SQLException {
	/*		
			System.out.println("Running Edit");
			boolean FNFilled = false;
			boolean LNFilled = false;
			boolean EMFilled = false;
			boolean PWFilled = false;
			//String email = signINController.this.user.getEmail();
			Connection con = Connect.getConnection();

				String FN =  FNTF.getText();
				String FNQuery = "UPDATE driver SET FirstName = '"+FN+"' WHERE Email = '"+UserID+"'";
				String fn = FNTF.getText();
				PreparedStatement fnps = con.prepareStatement(FNQuery);
			
			
			if(!LNTF.getText().equals("")) {
			
			String LNQuery = "UPDATE driver SET LastName = ? WHERE Email = '"+UserID+"'";
			String ln = LNTF.getText();
			PreparedStatement lnps = con.prepareStatement(LNQuery);
			lnps.setString(1, ln);
			lnps.executeUpdate();}
			if(ETF.getText().equals("")) {
			String	EMQuery = "UPDATE driver SET Email = ? WHERE Email = '"+UserID+"'";
			String email = ETF.getText();
			PreparedStatement emps = con.prepareStatement(EMQuery);
			emps.setString(1, email);
			emps.executeUpdate();
			}
			if(PTF.getText() != null) {
			String NewPword = PTF.getText();
			String PWQuery = "UPDATE driver SET Password = ? WHERE Email = '"+UserID+"'";
			String pw = FNTF.getText();
			PreparedStatement pwps = con.prepareStatement(PWQuery);
			pwps.setString(1, pw);
			pwps.executeUpdate();
			}
			*/
			
			
			
			
		}
}
