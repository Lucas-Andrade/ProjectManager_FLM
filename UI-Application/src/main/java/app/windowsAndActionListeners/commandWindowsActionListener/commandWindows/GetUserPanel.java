package app.windowsAndActionListeners.commandWindowsActionListener.commandWindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import app.windows.commandWindowsAL.commandWindows.MainGetPanel;


public class GetUserPanel extends MainGetPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField userID;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GetUserPanel dialog = new GetUserPanel();
			//definimos o título da janela
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param getUserResult 
	 * @throws IOException 
	 */
	public GetUserPanel() 
	{
		this.setTitle("Get Users");    //definimos o título da janela
		this.setImage("images/user2-icon.png");
		this.setTitleLabel("Get Users");
		this.setHelpTip("Updates the information of the consultant with the specify Id.");
		
		setBounds(100, 100, 546, 386);
		GridBagLayout gridBagLayout = (GridBagLayout) getMainGetPanel().getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{30, 100, 0, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 20, 0, 0, 0, 0, 0, 0};
		
		//Opções de seleção
		JRadioButton allUsers = new JRadioButton("All User");
		GridBagConstraints gbc_AllUsers = new GridBagConstraints();
		gbc_AllUsers.anchor = GridBagConstraints.NORTHWEST;
		gbc_AllUsers.insets = new Insets(0, 0, 5, 5);
		gbc_AllUsers.gridx = 2;
		gbc_AllUsers.gridy = 3;
		getMainGetPanel().add(allUsers, gbc_AllUsers);
		allUsers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
			}
		});
		
		JRadioButton userId = new JRadioButton("User ID:");
		GridBagConstraints gbc_UserId = new GridBagConstraints();
		gbc_UserId.anchor = GridBagConstraints.NORTHWEST;
		gbc_UserId.insets = new Insets(0, 0, 5, 5);
		gbc_UserId.gridx = 2;
		gbc_UserId.gridy = 5;
		getMainGetPanel().add(userId, gbc_UserId);
		userId.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
			}
		});
		
		userID = new JTextField();
		GridBagConstraints gbc_userID = new GridBagConstraints();
		gbc_userID.anchor = GridBagConstraints.WEST;
		gbc_userID.insets = new Insets(0, 0, 5, 5);
		gbc_userID.fill = GridBagConstraints.HORIZONTAL;
		gbc_userID.gridx = 3;
		gbc_userID.gridy = 5;
		getMainGetPanel().add(userID, gbc_userID);
		userID.setColumns(10);
		
		
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(allUsers);
	    group.add(userId);
		
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridheight = 2;
		gbc_textField.gridwidth = 8;
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 7;
		getMainGetPanel().add(textField, gbc_textField);
		textField.setColumns(10);
	}
}