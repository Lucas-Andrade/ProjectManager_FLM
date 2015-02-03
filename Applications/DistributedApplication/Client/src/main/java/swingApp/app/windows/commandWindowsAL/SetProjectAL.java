package swingApp.app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import swingApp.app.windows.PublishToErrorDialog;
import swingApp.app.windows.PublishToMainFrame;
import swingApp.app.windows.SwingWorkerCommand;
import swingApp.app.windows.mainFrameAL.mainFrame.ErrorDialog;

import commandProxy.Command;
import commandProxy.SetProjectPropertiesFromRepo;


/**
 * Class responsible for instantiating the {@code Command}
 * {@link SetProjectPropertiesFromRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see SetProjectPropertiesFromRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class SetProjectAL implements ActionListener {

	/**
	 * An array of {@code JTextField}s containing the parameters for
	 * instantiating the {@code Command} {@link SetProjectPropertiesFromRepo}.
	 */
	private JTextField[] textFields;

	/**
	 * The constructor for {@code SetProjectAL}.
	 * 
	 * @param textFields
	 *            A {@code JTextField} containing the parameters for
	 *            instantiating the {@code Command}
	 *            {@link SetProjectPropertiesFromRepo}.
	 */
	public SetProjectAL(JTextField[] textFields) {
		this.textFields = textFields;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link SetProjectPropertiesFromRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String price;
		String latitude;
		String longitude;
		String localName;
		String pidString;
		try {
			pidString = textFields[0].getText();
			localName = textFields[1].getText();
			price = textFields[2].getText();
			latitude = textFields[4].getText();
			longitude = textFields[3].getText();

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
			Command command = new SetProjectPropertiesFromRepo(pidString, longitude,
					latitude, price, localName);
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
