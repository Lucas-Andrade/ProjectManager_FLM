package guiElements.commandWindowsAL;

import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * Class responsible for calling the method {@code ICommandCaller#callPostConsultant}.
 * Implements {@code ActionListener}.
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
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callPostConsultant}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callPostConsultant} will not be called.
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
			GUIUtils.getCommandCaller().callPostConsultant(name, priceHour, bonus);

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
