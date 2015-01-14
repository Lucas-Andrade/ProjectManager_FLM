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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

public class PostConsultantFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel postConsultantPanel = new JPanel();
	private JTextField nameField;
	private JTextField priceHour;
	private JTextField bonus;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PostConsultantFrame dialog = new PostConsultantFrame();
			//definimos o título da janela
			dialog.setTitle("Post Consultant");
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
	public PostConsultantFrame() 
	{
		//Definição da Caixa de Diálogo
		setBounds(100, 100, 636, 387);
		getContentPane().setLayout(new BorderLayout());
		postConsultantPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(postConsultantPanel, BorderLayout.CENTER);
		
		// Definição do Painel interno
		GridBagLayout gbl_postConsultantPanel = new GridBagLayout();  //Layout Manager
		gbl_postConsultantPanel.columnWidths = new int[]{20, 0, 50, 100, 100, 0, 80, 0, 50, 0};
		gbl_postConsultantPanel.rowHeights = new int[]{0, 0, 50, 0, 0, 0, 0, 0};
		gbl_postConsultantPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_postConsultantPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		postConsultantPanel.setLayout(gbl_postConsultantPanel);
		
	    //Inserir imagem : consultant -> Path e localização no Painel
		{ 	
			JLabel consultantLabel = new JLabel("");
			consultantLabel.setIcon(new ImageIcon(GetUserPanel.class.getClassLoader().getResource("images/user.jpg")));
			GridBagConstraints gbc_lblConsultant = new GridBagConstraints();
			gbc_lblConsultant.gridheight = 2;
			gbc_lblConsultant.insets = new Insets(0, 0, 5, 5);
			gbc_lblConsultant.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblConsultant.gridx = 1;
			gbc_lblConsultant.gridy = 3;
			postConsultantPanel.add(consultantLabel, gbc_lblConsultant);
	
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
			postConsultantPanel.add(lblWellcome, gbc_lblWellcome);
		}
		
		{
			JLabel lblRegistryuser = new JLabel("RegistryUser");
			GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
			gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 0);
			gbc_lblRegistryuser.gridx = 8;
			gbc_lblRegistryuser.gridy = 1;
			postConsultantPanel.add(lblRegistryuser, gbc_lblRegistryuser);
		}
			
		
		// Título do Comando : Post Consultant
		{
			JLabel lblPostConsultant = new JLabel("Post Consultant");
			lblPostConsultant.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
			GridBagConstraints gbc_lblPostConsultant = new GridBagConstraints();
			gbc_lblPostConsultant.gridwidth = 9;
			gbc_lblPostConsultant.insets = new Insets(0, 0, 5, 5);
			gbc_lblPostConsultant.gridx = 0;
			gbc_lblPostConsultant.gridy = 0;
			postConsultantPanel.add(lblPostConsultant, gbc_lblPostConsultant);
		}
		
		
		    // Labels e campos a ser preenchidos
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 2;
			gbc_lblName.gridy = 3;
			postConsultantPanel.add(lblName, gbc_lblName);
		}
		{
			nameField = new JTextField();
			GridBagConstraints gbc_nameField = new GridBagConstraints();
			gbc_nameField.gridwidth = 2;
			gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameField.insets = new Insets(0, 0, 5, 5);
			gbc_nameField.anchor = GridBagConstraints.SOUTH;
			gbc_nameField.gridx = 3;
			gbc_nameField.gridy = 3;
			postConsultantPanel.add(nameField, gbc_nameField);
			nameField.setColumns(10);
		}
		
		{
			JLabel lblPriceHour = new JLabel("Price/Hour:");
			lblPriceHour.setToolTipText("Euros");
			GridBagConstraints gbc_lblPriceHour = new GridBagConstraints();
			gbc_lblPriceHour.anchor = GridBagConstraints.EAST;
			gbc_lblPriceHour.insets = new Insets(0, 0, 5, 5);
			gbc_lblPriceHour.gridx = 6;
			gbc_lblPriceHour.gridy = 3;
			postConsultantPanel.add(lblPriceHour, gbc_lblPriceHour);
			lblPriceHour.setToolTipText("Euros");

		}
		{
			priceHour = new JTextField();
			GridBagConstraints gbc_priceHourField = new GridBagConstraints();
			gbc_priceHourField.fill = GridBagConstraints.HORIZONTAL;
			gbc_priceHourField.insets = new Insets(0, 0, 5, 5);
			gbc_priceHourField.gridx = 7;
			gbc_priceHourField.gridy = 3;
			postConsultantPanel.add(priceHour, gbc_priceHourField);
			priceHour.setColumns(10);
			priceHour.setToolTipText("Euros");
		}
		
		{
			JLabel lblCoin = new JLabel("Euros");
			GridBagConstraints gbc_lblCoin = new GridBagConstraints();
			gbc_lblCoin.anchor = GridBagConstraints.WEST;
			gbc_lblCoin.insets = new Insets(0, 0, 5, 0);
			gbc_lblCoin.gridx = 8;
			gbc_lblCoin.gridy = 3;
			postConsultantPanel.add(lblCoin, gbc_lblCoin);

		}
		{
			JRadioButton rdbtnManager = new JRadioButton("Manager");
			GridBagConstraints gbc_rdbtnManager = new GridBagConstraints();
			gbc_rdbtnManager.anchor = GridBagConstraints.WEST;
			gbc_rdbtnManager.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnManager.gridx = 2;
			gbc_rdbtnManager.gridy = 4;
			postConsultantPanel.add(rdbtnManager, gbc_rdbtnManager);
		}
		
		
	
		{
			JLabel lblBonus = new JLabel("Bónus:");
			GridBagConstraints gbc_lblBonus = new GridBagConstraints();
			gbc_lblBonus.anchor = GridBagConstraints.EAST;
			gbc_lblBonus.insets = new Insets(0, 0, 5, 5);
			gbc_lblBonus.gridx = 2;
			gbc_lblBonus.gridy = 5;
			postConsultantPanel.add(lblBonus, gbc_lblBonus);
		}
		{
			bonus = new JTextField();
			GridBagConstraints gbc_bonusField = new GridBagConstraints();
			gbc_bonusField.fill = GridBagConstraints.HORIZONTAL;
			gbc_bonusField.insets = new Insets(0, 0, 5, 5);
			gbc_bonusField.gridx = 3;
			gbc_bonusField.gridy = 5;
			postConsultantPanel.add(bonus, gbc_bonusField);
			bonus.setColumns(10);
		}
		{
			JLabel label = new JLabel("Euros");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.WEST;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 4;
			gbc_label.gridy = 5;
			postConsultantPanel.add(label, gbc_label);
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
