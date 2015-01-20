package app.windows.commandWindowsAL.commandWindows;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.GridBagLayout;


public class PostConsultantFrame extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8060989086766838002L;
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
			dialog.setImage("images/newConsultant.png");
			dialog.setTitleLabel("Post Consultant");
			dialog.setHelpTip("Add a consultant to the Workers Repository");
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
	public PostConsultantFrame() {
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 30, 0, 0, 30, 15, 15, 0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 100, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		
		this.setTitle("New Consultant");
		this.setImage("images/newConsultant.png");
		this.setTitleLabel("New Consultant");
		this.setHelpTip("Add a new consultant to the Workers Repository");
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 3;
		getMainDialogPanel().add(lblName, gbc_lblName);
	
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 4;
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.anchor = GridBagConstraints.SOUTH;
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 3;
		getMainDialogPanel().add(nameField, gbc_nameField);
		nameField.setColumns(10);
					
						
						JLabel lblPriceHour = new JLabel("Price/Hour:");
						lblPriceHour.setToolTipText("Euros");
						GridBagConstraints gbc_lblPriceHour = new GridBagConstraints();
						gbc_lblPriceHour.anchor = GridBagConstraints.EAST;
						gbc_lblPriceHour.insets = new Insets(0, 0, 5, 5);
						gbc_lblPriceHour.gridx = 2;
						gbc_lblPriceHour.gridy = 4;
						getMainDialogPanel().add(lblPriceHour, gbc_lblPriceHour);
						lblPriceHour.setToolTipText("Euros");
					
						
							priceHour = new JTextField();
							GridBagConstraints gbc_priceHourField = new GridBagConstraints();
							gbc_priceHourField.fill = GridBagConstraints.HORIZONTAL;
							gbc_priceHourField.insets = new Insets(0, 0, 5, 5);
							gbc_priceHourField.gridx = 3;
							gbc_priceHourField.gridy = 4;
							getMainDialogPanel().add(priceHour, gbc_priceHourField);
							priceHour.setColumns(10);
							priceHour.setToolTipText("Euros");
					
						
						JLabel lblCoin = new JLabel("Euros");
						GridBagConstraints gbc_lblCoin = new GridBagConstraints();
						gbc_lblCoin.anchor = GridBagConstraints.WEST;
						gbc_lblCoin.insets = new Insets(0, 0, 5, 5);
						gbc_lblCoin.gridx = 4;
						gbc_lblCoin.gridy = 4;
						getMainDialogPanel().add(lblCoin, gbc_lblCoin);
			
				
					JRadioButton rdbtnManager = new JRadioButton("Manager");
					GridBagConstraints gbc_rdbtnManager = new GridBagConstraints();
					gbc_rdbtnManager.anchor = GridBagConstraints.WEST;
					gbc_rdbtnManager.insets = new Insets(0, 0, 5, 5);
					gbc_rdbtnManager.gridx = 2;
					gbc_rdbtnManager.gridy = 6;
					getMainDialogPanel().add(rdbtnManager, gbc_rdbtnManager);
		
			
			JLabel lblBonus = new JLabel("Bonus:");
			GridBagConstraints gbc_lblBonus = new GridBagConstraints();
			gbc_lblBonus.anchor = GridBagConstraints.EAST;
			gbc_lblBonus.insets = new Insets(0, 0, 5, 5);
			gbc_lblBonus.gridx = 2;
			gbc_lblBonus.gridy = 7;
			getMainDialogPanel().add(lblBonus, gbc_lblBonus);
			
				
				bonus = new JTextField();
				GridBagConstraints gbc_bonusField = new GridBagConstraints();
				gbc_bonusField.fill = GridBagConstraints.HORIZONTAL;
				gbc_bonusField.insets = new Insets(0, 0, 5, 5);
				gbc_bonusField.gridx = 3;
				gbc_bonusField.gridy = 7;
				getMainDialogPanel().add(bonus, gbc_bonusField);
				bonus.setColumns(10);
				
					
					JLabel label = new JLabel("Euros");
					GridBagConstraints gbc_label = new GridBagConstraints();
					gbc_label.anchor = GridBagConstraints.WEST;
					gbc_label.insets = new Insets(0, 0, 5, 5);
					gbc_label.gridx = 4;
					gbc_label.gridy = 7;
					getMainDialogPanel().add(label, gbc_label);
	}
}