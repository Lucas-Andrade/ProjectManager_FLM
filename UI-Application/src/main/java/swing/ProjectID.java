package swing;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

public class ProjectID extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ProjectID() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 5};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 1};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 2;
		gbc_label.gridy = 2;
		add(label, gbc_label);
		
		
		
		JLabel lblProjectID = new JLabel("Project ID:");  // Labels e campos a ser preenchidos
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 2;
		gbc_lblProjectID.gridy = 3;
	//	getMainDialogPanel().add(lblProjectID, gbc_lblProjectID);
	

		//elementos da lista
		String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };  //terá a lista dos projectos no repositório 
		JComboBox projectComboBox = new JComboBox(petStrings);
		GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
		gbc_projectComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_projectComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_projectComboBox.gridx = 3;
		gbc_projectComboBox.gridy = 3;
	//	getMainDialogPanel().add(projectComboBox, gbc_projectComboBox);
		projectComboBox.setEditable(true);
		//projectComboBox.addActionListener(this);
	}

}
