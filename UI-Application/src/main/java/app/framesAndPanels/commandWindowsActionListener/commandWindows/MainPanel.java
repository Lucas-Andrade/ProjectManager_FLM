package app.framesAndPanels.commandWindowsActionListener.commandWindows;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel mainImageLabel;
	private JLabel titleLabel;
	private JLabel helpLabel;

	
	public void setImage(String imagePath){
		mainImageLabel.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource(imagePath)));
	}
	
	public void setTitleLabel(String title){
		titleLabel.setText(title);
	}
	
	public void setHelpTip(String help){
		helpLabel.setToolTipText(help);
	}
	
	
	/**
	 * Create the panel.
	 */
	public MainPanel() {

		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
				
		GridBagLayout gbl_patchConsultantPanel = new GridBagLayout();  // Definição do Painel interno
		gbl_patchConsultantPanel.columnWidths = new int[]{5, 100, 60, 100, 0, 0, 50, 0, 75, 0};
		gbl_patchConsultantPanel.rowHeights = new int[]{0, 0, 50, 0, 0, 0, 0, 0, 0, 0};
		gbl_patchConsultantPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_patchConsultantPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_patchConsultantPanel);
							

		mainImageLabel = new JLabel("");
		GridBagConstraints gbc_mainImageLabel = new GridBagConstraints();
		gbc_mainImageLabel.gridheight = 5;
		gbc_mainImageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_mainImageLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_mainImageLabel.gridx = 1;
		gbc_mainImageLabel.gridy = 2;
		this.add(mainImageLabel, gbc_mainImageLabel);

		
		titleLabel = new JLabel("");
		titleLabel.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 9;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		this.add(titleLabel, gbc_titleLabel);
		
		
		helpLabel = new JLabel("");
		helpLabel.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/help.png")));
		GridBagConstraints gbc_helpLabel = new GridBagConstraints();
		gbc_helpLabel.insets = new Insets(0, 0, 5, 0);
		gbc_helpLabel.gridx = 8;
		gbc_helpLabel.gridy = 0;
		this.add(helpLabel, gbc_helpLabel);
			
	
		JLabel lblWellcome = new JLabel("Bem-vindo"); 
		GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
		gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblWellcome.gridx = 7;
		gbc_lblWellcome.gridy = 1;
		this.add(lblWellcome, gbc_lblWellcome);
	
		
		JLabel lblRegistryuser = new JLabel("RegistryUser"); // Informação sobre o user que está a usar o programa
		GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
		gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 0);
		gbc_lblRegistryuser.gridx = 8;
		gbc_lblRegistryuser.gridy = 1;
		this.add(lblRegistryuser, gbc_lblRegistryuser);
	}
}
