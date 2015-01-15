package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
<<<<<<< HEAD:UI-Application/src/main/java/swing/PostProject.java

=======
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
>>>>>>> 36468fd7df7c31c1333fec16c867e1277b7cf1a7:UI-Application/src/main/java/app/framesAndPanels/PostProjectFrame.java
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

<<<<<<< HEAD:UI-Application/src/main/java/swing/PostProject.java
public class PostProject extends MainDialogFrame {
=======
public class PostProjectFrame extends JDialog {
>>>>>>> 36468fd7df7c31c1333fec16c867e1277b7cf1a7:UI-Application/src/main/java/app/framesAndPanels/PostProjectFrame.java

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
			PostProjectFrame dialog = new PostProjectFrame(null);
			//definimos o título da janel
			dialog.setTitle("Post Project");
			dialog.setImage("images/project.png");
			dialog.setTitleLabel("Post Project");
			dialog.setHelpTip("Add a Project to the Project repository.");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
<<<<<<< HEAD:UI-Application/src/main/java/swing/PostProject.java
	public PostProject() {
		super();
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 50, 20, 15, 20, 15, 0, 0};
=======
	public PostProjectFrame(ActionListener okActionListener) {
		//Definição da Caixa de Diálogo
		setBounds(100, 100, 636, 387);
		getContentPane().setLayout(new BorderLayout());
		postProjectPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(postProjectPanel, BorderLayout.CENTER);
>>>>>>> 36468fd7df7c31c1333fec16c867e1277b7cf1a7:UI-Application/src/main/java/app/framesAndPanels/PostProjectFrame.java
		
		JLabel lblLocation = new JLabel("Location:");
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 2;
		gbc_lblLocation.gridy = 3;
		getMainDialogPanel().add(lblLocation, gbc_lblLocation);
	
<<<<<<< HEAD:UI-Application/src/main/java/swing/PostProject.java
=======
		{	//Inserir imagem : Project -> Path e localização no Painel
			JLabel projectLabel = new JLabel("");
			projectLabel.setIcon(new ImageIcon(PatchProjectFrame.class.getClassLoader().getResource("images/project.png")));
			GridBagConstraints gbc_lblProject = new GridBagConstraints();
			gbc_lblProject.gridheight = 3;
			gbc_lblProject.insets = new Insets(0, 0, 5, 5);
			gbc_lblProject.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblProject.gridx = 1;
			gbc_lblProject.gridy = 4;
			postProjectPanel.add(projectLabel, gbc_lblProject);
		}
		
		{
			JLabel lblLocation = new JLabel("Location:");
			GridBagConstraints gbc_lblLocation = new GridBagConstraints();
			gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
			gbc_lblLocation.gridx = 2;
			gbc_lblLocation.gridy = 3;
			postProjectPanel.add(lblLocation, gbc_lblLocation);
		}
				
>>>>>>> 36468fd7df7c31c1333fec16c867e1277b7cf1a7:UI-Application/src/main/java/app/framesAndPanels/PostProjectFrame.java
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 5;
		getMainDialogPanel().add(lblName, gbc_lblName);


		nameField = new JTextField();
		nameField.setColumns(10);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.anchor = GridBagConstraints.SOUTH;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 5;
		getMainDialogPanel().add(nameField, gbc_nameField);

		
<<<<<<< HEAD:UI-Application/src/main/java/swing/PostProject.java
		JLabel lblPrice = new JLabel("Price:");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 5;
		gbc_lblPrice.gridy = 5;
		getMainDialogPanel().add(lblPrice, gbc_lblPrice);
		lblPrice.setToolTipText("Euros");


		priceField = new JTextField();
		priceField.setColumns(10);
		GridBagConstraints gbc_priceField = new GridBagConstraints();
		gbc_priceField.anchor = GridBagConstraints.SOUTH;
		gbc_priceField.insets = new Insets(0, 0, 5, 5);
		gbc_priceField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceField.gridx = 6;
		gbc_priceField.gridy = 5;
		getMainDialogPanel().add(priceField, gbc_priceField);
=======
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(okActionListener);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new CancelActionListener(this));
			}
		}
	}
>>>>>>> 36468fd7df7c31c1333fec16c867e1277b7cf1a7:UI-Application/src/main/java/app/framesAndPanels/PostProjectFrame.java


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
