package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.SetUserPropertiesFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
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
	private JPasswordField[] textFields;
	private JTextField usernameField;

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
		this.textFields = textFields;
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
		String userName;
		String oldPassword;
		String newPassword;
		String validateNewPass;
		try {
			userName = usernameField.getText();
			oldPassword = textFields[0].getSelectedText();
			newPassword = textFields[1].getSelectedText();
			validateNewPass = textFields[2].getSelectedText();

		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (userName.length() == 0 || oldPassword.length() == 0
				|| newPassword.length() == 0 || validateNewPass.length() == 0) {
			new ErrorDialog("At least one required field was left blank.")
					.setVisible(true);
			return;
		}

		if (!(newPassword == validateNewPass)) {
			new ErrorDialog(
					"New Passord and confirmation Password do not match")
					.setVisible(true);
			return;
		}

		try {
			Command command = new SetUserPropertiesFromRepo(MainFrame
					.getRepositories().getUsersRepo(), userName, oldPassword,
					newPassword);
			new SwingWorkerCommand(command, new PublishToMainFrame(),
					new PublishToErrorDialog()).execute();
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage())
					.setVisible(true);
		}
	}

}
