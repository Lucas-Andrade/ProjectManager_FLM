package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import app.repositoryHolders.InMemoryRepositoryHolder;
import app.result.CommandResult;
import app.result.PostProjectResult;

public class PostProjectFrame extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField longitudeField;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField latitudeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PostProjectFrame dialog = new PostProjectFrame(new PostProjectResult(new JSplitPane(), new InMemoryRepositoryHolder()));
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
	public PostProjectFrame(CommandResult result) {
		super(result);
		
		this.setTitle("New Project");
		this.setImage("images/project.png");
		this.setTitleLabel("New Project");
		this.setHelpTip("Add a Project to the Project repository.");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 50, 20, 15, 20, 15, 0, 0};
	
		JLabel lblLocation = new JLabel("Location:");
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 2;
		gbc_lblLocation.gridy = 3;
		getMainDialogPanel().add(lblLocation, gbc_lblLocation);
	

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

		JLabel lblLatitude = new JLabel("Latitude:");
		GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
		gbc_lblLatitude.anchor = GridBagConstraints.EAST;
		gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblLatitude.gridx = 2;
		gbc_lblLatitude.gridy = 7;
		getMainDialogPanel().add(lblLatitude, gbc_lblLatitude);


		latitudeField = new JTextField();
		GridBagConstraints gbc_latitudeField = new GridBagConstraints();
		gbc_latitudeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_latitudeField.insets = new Insets(0, 0, 5, 5);
		gbc_latitudeField.gridx = 3;
		gbc_latitudeField.gridy = 7;
		getMainDialogPanel().add(latitudeField, gbc_latitudeField);
		latitudeField.setColumns(10);


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
		
		JTextField[] textFields = new JTextField[4];
		textFields[0] = nameField; //assign each field to a position in the array
		textFields[1] = priceField;
		textFields[2] = longitudeField;
		textFields[3] = latitudeField;
		this.setOkButtonActionListener(textFields);
	}
}
