package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.GetUserFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishUsersToGetPanel;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

/**
 * Class responsible for instantiating the {@code Command}
 * {@link GetUserFromRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see GetUserFromRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetUserAL implements ActionListener {

	/**
	 * A {@code JTextField} containing the {@code IUser}'s username.
	 */
	private JTextField parameter;

	/**
	 * A {@code String} containing the {@code IUser}'s username.
	 */
	private String username;

	/**
	 * The constructor for {@code GetUserAL}.
	 * 
	 * @param parameter
	 *            A {@code JTextField} containing the {@code IUser}'s username.
	 */
	public GetUserAL(JTextField parameter) {
		this.parameter = parameter;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link GetUserFromRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			username = parameter.getText();
			if (username.length() == 0) {
				new ErrorDialog("The username field was left blank.")
						.setVisible(true);
				return;
			}
			Command command = new GetUserFromRepo(MainFrame.getRepositories()
					.getUsersRepo(), username);
			new SwingWorkerCommand(command, new PublishUsersToGetPanel(),
					new PublishToErrorDialog()).execute();

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage())
					.setVisible(true);
		}

	}

}
