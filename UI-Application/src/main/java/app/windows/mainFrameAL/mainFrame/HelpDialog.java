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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HelpDialog errorDialog = new HelpDialog();
			errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			errorDialog.setVisible(true);
		} catch (Exception e) {		}
	}
	private JLabel help;
	
	public HelpDialog(){
		this.setTitle("Help");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.setSize(500, 500);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{0, 100, 30};
		gridBagLayout.columnWidths = new int[]{0, 50, 200, 50};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Help");
		titleLabel.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 2;
		gbc_titleLabel.gridy = 0;
		getContentPane().add(titleLabel, gbc_titleLabel);
		
		help = new JLabel("<html>AJUDA<br><br>Este programa serve para organizares os teus projectozinhos.<br>Podes colocar trabalhadores, subprojectos e mais cenas fixes.<br>Também podes criar vários utilizadores para utilizarem esta bomba de programa.<br><br>Botões OK -> Pressiona aqui para dares o teu OK.<br>Botões Cancel -> Se não gostas, carrega aqui.<br>Botões Save -> É para guardares as tuas cenas pah.<br>Login -> Não te esqueças de fazer o login com a tua username e password.<br>Logout -> Carrega para fazeres logout.<br>Exit -> NÃO CARREGUES AQUI. O programa vai embora.<br>Help -> É esta coisa que tás a ler.<br>About -> Sobre este programa e os seus fantásticos criadores.<br><br>HELP<br><br>This app let's you do things. For more help place the Portuguese Help in Google Translater.</html>");
		help.setHorizontalAlignment(SwingConstants.CENTER);
		help.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_help = new GridBagConstraints();
		gbc_help.fill = GridBagConstraints.BOTH;
		gbc_help.insets = new Insets(0, 0, 5, 5);
		gbc_help.gridx = 2;
		gbc_help.gridy = 1;
		getContentPane().add(help, gbc_help);
						
	}

}