package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ConsultantID extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ConsultantID() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {75, 200, 5};
		gridBagLayout.rowHeights = new int[] {0, 1};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
	
	
	//elementos da lista -> alterar
		String[] consultants = { "", "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		
		JCheckBox chckbxConsultants = new JCheckBox("Consultants");
		GridBagConstraints gbc_chckbxConsultants = new GridBagConstraints();
		gbc_chckbxConsultants.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxConsultants.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxConsultants.gridx = 0;
		gbc_chckbxConsultants.gridy = 0;
		add(chckbxConsultants, gbc_chckbxConsultants);
		
		JComboBox consultantsComboBox = new JComboBox(consultants);
		consultantsComboBox.setEditable(true);
		GridBagConstraints gbc_consultantsComboBox = new GridBagConstraints();
		gbc_consultantsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_consultantsComboBox.gridx = 1;
		gbc_consultantsComboBox.gridy = 0;
		add(consultantsComboBox, gbc_consultantsComboBox);
	}

}
