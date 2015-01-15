package app.framesAndPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class ErrorDialog extends JDialog {
	
	private static final long serialVersionUID = -8117762702441573121L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ErrorDialog errorDialog = new ErrorDialog("Error!!!");
			errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			errorDialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel errorPanel = new JPanel();
	private JLabel mainImageLabel;
	private JLabel errorMessage;
	
	public ErrorDialog(String message){
		this.setTitle("Error");
		
		setBounds(100, 100, 303, 225);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{0, 100, 30};
		gridBagLayout.columnWidths = new int[]{0, 50, 200, 50};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		errorPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_errorPanel = new GridBagConstraints();
		gbc_errorPanel.insets = new Insets(0, 0, 5, 5);
		gbc_errorPanel.fill = GridBagConstraints.BOTH;
		gbc_errorPanel.gridx = 0;
		gbc_errorPanel.gridy = 0;
		getContentPane().add(errorPanel, gbc_errorPanel);
		
		mainImageLabel = new JLabel("");
		mainImageLabel.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/error.png")));
		GridBagConstraints gbc_mainImageLabel = new GridBagConstraints();
		gbc_mainImageLabel.gridheight = 2;
		gbc_mainImageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_mainImageLabel.gridx = 1;
		gbc_mainImageLabel.gridy = 0;
		getContentPane().add(mainImageLabel, gbc_mainImageLabel);
		
		JLabel titleLabel = new JLabel("Error Message");
		titleLabel.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 2;
		gbc_titleLabel.gridy = 0;
		getContentPane().add(titleLabel, gbc_titleLabel);
		
		errorMessage = new JLabel(message);
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessage.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_ErrorMessage = new GridBagConstraints();
		gbc_ErrorMessage.fill = GridBagConstraints.BOTH;
		gbc_ErrorMessage.insets = new Insets(0, 0, 5, 5);
		gbc_ErrorMessage.gridx = 2;
		gbc_ErrorMessage.gridy = 1;
		getContentPane().add(errorMessage, gbc_ErrorMessage);
												
													
		JButton okButton = new JButton("OK");
		GridBagConstraints gbc_okButton = new GridBagConstraints();
		gbc_okButton.insets = new Insets(0, 0, 0, 5);
		gbc_okButton.anchor = GridBagConstraints.SOUTHEAST;
		gbc_okButton.gridwidth = 1;
		gbc_okButton.gridx = 2;
		gbc_okButton.gridy = 2;
		getContentPane().add(okButton, gbc_okButton);
		okButton.setBackground(new Color(0, 206, 209));
					
						
	}

}