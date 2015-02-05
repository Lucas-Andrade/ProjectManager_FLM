package commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mainFrameAL.mainFrame.ErrorDialog;
import app.AppUI;
import app.domainCommands.Command;
import app.domainCommands.GetProjectWorkersFromRepo;
import app.publisher.PublishTeamToGetPanel;
import app.publisher.PublishToErrorDialog;
import app.publisher.SwingWorkerCommand;


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
	 * The button which selection state will be used to determine
	 * whether the user is looking for the manager, or the team of
	 * consultants.
	 */
	private JRadioButton button;

	/**
	 * The constructor for {@code GetWorkersAL}.
	 * 
	 * @param projectField
	 *            A {@code JTextField} containing the {@code AWorker}'s type.
	 * @param button
	 *            The button which selection state will be used to determine
	 *            whether the user is looking for the manager, or the team of
	 *            consultants.
	 */
	public GetWorkersAL(JTextField projectField, JRadioButton button) {
		this.projectField = projectField;
		this.button = button;
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
			workerOpt = button.isSelected() ? "consultant" : "manager";
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
			Command command = new GetProjectWorkersFromRepo(AppUI
					.getRepositories().getProjectsRepo(), pid, workerOpt);
			new SwingWorkerCommand(command, new PublishTeamToGetPanel(),
					new PublishToErrorDialog()).execute();

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
