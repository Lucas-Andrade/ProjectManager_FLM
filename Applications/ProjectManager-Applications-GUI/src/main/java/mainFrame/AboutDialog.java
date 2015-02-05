package mainFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import commandWindows.MainDialogFrame;

public class AboutDialog extends JDialog {
	
	private static final long serialVersionUID = -8117122702441573121L;
	private JLabel help;
	private JLabel logo;
	
	public AboutDialog(){
				
		this.setTitle("About");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.setSize(422, 222);
		setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{0, 100, 30};
		gridBagLayout.columnWidths = new int[]{0, 50, 200, 50};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		getContentPane().setBackground(new Color(176, 196, 222));
		
		JLabel titleLabel = new JLabel("About");
		titleLabel.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		getContentPane().add(titleLabel, gbc_titleLabel);
		
		logo = new JLabel("");
		logo.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader()
				.getResource("images/Logo128_.png")));
		logo.setOpaque(false);
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.fill = GridBagConstraints.BOTH;
		gbc_logo.insets = new Insets(0, 0, 5, 5);
		gbc_logo.gridx = 1;
		gbc_logo.gridy = 1;
		getContentPane().add(logo, gbc_logo);
		
		help = new JLabel("<html>Project Manager 0.3.0<br>by Team FFL (Filipa, Filipe, Lucas)<br><br>Formação RePrograma a tua Carreira 2014<br>@Randstad</html>");
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