package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;

<<<<<<< HEAD:UI-Application/src/main/java/swing/PatchProject.java
public class PatchProject extends MainDialogFrame {
=======
public class PatchProjectFrame extends JDialog {
>>>>>>> 36468fd7df7c31c1333fec16c867e1277b7cf1a7:UI-Application/src/main/java/app/framesAndPanels/PatchProjectFrame.java

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField LatitudeField;
	private JTextField longitudeField;
	private JTextField nameField;
	private JTextField priceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PatchProjectFrame dialog = new PatchProjectFrame();
			//definimos o título da janel
			dialog.setTitle("Patch Project");
			dialog.setImage("images/editProject.png");
			dialog.setTitleLabel("Patch Project");
			dialog.setHelpTip("Update the information of the project identified by the specify Id");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
<<<<<<< HEAD:UI-Application/src/main/java/swing/PatchProject.java
	public PatchProject() {
		super();
=======
	public PatchProjectFrame() {
		//Definição da Caixa de Diálogo
		setBounds(100, 100, 636, 387);
		getContentPane().setLayout(new BorderLayout());
		patchProjectPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(patchProjectPanel, BorderLayout.CENTER);
>>>>>>> 36468fd7df7c31c1333fec16c867e1277b7cf1a7:UI-Application/src/main/java/app/framesAndPanels/PatchProjectFrame.java
		
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 50, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 20, 20, 20};

<<<<<<< HEAD:UI-Application/src/main/java/swing/PatchProject.java
		JLabel lblProjectID = new JLabel("Project ID:");  // Labels e campos a ser preenchidos
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 2;
		gbc_lblProjectID.gridy = 3;
		getMainDialogPanel().add(lblProjectID, gbc_lblProjectID);
	

		String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };  //terá a lista dos projectos no repositório 
		JComboBox projectComboBox = new JComboBox(petStrings);
		GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
		gbc_projectComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_projectComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_projectComboBox.gridx = 3;
		gbc_projectComboBox.gridy = 3;
		getMainDialogPanel().add(projectComboBox, gbc_projectComboBox);
		projectComboBox.setEditable(true);
		//projectComboBox.addActionListener(this);
	

		JLabel lblLocation = new JLabel("Location:");
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 2;
		gbc_lblLocation.gridy = 5;
		getMainDialogPanel().add(lblLocation, gbc_lblLocation);
=======
			
		// Título do Comando : Patch Project
		{
			JLabel lblPatchProject = new JLabel("Patch Project");
			lblPatchProject.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
			GridBagConstraints gbc_lblPatchProject = new GridBagConstraints();
			gbc_lblPatchProject.gridwidth = 5;
			gbc_lblPatchProject.insets = new Insets(0, 0, 5, 5);
			gbc_lblPatchProject.gridx = 2;
			gbc_lblPatchProject.gridy = 0;
			patchProjectPanel.add(lblPatchProject, gbc_lblPatchProject);
		}
		
	    //Inserir imagem : user -> Path e localização no Painel
		{ 
			JLabel projectLabel = new JLabel("");
			projectLabel.setIcon(new ImageIcon(PatchProjectFrame.class.getClassLoader().getResource("images/project.png")));
			GridBagConstraints gbc_lblProject = new GridBagConstraints();
			gbc_lblProject.gridheight = 3;
			gbc_lblProject.insets = new Insets(0, 0, 5, 5);
			gbc_lblProject.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblProject.gridx = 1;
			gbc_lblProject.gridy = 4;
			patchProjectPanel.add(projectLabel, gbc_lblProject);
		}
		
		// Informação sobre o user que está a usar o programa
		{
			JLabel lblWellcome = new JLabel("Bem-vindo");
			GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
			gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
			gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
			gbc_lblWellcome.gridx = 6;
			gbc_lblWellcome.gridy = 1;
			patchProjectPanel.add(lblWellcome, gbc_lblWellcome);
		}
		{
			JLabel lblRegistryuser = new JLabel("RegistryUser");
			GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
			gbc_lblRegistryuser.anchor = GridBagConstraints.WEST;
			gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 0);
			gbc_lblRegistryuser.gridx = 7;
			gbc_lblRegistryuser.gridy = 1;
			patchProjectPanel.add(lblRegistryuser, gbc_lblRegistryuser);
		}
		
		   // Labels e campos a ser preenchidos
		{
			JLabel lblProjectID = new JLabel("Project ID:");
			GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
			gbc_lblProjectID.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
			gbc_lblProjectID.gridx = 2;
			gbc_lblProjectID.gridy = 3;
			patchProjectPanel.add(lblProjectID, gbc_lblProjectID);
		}
		
		//terá a lista dos projectos no repositório 
		{
			//elementos da lista
			String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
			
			//Create the combo box, select item at index 4.
			//Indices start at 0, so 4 specifies the pig.
			JComboBox projectComboBox = new JComboBox(petStrings);
			GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
			gbc_projectComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_projectComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_projectComboBox.gridx = 3;
			gbc_projectComboBox.gridy = 3;
			patchProjectPanel.add(projectComboBox, gbc_projectComboBox);
			projectComboBox.setEditable(true);
			//projectComboBox.addActionListener(this);
		}
		
			{
				JLabel lblLocation = new JLabel("Location:");
				GridBagConstraints gbc_lblLocation = new GridBagConstraints();
				gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
				gbc_lblLocation.gridx = 2;
				gbc_lblLocation.gridy = 5;
				patchProjectPanel.add(lblLocation, gbc_lblLocation);
			}
		
		
		   
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 2;
			gbc_lblName.gridy = 6;
			patchProjectPanel.add(lblName, gbc_lblName);
		}
		{
			nameField = new JTextField();
			nameField.setColumns(10);
			GridBagConstraints gbc_nameField = new GridBagConstraints();
			gbc_nameField.gridwidth = 2;
			gbc_nameField.insets = new Insets(0, 0, 5, 5);
			gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameField.gridx = 3;
			gbc_nameField.gridy = 6;
			patchProjectPanel.add(nameField, gbc_nameField);
		}
>>>>>>> 36468fd7df7c31c1333fec16c867e1277b7cf1a7:UI-Application/src/main/java/app/framesAndPanels/PatchProjectFrame.java
		
	
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 6;
		getMainDialogPanel().add(lblName, gbc_lblName);
	
	
		nameField = new JTextField();
		nameField.setColumns(10);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 2;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 6;
		getMainDialogPanel().add(nameField, gbc_nameField);
	

		JLabel lblPrice = new JLabel("Price:");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 5;
		gbc_lblPrice.gridy = 6;
		getMainDialogPanel().add(lblPrice, gbc_lblPrice);
		lblPrice.setToolTipText("Euros");


		priceField = new JTextField();
		priceField.setColumns(10);
		GridBagConstraints gbc_priceField = new GridBagConstraints();
		gbc_priceField.insets = new Insets(0, 0, 5, 5);
		gbc_priceField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceField.gridx = 6;
		gbc_priceField.gridy = 6;
		getMainDialogPanel().add(priceField, gbc_priceField);
	
		
		JLabel label = new JLabel("Euros");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 7;
		gbc_label.gridy = 6;
		getMainDialogPanel().add(label, gbc_label);
	
		
		JLabel lblLatitude = new JLabel("Latitude:");
		GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
		gbc_lblLatitude.anchor = GridBagConstraints.EAST;
		gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblLatitude.gridx = 2;
		gbc_lblLatitude.gridy = 7;
		getMainDialogPanel().add(lblLatitude, gbc_lblLatitude);
	
		
		LatitudeField = new JTextField();
		GridBagConstraints gbc_latitudeField = new GridBagConstraints();
		gbc_latitudeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_latitudeField.insets = new Insets(0, 0, 5, 5);
		gbc_latitudeField.gridx = 3;
		gbc_latitudeField.gridy = 7;
		getMainDialogPanel().add(LatitudeField, gbc_latitudeField);
		LatitudeField.setColumns(10);
	
		
		JLabel lblLongitude = new JLabel("Longitude:");
		GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
		gbc_lblLongitude.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblLongitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblLongitude.gridx = 2;
		gbc_lblLongitude.gridy = 8;
		getMainDialogPanel().add(lblLongitude, gbc_lblLongitude);
	
		
		longitudeField = new JTextField();
		GridBagConstraints gbc_longitudeField = new GridBagConstraints();
		gbc_longitudeField.anchor = GridBagConstraints.NORTH;
		gbc_longitudeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_longitudeField.insets = new Insets(0, 0, 5, 5);
		gbc_longitudeField.gridx = 3;
		gbc_longitudeField.gridy = 8;
		getMainDialogPanel().add(longitudeField, gbc_longitudeField);
		longitudeField.setColumns(10);
	}

}
