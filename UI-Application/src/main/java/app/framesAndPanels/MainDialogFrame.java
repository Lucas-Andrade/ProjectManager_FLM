package app.framesAndPanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class MainDialogFrame extends JDialog {  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int Y_BOUND = 100;
	private static final int X_BOUND =100;
	private static final int WIDTH_BOUND =636;
	private static final int HEIGHT_BOUND =387;
	private final JPanel mainDialogPanel = new JPanel();
	private JLabel mainImageLabel;
	private JLabel titleLabel;
	private JLabel helpLabel;
	

	/**
	 * Method that allows the user to insert different images in each child class
	 * @param imagePath
	 */
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
	 * Create the dialog.
	 * @param image,  
	 */
	public MainDialogFrame() {
				
		setBounds(X_BOUND,Y_BOUND,WIDTH_BOUND, HEIGHT_BOUND);   //Definição da Caixa de Diálogo
		getContentPane().setLayout(new BorderLayout());
		getMainDialogPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(getMainDialogPanel(), BorderLayout.CENTER);
		
		
		GridBagLayout gbl_patchConsultantPanel = new GridBagLayout();  // Definição do Painel interno
		gbl_patchConsultantPanel.columnWidths = new int[]{20, 50, 60, 100, 0, 0, 50, 0, 50, 0};
		gbl_patchConsultantPanel.rowHeights = new int[]{0, 0, 50, 0, 0, 0, 0, 0, 0, 0};
		gbl_patchConsultantPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_patchConsultantPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getMainDialogPanel().setLayout(gbl_patchConsultantPanel);
							

		mainImageLabel = new JLabel("");
		GridBagConstraints gbc_mainImageLabel = new GridBagConstraints();
		gbc_mainImageLabel.gridheight = 5;
		gbc_mainImageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_mainImageLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_mainImageLabel.gridx = 1;
		gbc_mainImageLabel.gridy = 2;
		getMainDialogPanel().add(mainImageLabel, gbc_mainImageLabel);

		
		titleLabel = new JLabel("");
		titleLabel.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 9;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		getMainDialogPanel().add(titleLabel, gbc_titleLabel);
		
		
		helpLabel = new JLabel("");
		helpLabel.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/help.png")));
		GridBagConstraints gbc_helpLabel = new GridBagConstraints();
		gbc_helpLabel.insets = new Insets(0, 0, 5, 0);
		gbc_helpLabel.gridx = 8;
		gbc_helpLabel.gridy = 0;
		getMainDialogPanel().add(helpLabel, gbc_helpLabel);
			
	
		JLabel lblWellcome = new JLabel("Bem-vindo"); 
		GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
		gbc_lblWellcome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblWellcome.gridx = 7;
		gbc_lblWellcome.gridy = 1;
		getMainDialogPanel().add(lblWellcome, gbc_lblWellcome);
	
		
		JLabel lblRegistryuser = new JLabel("RegistryUser"); // Informação sobre o user que está a usar o programa
		GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
		gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 0);
		gbc_lblRegistryuser.gridx = 8;
		gbc_lblRegistryuser.gridy = 1;
		getMainDialogPanel().add(lblRegistryuser, gbc_lblRegistryuser);
				
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/Ok.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/cancel.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * @return the mainDialogPanel
	 */
	public JPanel getMainDialogPanel() {
		return mainDialogPanel;
	}
}

