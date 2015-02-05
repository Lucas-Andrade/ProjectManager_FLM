package commandWindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import mainFrame.ProjectID;
import app.windows.commandWindowsAL.RemoveProjectAL;

/**
 * This {@code MainDialogFrame} allows to insert the mandatory parameters to
 * remove a {@code Project} and it's subprojects from the repository. 
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015 *
 */
public class DeleteProjectFrame extends MainDialogFrame {

	private static final long serialVersionUID = 1L;
	private ProjectID projectId;

	/**
	 * Create the dialog.
	 */

	public DeleteProjectFrame() {
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 32, 0, 0, 0, 0, 0};
		
		this.setImage("images/project-delete.png");
		this.setTitle("Delete Project");
		this.setTitleLabel("Delete Project");
		this.setHelpTip("Deletes the project with the specify Id and all its subprojects");
		
		projectId = new ProjectID();
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.gridwidth = 4;
		gbc_lblProjectID.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 2;
		gbc_lblProjectID.gridy = 4;
		getMainDialogPanel().add(projectId, gbc_lblProjectID);

		this.getSaveButton().addActionListener(new RemoveProjectAL(projectId.getProjectIDField()));
		pack();
	}

	@Override
	public void resetAllFields() {
		projectId.resetAllFields();
	}


}
