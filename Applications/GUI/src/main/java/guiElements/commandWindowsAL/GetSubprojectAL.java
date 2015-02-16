package guiElements.commandWindowsAL;

import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;


/**
 * Class responsible for calling the method {@code ICommandCaller#callGetSubprojects}.
 * Implements {@code ActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetSubprojectAL implements ActionListener {

	/**
	 * A {@code JTextField} containing the {@code Project}'s ID.
	 */
	private JTextField projectId;

	/**
	 * A {@code String} containing the {@code Project}'s ID.
	 */
	private String pidString;

	/**
	 * The constructor for {@code GetSubprojectAL}.
	 * 
	 * @param projectId
	 *            A {@code JTextField} containing the {@code Project}'s ID.
	 */
	public GetSubprojectAL(JTextField projectId) {
		this.projectId = projectId;
	}

	/**
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callGetSubprojects}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callGetSubprojects} will not be called.
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
			new ErrorDialog("At least one field was left blank.")
					.setVisible(true);
			return;
		}
		try {
			GUIUtils.getCommandCaller().callGetSubprojects(pidString);

		} catch (IllegalArgumentException iae) {

			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
