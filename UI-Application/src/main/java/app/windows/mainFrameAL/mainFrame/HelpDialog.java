package app.windows.mainFrameAL.mainFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HelpDialog extends JDialog {
	
	private static final long serialVersionUID = -8117622702441573121L;
	private JLabel help;
	
	public HelpDialog(){
		
		this.setTitle("Help");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(500, 500);
		setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{0, 100, 30};
		gridBagLayout.columnWidths = new int[]{0, 50, 200, 50};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		getContentPane().setBackground(new Color(176, 196, 222));
		
		
		JLabel titleLabel = new JLabel("Help");
		titleLabel.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		getContentPane().add(titleLabel, gbc_titleLabel);
		
		help = new JLabel(
				"<html> HELP <br> This program allows you to organize your little projects. "
				+ "<br> You can add employees, sub-projects and many cool things."
				+ "<br> You can also create multiple users use this program pump. "
				+ "<br><br> OK Buttons -> Press here for you to give your OK. "
				+ "<br> Cancel Buttons -.> If you do not like, loaded here. "
				+ "<br> Save buttons -> is to put your favorite stuff! "
				+ "<br> Login -> Do not forget to login with your username and password. "
				+ "<br> Logout -> Load to log out. "
				+ "<br> Exit -> Do not upload HERE. The program goes away. "
				+ "<br> Help -.> É isso que você está lendo. "
				+ "<br> About -..> Contains the information on this program and its fantastic creators."
				+ "<br> HELP <br> This App allows you to do much.. "
				+ "For more help place the Portuguese Help in Google Translater </ html>. ");
		help.setHorizontalAlignment(SwingConstants.CENTER);
		help.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_help = new GridBagConstraints();
		gbc_help.fill = GridBagConstraints.BOTH;
		gbc_help.insets = new Insets(0, 0, 5, 5);
		gbc_help.gridx = 2;
		gbc_help.gridy = 1;
		getContentPane().add(help, gbc_help);
		
		pack();
						
	}

}