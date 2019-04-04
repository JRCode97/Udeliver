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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartUpPage extends Application {
	  @Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {
	    // Create a pane and set its properties
	    GridPane pane = new GridPane();
	    pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	    pane.setHgap(5.5);
	    pane.setVgap(5.5);
	    pane.setStyle("-fx-background-color: maroon;");
	    
	    // Place nodes in the pane
	    
	    Text Welcome = new Text("Welcome to uDeliver");
	    pane.add(Welcome, 0, 0);
	    Button btSignIn = new Button("Sign In");
	    pane.add(btSignIn,0, 2);
	    GridPane.setHalignment(btSignIn, HPos.LEFT); 
	    Button btSignUp = new Button("Sign Up");  
	    pane.add(btSignUp, 1, 2);
	    GridPane.setHalignment(btSignUp, HPos.RIGHT);
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

