package guiElements.commandWindowsAL;

import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * Class responsible for calling the method {@code ICommandCaller#callPatchConsultant}.
 * Implements {@code ActionListener}.
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
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callPatchConsultant}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callPatchConsultant} will not be called.
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
			GUIUtils.getCommandCaller().callPatchConsultant(consultantId, consultantName, priceHour);
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
