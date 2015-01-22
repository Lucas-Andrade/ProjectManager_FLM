package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.AddUserToRepo;
import app.domainCommands.Command;
import app.windows.PublishToErrorDialog;
import app.windows.PublishUsersToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

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

	/**
	 * The constructor for {@code NewUserAL}.
	 * 
	 * @param projectId
	 *            A {@code JTextField} containing the parameters for
	 *            instantiating the {@code Command} {@link AddUserToRepo}.
	 */
	public NewUserAL(JTextField[] fields) {
		this.textfields = fields;
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
		String password;
		String email;
		String fullname;
		try {
			username = textfields[0].getText();
			password = textfields[1].getText();
			email = textfields[2].getText();
			fullname = textfields[3].getText();

		} catch (NullPointerException npe) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (username.length() == 0 || password.length() == 0
				|| email.length() == 0) {
			new ErrorDialog("At least one required field was left blank.")
					.setVisible(true);
			return;
		}

		try {
			Command command = new AddUserToRepo(MainFrame.getRepositories()
					.getUsersRepo(), username, password, email, fullname);
			new SwingWorkerCommand(command, new PublishUsersToMainFrame(),
					new PublishToErrorDialog()).execute();

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
