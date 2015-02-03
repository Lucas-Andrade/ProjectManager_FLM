package swingApp.app.windows.commandWindowsAL.commandWindows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import swingApp.app.windows.commandWindowsAL.commandWindows.MainGetPanel;

/**
 * This {@code MainGetPanel} allows to insert the mandatory parameters to allows
 * to get all {@code User}'s or a specified {@code User} from the repository.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetUserPanel extends MainGetPanel {
	
	private static final long serialVersionUID = -457464140944368035L;
	private JTextField userID;

	/**
	 * Create the dialog.
	 * @param getUserResult 
	 * @throws IOException 
	 */
	public GetUserPanel() {
		
		this.setImage("images/getUser.png");
		this.setTitleLabel("Get Users");
		this.setHelpTip("Updates the information of the consultant with the specify Id.");
		
		setBounds(100, 100, 546, 386);
		GridBagLayout gridBagLayout = (GridBagLayout) getMainGetPanel().getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{30, 100, 0, 120};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 20, 0, 0, 0, 0, 0, 0};
		
		//Opções de seleção
		JRadioButton allUsers = new JRadioButton("All User");
		allUsers.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_AllUsers = new GridBagConstraints();
		gbc_AllUsers.anchor = GridBagConstraints.NORTHWEST;
		gbc_AllUsers.insets = new Insets(0, 0, 5, 5);
		gbc_AllUsers.gridx = 2;
		gbc_AllUsers.gridy = 3;
		getMainGetPanel().add(allUsers, gbc_AllUsers);
		allUsers.setSelected(true);
		
		JRadioButton userId = new JRadioButton("Username:");
		userId.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_UserId = new GridBagConstraints();
		gbc_UserId.anchor = GridBagConstraints.NORTHWEST;
		gbc_UserId.insets = new Insets(0, 0, 5, 5);
		gbc_UserId.gridx = 2;
		gbc_UserId.gridy = 5;
		getMainGetPanel().add(userId, gbc_UserId);
		
		userID = new JTextField();
		GridBagConstraints gbc_userID = new GridBagConstraints();
		gbc_userID.insets = new Insets(0, 0, 5, 5);
		gbc_userID.fill = GridBagConstraints.HORIZONTAL;
		gbc_userID.gridx = 3;
		gbc_userID.gridy = 5;
		getMainGetPanel().add(userID, gbc_userID);
		userID.setColumns(10);
		userID.setEnabled(false);
		
		userId.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				allUsers.setSelected(false);
				userId.setSelected(true);
				userID.setEnabled(true);
			}
		});
		
		allUsers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				allUsers.setSelected(true);
				userId.setSelected(false);
				userID.setEnabled(false);
			}
		});
		
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(allUsers);
	    group.add(userId);
	    
		results = new JPanel();
		GridBagConstraints gbc_results = new GridBagConstraints();
		gbc_results.gridheight = 2;
		gbc_results.gridwidth = 8;
		gbc_results.anchor = GridBagConstraints.WEST;
		gbc_results.insets = new Insets(0, 0, 5, 5);
		gbc_results.fill = GridBagConstraints.BOTH;
		gbc_results.gridx = 1;
		gbc_results.gridy = 7;
		getMainGetPanel().add(results, gbc_results);

		this.getGetButton().addActionListener(new swingApp.app.windows.commandWindowsAL.GetUserAL(userID, allUsers));
	}

	@Override
	public void resetAllFields() {
		userID.setText("");
	}
}
