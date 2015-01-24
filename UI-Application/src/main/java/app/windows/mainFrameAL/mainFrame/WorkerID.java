package app.windows.mainFrameAL.mainFrame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class WorkerID extends JPanel {

	private static final long serialVersionUID = 7106518727634653163L;
	private JTextField consultants;
	private JRadioButton rdbtnConsultants;
	private JTextField managerId;
	private JRadioButton radbtnManager;

	/**
	 * Create the panel.
	 */
	public WorkerID() {
		
		this.setBackground(new Color(176, 196, 222));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {75, 200, 5};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 1};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		radbtnManager = new JRadioButton("Manager");
		radbtnManager.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_radbtnManager = new GridBagConstraints();
		gbc_radbtnManager.insets = new Insets(0, 0, 5, 5);
		gbc_radbtnManager.gridx = 0;
		gbc_radbtnManager.gridy = 0;
		add(radbtnManager, gbc_radbtnManager);
		
		managerId = new JTextField();
		managerId.setToolTipText("Worker ID");
		managerId.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(managerId, gbc_textField);
		managerId.setEnabled(false);
		
		radbtnManager.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnConsultants.setSelected(false);
				managerId.setEnabled(true);
				consultants.setText("");
				consultants.setEnabled(false);
			}
			
		});
		
		rdbtnConsultants = new JRadioButton("Consultant");
		rdbtnConsultants.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_rdbtnConsultants = new GridBagConstraints();
		gbc_rdbtnConsultants.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnConsultants.gridx = 0;
		gbc_rdbtnConsultants.gridy = 2;
		add(rdbtnConsultants, gbc_rdbtnConsultants);
		rdbtnConsultants.setSelected(true);
		
		consultants = new JTextField();
		consultants.setToolTipText("Worker ID");
		GridBagConstraints gbc_consultant = new GridBagConstraints();
		gbc_consultant.fill = GridBagConstraints.HORIZONTAL;
		gbc_consultant.gridx = 1;
		gbc_consultant.gridy = 2;
		add(consultants, gbc_consultant);
		consultants.setColumns(10);
		
		rdbtnConsultants.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				radbtnManager.setEnabled(false);
				consultants.setEnabled(true);
				managerId.setText("");
				managerId.setEnabled(false);
			}
			
		});
		
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(radbtnManager);
	    group.add(rdbtnConsultants);
	}
	
	
	public String getSelected(){
		return rdbtnConsultants.isSelected()? rdbtnConsultants.getText() : radbtnManager.getText();
	}
	
	
	public JTextField getIDField(){
		return rdbtnConsultants.isSelected()? consultants : managerId;
	}
	
	
	public void setName(String text){
		rdbtnConsultants.setText(text);
	}
}
