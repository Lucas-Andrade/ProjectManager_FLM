package guiElements.commandWindowsAL;

import guiElements.FrameAndPanelHolder;
import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;


/**
 * Class responsible for calling the method {@code ICommandCaller#callGetWorkersInProject}.
 * Implements {@code ActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetWorkersAL implements ActionListener {

	/**
	 * A {@code JTextField} containing the {@code AWorker}'s type.
	 */
	private JTextField projectField;

	/**
	 * A {@code String} containing the {@code AWorker}'s type.
	 */
	private String workerOpt;
	
	/**
	 * The button which selection state will be used to determine
	 * whether the user is looking for the manager, or the team of
	 * consultants.
	 */
	private JRadioButton button;

	/**
	 * The constructor for {@code GetWorkersAL}.
	 * 
	 * @param projectField
	 *            A {@code JTextField} containing the {@code AWorker}'s type.
	 * @param button
	 *            The button which selection state will be used to determine
	 *            whether the user is looking for the manager, or the team of
	 *            consultants.
	 */
	public GetWorkersAL(JTextField projectField, JRadioButton button) {
		this.projectField = projectField;
		this.button = button;
	}

	/**
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callGetWorkersInProject}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callGetWorkersInProject} will not be called.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String pid;
		try {
			pid = projectField.getText();
			workerOpt = button.isSelected() ? "consultant" : "manager";
		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (pid.length() == 0) {
			new ErrorDialog("At least one field was left blank.")
					.setVisible(true);
			return;
		}

		try {
			GUIUtils.getCommandCaller().callGetWorkersInProject(pid, workerOpt);

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
			return;
		}
		
		FrameAndPanelHolder.resetFields();
	}

}
