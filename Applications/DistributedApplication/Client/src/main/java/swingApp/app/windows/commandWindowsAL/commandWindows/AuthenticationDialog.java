package swingApp.app.windows.commandWindowsAL.commandWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import commandWindowsAL.commandWindows.MainDialogFrame;
import swingApp.app.authentication.Authentication;

/**
 * This {@code JDialog} allows the user to make the necessary authentication to
 * use all the {@code Command}s frames
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class AuthenticationDialog extends JDialog{

	private static final long serialVersionUID = 9190969392304934338L;
	private final JPanel authenticationPanel = new JPanel();
	private JTextField nameField;
	private JLabel mainImageLabel;
	private JPasswordField passwordField;


	public AuthenticationDialog(){
		setResizable(false);

		this.setTitle("Login");

		setBounds(100, 100, 300, 194);
		getContentPane().setLayout(new BorderLayout());
		authenticationPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(authenticationPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 75, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 34 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		authenticationPanel.setLayout(gbl_contentPanel);
		authenticationPanel.setBackground(new Color(176, 196, 222));

		mainImageLabel = new JLabel("");
		mainImageLabel.setIcon(new ImageIcon(MainDialogFrame.class
				.getClassLoader().getResource("images/Login.png")));
		mainImageLabel.setOpaque(false);
		GridBagConstraints gbc_mainImageLabel = new GridBagConstraints();
		gbc_mainImageLabel.anchor = GridBagConstraints.NORTH;
		gbc_mainImageLabel.gridheight = 4;
		gbc_mainImageLabel.insets = new Insets(0, 0, 0, 5);
		gbc_mainImageLabel.gridx = 0;
		gbc_mainImageLabel.gridy = 0;
		authenticationPanel.add(mainImageLabel, gbc_mainImageLabel);

		JLabel lblName = new JLabel("Username:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		authenticationPanel.add(lblName, gbc_lblName);

		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.anchor = GridBagConstraints.SOUTH;
		gbc_nameField.gridx = 2;
		gbc_nameField.gridy = 0;
		authenticationPanel.add(nameField, gbc_nameField);
		nameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setToolTipText("Minimum 4 characters");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 1;
		authenticationPanel.add(lblPassword, gbc_lblPassword);
		lblPassword.setToolTipText("Minimum 4 character.");

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 1;
		authenticationPanel.add(passwordField, gbc_passwordField);

		JTextField[] textField = new JTextField[2];
		textField[0] = nameField;
		textField[1] = passwordField;

		JButton loginButton = new JButton("Login");
		loginButton.setBackground(new Color(51, 204, 255));
		GridBagConstraints gbc_LoginButton = new GridBagConstraints();
		gbc_LoginButton.insets = new Insets(0, 0, 5, 0);
		gbc_LoginButton.anchor = GridBagConstraints.SOUTH;
		gbc_LoginButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_LoginButton.gridwidth = 4;
		gbc_LoginButton.gridx = 1;
		gbc_LoginButton.gridy = 2;
		authenticationPanel.add(loginButton, gbc_LoginButton);

		ActionListener login = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Authentication.authenticate(textField);
				dispose();
			}
		};
		loginButton.addActionListener(login);
		KeyListener loginEnterKey = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e)
			{
			}
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					login.actionPerformed(null);
				}
			}
			@Override
			public void keyReleased(KeyEvent e)
			{
			}
		};
		this.getRootPane().setDefaultButton( loginButton );
		loginButton.addKeyListener(loginEnterKey);
		pack();
	}
	
}
