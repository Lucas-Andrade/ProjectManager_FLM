package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import app.repositoryHolders.InMemoryRepositoryHolder;
import app.repositoryHolders.RepositoryHolder;
import app.result.CommandResult;
import app.result.PostProjectResult;


public class PatchUserFrame extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField passwordField;
	private JTextField newPasswordField;
	private JTextField validateNewPassField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PatchUserFrame dialog = new PatchUserFrame(new PostProjectResult(new JSplitPane(), new InMemoryRepositoryHolder()));
			//definimos o título da janel
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
	public PatchUserFrame(CommandResult result) {
		super(result);
		
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 200, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		
		this.setTitle("Edit User");
		this.setImage("images/Edit_user.jpg");
		this.setTitleLabel("Edit User");
		this.setHelpTip("Updates the password of the user identified by the specify username.");
		
		JLabel lblName = new JLabel("Username:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 3;
		gbc_lblName.gridy = 3;
		getMainDialogPanel().add(lblName, gbc_lblName);
	
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.anchor = GridBagConstraints.SOUTH;
		gbc_nameField.gridx = 4;
		gbc_nameField.gridy = 3;
		getMainDialogPanel().add(nameField, gbc_nameField);
		nameField.setColumns(10);
	
		
		JLabel lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setToolTipText("Minimum 4 characters");
		GridBagConstraints gbc_lblOldPassword = new GridBagConstraints();
		gbc_lblOldPassword.anchor = GridBagConstraints.EAST;
		gbc_lblOldPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblOldPassword.gridx = 3;
		gbc_lblOldPassword.gridy = 4;
		getMainDialogPanel().add(lblOldPassword, gbc_lblOldPassword);
		lblOldPassword.setToolTipText("Minimum 4 character.");

	
		passwordField = new JTextField();
		GridBagConstraints gbc_oldPasswordField = new GridBagConstraints();
		gbc_oldPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_oldPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_oldPasswordField.gridx = 4;
		gbc_oldPasswordField.gridy = 4;
		getMainDialogPanel().add(passwordField, gbc_oldPasswordField);
		passwordField.setColumns(10);
		passwordField.setToolTipText("Minimum 4 character.");
	
		
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setToolTipText("Minimum 4 characters");
		GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
		gbc_lblNewPassword.anchor = GridBagConstraints.EAST;
		gbc_lblNewPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPassword.gridx = 3;
		gbc_lblNewPassword.gridy = 5;
		getMainDialogPanel().add(lblNewPassword, gbc_lblNewPassword);
	
		
		newPasswordField = new JTextField();
		GridBagConstraints gbc_newPasswordField = new GridBagConstraints();
		gbc_newPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_newPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_newPasswordField.gridx = 4;
		gbc_newPasswordField.gridy = 5;
		getMainDialogPanel().add(newPasswordField, gbc_newPasswordField);
		newPasswordField.setColumns(10);
	
		
		JLabel lblValidateNewPass = new JLabel("Confirm New Password:");
		lblValidateNewPass.setToolTipText("Minimum 4 characters");
		GridBagConstraints gbc_lblValidateNewPass = new GridBagConstraints();
		gbc_lblValidateNewPass.anchor = GridBagConstraints.EAST;
		gbc_lblValidateNewPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidateNewPass.gridx = 3;
		gbc_lblValidateNewPass.gridy = 6;
		getMainDialogPanel().add(lblValidateNewPass, gbc_lblValidateNewPass);


		validateNewPassField = new JTextField();
		GridBagConstraints gbc_validateNewPassField = new GridBagConstraints();
		gbc_validateNewPassField.fill = GridBagConstraints.HORIZONTAL;
		gbc_validateNewPassField.insets = new Insets(0, 0, 5, 5);
		gbc_validateNewPassField.gridx = 4;
		gbc_validateNewPassField.gridy = 6;
		getMainDialogPanel().add(validateNewPassField, gbc_validateNewPassField);
	}

}
