package app.windows.commandWindowsAL.commandWindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import app.repositoryHolders.InMemoryRepositoryHolder;
import app.result.CommandResult;
import app.result.GetSubprojectsResult;
import app.result.GetWorkersInProjectResult;
import app.windows.mainFrameAL.mainFrame.ProjectID;

public class GetWorkersInProjectFrame extends MainGetPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectID projectId;
	private JRadioButton manager;
	private JRadioButton consultantId;
	private JLabel label;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GetWorkersInProjectFrame dialog = new GetWorkersInProjectFrame(new GetWorkersInProjectResult(new JSplitPane(), new InMemoryRepositoryHolder()));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * Create the frame.
	 */
	public GetWorkersInProjectFrame(CommandResult result) {
		super();
		
		setBounds(100, 100, 676, 387);
		GridBagLayout gridBagLayout = (GridBagLayout) getMainGetPanel().getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 100, 0, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 42, 0, 0, 0, 0, 0};
		
		this.setTitle("Get Workers in Project");
		this.setImage("images/getUser.jpg");
		this.setTitleLabel("Get Workers In Project");
		this.setHelpTip("Return the information of all subprojects of a project with the specify Id.");

		
		projectId = new ProjectID();
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.gridwidth = 3;
		gbc_lblProjectID.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 2;
		gbc_lblProjectID.gridy = 2;
		getMainGetPanel().add(projectId, gbc_lblProjectID);
		
		label = new JLabel("Add Workers:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 3;
		getMainGetPanel().add(label, gbc_label);
		
		
		//Opções de seleção
		manager = new JRadioButton("Manager");
		GridBagConstraints gbc_manager = new GridBagConstraints();
		gbc_manager.anchor = GridBagConstraints.NORTHWEST;
		gbc_manager.insets = new Insets(0, 0, 5, 5);
		gbc_manager.gridx = 3;
		gbc_manager.gridy = 4;
		getMainGetPanel().add(manager, gbc_manager);
		
		
		consultantId = new JRadioButton("Consultants");
		GridBagConstraints gbc_consultantId = new GridBagConstraints();
		gbc_consultantId.anchor = GridBagConstraints.NORTHWEST;
		gbc_consultantId.insets = new Insets(0, 0, 5, 5);
		gbc_consultantId.gridx = 3;
		gbc_consultantId.gridy = 5;
		getMainGetPanel().add(consultantId, gbc_consultantId);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 2;
		gbc_table.gridwidth = 6;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 2;
		gbc_table.gridy = 6;
		getMainGetPanel().add(table, gbc_table);
		
	}

}
