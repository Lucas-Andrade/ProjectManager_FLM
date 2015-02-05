package commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import mainFrameAL.mainFrame.ErrorDialog;
import app.AppUI;
import app.domainCommands.AddConsultantToRepo;
import app.domainCommands.Command;
import app.publisher.PublishTeamToMainFrame;
import app.publisher.PublishToErrorDialog;
import app.publisher.SwingWorkerCommand;


/**
 * Class responsible for instantiating the {@code Command}
 * {@link AddConsultantToRepo} and for executing it in a new
 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
 * with exception messages. Implements {@code ActionListener}.
 * 
 * @see AddConsultantToRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class NewConsultantAL implements ActionListener {

	/**
	 * An array of {@code JTextField}s containing the parameters for
	 * instantiating the {@code Command} {@link AddConsultantToRepo}.
	 */
	private JTextField[] textfields;

	/**
	 * The constructor for {@code NewConsultantAL}.
	 * 
	 * @param textFields
	 *            A {@code JTextField} containing the parameters for
	 *            instantiating the {@code Command} {@link AddConsultantToRepo}.
	 */
	public NewConsultantAL(JTextField[] textFields) {
		this.textfields = textFields;
	}

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link AddConsultantToRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}, if not possible displays {@link ErrorDialog}s
	 * with exception messages. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	public void actionPerformed(ActionEvent e) {
		String name;
		String priceHour;
		String bonus;
		try {
			name = textfields[0].getText();
			priceHour = textfields[1].getText();
			bonus = textfields[2].getText();

		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (name.length() == 0 || priceHour.length() == 0) {
			new ErrorDialog("At least one required field was left blank.")
					.setVisible(true);
			return;
		}

		try {
			Command command = new AddConsultantToRepo(AppUI
					.getRepositories().getWorkersRepo(), name, priceHour, bonus);
			new SwingWorkerCommand(command, new PublishTeamToMainFrame(),
					new PublishToErrorDialog()).execute();

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
