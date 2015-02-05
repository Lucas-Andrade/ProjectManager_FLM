package commandWindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import commandWindowsAL.GetWorkersAL;
import mainFrameAL.mainFrame.ProjectID;


/**
 * This {@code MainGetPanel} allows to insert the mandatory parameters to get the
 * {@code Team} or the {@code Manager} of a particular {@code Project}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class GetWorkersInProjectPanel extends MainGetPanel {

	private static final long serialVersionUID = 3937953220998193520L;
	private ProjectID projectId;
	private JRadioButton manager;
	private JLabel label;
	private JRadioButton consultants;
	

	/**
	 * Create the frame.
	 */
	public GetWorkersInProjectPanel() {
		setBounds(100, 100, 676, 387);
		GridBagLayout gridBagLayout = (GridBagLayout) getMainGetPanel().getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 100, 0, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 42, 0, 0, 0, 0, 0};
		
		this.setImage("images/getWorkerProject.png");
		this.setTitleLabel("Get Workers In Project");
		this.setHelpTip("Return the information of all subprojects of a project with the specify Id.");

		
		projectId = new ProjectID();
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.gridwidth = 4;
		gbc_lblProjectID.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 2;
		gbc_lblProjectID.gridy = 2;
		getMainGetPanel().add(projectId, gbc_lblProjectID);
		
		label = new JLabel("Workers:");
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
		
		consultants = new JRadioButton("Consultants");
		GridBagConstraints gbc_consultantId = new GridBagConstraints();
		gbc_consultantId.anchor = GridBagConstraints.NORTHWEST;
		gbc_consultantId.insets = new Insets(0, 0, 5, 5);
		gbc_consultantId.gridx = 3;
		gbc_consultantId.gridy = 5;
		getMainGetPanel().add(consultants, gbc_consultantId);
		consultants.setSelected(true);
		
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(manager);
	    group.add(consultants);
	    
	    results = new JPanel();
	    GridBagConstraints gbc_panel = new GridBagConstraints();
	    gbc_panel.gridwidth = 7;
	    gbc_panel.insets = new Insets(0, 0, 5, 5);
	    gbc_panel.fill = GridBagConstraints.BOTH;
	    gbc_panel.gridx = 1;
	    gbc_panel.gridy = 7;
	    getMainGetPanel().add(results, gbc_panel);
		
		//String worker = (consultants.isSelected()) ? "Consultant" : manager.getText();
	    this.getGetButton().addActionListener(new GetWorkersAL(projectId.getProjectIDField(), consultants));
	    
		this.setVisible(true);
	}


	@Override
	public void resetAllFields() {
		projectId.resetAllFields();
		manager.setText("");
		label.setText("");
		consultants.setText("");
	}

}
