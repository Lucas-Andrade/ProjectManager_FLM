package guiElements.commandWindowsAL;

import guiElements.FrameAndPanelHolder;
import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;


/**
 * Class responsible for calling the method {@code ICommandCaller#callGetProject}.
 * Implements {@code ActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetProjectAL implements ActionListener{

	/**
	 * A {@code JTextField} containing the {@code Project}'s ID.
	 */
	JTextField projectId;
	
	/**
	 * A {@code String} containing the {@code Project}'s ID.
	 */
	String pidString;
	
	/**
	 * The constructor for {@code GetProjectAL}.
	 * 
	 * @param projectId
	 *            A {@code JTextField} containing the {@code Project}'s ID.
	 */
	public GetProjectAL(JTextField pid) {
		this.projectId = pid;
	}
	
	/**
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callGetProject}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callGetProject} will not be called.
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
			new ErrorDialog("Project ID field was left blank.").setVisible(true);
			return;
		}
		
		try {
			GUIUtils.getCommandCaller().callGetProject(pidString);

		} catch (IllegalArgumentException iae) {

			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
			return;
		}
		
		FrameAndPanelHolder.resetFields();
	}
	

}
