package guiElements.commandWindowsAL;

import guiElements.FrameAndPanelHolder;
import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * Class responsible for calling the method {@code ICommandCaller#callPostProject}.
 * Implements {@code ActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class NewProjectAL implements ActionListener {

	/**
	 * An array of {@code JTextField}s containing the parameters for
	 * instantiating the {@code Command} {@link AddProjectToRepo}.
	 */
	private JTextField[] textfields;

	/**
	 * The constructor for {@code NewProjectAL}.
	 * 
	 * @param textFields
	 *            A {@code JTextField} containing the parameters for
	 *            instantiating the {@code Command} {@link AddProjectToRepo}.
	 */
	public NewProjectAL(JTextField[] textFields) {
		this.textfields = textFields;
	}

	/**
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callPostProject}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callPostProject} will not be called.
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
			GUIUtils.getCommandCaller().callPostProject(latitude, longitude, name, price);
		} catch (NumberFormatException nfe) {
			new ErrorDialog(
					"Numbers were not introduced in one of the following fields: Price, Longitude of Latitude.")
					.setVisible(true);
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
			return;
		}
		
		FrameAndPanelHolder.resetFields();
	}

}
