package commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import mainFrameAL.mainFrame.ErrorDialog;
import app.AppUI;
import app.domainCommands.Command;
import app.domainCommands.GetSubprojectsFromRepo;
import app.publisher.PublishToErrorDialog;
import app.publisher.PublishToGetPanel;
import app.publisher.SwingWorkerCommand;


/**
 * Class responsible for instantiating the {@code Command}
 * {@link GetSubprojectsFromRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see GetSubprojectsFromRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetSubprojectAL implements ActionListener {

	/**
	 * A {@code JTextField} containing the {@code Project}'s ID.
	 */
	private JTextField projectId;

	/**
	 * A {@code String} containing the {@code Project}'s ID.
	 */
	private String pidString;

	/**
	 * The constructor for {@code GetSubprojectAL}.
	 * 
	 * @param projectId
	 *            A {@code JTextField} containing the {@code Project}'s ID.
	 */
	public GetSubprojectAL(JTextField projectId) {
		this.projectId = projectId;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link GetSubprojectsFromRepo} and for executing it in a new
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
			new ErrorDialog("At least one field was left blank.")
					.setVisible(true);
			return;
		}
		try {
			Command command = new GetSubprojectsFromRepo(AppUI
					.getRepositories().getProjectsRepo(), pidString);
			new SwingWorkerCommand(command, new PublishToGetPanel(),
					new PublishToErrorDialog()).execute();

		} catch (IllegalArgumentException iae) {

			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
