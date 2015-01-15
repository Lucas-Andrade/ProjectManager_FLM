package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class PatchConsultantFrame extends MainDialogFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField priceHour;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			PatchConsultantFrame dialog = new PatchConsultantFrame();
			
			dialog.setTitle("Edit Consultant");    //definimos o título da janela
			dialog.setImage("images/edit.jpg");
			dialog.setTitleLabel("Edit Consultant");
			dialog.setHelpTip("Updates the information of the consultant with the specify Id.");
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public PatchConsultantFrame() 
	{
		super();
		
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.columnWidths = new int[]{64, 0, 0, 0, 0, 0, 0, 0, 0};

		JLabel lblConsultantId = new JLabel("Consultant ID:");
		GridBagConstraints gbc_lblConsultantId = new GridBagConstraints();
		gbc_lblConsultantId.anchor = GridBagConstraints.EAST;
		gbc_lblConsultantId.insets = new Insets(0, 0, 5, 5);
		gbc_lblConsultantId.gridx = 2;
		gbc_lblConsultantId.gridy = 3;
		getMainDialogPanel().add(lblConsultantId, gbc_lblConsultantId);
	
		
		JComboBox consultantcomboBox = new JComboBox();
		GridBagConstraints gbc_consultantcomboBox = new GridBagConstraints();
		gbc_consultantcomboBox.insets = new Insets(0, 0, 5, 5);
		gbc_consultantcomboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_consultantcomboBox.gridx = 3;
		gbc_consultantcomboBox.gridy = 3;
		getMainDialogPanel().add(consultantcomboBox, gbc_consultantcomboBox);

		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 5;
		getMainDialogPanel().add(lblName, gbc_lblName);
	
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 3;
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.anchor = GridBagConstraints.SOUTH;
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 5;
		getMainDialogPanel().add(nameField, gbc_nameField);
		nameField.setColumns(10);
	
		
		JLabel lblPriceHour = new JLabel("Price/Hour:");
		lblPriceHour.setToolTipText("Euros");
		GridBagConstraints gbc_lblPriceHour = new GridBagConstraints();
		gbc_lblPriceHour.anchor = GridBagConstraints.EAST;
		gbc_lblPriceHour.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriceHour.gridx = 2;
		gbc_lblPriceHour.gridy = 6;
		getMainDialogPanel().add(lblPriceHour, gbc_lblPriceHour);
		lblPriceHour.setToolTipText("Euros");

	
		priceHour = new JTextField();
		GridBagConstraints gbc_priceHourField = new GridBagConstraints();
		gbc_priceHourField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceHourField.insets = new Insets(0, 0, 5, 5);
		gbc_priceHourField.gridx = 3;
		gbc_priceHourField.gridy = 6;
		getMainDialogPanel().add(priceHour, gbc_priceHourField);
		priceHour.setColumns(10);
		priceHour.setToolTipText("Euros");
	
		
		JLabel lblCoin = new JLabel("Euros");
		GridBagConstraints gbc_lblCoin = new GridBagConstraints();
		gbc_lblCoin.anchor = GridBagConstraints.WEST;
		gbc_lblCoin.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoin.gridx = 4;
		gbc_lblCoin.gridy = 6;
		getMainDialogPanel().add(lblCoin, gbc_lblCoin);	
	}
}
