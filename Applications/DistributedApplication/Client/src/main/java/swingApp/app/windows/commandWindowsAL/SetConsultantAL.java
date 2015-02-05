package swingApp.app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import swingApp.app.windows.PublishTeamToMainFrame;
import swingApp.app.windows.PublishToErrorDialog;
import swingApp.app.windows.SwingWorkerCommand;
import swingApp.app.windows.mainFrameAL.mainFrame.ErrorDialog;
import commandRequest.Command;
import commandRequest.SetConsultantPropertiesFromRepo;


/**
 * Class responsible for instantiating the {@code Command}
 * {@link SetConsultantPropertiesFromRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see SetConsultantPropertiesFromRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class SetConsultantAL implements ActionListener {

	/**
	 * An array of {@code JTextField}s containing the parameters for
	 * instantiating the {@code Command} {@link SetConsultantPropertiesFromRepo}.
	 */
	private JTextField[] textFields;

	/**
	 * The constructor for {@code SetConsultantAL}.
	 * 
	 * @param textFields
	 *            A {@code JTextField} containing the parameters for
	 *            instantiating the {@code Command}
	 *            {@link SetConsultantPropertiesFromRepo}.
	 */
	public SetConsultantAL(JTextField[] textFields) {
		this.textFields = textFields;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link SetConsultantPropertiesFromRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String consultantId;
		String consultantName;
		String priceHour;
		try {
			consultantId = textFields[0].getText();
			consultantName = textFields[1].getText();
			priceHour = textFields[2].getText();

		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (consultantId.length() == 0) {
			new ErrorDialog("The Consultant ID field was left blank.")
					.setVisible(true);
			return;
		} else if (consultantName.length() == 0 && priceHour.length() == 0) {
			new ErrorDialog("The Consultant ID field was left blank.")
					.setVisible(true);
			return;
		}

		try {
			Command command = new SetConsultantPropertiesFromRepo(consultantId,
					consultantName, priceHour);
			new SwingWorkerCommand(command, new PublishTeamToMainFrame(),
					new PublishToErrorDialog()).execute();
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
