
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.text.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Create a GUI for use with the PasswordDriverFX and PasswordCheckerUtility classes.
 * Used for entering one or multiple passwords for validation
 * @author Michael Meyers
 *
 */
public class PasswordMain extends BorderPane
{
	//Create the labels for UI items
	private Label passwordLabel, passwordALabel, instruction1Label, instruction2Label,instruction3Label,instruction4Label;
	private Label instruction5Label, instruction6Label;
	
	//Create text fields for password entry
	private TextField passwordText, passwordAText ;
	private Button checkPwdButton, exitButton, checkPwdsInFileButton;
	DecimalFormat format = new DecimalFormat("#0.000");
	
	//Creat password checker utility object for examining passwords
	PasswordCheckerUtility pwdChecker;
	

	public PasswordMain()
	{
		pwdChecker = new PasswordCheckerUtility();
		
		VBox subpanel = new VBox();
		instruction1Label = new Label("Use the following rules when creating your passwords:");
		instruction2Label = new Label("\t1.  Length must be greater than 6; a strong password will contain at least 10 characters");
		instruction3Label = new Label("\t2.  Must contain at least one upper case alpha character");
		instruction4Label = new Label("\t3.  Must contain at least one lower case alpha character");
		instruction5Label = new Label("\t4.  Must contain at least one numeric charcter");
		instruction6Label = new Label("\t5.  May not have more than 2 of the same character in sequence");
		VBox.setMargin(instruction1Label, new Insets(2,10,2,10));
		VBox.setMargin(instruction2Label, new Insets(2,10,2,10));
		VBox.setMargin(instruction3Label, new Insets(2,10,2,10));
		VBox.setMargin(instruction4Label, new Insets(2,10,2,10));
		VBox.setMargin(instruction5Label, new Insets(2,10,2,10));
		VBox.setMargin(instruction6Label, new Insets(2,10,2,10));

		subpanel.setAlignment(Pos.CENTER_LEFT);
		subpanel.getChildren().addAll(instruction1Label, instruction2Label, instruction3Label,
				instruction4Label, instruction5Label, instruction6Label);
		
		HBox subpanel1a = new HBox();
		passwordLabel = new Label ("Password");
		
		passwordText = new TextField();
		HBox.setMargin(passwordLabel, new Insets(10,10,10,10));
		HBox.setMargin(passwordText, new Insets(10,10,10,10));
		subpanel1a.setAlignment(Pos.CENTER);
		subpanel1a.getChildren().addAll(passwordLabel, passwordText);
		
		HBox subpanel1b = new HBox();
		passwordALabel = new Label ("Re-type\nPassword");
		
		passwordAText = new TextField();
		HBox.setMargin(passwordALabel, new Insets(10,10,10,10));
		HBox.setMargin(passwordAText, new Insets(10,10,10,10));
		subpanel1b.setAlignment(Pos.CENTER);
		subpanel1b.getChildren().addAll(passwordALabel, passwordAText);
		
		VBox subpanel1 = new VBox();
		VBox.setMargin(subpanel1a, new Insets(10,10,10,10));
		VBox.setMargin(subpanel1b, new Insets(10,10,10,10));
		subpanel1.setAlignment(Pos.CENTER);
		subpanel1.getChildren().addAll(subpanel1a, subpanel1b);
		
		//Check Passwords in File button allows the user to specify a text
		//file for validating multiple passwords at once.
		//An Alert window will pop up with any bad passwords found.
		//An Alert window will also pop up if no file is found.
		checkPwdsInFileButton = new Button("Check Passwords in _File");
		checkPwdsInFileButton.setMnemonicParsing(true);
		checkPwdsInFileButton.setTooltip(new Tooltip("Open a text file of passwords to check"));
		checkPwdsInFileButton.setOnAction(
        		event -> {
        			try {
        				//Create ArrayList for validating names
        				ArrayList<String> passwords = new ArrayList<>();
        				
        				//Allow the user to specify a file to read from.
        				//That file is then read into the ArrayList
						passwords = pwdChecker.validPasswords(readFile());

						//If all passwords were valid, display information alert
						if (passwords.size() == 0) {
							Alert validPasswordListAlert = new Alert(AlertType.INFORMATION);
							validPasswordListAlert.setHeaderText("Password Check Cleared");
							validPasswordListAlert.setContentText("No invalid passwords found");
							validPasswordListAlert.showAndWait();
						}
						
						//If invalid passwords were found, display Alert window with the 
						//bad passwords and their error messages
						else {
							//Convert the ArrayList into a single String for display
							String alertText = "";
							for (String badPassword : passwords) {
								alertText += badPassword + "\n";
							}
							
							//Create the Alert and display the String of invalidated passwords
							Alert badPasswordListAlert = new Alert(AlertType.WARNING);
							badPasswordListAlert.setHeaderText("Invalid Passwords Found");
							badPasswordListAlert.setContentText(alertText);
							badPasswordListAlert.showAndWait();
							}
        				}
        			
        			//If no file is found, display an Alert window
					catch (FileNotFoundException e) {
						
						Alert fileNotFoundAlert = new Alert(AlertType.ERROR);
						fileNotFoundAlert.setHeaderText("ERROR");
						fileNotFoundAlert.setContentText("The specified file was not found");
						fileNotFoundAlert.showAndWait();
					}
        			
        			//If the user closes the window without selecting anything, do nothing
        			catch (NullPointerException e) {}
        		});
		
		//Check a user-entered password
		checkPwdButton = new Button ("Check _Password");
		checkPwdButton.setMnemonicParsing(true);
		checkPwdButton.setTooltip(new Tooltip("Validate the entered password"));
		checkPwdButton.setOnAction(
        		event -> {
        			if (!(passwordText.getText().equals(passwordAText.getText()))) {
        				
        				//Display alert if the two text boxes don't match
        				Alert mismatchAlert = new Alert(AlertType.WARNING);
        				mismatchAlert.setHeaderText("Error");
        				mismatchAlert.setContentText("The passwords do not match. " +
        											"Please re-enter and try again.");
        				mismatchAlert.showAndWait();
        			}
        			
        			else {
        				//Otherwise, validate the matching passwords
        				try {
        					pwdChecker.isValidPassword(passwordText.getText());
        					
							//If valid, pop up window affirming valid password
        					Alert validAlert = new Alert(AlertType.INFORMATION);
        					validAlert.setHeaderText("Valid Password");
        					validAlert.setContentText("The password is valid.");
        					validAlert.showAndWait();
        				}
        				
        				catch (RuntimeException e) {
        					
        					//If invalid, pop up the Exception message in a new Alert window
        					Alert invalidAlert = new Alert(AlertType.WARNING);
        					invalidAlert.setHeaderText("Invalid Password");
        					invalidAlert.setContentText(e.getMessage());
        					invalidAlert.showAndWait();
        				}
        			}
        		});
		
		//Button to close and exit the program
		exitButton = new Button("E_xit");
		exitButton.setMnemonicParsing(true);
		exitButton.setTooltip(new Tooltip("Exit the program"));
		
	    //use a lambda expression for the EventHandler class for exitButton
	    exitButton.setOnAction(
        		event -> {
	            	 Platform.exit();
	                 System.exit(0);
        		}
        	);
		
	    //HBox to hold the three buttons
		HBox buttonPanel = new HBox();
		HBox.setMargin(checkPwdButton, new Insets(10,10,10,10));
		HBox.setMargin(checkPwdsInFileButton, new Insets(10,10,10,10));
		HBox.setMargin(exitButton, new Insets(10,10,10,10));
		buttonPanel.setAlignment(Pos.CENTER);
		buttonPanel.getChildren().addAll(checkPwdButton, checkPwdsInFileButton, exitButton);
		
		//Set top, center, and bottom regions of the main BorderPane
		setTop(subpanel);
		setCenter(subpanel1);
		setBottom(buttonPanel);

	
	}

	/**
	 * Open a text file of passwords and examine each one for validity
	 * @return an ArrayList of invalid passwords along with the error messages
	 * explaining invalidation, or an empty ArrayList if all passwords are valid
	 * @throws FileNotFoundException if a file is not found
	 * @throws NullPointerException if the user closes the FileChooser window prematurely
	 */
	public ArrayList<String> readFile() throws FileNotFoundException, NullPointerException {
			
		//Create ArrayList to return
		ArrayList<String>passwords = new ArrayList<>();
		
		//Open up a FileChooser dialog in a new window
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("TXT files (*.txt)", "*.txt"));
		File file = fc.showOpenDialog(new Stage());
		
		//Open a scanner to read the file
		Scanner scanner = new Scanner(file);
	
		//Read each line from the file and append it to the ArrayList
		while(scanner.hasNextLine()) {
			passwords.add(scanner.nextLine());
		}
		
		scanner.close(); // close scanner at end of file
		
		//Return generated ArrayList
		return passwords;
	}
	
}