package app.framesAndPanels.mainFrameActionListener.mainFrame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProjectID extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox projectCBox;
	/**
	 * Create the panel.
	 */
	public ProjectID() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {100, 200, 5};
		gridBagLayout.rowHeights = new int[] {10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0};
		setLayout(gridBagLayout);
		
		
		JLabel lblProjectID = new JLabel("Project ID:");  // Labels e campos a ser preenchidos
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.anchor = GridBagConstraints.EAST;
		gbc_lblProjectID.insets = new Insets(0, 0, 0, 5);
		gbc_lblProjectID.gridx = 0;
		gbc_lblProjectID.gridy = 0;
		add(lblProjectID, gbc_lblProjectID);
		
		//terá a lista dos projectos no repositório 
				//elementos da lista -> alterar
		String[] projects = { "", "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		projectCBox = new JComboBox(projects);
		GridBagConstraints gbc_projectCBox = new GridBagConstraints();
		gbc_projectCBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_projectCBox.gridx = 1;
		gbc_projectCBox.gridy = 0;
		add(projectCBox, gbc_projectCBox);
		projectCBox.setEditable(true);
		projectCBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public String getSelectedItem()
	{
		return projectCBox.getSelectedItem().toString();
	}

}
