package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.GetProjectWorkersFromRepo;
import app.windows.PublishTeamToGetPanel;
import app.windows.PublishToErrorDialog;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

/**
 * Class responsible for instantiating the {@code Command}
 * {@link GetProjectWorkersFromRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see GetProjectWorkersFromRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetWorkersAL implements ActionListener {

	/**
	 * A {@code JTextField} containing the {@code AWorker}'s type.
	 */
	private JTextField projectField;

	/**
	 * A {@code String} containing the {@code AWorker}'s type.
	 */
	private String workerOpt;

	/**
	 * The constructor for {@code GetWorkersAL}.
	 * 
	 * @param projectId
	 *            A {@code JTextField} containing the {@code AWorker}'s type.
	 */
	public GetWorkersAL(JTextField projectField, String worker) {
		this.projectField = projectField;
		this.workerOpt = worker;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link GetProjectWorkersFromRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String pid;
		try {
			pid = projectField.getText();
		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (pid.length() == 0) {
			new ErrorDialog("At least one field was left blank.")
					.setVisible(true);
			return;
		}

		try {
			Command command = new GetProjectWorkersFromRepo(MainFrame
					.getRepositories().getProjectsRepo(), pid, workerOpt);
			new SwingWorkerCommand(command, new PublishTeamToGetPanel(),
					new PublishToErrorDialog()).execute();

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
