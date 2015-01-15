package app.framesAndPanels;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class GetWorkersInProjectFrame extends MainGetFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel getWorkerPanel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GetUserPanel dialog = new GetUserPanel();
			//definimos o título da janela
			dialog.setTitle("Get Workers In Project");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * Create the frame.
	 */
	public GetWorkersInProjectFrame() {
		super();
		
		setBounds(100, 100, 626, 387);
		getContentPane().setLayout(new BorderLayout());
		getWorkerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(getWorkerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_getWorkerPanel = new GridBagLayout();
		gbl_getWorkerPanel.columnWidths = new int[]{0, 100, 100, 100, 0, 0, 48, 0, 0};
		gbl_getWorkerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 100};
		gbl_getWorkerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_getWorkerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getWorkerPanel.setLayout(gbl_getWorkerPanel);

		
		{
			//Inserir imagem : consultant -> Path e localização no Painel
			
			JLabel consultantLabel = new JLabel("");
			consultantLabel.setIcon(new ImageIcon(GetWorkersInProjectFrame.class.getClassLoader().getResource("images/user.jpg")));
			GridBagConstraints gbc_lblConsultant = new GridBagConstraints();
			gbc_lblConsultant.insets = new Insets(0, 0, 5, 5);
			gbc_lblConsultant.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblConsultant.gridx = 1;
			gbc_lblConsultant.gridy = 2;
			getWorkerPanel.add(consultantLabel, gbc_lblConsultant);
		}
	}

}
