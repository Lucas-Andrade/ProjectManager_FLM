package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import app.repositoryHolders.InMemoryRepositoryHolder;
import app.repositoryHolders.RepositoryHolder;
import app.result.CommandResult;
import app.result.PostProjectResult;

public class PostSubprojectFrame extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField subprojects;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PostSubprojectFrame dialog = new PostSubprojectFrame(new PostProjectResult(new JSplitPane(), new InMemoryRepositoryHolder()));
			dialog.setTitle("Add Subproject");
			dialog.setImage("images/subproject.jpg");
			dialog.setTitleLabel("Add Subproject");
			dialog.setHelpTip("Add a subproject to a projects/subprojects.");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PostSubprojectFrame(CommandResult result) {
		super(result);

		this.setTitle("Add Subproject");
		this.setImage("images/subproject.jpg");
		this.setTitleLabel("Add Subproject");
		this.setHelpTip("Add a subproject to a projects/subprojects.");
		
		JLabel lblProjectID = new JLabel("Project ID:");
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 2;
		gbc_lblProjectID.gridy = 3;
		getMainDialogPanel().add(lblProjectID, gbc_lblProjectID);
	
		
		//elementos da lista de repositórios
		String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		
		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		JComboBox projectComboBox = new JComboBox(petStrings);
		GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
		gbc_projectComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_projectComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_projectComboBox.gridx = 3;
		gbc_projectComboBox.gridy = 3;
		getMainDialogPanel().add(projectComboBox, gbc_projectComboBox);
		projectComboBox.setEditable(true);
		//projectComboBox.addActionListener(this);


		JLabel lblSubprojects = new JLabel("Add Subprojects ID:");
		GridBagConstraints gbc_lblSubprojects = new GridBagConstraints();
		gbc_lblSubprojects.anchor = GridBagConstraints.EAST;
		gbc_lblSubprojects.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubprojects.gridx = 2;
		gbc_lblSubprojects.gridy = 5;
		getMainDialogPanel().add(lblSubprojects, gbc_lblSubprojects);
	
	
		JComboBox subprojectComboBox = new JComboBox(petStrings);
		GridBagConstraints gbc_projectComboBox1 = new GridBagConstraints();
		gbc_projectComboBox1.insets = new Insets(0, 0, 5, 5);
		gbc_projectComboBox1.fill = GridBagConstraints.HORIZONTAL;
		gbc_projectComboBox1.gridx = 3;
		gbc_projectComboBox1.gridy = 5;
		getMainDialogPanel().add(subprojectComboBox, gbc_projectComboBox1);
		subprojectComboBox.setEditable(true);
		//projectComboBox.addActionListener(this);
	
	
	//caixa de texto onde será inserida a informação do Get Subproject
	
		subprojects = new JTextField();
		GridBagConstraints gbc_subprojects = new GridBagConstraints();
		gbc_subprojects.gridwidth = 7;
		gbc_subprojects.insets = new Insets(0, 0, 0, 5);
		gbc_subprojects.fill = GridBagConstraints.BOTH;
		gbc_subprojects.gridx = 1;
		gbc_subprojects.gridy = 8;
		getMainDialogPanel().add(subprojects, gbc_subprojects);
		subprojects.setColumns(10);
		}
	}
