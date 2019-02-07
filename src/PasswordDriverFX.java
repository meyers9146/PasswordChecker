import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PasswordDriverFX extends Application{
	
   /**
    * Launch the GUI interface
    * @param args arguments not used in program
    */
   public static void main(String[] args){
	   launch(args);
   }
   
   /**
    * Build the primary GUI window and display on launch
    */
   public void start(Stage stage)
   {

	   //call the main scene from the PasswordMain file
	   PasswordMain mainPane = new PasswordMain();

	   //Set stage, scene, and title of window
	   Scene scene = new Scene(mainPane, 550, 350);
	   stage.setScene(scene);
	   stage.setTitle("Password Checker");
	   
	   //Display window and GUI
	   stage.show();
   }
   
}
