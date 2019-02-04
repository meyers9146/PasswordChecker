package p1;

// ----------  ----------  ----------
//
// PLEASE READ | From Prof. Thai
//
// This code is incomplete!  It's provided as a starting point only.  Each student is welcome to implement their own UI
// 
// ----------  ----------  ----------


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.text.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;




public class PasswordMain extends BorderPane
{
	private Label passwordLabel, passwordALabel, instruction1Label, instruction2Label,instruction3Label,instruction4Label;
	private Label instruction5Label, instruction6Label;
	
	private TextField passwordText, passwordAText ;
	private TextField passwordText0, passwordAText0 ;
	private ArrayList<String> illegalPasswords;
	private Button checkPwdButton, exitButton, checkPwdsInFileButton;
	DecimalFormat format = new DecimalFormat("#0.000");
	PasswordCheckerUtility pwdChecker;
	
	public PasswordMain()
	{
		pwdChecker = new PasswordCheckerUtility();
		illegalPasswords = new ArrayList<String>();
		
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
		passwordText0 = new TextField();
		HBox.setMargin(passwordLabel, new Insets(10,10,10,10));
		HBox.setMargin(passwordText, new Insets(10,10,10,10));
		subpanel1a.setAlignment(Pos.CENTER);
		subpanel1a.getChildren().addAll(passwordLabel, passwordText);
		
		HBox subpanel1b = new HBox();
		passwordALabel = new Label ("Re-type\nPassword");
		
		passwordAText = new TextField();
		passwordAText0 = new TextField();
		HBox.setMargin(passwordALabel, new Insets(10,10,10,10));
		HBox.setMargin(passwordAText, new Insets(10,10,10,10));
		subpanel1b.setAlignment(Pos.CENTER);
		subpanel1b.getChildren().addAll(passwordALabel, passwordAText);
		
		VBox subpanel1 = new VBox();
		VBox.setMargin(subpanel1a, new Insets(10,10,10,10));
		VBox.setMargin(subpanel1b, new Insets(10,10,10,10));
		subpanel1.setAlignment(Pos.CENTER);
		subpanel1.getChildren().addAll(subpanel1a, subpanel1b);
				
		checkPwdsInFileButton = new Button("Check Passwords in _File");
		checkPwdsInFileButton.setOnAction(
        		event -> {
        			try {
						readFile();
					} catch (Exception e) {
						e.printStackTrace();
					}
        		});
		
		checkPwdButton = new Button ("Check _Password");
		checkPwdButton.setOnAction(
        		event -> {
        			addPassword();
        		});
		
		exitButton = new Button("E_xit");
		
	    //use a lambda expression for the EventHandler class for exitButton
	    exitButton.setOnAction(
        		event -> {
	            	 Platform.exit();
	                 System.exit(0);
        		}
        	);
		 
	
		HBox buttonPanel = new HBox();
		HBox.setMargin(checkPwdButton, new Insets(10,10,10,10));
		HBox.setMargin(checkPwdsInFileButton, new Insets(10,10,10,10));
		HBox.setMargin(exitButton, new Insets(10,10,10,10));
		buttonPanel.setAlignment(Pos.CENTER);
		buttonPanel.getChildren().addAll(checkPwdButton, checkPwdsInFileButton, exitButton);

		setTop(subpanel);
		setCenter(subpanel1);
		setBottom(buttonPanel);

	
	}

	public void addPassword() {
		//Get information
		System.out.println("Prof Thai: INCOMPLETE CODE!");
	}



	//Listens for the Display Apps button to be pressed
	private class displayIllegalPasswords implements ActionListener
	{
		public void actionPerformed (ActionEvent theEvent)
		{
			System.out.println("Prof Thai: INCOMPLETE CODE!");
		}
	}

	public void readFile() {
			FileChooser chooser = new FileChooser();
			System.out.println("Prof Thai: INCOMPLETE CODE!");
			
	}
}
