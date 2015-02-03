package windows.mainFrameAL.mainFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import windows.commandWindowsAL.commandWindows.MainDialogFrame;

public class ErrorDialog extends JDialog {
	
	private static final long serialVersionUID = -8117762702441573121L;
	private JLabel mainImageLabel;
	private JLabel errorMessage;
	
	public ErrorDialog(String message){
				
		this.setTitle("Something went wrong");
		
		setBounds(100, 100, 303, 225);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{0, 100, 30};
		gridBagLayout.columnWidths = new int[]{0, 50, 200, 50};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		getContentPane().setBackground(new Color(176, 196, 222));
		
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
		gbc_okButton.anchor = GridBagConstraints.SOUTHEAST;
		gbc_okButton.gridwidth = 1;
		gbc_okButton.gridx = 3;
		gbc_okButton.gridy = 2;
		okButton.setActionCommand("OK");
		getContentPane().add(okButton, gbc_okButton);
		okButton.setBackground(new Color(0, 206, 209));
		
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});

		pack();
	}

}