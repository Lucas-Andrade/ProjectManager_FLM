package app.framesAndPanels;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GetSubprojectsFrame extends MainDialogFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GetUserPanel dialog = new GetUserPanel();
			//definimos o título da janela
			dialog.setTitle("Get Subproject");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final JPanel getSubprojectPanel = new JPanel();

	/**
	 * Create the frame.
	 */
	public GetSubprojectsFrame() {
		setBounds(100, 100, 626, 387);
		getContentPane().setLayout(new BorderLayout());
		getSubprojectPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(getSubprojectPanel, BorderLayout.CENTER);
		GridBagLayout gbl_getSubprojectPanel = new GridBagLayout();
		gbl_getSubprojectPanel.columnWidths = new int[]{0, 100, 100, 100, 0, 0, 48, 0, 0};
		gbl_getSubprojectPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 100};
		gbl_getSubprojectPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_getSubprojectPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getSubprojectPanel.setLayout(gbl_getSubprojectPanel);
	     
		{ //Inserir imagem : subproject -> Path e localização no Painel
			
			JLabel subprojectLabel = new JLabel("");
			subprojectLabel.setIcon(new ImageIcon(PostSubprojectFrame.class.getClassLoader().getResource("images/subproject.jpg")));
			GridBagConstraints gbc_lblSubproject = new GridBagConstraints();
			gbc_lblSubproject.gridheight = 3;
			gbc_lblSubproject.insets = new Insets(0, 0, 5, 5);
			gbc_lblSubproject.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblSubproject.gridx = 1;
			gbc_lblSubproject.gridy = 3;
			getSubprojectPanel.add(subprojectLabel, gbc_lblSubproject);	
		}
		
		
	}

}
