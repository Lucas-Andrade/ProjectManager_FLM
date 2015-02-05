package swingApp.app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import swingApp.app.windows.PublishToErrorDialog;
import swingApp.app.windows.PublishToGetPanel;
import swingApp.app.windows.SwingWorkerCommand;
import swingApp.app.windows.mainFrameAL.mainFrame.ErrorDialog;
import commandRequest.Command;
import commandRequest.GetProjectFromRepo;


/**
 * Class responsible for instantiating the {@code Command}
 * {@link GetProjectFromRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see GetSubprojectsFromRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetProjectAL implements ActionListener{

	/**
	 * A {@code JTextField} containing the {@code Project}'s ID.
	 */
	JTextField projectId;
	
	/**
	 * A {@code String} containing the {@code Project}'s ID.
	 */
	String pidString;
	
	/**
	 * The constructor for {@code GetProjectAL}.
	 * 
	 * @param projectId
	 *            A {@code JTextField} containing the {@code Project}'s ID.
	 */
	public GetProjectAL(JTextField pid) {
		this.projectId = pid;
	}
	
	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link GetProjectFromRepo} and for executing it in a new
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
			new ErrorDialog("Project ID field was left blank.")
					.setVisible(true);
			return;
		}
		
		try {
			Command command = new GetProjectFromRepo(pidString);
			new SwingWorkerCommand(command, new PublishToGetPanel(),
					new PublishToErrorDialog()).execute();

		} catch (IllegalArgumentException iae) {

			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
