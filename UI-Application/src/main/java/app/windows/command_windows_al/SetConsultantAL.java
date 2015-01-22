package app.windows.command_windows_al;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domain_commands.Command;
import app.domain_commands.SetConsultantPropertiesFromRepo;
import app.windows.PublishTeamToMainFrame;
import app.windows.PublishToErrorDialog;
import app.windows.SwingWorkerCommand;
import app.windows.main_frame_al.main_frame.ErrorDialog;
import app.windows.main_frame_al.main_frame.MainFrame;

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
			Command command = new SetConsultantPropertiesFromRepo(MainFrame
					.getRepositories().getWorkersRepo(), consultantId,
					consultantName, priceHour);
			new SwingWorkerCommand(command, new PublishTeamToMainFrame(),
					new PublishToErrorDialog()).execute();
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
