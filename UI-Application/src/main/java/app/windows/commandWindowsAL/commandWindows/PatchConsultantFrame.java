package app.windows.commandWindowsAL.commandWindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import app.windows.commandWindowsAL.SetConsultantAL;


public class PatchConsultantFrame extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2220765203521766381L;
	private JTextField nameField;
	private JTextField priceHour;
	private JLabel lblConsultant;
	private JTextField consultantId;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			PatchConsultantFrame dialog = new PatchConsultantFrame();
			
			dialog.setTitle("Edit Consultant");    //definimos o título da janela
			dialog.setImage("images/editConsultant.png");
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
	public PatchConsultantFrame() {
		this.setTitle("Edit Consultant");    //definimos o título da janela
		this.setImage("images/editConsultant.png");
		this.setTitleLabel("Edit Consultant");
		this.setHelpTip("Updates the information of the consultant with the specify Id.");
		
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 30, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{64, 0, 70, 100, 0, 0, 0, 0, 0};
		
		lblConsultant = new JLabel("Consultant ID: ");
		GridBagConstraints gbc_lblConsultant = new GridBagConstraints();
		gbc_lblConsultant.anchor = GridBagConstraints.EAST;
		gbc_lblConsultant.insets = new Insets(0, 0, 5, 5);
		gbc_lblConsultant.gridx = 2;
		gbc_lblConsultant.gridy = 3;
		getMainDialogPanel().add(lblConsultant, gbc_lblConsultant);
		
		consultantId = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		getMainDialogPanel().add(consultantId, gbc_textField);
		consultantId.setColumns(10);

		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 5;
		getMainDialogPanel().add(lblName, gbc_lblName);
	
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 4;
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
	
		
		JLabel lblCoin = new JLabel("€");
		GridBagConstraints gbc_lblCoin = new GridBagConstraints();
		gbc_lblCoin.anchor = GridBagConstraints.WEST;
		gbc_lblCoin.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoin.gridx = 4;
		gbc_lblCoin.gridy = 6;
		getMainDialogPanel().add(lblCoin, gbc_lblCoin);	
		
		JTextField[] textFields = new JTextField[3];
		textFields[0] = consultantId;
		textFields[1] = nameField; 
		textFields[2] = priceHour;
		
		this.getSaveButton().addActionListener(new SetConsultantAL(textFields));
		
		pack();
		this.setVisible(true);
	}
}
