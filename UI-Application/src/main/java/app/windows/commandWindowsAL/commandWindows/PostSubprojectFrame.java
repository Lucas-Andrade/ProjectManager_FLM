package app.windows.commandWindowsAL.commandWindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JTextField;

import app.windows.commandWindowsAL.NewProjectAL;
import app.windows.mainFrameAL.mainFrame.ProjectID;

public class PostSubprojectFrame extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8218293500239308528L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PostSubprojectFrame dialog = new PostSubprojectFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PostSubprojectFrame() {
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 50, 0, 20, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		
		this.setTitle("Add Subproject");
		this.setImage("images/subproject.jpg");
		this.setTitleLabel("Add Subproject");
		this.setHelpTip("Add a subproject to a projects/subprojects.");
			
		ProjectID projectId1 = new ProjectID();
		GridBagConstraints gbc_ProjectID = new GridBagConstraints();
		gbc_ProjectID.gridwidth = 4;
		gbc_ProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_ProjectID.fill = GridBagConstraints.HORIZONTAL;
		gbc_ProjectID.gridx = 2;
		gbc_ProjectID.gridy = 3;
		getMainDialogPanel().add(projectId1, gbc_ProjectID);
		
		
		ProjectID subprojectId = new ProjectID();
		GridBagConstraints gbc_subprojectID = new GridBagConstraints();
		gbc_subprojectID.gridwidth = 4;
		gbc_subprojectID.anchor = GridBagConstraints.SOUTHEAST;
		gbc_subprojectID.insets = new Insets(0, 0, 5, 5);
		gbc_subprojectID.gridx = 2;
		gbc_subprojectID.gridy = 5;
		getMainDialogPanel().add(subprojectId, gbc_subprojectID);
		subprojectId.setName("Add Subprojects ID:");
		

		JTextField[] textFields = new JTextField[2];
		textFields[0] = projectId1.getProjectIDField();
		textFields[1] = subprojectId.getProjectIDField();
		this.getSaveButton().addActionListener(new NewProjectAL(textFields));
		
		this.setVisible(true);
		}
	}
