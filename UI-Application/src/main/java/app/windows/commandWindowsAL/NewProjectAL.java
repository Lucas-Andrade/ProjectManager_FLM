package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.AddProjectToRepo;
import app.domainCommands.Command;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

/**
 * Class responsible for instantiating the {@code Command}
 * {@link AddProjectToRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see AddProjectToRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class NewProjectAL implements ActionListener {

	/**
	 * An array of {@code JTextField}s containing the parameters required for
	 * instantiating the {@code Command} {@link AddProjectToRepo}.
	 */
	private JTextField[] textfields;

	/**
	 * The constructor for {@code NewProjectAL}.
	 * 
	 * @param projectId
	 *            A {@code JTextField} containing the parameters required for
	 *            instantiating the {@code Command} {@link AddProjectToRepo}.
	 */
	public NewProjectAL(JTextField[] textFields2) {
		this.textfields = textFields2;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link AddProjectToRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String name;
		String price;
		String latitude;
		String longitude;
		try {
			name = textfields[0].getText();
			price = textfields[1].getText();
			latitude = textfields[3].getText();
			longitude = textfields[2].getText();
		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (name.length() == 0 || price.length() == 0 || latitude.length() == 0
				|| longitude.length() == 0) {
			new ErrorDialog("At least one field was left blank.")
					.setVisible(true);
			return;
		}

		try {
			Command command = new AddProjectToRepo(MainFrame.getRepositories()
					.getProjectsRepo(), latitude, longitude, name, price);
			new SwingWorkerCommand(command, new PublishToMainFrame(),
					new PublishToErrorDialog()).execute();
		} catch (NumberFormatException nfe) {
			new ErrorDialog(
					"Numbers were not introduced in one of the following fields: Price, Longitude of Latitude.")
					.setVisible(true);
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
