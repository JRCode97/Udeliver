package drivingInterface;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class SignUp extends Application {
	  @Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {
	    // Create a pane and set its properties
	    GridPane pane = new GridPane();
	    pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	    pane.setHgap(5.5);
	    pane.setVgap(5.5);
	    pane.setStyle("-fx-background-color: lightgreen;");
	    
	    // Place nodes in the pane
	    Label FN = new Label("First Name:");
	    pane.add(FN, 0, 0);
	    TextField FNTxt = new TextField();
	    pane.add(FNTxt, 1, 0);
	    Label LN = new Label("Last Name:");
	    pane.add(LN, 0, 1); 
	    TextField LNTxt = new TextField();
	    pane.add(LNTxt, 1, 1);
	    Label Email = new Label("E-mail:");
	    pane.add(Email, 0, 2);
	    TextField EmailTxt = new TextField();
	    pane.add(EmailTxt, 1, 2);
	    Label Password = new Label("Password:");
	    pane.add(Password, 0, 3);
	    TextField PasswordTxt = new TextField();
	    pane.add(PasswordTxt, 1, 3); 	    
	    Button btSignUp = new Button("Sign Up");  
	    pane.add(btSignUp, 1, 4);
	    GridPane.setHalignment(btSignUp, HPos.RIGHT);
	    Button btBack = new Button("Back");
	    pane.add(btBack,0, 4);
	    GridPane.setHalignment(btBack, HPos.LEFT); 

	    // Create a scene and place it in the stage
	    Scene scene = new Scene(pane);
	    primaryStage.setTitle("ShowGridPane"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  }
	  
	  /**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
	    launch(args);
	  }
	} 

