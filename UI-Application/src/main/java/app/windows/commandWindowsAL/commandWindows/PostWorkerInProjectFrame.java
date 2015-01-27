package app.windows.commandWindowsAL.commandWindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

import app.windows.commandWindowsAL.NewWorkerInProjectAL;
import app.windows.mainFrameAL.mainFrame.ProjectID;
import app.windows.mainFrameAL.mainFrame.WorkerID;

/**
 * This {@code Frame} allows to insert the mandatory parameters to add
 * {@code AWorker}s to a {@code Project}. It allows both to add {@code AWorker}s
 * to the {@code Team}, and to set a new {@code Leader} as the {@code Project}'s
 * {@code Leader}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PostWorkerInProjectFrame extends MainDialogFrame {

	
	private static final long serialVersionUID = -2466258711524395300L;
	private ProjectID projectId;
	private WorkerID workerID;
	

	/**
	 * Create the dialog.
	 */
	public PostWorkerInProjectFrame() {
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{5, 100, 0, 100};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 10, 40, 0, 0, 0, 0};
		
		this.setTitle("Add Worker In Project");
		this.setImage("images/addWorkerProject.png");
		this.setTitleLabel("Add Worker In Project");
		this.setHelpTip("Add a Consultant or Manager to a project/subproject.");

		
		projectId = new ProjectID();
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.gridwidth = 4;
		gbc_lblProjectID.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 2;
		gbc_lblProjectID.gridy = 2;
		getMainDialogPanel().add(projectId, gbc_lblProjectID);
	
		
		JLabel lblAddWorker = new JLabel("Add Workers:");
		GridBagConstraints gbc_lblAddWorker = new GridBagConstraints();
		gbc_lblAddWorker.anchor = GridBagConstraints.EAST;
		gbc_lblAddWorker.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddWorker.gridx = 2;
		gbc_lblAddWorker.gridy = 4;
		getMainDialogPanel().add(lblAddWorker, gbc_lblAddWorker);
	
		
		workerID = new WorkerID();
		GridBagConstraints gbc_Manager = new GridBagConstraints();
		gbc_Manager.gridwidth = 4;
		gbc_Manager.fill = GridBagConstraints.HORIZONTAL;
		gbc_Manager.insets = new Insets(0, 0, 5, 5);
		gbc_Manager.gridx = 3;
		gbc_Manager.gridy = 5;
		getMainDialogPanel().add(workerID, gbc_Manager);
		
		this.getSaveButton().addActionListener(new NewWorkerInProjectAL(projectId.getProjectIDField(), workerID));
		pack();
		
	}


	@Override
	public void resetAllFields() {
		projectId.resetAllFields();
		workerID.resetAllFields();	
	}
}
