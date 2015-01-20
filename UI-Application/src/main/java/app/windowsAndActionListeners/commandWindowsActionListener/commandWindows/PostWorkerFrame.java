package app.windowsAndActionListeners.commandWindowsActionListener.commandWindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.windowsAndActionListeners.mainFrameActionListener.mainFrame.ConsultantID;
import app.windowsAndActionListeners.mainFrameActionListener.mainFrame.ManagerID;
import app.windowsAndActionListeners.mainFrameActionListener.mainFrame.ProjectID;

public class PostWorkerFrame extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField previewField;
	ProjectID projectId;
	private ManagerID manager;
	private ConsultantID consultantID;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	//	try {
			PostWorkerFrame dialog = new PostWorkerFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//	dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * Create the dialog.
	 */
	public PostWorkerFrame() {
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{5, 100, 0, 100};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 10, 0, 0, 0, 0, 0};
		
		this.setTitle("Add Worker In Project");
		this.setImage("images/Add.jpg");
		this.setTitleLabel("Add Worker In Project");
		this.setHelpTip("Add a Consultant or Manager to a project/subproject.");

		
		projectId = new ProjectID();
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.gridwidth = 3;
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
	
		
		manager = new ManagerID();
		GridBagConstraints gbc_Manager = new GridBagConstraints();
		gbc_Manager.gridwidth = 3;
		gbc_Manager.fill = GridBagConstraints.HORIZONTAL;
		gbc_Manager.insets = new Insets(0, 0, 5, 5);
		gbc_Manager.gridx = 3;
		gbc_Manager.gridy = 5;
		getMainDialogPanel().add(manager, gbc_Manager);
	
	
		consultantID = new ConsultantID();
		GridBagConstraints gbc_consultantID = new GridBagConstraints();
		gbc_consultantID.gridwidth = 3;
		gbc_consultantID.fill = GridBagConstraints.HORIZONTAL;
		gbc_consultantID.insets = new Insets(0, 0, 5, 5);
		gbc_consultantID.gridx = 3;
		gbc_consultantID.gridy = 6;
		getMainDialogPanel().add(consultantID, gbc_consultantID);
			

		previewField = new JTextField();
		GridBagConstraints gbc_previewField = new GridBagConstraints();
		gbc_previewField.insets = new Insets(0, 0, 0, 5);
		gbc_previewField.gridwidth = 6;
		gbc_previewField.fill = GridBagConstraints.BOTH;
		gbc_previewField.gridx = 2;
		gbc_previewField.gridy = 7;
		getMainDialogPanel().add(previewField, gbc_previewField);
		previewField.setColumns(10);
		
		this.setVisible(true);
	}
}
