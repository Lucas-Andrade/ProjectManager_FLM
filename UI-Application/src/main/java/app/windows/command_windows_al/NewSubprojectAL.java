package app.windows.command_windows_al;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domain_commands.AddSubprojectToRepo;
import app.domain_commands.Command;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.main_frame_al.main_frame.ErrorDialog;
import app.windows.main_frame_al.main_frame.MainFrame;

/**
 * Class responsible for instantiating the {@code Command}
 * {@link AddSubprojectToRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see AddSubprojectToRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class NewSubprojectAL implements ActionListener {

	/**
	 * An array of {@code JTextField}s containing the parameters for
	 * instantiating the {@code Command} {@link AddSubprojectToRepo}.
	 */
	private JTextField[] textfields;

	/**
	 * The constructor for {@code NewSubprojectAL}.
	 * 
	 * @param textFields
	 *            A {@code JTextField} containing the parameters for
	 *            instantiating the {@code Command} {@link AddSubprojectToRepo}.
	 */
	public NewSubprojectAL(JTextField[] textFields) {
		this.textfields = textFields;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link AddSubprojectToRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String pid;
		String subprojectId;
		try {
			pid = textfields[0].getText();
			subprojectId = textfields[1].getText();

		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (pid.length() == 0 || subprojectId.length() == 0) {
			new ErrorDialog("At least one field was left blank.")
					.setVisible(true);
			return;
		}

		try {
			Command command = new AddSubprojectToRepo(MainFrame
					.getRepositories().getProjectsRepo(), pid, subprojectId);
			new SwingWorkerCommand(command, new PublishToMainFrame(),
					new PublishToErrorDialog()).execute();

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
