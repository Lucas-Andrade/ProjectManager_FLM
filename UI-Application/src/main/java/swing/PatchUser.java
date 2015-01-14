package swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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

public class PatchUser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel patchUserPanel = new JPanel();
	private JTextField nameField;
	private JTextField passwordField;
	private JTextField newPasswordField;
	private JTextField validateNewPassField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PatchUser dialog = new PatchUser();
			//definimos o título da janel
			dialog.setTitle("Patch User");
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
	public PatchUser() 
	{
		//Definição da Caixa de Diálogo
		setBounds(100, 100, 636, 387);
		getContentPane().setLayout(new BorderLayout());
		patchUserPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(patchUserPanel, BorderLayout.CENTER);
		
		// Definição do Painel interno
		GridBagLayout gbl_patchUserPanel = new GridBagLayout();  //Layout Manager
		gbl_patchUserPanel.columnWidths = new int[]{20, 34, 10, 100, 200, 0, 0, 0, 0};
		gbl_patchUserPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_patchUserPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_patchUserPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		patchUserPanel.setLayout(gbl_patchUserPanel);
		
		
		     //Inserir imagem : user -> Path e localização no Painel
		{ 
			JLabel userLabel = new JLabel("");
			userLabel.setIcon(GetUserPanel.class.getClassLoader("resources/images/user.jpg"));
			userLabel.setIcon(new ImageIcon(user));
			GridBagConstraints gbc_lblUser = new GridBagConstraints();
			gbc_lblUser.gridheight = 2;
			gbc_lblUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblUser.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblUser.gridx = 1;
			gbc_lblUser.gridy = 3;
			patchUserPanel.add(userLabel, gbc_lblUser);

		}
		
		// Informação sobre o user que está a usar o programa
		{
			JLabel lblWellcome = new JLabel("Bem-vindo");
			GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
			gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE;
			gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
			gbc_lblWellcome.gridx = 6;
			gbc_lblWellcome.gridy = 1;
			patchUserPanel.add(lblWellcome, gbc_lblWellcome);
		}
		{
			JLabel lblRegistryuser = new JLabel("RegistryUser");
			GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
			gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 0);
			gbc_lblRegistryuser.gridx = 7;
			gbc_lblRegistryuser.gridy = 1;
			patchUserPanel.add(lblRegistryuser, gbc_lblRegistryuser);
		}
		
		// Título do Comando : Patch User
		{
			JLabel lblPatchUser = new JLabel("Patch User");
			lblPatchUser.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
			GridBagConstraints gbc_lblPatchUser = new GridBagConstraints();
			gbc_lblPatchUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblPatchUser.gridx = 3;
			gbc_lblPatchUser.gridy = 0;
			postUserPanel.add(lblPatchUser, gbc_lblPatchUser);
		}
		
		    // Labels e campos a ser preenchidos
		{
			JLabel lblName = new JLabel("Username:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 3;
			gbc_lblName.gridy = 3;
			patchUserPanel.add(lblName, gbc_lblName);
		}
		{
			nameField = new JTextField();
			GridBagConstraints gbc_nameField = new GridBagConstraints();
			gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameField.insets = new Insets(0, 0, 5, 5);
			gbc_nameField.anchor = GridBagConstraints.SOUTH;
			gbc_nameField.gridx = 4;
			gbc_nameField.gridy = 3;
			patchUserPanel.add(nameField, gbc_nameField);
			nameField.setColumns(10);
		}
		
		{
			JLabel lblOldPassword = new JLabel("Old Password:");
			lblOldPassword.setToolTipText("Minimum 4 characters");
			GridBagConstraints gbc_lblOldPassword = new GridBagConstraints();
			gbc_lblOldPassword.anchor = GridBagConstraints.EAST;
			gbc_lblOldPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblOldPassword.gridx = 3;
			gbc_lblOldPassword.gridy = 4;
			patchUserPanel.add(lblOldPassword, gbc_lblOldPassword);
			lblOldPassword.setToolTipText("Minimum 4 character.");

		}
		{
			passwordField = new JTextField();
			GridBagConstraints gbc_oldPasswordField = new GridBagConstraints();
			gbc_oldPasswordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_oldPasswordField.insets = new Insets(0, 0, 5, 5);
			gbc_oldPasswordField.gridx = 4;
			gbc_oldPasswordField.gridy = 4;
			patchUserPanel.add(passwordField, gbc_oldPasswordField);
			passwordField.setColumns(10);
			passwordField.setToolTipText("Minimum 4 character.");
		}
	
		{
			JLabel lblNewPassword = new JLabel("New Password:");
			lblNewPassword.setToolTipText("Minimum 4 characters");
			GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
			gbc_lblNewPassword.anchor = GridBagConstraints.EAST;
			gbc_lblNewPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewPassword.gridx = 3;
			gbc_lblNewPassword.gridy = 5;
			patchUserPanel.add(lblNewPassword, gbc_lblNewPassword);
		}
		{
			newPasswordField = new JTextField();
			GridBagConstraints gbc_newPasswordField = new GridBagConstraints();
			gbc_newPasswordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_newPasswordField.insets = new Insets(0, 0, 5, 5);
			gbc_newPasswordField.gridx = 4;
			gbc_newPasswordField.gridy = 5;
			patchUserPanel.add(newPasswordField, gbc_newPasswordField);
			newPasswordField.setColumns(10);
		}
		
		{
			JLabel lblValidateNewPass = new JLabel("Confirm New Password:");
			lblValidateNewPass.setToolTipText("Minimum 4 characters");
			GridBagConstraints gbc_lblValidateNewPass = new GridBagConstraints();
			gbc_lblValidateNewPass.anchor = GridBagConstraints.EAST;
			gbc_lblValidateNewPass.insets = new Insets(0, 0, 5, 5);
			gbc_lblValidateNewPass.gridx = 3;
			gbc_lblValidateNewPass.gridy = 6;
			patchUserPanel.add(lblValidateNewPass, gbc_lblValidateNewPass);
		}
		
		{
			validateNewPassField = new JTextField();
			GridBagConstraints gbc_validateNewPassField = new GridBagConstraints();
			gbc_validateNewPassField.fill = GridBagConstraints.HORIZONTAL;
			gbc_validateNewPassField.insets = new Insets(0, 0, 5, 5);
			gbc_validateNewPassField.gridx = 4;
			gbc_validateNewPassField.gridy = 6;
			patchUserPanel.add(validateNewPassField, gbc_validateNewPassField);
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
