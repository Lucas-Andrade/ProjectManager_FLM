package app.windows.commandWindowsAL.commandWindows;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.windows.mainFrameAL.mainFrame.ProjectID;

import java.awt.GridBagLayout;


public class PatchProjectFrame extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -256281858791279368L;
	private JTextField LatitudeField;
	private JTextField longitudeField;
	private JTextField nameField;
	private JTextField priceField;
	private ProjectID projectId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PatchProjectFrame dialog = new PatchProjectFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public PatchProjectFrame() {
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 20, 0, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 100, 60, 100, 0, 25, 0, 0, 0};

		this.setTitle("Edit Project");
		this.setImage("images/editProject.png");
		this.setTitleLabel("Edit Project");
		this.setHelpTip("Update the information of the project identified by the specify Id");
		
		projectId = new ProjectID();
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.gridwidth = 3;
		gbc_lblProjectID.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 2;
		gbc_lblProjectID.gridy = 2;
		getMainDialogPanel().add(projectId, gbc_lblProjectID);
		

			JLabel lblLocation = new JLabel("Location:");
			GridBagConstraints gbc_lblLocation = new GridBagConstraints();
			gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
			gbc_lblLocation.gridx = 2;
			gbc_lblLocation.gridy = 4;
			getMainDialogPanel().add(lblLocation, gbc_lblLocation);
		
			
				JLabel lblName = new JLabel("Name:");
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.anchor = GridBagConstraints.EAST;
				gbc_lblName.insets = new Insets(0, 0, 5, 5);
				gbc_lblName.gridx = 2;
				gbc_lblName.gridy = 5;
				getMainDialogPanel().add(lblName, gbc_lblName);
		
		
			nameField = new JTextField();
			nameField.setColumns(10);
			GridBagConstraints gbc_nameField = new GridBagConstraints();
			gbc_nameField.gridwidth = 2;
			gbc_nameField.insets = new Insets(0, 0, 5, 5);
			gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameField.gridx = 3;
			gbc_nameField.gridy = 5;
			getMainDialogPanel().add(nameField, gbc_nameField);
					

						JLabel lblPrice = new JLabel("Price:");
						GridBagConstraints gbc_lblPrice = new GridBagConstraints();
						gbc_lblPrice.anchor = GridBagConstraints.EAST;
						gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
						gbc_lblPrice.gridx = 2;
						gbc_lblPrice.gridy = 6;
						getMainDialogPanel().add(lblPrice, gbc_lblPrice);
						lblPrice.setToolTipText("Euros");
			
			
					priceField = new JTextField();
					priceField.setColumns(10);
					GridBagConstraints gbc_priceField = new GridBagConstraints();
					gbc_priceField.insets = new Insets(0, 0, 5, 5);
					gbc_priceField.fill = GridBagConstraints.HORIZONTAL;
					gbc_priceField.gridx = 3;
					gbc_priceField.gridy = 6;
					getMainDialogPanel().add(priceField, gbc_priceField);
		
			
			JLabel label = new JLabel("Euros");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.WEST;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 4;
			gbc_label.gridy = 6;
			getMainDialogPanel().add(label, gbc_label);
		
			
			JLabel lblLatitude = new JLabel("Latitude:");
			GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
			gbc_lblLatitude.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
			gbc_lblLatitude.gridx = 2;
			gbc_lblLatitude.gridy = 7;
			getMainDialogPanel().add(lblLatitude, gbc_lblLatitude);
		
			
			LatitudeField = new JTextField();
			GridBagConstraints gbc_latitudeField = new GridBagConstraints();
			gbc_latitudeField.anchor = GridBagConstraints.SOUTH;
			gbc_latitudeField.fill = GridBagConstraints.HORIZONTAL;
			gbc_latitudeField.insets = new Insets(0, 0, 5, 5);
			gbc_latitudeField.gridx = 3;
			gbc_latitudeField.gridy = 7;
			getMainDialogPanel().add(LatitudeField, gbc_latitudeField);
			LatitudeField.setColumns(10);
			LatitudeField.updateUI();
		
			
			JLabel lblLongitude = new JLabel("Longitude:");
			GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
			gbc_lblLongitude.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblLongitude.insets = new Insets(0, 0, 0, 5);
			gbc_lblLongitude.gridx = 2;
			gbc_lblLongitude.gridy = 8;
			getMainDialogPanel().add(lblLongitude, gbc_lblLongitude);
		
			
			longitudeField = new JTextField();
			GridBagConstraints gbc_longitudeField = new GridBagConstraints();
			gbc_longitudeField.anchor = GridBagConstraints.NORTH;
			gbc_longitudeField.fill = GridBagConstraints.HORIZONTAL;
			gbc_longitudeField.insets = new Insets(0, 0, 0, 5);
			gbc_longitudeField.gridx = 3;
			gbc_longitudeField.gridy = 8;
			getMainDialogPanel().add(longitudeField, gbc_longitudeField);
			longitudeField.setColumns(10);
	}

}