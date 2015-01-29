package app.windows.commandWindowsAL.commandWindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import app.windows.commandWindowsAL.GetProjectAL;
import app.windows.mainFrameAL.mainFrame.ProjectID;

/**
 * This {@code MainGetPanel} allows to insert the mandatory parameters to get a
 * {@code Project} from the repository.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class GetProjectsPanel extends MainGetPanel {

	private static final long serialVersionUID = -1075896035298801798L;
	private ProjectID projectId;

	/**
	 * Create the frame.
	 */
	public GetProjectsPanel() {

		GridBagLayout gridBagLayout = (GridBagLayout) getMainGetPanel().getLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 100, 50, 50, 0, 100, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 30, 20, 0, 20, 0, 0, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		
		this.setImage("images/project1.png");
		this.setTitleLabel("Get Project");
		this.setHelpTip("Return the all the known information about the project with the specify Id.");
		
		projectId = new ProjectID();
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.gridwidth = 4;
		gbc_lblProjectID.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 3;
		gbc_lblProjectID.gridy = 3;
		getMainGetPanel().add(projectId, gbc_lblProjectID);
		
		results = new JPanel();
		GridBagConstraints gbc_results = new GridBagConstraints();
		gbc_results.gridwidth = 8;
		gbc_results.insets = new Insets(0, 0, 0, 5);
		gbc_results.fill = GridBagConstraints.BOTH;
		gbc_results.gridx = 1;
		gbc_results.gridy = 7;
		getMainGetPanel().add(results, gbc_results);
		
		this.getGetButton().addActionListener(new GetProjectAL(projectId.getProjectIDField()));
		
		this.setVisible(true);
	}

	@Override
	public void resetAllFields() {
		projectId.resetAllFields();
	}
}
