package app.framesAndPanels;

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

public class PatchConsultantFrame extends JDialog {

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
			PatchConsultantFrame dialog = new PatchConsultantFrame();
			//definimos o título da janela
			dialog.setTitle("Patch Consultant");
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
	public PatchConsultantFrame() 
	{
		//Definição da Caixa de Diálogo
		setBounds(100, 100, 636, 387);
		getContentPane().setLayout(new BorderLayout());
		patchConsultantPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(patchConsultantPanel, BorderLayout.CENTER);
		
		// Definição do Painel interno
		GridBagLayout gbl_patchConsultantPanel = new GridBagLayout();  //Layout Manager
		gbl_patchConsultantPanel.columnWidths = new int[]{20, 0, 60, 100, 0, 0, 50, 0, 50, 0};
		gbl_patchConsultantPanel.rowHeights = new int[]{0, 0, 50, 0, 0, 0, 0, 0, 0, 0};
		gbl_patchConsultantPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_patchConsultantPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		patchConsultantPanel.setLayout(gbl_patchConsultantPanel);
		
		
     //Inserir imagem : consultant -> Path e localização no Painel
		{
			
			JLabel consultantLabel = new JLabel("");
			consultantLabel.setIcon(new ImageIcon(PatchConsultantFrame.class.getClassLoader().getResource("images/user.jpg")));
			GridBagConstraints gbc_lblConsultant = new GridBagConstraints();
			gbc_lblConsultant.gridheight = 2;
			gbc_lblConsultant.insets = new Insets(0, 0, 5, 5);
			gbc_lblConsultant.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblConsultant.gridx = 1;
			gbc_lblConsultant.gridy = 5;
			patchConsultantPanel.add(consultantLabel, gbc_lblConsultant);
		}
		
		// Informação sobre o user que está a usar o programa
		{
			JLabel lblWellcome = new JLabel("Bem-vindo");
			GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
			gbc_lblWellcome.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE;
			gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
			gbc_lblWellcome.gridx = 7;
			gbc_lblWellcome.gridy = 1;
			patchConsultantPanel.add(lblWellcome, gbc_lblWellcome);
		}
		{
			JLabel lblRegistryuser = new JLabel("RegistryUser");
			GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
			gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 0);
			gbc_lblRegistryuser.gridx = 8;
			gbc_lblRegistryuser.gridy = 1;
			patchConsultantPanel.add(lblRegistryuser, gbc_lblRegistryuser);
		}
		{
			JLabel lblConsultantId = new JLabel("Consultant ID:");
			GridBagConstraints gbc_lblConsultantId = new GridBagConstraints();
			gbc_lblConsultantId.anchor = GridBagConstraints.EAST;
			gbc_lblConsultantId.insets = new Insets(0, 0, 5, 5);
			gbc_lblConsultantId.gridx = 2;
			gbc_lblConsultantId.gridy = 3;
			patchConsultantPanel.add(lblConsultantId, gbc_lblConsultantId);
		}
		{
			JComboBox consultantcomboBox = new JComboBox();
			GridBagConstraints gbc_consultantcomboBox = new GridBagConstraints();
			gbc_consultantcomboBox.insets = new Insets(0, 0, 5, 5);
			gbc_consultantcomboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_consultantcomboBox.gridx = 3;
			gbc_consultantcomboBox.gridy = 3;
			patchConsultantPanel.add(consultantcomboBox, gbc_consultantcomboBox);
		}

		
		// Título do Comando : Path Consultant
		{
			JLabel lblPostConsultant = new JLabel("Patch Consultant");
			lblPostConsultant.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
			GridBagConstraints gbc_lblPostConsultant = new GridBagConstraints();
			gbc_lblPostConsultant.gridwidth = 9;
			gbc_lblPostConsultant.insets = new Insets(0, 0, 5, 5);
			gbc_lblPostConsultant.gridx = 0;
			gbc_lblPostConsultant.gridy = 0;
			patchConsultantPanel.add(lblPostConsultant, gbc_lblPostConsultant);
		}
		
		
		    // Labels e campos a ser preenchidos
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 2;
			gbc_lblName.gridy = 5;
			patchConsultantPanel.add(lblName, gbc_lblName);
		}
		{
			nameField = new JTextField();
			GridBagConstraints gbc_nameField = new GridBagConstraints();
			gbc_nameField.gridwidth = 3;
			gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameField.insets = new Insets(0, 0, 5, 5);
			gbc_nameField.anchor = GridBagConstraints.SOUTH;
			gbc_nameField.gridx = 3;
			gbc_nameField.gridy = 5;
			patchConsultantPanel.add(nameField, gbc_nameField);
			nameField.setColumns(10);
		}
		
		{
			JLabel lblPriceHour = new JLabel("Price/Hour:");
			lblPriceHour.setToolTipText("Euros");
			GridBagConstraints gbc_lblPriceHour = new GridBagConstraints();
			gbc_lblPriceHour.anchor = GridBagConstraints.EAST;
			gbc_lblPriceHour.insets = new Insets(0, 0, 5, 5);
			gbc_lblPriceHour.gridx = 2;
			gbc_lblPriceHour.gridy = 6;
			patchConsultantPanel.add(lblPriceHour, gbc_lblPriceHour);
			lblPriceHour.setToolTipText("Euros");

		}
		{
			priceHour = new JTextField();
			GridBagConstraints gbc_priceHourField = new GridBagConstraints();
			gbc_priceHourField.fill = GridBagConstraints.HORIZONTAL;
			gbc_priceHourField.insets = new Insets(0, 0, 5, 5);
			gbc_priceHourField.gridx = 3;
			gbc_priceHourField.gridy = 6;
			patchConsultantPanel.add(priceHour, gbc_priceHourField);
			priceHour.setColumns(10);
			priceHour.setToolTipText("Euros");
		}
		
		{
			JLabel lblCoin = new JLabel("Euros");
			GridBagConstraints gbc_lblCoin = new GridBagConstraints();
			gbc_lblCoin.anchor = GridBagConstraints.WEST;
			gbc_lblCoin.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoin.gridx = 4;
			gbc_lblCoin.gridy = 6;
			patchConsultantPanel.add(lblCoin, gbc_lblCoin);

		}
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
