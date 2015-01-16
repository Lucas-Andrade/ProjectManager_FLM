package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ManagerID extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox boxManager;
	private JComboBox<?> managerCBox;
	/**
	 * Create the panel.
	 */
	public ManagerID() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {87, 200, 5};
		gridBagLayout.rowHeights = new int[] {0, 1};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
			
			
			//elementos da lista -> alterar
			
		String[] managers = { "", "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		
		//terá a lista dos projectos no repositório (tentar que permita a seleção de vários projectos)
		
		boxManager = new JCheckBox("Manager");
		GridBagConstraints gbc_boxManager = new GridBagConstraints();
		gbc_boxManager.fill = GridBagConstraints.HORIZONTAL;
		gbc_boxManager.insets = new Insets(0, 0, 0, 5);
		gbc_boxManager.gridx = 0;
		gbc_boxManager.gridy = 0;
		add(boxManager, gbc_boxManager);
		boxManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		managerCBox = new JComboBox<Object>(managers);
		GridBagConstraints gbc_managerCBox = new GridBagConstraints();
		gbc_managerCBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_managerCBox.gridx = 1;
		gbc_managerCBox.gridy = 0;
		add(managerCBox, gbc_managerCBox);
		managerCBox.setEditable(true);
		managerCBox.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
