package swingApp.app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import swingApp.app.windows.PublishToErrorDialog;
import swingApp.app.windows.PublishToMainFrame;
import swingApp.app.windows.SwingWorkerCommand;
import swingApp.app.windows.mainFrameAL.mainFrame.ErrorDialog;
import commandRequest.Command;
import commandRequest.RemoveProjectToRepo;


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
				Command command = new RemoveProjectToRepo(pidString);
				new SwingWorkerCommand(command, new PublishToMainFrame(),
						new PublishToErrorDialog()).execute();
			}
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
