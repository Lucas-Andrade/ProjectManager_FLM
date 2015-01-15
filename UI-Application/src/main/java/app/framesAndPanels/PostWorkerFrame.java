package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PostWorkerFrame extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField previewField;
	ProjectID projectId;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PostWorkerFrame dialog = new PostWorkerFrame();
			dialog.setTitle("Add Worker In Project");
			dialog.setImage("images/Add.jpg");
			dialog.setTitleLabel("Add Worker In Project");
			dialog.setHelpTip("Add a Consultant or Manager to a project/subproject.");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PostWorkerFrame() 
	{
		super();
		
		this.setTitle("Add Worker In Project");
		this.setImage("images/Add.jpg");
		this.setTitleLabel("Add Worker In Project");
		this.setHelpTip("Add a Consultant or Manager to a project/subproject.");

		
		projectId = new ProjectID();
//		JLabel lblProjectID = new JLabel("Project ID:");
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProjectID.gridwidth = 3;
		gbc_lblProjectID.anchor = GridBagConstraints.SOUTH;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 2;
		gbc_lblProjectID.gridy = 3;
		getMainDialogPanel().add(getProjectIdFrame(), gbc_lblProjectID);
	

		
		JLabel lblAddWorker = new JLabel("Add Workers:");
		GridBagConstraints gbc_lblAddWorker = new GridBagConstraints();
		gbc_lblAddWorker.anchor = GridBagConstraints.EAST;
		gbc_lblAddWorker.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddWorker.gridx = 2;
		gbc_lblAddWorker.gridy = 5;
		getMainDialogPanel().add(lblAddWorker, gbc_lblAddWorker);
	
	
	//terá a lista dos projectos no repositório (tentar que permita a seleção de vários projectos)
					
		JCheckBox chckbxManager = new JCheckBox("Manager");
		GridBagConstraints gbc_chckbxManager = new GridBagConstraints();
		gbc_chckbxManager.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxManager.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxManager.gridx = 3;
		gbc_chckbxManager.gridy = 6;
		getMainDialogPanel().add(chckbxManager, gbc_chckbxManager);
	
	
	//elementos da lista -> alterar
	
		String[] managers = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		JComboBox subprojectComboBox = new JComboBox(managers);
		GridBagConstraints gbc_projectComboBox1 = new GridBagConstraints();
		gbc_projectComboBox1.insets = new Insets(0, 0, 5, 5);
		gbc_projectComboBox1.fill = GridBagConstraints.HORIZONTAL;
		gbc_projectComboBox1.gridx = 4;
		gbc_projectComboBox1.gridy = 6;
		getMainDialogPanel().add(subprojectComboBox, gbc_projectComboBox1);
		subprojectComboBox.setEditable(true);
	
		
		JCheckBox chckbxConsultants = new JCheckBox("Consultants");
		GridBagConstraints gbc_chckbxConsultants = new GridBagConstraints();
		gbc_chckbxConsultants.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxConsultants.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxConsultants.gridx = 3;
		gbc_chckbxConsultants.gridy = 7;
		getMainDialogPanel().add(chckbxConsultants, gbc_chckbxConsultants);
	
	
	//elementos da lista -> alterar
		String[] consultants = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		JComboBox consultantsComboBox = new JComboBox(consultants);
		consultantsComboBox.setEditable(true);
		GridBagConstraints gbc_consultantsComboBox = new GridBagConstraints();
		gbc_consultantsComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_consultantsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_consultantsComboBox.gridx = 4;
		gbc_consultantsComboBox.gridy = 7;
		getMainDialogPanel().add(consultantsComboBox, gbc_consultantsComboBox);


		previewField = new JTextField();
		GridBagConstraints gbc_previewField = new GridBagConstraints();
		gbc_previewField.gridwidth = 8;
		gbc_previewField.insets = new Insets(0, 0, 0, 5);
		gbc_previewField.fill = GridBagConstraints.BOTH;
		gbc_previewField.gridx = 1;
		gbc_previewField.gridy = 8;
		getMainDialogPanel().add(previewField, gbc_previewField);
		previewField.setColumns(10);
		}
	
	public ProjectID getProjectIdFrame(){
		return projectId;
	}
	
}
