package guiElements.commandWindowsAL;

import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;


public class AuthenticationAL implements ActionListener {

	private JTextField usernameField;
	private JTextField passwordField;
	
	public AuthenticationAL(JTextField[] textFields) {
		this.usernameField = textFields[0];
		this.passwordField = textFields[1];
	}

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
		}
	}

}
