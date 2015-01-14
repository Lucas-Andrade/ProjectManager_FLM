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

//import com.jgoodies.forms.factories.DefaultComponentFactory;

public class PostUserPanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel postUserPanel = new JPanel();
	private JTextField nameField;
	private JTextField passwordField;
	private JTextField emailField;
	private JTextField fullNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PostUserPanel dialog = new PostUserPanel();
			//definimos o título da janel
			dialog.setTitle("Post User");
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
	public PostUserPanel() 
	{
		//Definição da Caixa de Diálogo
		setBounds(100, 100, 636, 387);
		getContentPane().setLayout(new BorderLayout());
		postUserPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(postUserPanel, BorderLayout.CENTER);
		
		// Definição do Painel interno
		GridBagLayout gbl_postUserPanel = new GridBagLayout();  //Layout Manager
		gbl_postUserPanel.columnWidths = new int[]{20, 0, 100, 12, 0, 0, 0};
		gbl_postUserPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_postUserPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_postUserPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		postUserPanel.setLayout(gbl_postUserPanel);
		
		
		     //Inserir imagem : user -> Path e localização no Painel
		{ 
			JLabel userLabel = new JLabel("");
			userLabel.setIcon(new ImageIcon(PostUserPanel.class.getClassLoader().getResource("images/user.jpg")));
			GridBagConstraints gbc_lblUser = new GridBagConstraints();
			gbc_lblUser.gridheight = 2;
			gbc_lblUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblUser.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblUser.gridx = 1;
			gbc_lblUser.gridy = 3;
			postUserPanel.add(userLabel, gbc_lblUser);	
		}
		
		// Informação sobre o user que está a usar o programa
		{
			JLabel lblWellcome = new JLabel("Bem-vindo");
			GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
			gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE;
			gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
			gbc_lblWellcome.gridx = 4;
			gbc_lblWellcome.gridy = 1;
			postUserPanel.add(lblWellcome, gbc_lblWellcome);
		}
		{
			JLabel lblRegistryuser = new JLabel("RegistryUser");
			GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
			gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 0);
			gbc_lblRegistryuser.gridx = 5;
			gbc_lblRegistryuser.gridy = 1;
			postUserPanel.add(lblRegistryuser, gbc_lblRegistryuser);
		}
		
		// Título do Comando : Post User
		{
 			JLabel lblPostUser = new JLabel("Post Subroject");
			lblPostUser.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
			GridBagConstraints gbc_lblPostUser = new GridBagConstraints();
			gbc_lblPostUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblPostUser.gridx = 3;
			gbc_lblPostUser.gridy = 0;
			postUserPanel.add(lblPostUser, gbc_lblPostUser);
		}
		
		    // Labels e campos a ser preenchidos
		{
			JLabel lblName = new JLabel("Username:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 2;
			gbc_lblName.gridy = 3;
			postUserPanel.add(lblName, gbc_lblName);
		}
		{
			nameField = new JTextField();
			GridBagConstraints gbc_nameField = new GridBagConstraints();
			gbc_nameField.insets = new Insets(0, 0, 5, 5);
			gbc_nameField.anchor = GridBagConstraints.SOUTHWEST;
			gbc_nameField.gridx = 3;
			gbc_nameField.gridy = 3;
			postUserPanel.add(nameField, gbc_nameField);
			nameField.setColumns(10);
		}
		
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setToolTipText("Minimum 4 characters");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.EAST;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 2;
			gbc_lblPassword.gridy = 4;
			postUserPanel.add(lblPassword, gbc_lblPassword);
			lblPassword.setToolTipText("Minimum 4 character.");

		}
		{
			passwordField = new JTextField();
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.anchor = GridBagConstraints.WEST;
			gbc_passwordField.insets = new Insets(0, 0, 5, 5);
			gbc_passwordField.gridx = 3;
			gbc_passwordField.gridy = 4;
			postUserPanel.add(passwordField, gbc_passwordField);
			passwordField.setColumns(10);
			passwordField.setToolTipText("Minimum 4 character.");
		}
	
		{
			JLabel lblEmail = new JLabel("Email:");
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.anchor = GridBagConstraints.EAST;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.gridx = 2;
			gbc_lblEmail.gridy = 5;
			postUserPanel.add(lblEmail, gbc_lblEmail);
		}
		{
			emailField = new JTextField();
			GridBagConstraints gbc_emailField = new GridBagConstraints();
			gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
			gbc_emailField.insets = new Insets(0, 0, 5, 5);
			gbc_emailField.gridx = 3;
			gbc_emailField.gridy = 5;
			postUserPanel.add(emailField, gbc_emailField);
			emailField.setColumns(10);
		}
		{
			JLabel lblFullname = new JLabel("Fullname:");
			GridBagConstraints gbc_lblFullname = new GridBagConstraints();
			gbc_lblFullname.anchor = GridBagConstraints.EAST;
			gbc_lblFullname.insets = new Insets(0, 0, 5, 5);
			gbc_lblFullname.gridx = 2;
			gbc_lblFullname.gridy = 6;
			postUserPanel.add(lblFullname, gbc_lblFullname);
		}
		{
			fullNameField = new JTextField();
			GridBagConstraints gbc_fullNameField = new GridBagConstraints();
			gbc_fullNameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_fullNameField.insets = new Insets(0, 0, 5, 5);
			gbc_fullNameField.gridx = 3;
			gbc_fullNameField.gridy = 6;
			postUserPanel.add(fullNameField, gbc_fullNameField);
			fullNameField.setColumns(10);
		}
		{
			JLabel lblOptionalLabel = new JLabel("(Opcional)");
			GridBagConstraints gbc_lblOptionalLabel = new GridBagConstraints();
			gbc_lblOptionalLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblOptionalLabel.gridx = 4;
			gbc_lblOptionalLabel.gridy = 6;
			postUserPanel.add(lblOptionalLabel, gbc_lblOptionalLabel);
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
