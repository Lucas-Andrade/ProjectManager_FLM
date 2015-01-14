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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PostProject extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel postProjectPanel = new JPanel();
	private JTextField LatitudeField;
	private JTextField longitudeField;
	private JTextField nameField;
	private JTextField priceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PostProject dialog = new PostProject();
			//definimos o título da janel
			dialog.setTitle("Post Project");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PostProject() {
		//Definição da Caixa de Diálogo
		setBounds(100, 100, 636, 387);
		getContentPane().setLayout(new BorderLayout());
		postProjectPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(postProjectPanel, BorderLayout.CENTER);
		
		// Definição do Painel interno
		GridBagLayout gbl_postProjectPanel = new GridBagLayout();  //Layout Manager
		gbl_postProjectPanel.columnWidths = new int[]{20, 0, 100, 100, 0, 60, 100, 0, 0};
		gbl_postProjectPanel.rowHeights = new int[]{0, 0, 30, 0, 0, 0, 30, 0, 0, 0, 0};
		gbl_postProjectPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_postProjectPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		postProjectPanel.setLayout(gbl_postProjectPanel);
		
								
		// Título do Comando : Post Project
		{
			JLabel lblPostConsultant = new JLabel("Post Project");
			lblPostConsultant.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
			GridBagConstraints gbc_lblPostConsultant = new GridBagConstraints();
			gbc_lblPostConsultant.gridwidth = 5;
			gbc_lblPostConsultant.insets = new Insets(0, 0, 5, 5);
			gbc_lblPostConsultant.gridx = 2;
			gbc_lblPostConsultant.gridy = 0;
			postProjectPanel.add(lblPostConsultant, gbc_lblPostConsultant);
		}
	
		{	//Inserir imagem : Project -> Path e localização no Painel
			JLabel projectLabel = new JLabel("");
			projectLabel.setIcon(PatchProject.class.getClassLoader("resources/images/project.png"));
			GridBagConstraints gbc_lblUser = new GridBagConstraints();
			gbc_lblUser.gridheight = 3;
			gbc_lblUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblUser.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblUser.gridx = 1;
			gbc_lblUser.gridy = 4;
			postProjectPanel.add(userLabel, gbc_lblUser);
		}
		
		{
			JLabel lblLocation = new JLabel("Location:");
			GridBagConstraints gbc_lblLocation = new GridBagConstraints();
			gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
			gbc_lblLocation.gridx = 2;
			gbc_lblLocation.gridy = 3;
			postProjectPanel.add(lblLocation, gbc_lblLocation);
		}
				
		
		// Informação sobre o user que está a usar o programa
		{
			JLabel lblWellcome = new JLabel("Bem-vindo");
			GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
			gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
			gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
			gbc_lblWellcome.gridx = 6;
			gbc_lblWellcome.gridy = 1;
			postProjectPanel.add(lblWellcome, gbc_lblWellcome);
		}
		{
			JLabel lblRegistryuser = new JLabel("RegistryUser");
			GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
			gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 0);
			gbc_lblRegistryuser.gridx = 7;
			gbc_lblRegistryuser.gridy = 1;
			postProjectPanel.add(lblRegistryuser, gbc_lblRegistryuser);
		}
		
		    // Labels e campos a ser preenchidos
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 2;
			gbc_lblName.gridy = 5;
			postProjectPanel.add(lblName, gbc_lblName);
		}
		{
			nameField = new JTextField();
			nameField.setColumns(10);
			GridBagConstraints gbc_nameField = new GridBagConstraints();
			gbc_nameField.insets = new Insets(0, 0, 5, 5);
			gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameField.gridx = 3;
			gbc_nameField.gridy = 5;
			postProjectPanel.add(nameField, gbc_nameField);
		}
		
		{
			JLabel lblPrice = new JLabel("Price:");
			GridBagConstraints gbc_lblPrice = new GridBagConstraints();
			gbc_lblPrice.anchor = GridBagConstraints.EAST;
			gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrice.gridx = 5;
			gbc_lblPrice.gridy = 5;
			postProjectPanel.add(lblPrice, gbc_lblPrice);
			lblPrice.setToolTipText("Euros");

		}
		{
			priceField = new JTextField();
			priceField.setColumns(10);
			GridBagConstraints gbc_priceField = new GridBagConstraints();
			gbc_priceField.insets = new Insets(0, 0, 5, 5);
			gbc_priceField.fill = GridBagConstraints.HORIZONTAL;
			gbc_priceField.gridx = 6;
			gbc_priceField.gridy = 5;
			postProjectPanel.add(priceField, gbc_priceField);
		}
		
		{
			JLabel lblLatitude = new JLabel("Latitude:");
			GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
			gbc_lblLatitude.anchor = GridBagConstraints.EAST;
			gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
			gbc_lblLatitude.gridx = 2;
			gbc_lblLatitude.gridy = 7;
			postProjectPanel.add(lblLatitude, gbc_lblLatitude);
		}
		
		{
			LatitudeField = new JTextField();
			GridBagConstraints gbc_latitudeField = new GridBagConstraints();
			gbc_latitudeField.fill = GridBagConstraints.HORIZONTAL;
			gbc_latitudeField.insets = new Insets(0, 0, 5, 5);
			gbc_latitudeField.gridx = 3;
			gbc_latitudeField.gridy = 7;
			postProjectPanel.add(LatitudeField, gbc_latitudeField);
			LatitudeField.setColumns(10);
		}
		{
			JLabel lblLongitude = new JLabel("Longitude:");
			GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
			gbc_lblLongitude.anchor = GridBagConstraints.EAST;
			gbc_lblLongitude.insets = new Insets(0, 0, 5, 5);
			gbc_lblLongitude.gridx = 2;
			gbc_lblLongitude.gridy = 8;
			postProjectPanel.add(lblLongitude, gbc_lblLongitude);
		}
		{
			longitudeField = new JTextField();
			GridBagConstraints gbc_longitudeField = new GridBagConstraints();
			gbc_longitudeField.fill = GridBagConstraints.HORIZONTAL;
			gbc_longitudeField.insets = new Insets(0, 0, 5, 5);
			gbc_longitudeField.gridx = 3;
			gbc_longitudeField.gridy = 8;
			postProjectPanel.add(longitudeField, gbc_longitudeField);
			longitudeField.setColumns(10);
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
