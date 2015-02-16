package guiElements.commandWindowsAL;

import guiElements.FrameAndPanelHolder;
import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * Class responsible for calling the method {@code ICommandCaller#callAuthenticateUser}.
 * Implements {@code ActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/02/2015
 */
public class AuthenticationAL implements ActionListener {

	private JTextField usernameField;
	private JTextField passwordField;
	
	/**
	 * Constructs this {@code ActionListener}, and sets the fields {@code usernameField}
	 * and {@code passwordField} using information taken from the {@code JTextField[]}
	 * passed as parameter.
	 * 
	 * @param textFields
	 * 		- an array of {@code JTextField} objects containing the username and password
	 * 		of the {@code User} who is trying to authenticate.
	 */
	public AuthenticationAL(JTextField[] textFields) {
		this.usernameField = textFields[0];
		this.passwordField = textFields[1];
	}

	/**
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callAuthenticateUser}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callAuthenticateUser} will not be called.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String username;
		String password;
		
		try {
			username = usernameField.getText();
			password = passwordField.getText();
		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}
		
		if (username.length() == 0 || password.length() == 0) {
			new ErrorDialog("A field was left blank.").setVisible(true);
			return;
		}
		
		try {
			GUIUtils.getCommandCaller().callAuthenticateUser(username, password);

		} catch (IllegalArgumentException iae) {

			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
			return;
		}
		
		FrameAndPanelHolder.resetFields();
	}

}
