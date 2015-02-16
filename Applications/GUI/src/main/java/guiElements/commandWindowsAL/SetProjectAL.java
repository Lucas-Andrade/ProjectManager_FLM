package guiElements.commandWindowsAL;

import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * Class responsible for calling the method {@code ICommandCaller#callPatchProject}.
 * Implements {@code ActionListener}.
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
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callPatchProject}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callPatchProject} will not be called.
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
			GUIUtils.getCommandCaller().callPatchProject(pidString, longitude, latitude, price, localName);
		} catch (NumberFormatException nfe) {
			new ErrorDialog(
					"Numbers were not introduced in one of the following fields: Price, Longitude of Latitude.")
					.setVisible(true);
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
