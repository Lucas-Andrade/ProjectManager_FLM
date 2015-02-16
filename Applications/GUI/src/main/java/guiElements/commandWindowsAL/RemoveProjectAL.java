package guiElements.commandWindowsAL;

import guiElements.FrameAndPanelHolder;
import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Class responsible for calling the method {@code ICommandCaller#callDeleteProject}.
 * Implements {@code ActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class RemoveProjectAL implements ActionListener {

	/**
	 * A {@code JTextField} containing the {@code Project}'s ID.
	 */
	private String pidString;

	/**
	 * A {@code String} containing the {@code Project}'s ID.
	 */
	private JTextField projectId;

	/**
	 * The constructor for {@code RemoveProjectAL}.
	 * 
	 * @param textFields
	 *            A {@code JTextField} containing the {@code Project}'s ID.
	 */
	public RemoveProjectAL(JTextField projectId) {
		this.projectId = projectId;
	}

	/**
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callDeleteProject}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callDeleteProject} will not be called.
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
			new ErrorDialog("Required parameter not present.").setVisible(true);
			return;
		}

		int resposta = JOptionPane.showConfirmDialog(null,
				"Do you want to delete this project?", "Confirm",
				JOptionPane.OK_CANCEL_OPTION);
		try {
			if (resposta == JOptionPane.OK_OPTION) {
				GUIUtils.getCommandCaller().callDeleteProject(pidString);
			}
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
			return;
		}
		
		FrameAndPanelHolder.resetFields();
	}

}
