package guiElements.commandWindowsAL;

import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Class responsible for instantiating the {@code Command} {@link AddUserToRepo}
 * and for executing it in a new {@link SwingWorkerCommand}, if not possible
 * displays {@link ErrorDialog}s with exception messages. Implements
 * {@code ActionListener}.
 * 
 * @see AddUserToRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class NewUserAL implements ActionListener {

	/**
	 * An array of {@code JTextField}s containing the parameters for
	 * instantiating the {@code Command} {@link AddUserToRepo}.
	 */
	private JTextField[] textfields;
	private JPasswordField passwordField;

	/**
	 * The constructor for {@code NewUserAL}.
	 * 
	 * @param fields
	 *            A {@code JTextField} containing the parameters for
	 *            instantiating the {@code Command} {@link AddUserToRepo}.
	 * @param passwordField 
	 */
	public NewUserAL(JTextField[] fields, JPasswordField passwordField) {
		this.textfields = fields;
		this.passwordField = passwordField;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link AddUserToRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String username;
		char[] passwordChars;
		String email;
		String fullname;
		try {
			username = textfields[0].getText();
			passwordChars = passwordField.getPassword();
			email = textfields[1].getText();
			fullname = textfields[2].getText();

		} catch (NullPointerException npe) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		String password = constructPassword(passwordChars);
		if (username.length() == 0 || password.length() == 0
				|| email.length() == 0) {
			new ErrorDialog("At least one required field was left blank.")
					.setVisible(true);
			return;
		}

		try {
			GUIUtils.getCommandCaller().callPostUser(username, password, email, fullname);

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

	public static String constructPassword(char[] charPass) {
		StringBuilder builder = new StringBuilder();
		for(char chars : charPass) {
			builder.append(chars);
		}
		return builder.toString();
	}
}
