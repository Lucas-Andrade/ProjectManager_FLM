package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.SetUserPropertiesFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishUsersToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;


/**
 * Class responsible for instantiating the {@code Command}
 * {@link SetUserPropertiesFromRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see SetUserPropertiesFromRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class SetUserAL implements ActionListener {

	/**
	 * An array of {@code JTextField}s containing the parameters for
	 * instantiating the {@code Command} {@link SetUserPropertiesFromRepo}.
	 */
	private JPasswordField[] passwordFields;
	private JTextField usernameField;
	String userName;
	String oldPassword;
	String newPassword;
	String validateNewPass;

	/**
	 * The constructor for {@code SetUserAL}.
	 * @param nameField 
	 * 
	 * @param textFields
	 *            A {@code JTextField} containing the parameters for
	 *            instantiating the {@code Command}
	 *            {@link SetUserPropertiesFromRepo}.
	 */
	public SetUserAL(JTextField nameField, JPasswordField[] textFields) {
		this.passwordFields = textFields;
		this.usernameField = nameField;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link SetUserPropertiesFromRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		char[] oldPasswordChars;
		char[] newPasswordChars;
		char[] validateNewPassChars;
		
		try {
			userName = usernameField.getText();
			oldPasswordChars = passwordFields[0].getPassword();
			newPasswordChars = passwordFields[1].getPassword();
			validateNewPassChars = passwordFields[2].getPassword();
		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}
		
		oldPassword = NewUserAL.constructPassword(oldPasswordChars);
		newPassword = NewUserAL.constructPassword(newPasswordChars);
		validateNewPass = NewUserAL.constructPassword(validateNewPassChars);
		
		if(!testParameter()) {
			return;
		}

		try {
			Command command = new SetUserPropertiesFromRepo(MainFrame
					.getRepositories().getUsersRepo(), userName, oldPassword,
					newPassword);
			new SwingWorkerCommand(command, new PublishUsersToMainFrame(),
					new PublishToErrorDialog()).execute();
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage())
					.setVisible(true);
		}
	}

	/**
	 * Tests the parameters got from the {@code JTextField}. If they are not deemed 
	 * acceptable the method returns false.
	 * @return false if an error was encountered
	 * @return true if everything is in order
	 */
	private boolean testParameter() {
		
		if (userName.length() == 0 || oldPassword.length() == 0
				|| newPassword.length() == 0 || validateNewPass.length() == 0) {
			new ErrorDialog("At least one required field was left blank.")
					.setVisible(true);
			return false;
		}

		if (!(newPassword.equals(validateNewPass))) {
			new ErrorDialog(
					"New Password and confirmation Password do not match")
					.setVisible(true);
			return false;
		}
		
		return true;
	}

}
