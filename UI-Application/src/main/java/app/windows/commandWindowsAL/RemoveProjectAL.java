package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.RemoveProjectToRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

/**
 * Class responsible for instantiating the {@code Command}
 * {@link RemoveProjectToRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see RemoveProjectToRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class RemoveProjectAL implements ActionListener {

	/**
	 * A {@code JTextField} containing the {@code Project}'s ID.
	 */
	private String pidString;

	/**
	 * A {@code String} containing the {@code Project}'s ID.
	 */
	private JTextField projectId;

	/**
	 * The constructor for {@code RemoveProjectAL}.
	 * 
	 * @param textFields
	 *            A {@code JTextField} containing the {@code Project}'s ID.
	 */
	public RemoveProjectAL(JTextField projectId) {
		this.projectId = projectId;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link RemoveProjectToRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			pidString = projectId.getText();

		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (pidString.length() == 0) {
			new ErrorDialog("Required parameter not present.").setVisible(true);
			return;
		}

		int resposta = JOptionPane.showConfirmDialog(null,
				"Do you want to delete this project?", "Confirm",
				JOptionPane.OK_CANCEL_OPTION);
		try {
			if (resposta == JOptionPane.OK_OPTION) {
				Command command = new RemoveProjectToRepo(MainFrame
						.getRepositories().getProjectsRepo(), pidString);
				new SwingWorkerCommand(command, new PublishToMainFrame(),
						new PublishToErrorDialog()).execute();
			}
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
