package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import org.PublishToErrorDialog;
import org.PublishToMainFrame;
import org.SwingWorkerCommand;

import app.domainCommands.AddWorkerToProjectInRepo;
import app.domainCommands.Command;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;
import app.windows.mainFrameAL.mainFrame.WorkerID;


/**
 * Class responsible for instantiating the {@code Command}
 * {@link AddWorkerToProjectInRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see AddWorkerToProjectInRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class NewWorkerInProjectAL implements ActionListener {

	/**
	 * An array of {@code JTextField}s containing the parameters for
	 * instantiating the {@code Command} {@link AddWorkerToProjectInRepo}.
	 */
	private JTextField textFields;

	/**
	 * The {@code WorkerID} containing information about the worker to be added
	 * in a {@code Project}.
	 */
	private WorkerID workerID;

	/**
	 * The constructor for {@code NewWorkerInProjectAL}.
	 * 
	 * @param textFields
	 *            A {@code JTextField} containing the parameters for
	 *            instantiating the {@code Command}
	 *            {@link AddWorkerToProjectInRepo}.
	 * @param workerId
	 *            The {@code WorkerID} containing information about the
	 *            {@code AWorker} to be added in a {@code Project}.
	 */
	public NewWorkerInProjectAL(JTextField textFields, WorkerID workerId) {
		this.textFields = textFields;
		this.workerID = workerId;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link AddWorkerToProjectInRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String worker;
		String pid;
		String cid;
		try {
			pid = textFields.getText();
			cid = workerID.getIDField().getText();
			worker = workerID.getSelected();
		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (pid.length() == 0 || cid.length() == 0 || worker.length() == 0) {
			new ErrorDialog("Required parameter not present.").setVisible(true);
			return;
		}
		try {
			Command command = new AddWorkerToProjectInRepo(MainFrame
					.getRepositories().getProjectsRepo(), MainFrame
					.getRepositories().getWorkersRepo(), pid, cid, worker);
			new SwingWorkerCommand(command, new PublishToMainFrame(),
					new PublishToErrorDialog()).execute();

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
