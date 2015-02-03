package swingApp.app.windows.mainFrameAL.mainFrame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProjectID extends JPanel {

	private static final long serialVersionUID = 830972996693805538L;
	private JTextField projectID;
	private JLabel lblProjectID;
	
	/**
	 * Create the panel.
	 */
	public ProjectID() {
		this.setBackground(new Color(176, 196, 222));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {100, 200, 5};
		gridBagLayout.rowHeights = new int[] {10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0};
		setLayout(gridBagLayout);
		
		
		lblProjectID = new JLabel("Project ID:");  // Labels e campos a ser preenchidos
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.anchor = GridBagConstraints.EAST;
		gbc_lblProjectID.insets = new Insets(0, 0, 0, 5);
		gbc_lblProjectID.gridx = 0;
		gbc_lblProjectID.gridy = 0;
		add(lblProjectID, gbc_lblProjectID);
		
		projectID = new JTextField();
		GridBagConstraints gbc_projectID = new GridBagConstraints();
		gbc_projectID.fill = GridBagConstraints.HORIZONTAL;
		gbc_projectID.gridx = 1;
		gbc_projectID.gridy = 0;
		add(projectID, gbc_projectID);
		projectID.setColumns(10);	
	}
	
	public void setName(String text){
		lblProjectID.setText(text);
	}
	
	public JTextField getProjectIDField()
	{
		return projectID;
	}
	public void resetAllFields() {
		projectID.setText("");
	}

}
