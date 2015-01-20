package app.windows.mainFrameAL.mainFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AboutDialog extends JDialog {
	
	private static final long serialVersionUID = -8117122702441573121L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutDialog errorDialog = new AboutDialog();
			errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			errorDialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private JLabel help;
	
	public AboutDialog(){
		this.setTitle("About");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.setSize(400, 200);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{0, 100, 30};
		gridBagLayout.columnWidths = new int[]{0, 50, 200, 50};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("About");
		titleLabel.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 2;
		gbc_titleLabel.gridy = 0;
		getContentPane().add(titleLabel, gbc_titleLabel);
		
		help = new JLabel("<html>Project Manager 0.3.0<br>by Team FFL (Filipa, Filipe, Lucas)<br><br>Formação RePrograma a tua Carreira 2014<br>@Randstad</html>");
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