package guiElements.commandWindowsAL;

import guiElements.FrameAndPanelHolder;
import guiElements.GUIUtils;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;
import guiElements.mainFrameAL.mainFrame.WorkerID;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;


/**
 * Class responsible for calling the method {@code ICommandCaller#callPostWorkerInProject}.
 * Implements {@code ActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class NewWorkerInProjectAL implements ActionListener {

	/**
	 * An array of {@code JTextField}s containing the parameters for
	 * instantiating the {@code Command} {@link AddWorkerToProjectInRepo}.
	 */
	private JTextField textFields;

	/**
	 * The {@code WorkerID} containing information about the worker to be added
	 * in a {@code Project}.
	 */
	private WorkerID workerID;

	/**
	 * The constructor for {@code NewWorkerInProjectAL}.
	 * 
	 * @param textFields
	 *            A {@code JTextField} containing the parameters for
	 *            instantiating the {@code Command}
	 *            {@link AddWorkerToProjectInRepo}.
	 * @param workerId
	 *            The {@code WorkerID} containing information about the
	 *            {@code AWorker} to be added in a {@code Project}.
	 */
	public NewWorkerInProjectAL(JTextField textFields, WorkerID workerId) {
		this.textFields = textFields;
		this.workerID = workerId;
	}

	/**
	 * Tests if the {@code TextField}s received from the frame that constructed {@code this}
	 * were left blank and calls the method {@code ICommandCaller#callPostWorkerInProject}.
	 * If a field was left blank an {@code ErrorDialog} will be set to visible and
	 * the method {@code ICommandCaller#callPostWorkerInProject} will not be called.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String worker;
		String pid;
		String cid;
		try {
			pid = textFields.getText();
			cid = workerID.getIDField().getText();
			worker = workerID.getSelected();
		} catch (NullPointerException | ArrayIndexOutOfBoundsException a) {
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		if (pid.length() == 0 || cid.length() == 0 || worker.length() == 0) {
			new ErrorDialog("Required parameter not present.").setVisible(true);
			return;
		}
		try {
			GUIUtils.getCommandCaller().callPostWorkerInProject(pid, cid, worker);

		} catch (IllegalArgumentException iae) {
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
			return;
		}
		
		FrameAndPanelHolder.resetFields();
	}

}
