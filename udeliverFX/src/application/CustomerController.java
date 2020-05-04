package application;
import javafx.scene.control.Button;
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


public class CustomerController implements Initializable{
	@FXML
	private AnchorPane rootPane;
	@FXML
	private TableView<CustomerTable> customer;
	@FXML
	private TableColumn<CustomerTable,String> Address;
	@FXML
	private TableColumn<CustomerTable,String> TotalTip;
	@FXML
	private TableColumn<CustomerTable,String> visits;
	@FXML
	private TableColumn<CustomerTable,String> Average;
	@FXML
	private TableColumn<CustomerTable,Button> AddDelivery;
	@FXML
	private TableColumn<CustomerTable,Button> DeleteCustomer;
	ObservableList<CustomerTable> oblist = FXCollections.observableArrayList(); 
	private int UserID;
	public void setUser(int user) {
		UserID = user;
		System.out.println("Cust user:"+ UserID);
	}
	public int getUser() {
		return UserID;
	}
	@Override 
	public void initialize(URL url,ResourceBundle rb)
	{		try {
		String Query = "SELECT * FROM customer";
		Connection con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement(Query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			String address = rs.getString("Address");
			String zip = rs.getString("Zip");
			int RestID = rs.getInt("RestaurantID");
			Button addBtn = new Button("+");
			
			addBtn.setOnAction(e->{
				System.out.println("AD running");
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/application/AddDelivery.fxml"));
				AnchorPane ADPane = null;
				try {
					ADPane = (AnchorPane) loader.load();
				} catch (IOException e1) {
					
					System.out.println("unable to load in customer add button");
				}
				ADController controller = loader.getController();
					controller.ATF.setText(address);
					controller.ZTF.setText(zip);	
				controller.LoadRestaurantList(UserID);
				controller.setUserID(UserID);

				rootPane.getChildren().setAll(ADPane);
				
			});
			Button deleteBtn = new Button("-");
			deleteBtn.setOnAction(e->{String CustomerQuery = "SELECT customer.* FROM customer,driverscustomer WHERE driverscustomer.CustomerID = customer.ID AND driverscustomer.DriverID = '"+UserID+"' ";
			
			PreparedStatement Cps = null;
			try {
				Cps = con.prepareStatement(CustomerQuery);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			ResultSet Crs = null;
			try {
				Crs = Cps.executeQuery();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			try {
				while(Crs.next()) {
					int CustomerID = Crs.getInt("ID");
					String DeleteDeliv = "DELETE FROM delivery WHERE DriverID = '"+UserID+"' AND CustomerID = '"+CustomerID+"'"; 
					PreparedStatement dropDelivery = con.prepareStatement(DeleteDeliv);
					dropDelivery.executeUpdate();
					String DeleteDriverCustomer = "DELETE FROM driverscustomer WHERE CustomerID = '"+CustomerID+"' AND DriverID = '"+UserID+"' "; 
					PreparedStatement dropdriversCustomer= con.prepareStatement(DeleteDriverCustomer);
					dropdriversCustomer.executeUpdate();	
					String DeleteCustomer = "DELETE FROM customer WHERE ID = '"+CustomerID+"'"; 
					PreparedStatement dropCustomer = con.prepareStatement(DeleteCustomer);
					dropCustomer.executeUpdate();	
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			});
			float TipOnAverage = rs.getFloat("TotalTip") / rs.getInt("visits");
			oblist.add(new CustomerTable(rs.getString("Address"),Float.toString(rs.getFloat("TotalTip")),Integer.toString(rs.getInt("visits")),Float.toString(TipOnAverage),addBtn,deleteBtn));
			}
		
	}catch(SQLException ex) {
		Logger.getLogger(RestaurantController.class.getName()).log(Level.SEVERE,null,ex);
	}
	Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
	TotalTip.setCellValueFactory(new PropertyValueFactory<>("TotalTip"));
	visits.setCellValueFactory(new PropertyValueFactory<>("visits"));
	Average.setCellValueFactory(new PropertyValueFactory<>("Average"));
	AddDelivery.setCellValueFactory(new PropertyValueFactory<>("add"));
	DeleteCustomer.setCellValueFactory(new PropertyValueFactory<>("Delete"));
	
	customer.setItems(oblist);
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
		System.out.println("");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/UserPanel2.fxml"));
		AnchorPane UserPane = (AnchorPane) loader.load();
		UserController controller = loader.getController();
		controller.setUser(UserID);
		rootPane.getChildren().setAll(UserPane);
		System.out.println("User running");
			
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
	private void LoadRestaurant(MouseEvent event) throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/RestaurantPane.fxml"));
		AnchorPane RestaurantPane = (AnchorPane) loader.load();
		RestaurantController controller = loader.getController();
		controller.setUser(UserID);
		controller.loadData();
		
		//AnchorPane RestaurantPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/RestaurantPane.fxml"));
		rootPane.getChildren().setAll(RestaurantPane);
		System.out.println("restaurant running");
	}
	@FXML
	private void LoadAD(ActionEvent event) throws IOException {
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
}