package swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class PatchConsultant extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel patchConsultantPanel = new JPanel();
	private JTextField nameField;
	private JTextField priceHour;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PatchConsultant dialog = new PatchConsultant();
			
			dialog.setTitle("Patch Consultant");    //definimos o título da janela
			dialog.setImage("images/edit.jpg");
			dialog.setTitleLabel("Patch Consultant");
			dialog.setHelpTip("Updates the information of the consultant with the specify Id.");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public PatchConsultant() 
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
