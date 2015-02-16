package guiElements.commandWindowsAL;

import guiElements.FrameAndPanelHolder;
import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;


/**
 * Class responsible for calling the method {@code ICommandCaller#callGetUsers}.
 * Implements {@code ActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetUserAL implements ActionListener {

	/**
	 * A {@code JTextField} containing the {@code IUser}'s username.
	 */
	private JTextField parameter;

	/**
	 * A {@code String} containing the {@code IUser}'s username.
	 */
	private String username;
	
	private JRadioButton allUsers;

	/**
	 * The constructor for {@code GetUserAL}.
	 * 
	 * @param parameter
	 *            A {@code JTextField} containing the {@code IUser}'s username.
	 */
	public GetUserAL(JTextField parameter, JRadioButton allUsers) {
		this.parameter = parameter;
		this.allUsers = allUsers;
	}

	/**
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callGetUsers}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callGetUsers} will not be called.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			username = parameter.getText();
			
			if(allUsers.isSelected()){
				GUIUtils.getCommandCaller().callGetUsers();
			} else {
				GUIUtils.getCommandCaller().callGetUser(username);
			}
			
		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage())
					.setVisible(true);
			return;
		}

		FrameAndPanelHolder.resetFields();
	}

}
