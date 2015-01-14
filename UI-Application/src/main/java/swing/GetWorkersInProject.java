package swing;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class GetWorkersInProject extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetWorkersInProject frame = new GetWorkersInProject();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GetWorkersInProject() {
		setBounds(100, 100, 450, 300);

		
		{
			//Inserir imagem : user -> Path e localização no Painel
			
			JLabel consultantLabel = new JLabel("");
			consultantLabel.setIcon(GetWorkersInProject.class.getClassLoader("resources/images/user.jpg"));
			GridBagConstraints gbc_lblConsultant = new GridBagConstraints();
			gbc_lblConsultant.gridheight = 2;
			gbc_lblConsultant.insets = new Insets(0, 0, 5, 5);
			gbc_lblConsultant.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblConsultant.gridx = 1;
			gbc_lblConsultant.gridy = 5;
			patchConsultantPanel.add(consultantLabel, gbc_lblConsultant);
		}
	}

}
