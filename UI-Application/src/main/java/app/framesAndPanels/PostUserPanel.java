package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

//import com.jgoodies.forms.factories.DefaultComponentFactory;

public class PostUserPanel extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
			dialog.setImage("images/add_user.jpg");
			dialog.setTitleLabel("Post User");
			dialog.setHelpTip("Add a user to the User Repository");
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
		super();
		
		JLabel lblName = new JLabel("Username:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 3;
		getMainDialogPanel().add(lblName, gbc_lblName);


		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridwidth = 2;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.anchor = GridBagConstraints.SOUTH;
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 3;
		getMainDialogPanel().add(nameField, gbc_nameField);
		nameField.setColumns(10);


		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setToolTipText("Minimum 4 characters");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 4;
		getMainDialogPanel().add(lblPassword, gbc_lblPassword);
		lblPassword.setToolTipText("Minimum 4 character.");


		passwordField = new JTextField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 4;
		getMainDialogPanel().add(passwordField, gbc_passwordField);
		passwordField.setColumns(10);
		passwordField.setToolTipText("Minimum 4 character.");


		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 5;
		getMainDialogPanel().add(lblEmail, gbc_lblEmail);


		emailField = new JTextField();
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.gridwidth = 3;
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.gridx = 3;
		gbc_emailField.gridy = 5;
		getMainDialogPanel().add(emailField, gbc_emailField);
		emailField.setColumns(10);


		JLabel lblFullname = new JLabel("Fullname:");
		GridBagConstraints gbc_lblFullname = new GridBagConstraints();
		gbc_lblFullname.anchor = GridBagConstraints.EAST;
		gbc_lblFullname.insets = new Insets(0, 0, 5, 5);
		gbc_lblFullname.gridx = 2;
		gbc_lblFullname.gridy = 6;
		getMainDialogPanel().add(lblFullname, gbc_lblFullname);


		fullNameField = new JTextField();
		GridBagConstraints gbc_fullNameField = new GridBagConstraints();
		gbc_fullNameField.gridwidth = 3;
		gbc_fullNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_fullNameField.insets = new Insets(0, 0, 5, 5);
		gbc_fullNameField.gridx = 3;
		gbc_fullNameField.gridy = 6;
		getMainDialogPanel().add(fullNameField, gbc_fullNameField);
		fullNameField.setColumns(10);
		
		
				JLabel lblOptionalLabel = new JLabel("(Opcional)");
				GridBagConstraints gbc_lblOptionalLabel = new GridBagConstraints();
				gbc_lblOptionalLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblOptionalLabel.gridx = 6;
				gbc_lblOptionalLabel.gridy = 6;
				getMainDialogPanel().add(lblOptionalLabel, gbc_lblOptionalLabel);
	}
}
