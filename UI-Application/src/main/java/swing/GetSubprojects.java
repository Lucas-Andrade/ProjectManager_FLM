package swing;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class GetSubprojects extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetSubprojects frame = new GetSubprojects();
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
	public GetSubprojects() {
		setBounds(100, 100, 450, 300);
	     
		{ //Inserir imagem : subproject -> Path e localização no Painel
			
			JLabel subprojectLabel = new JLabel("");
			subprojectLabel.setIcon(PostSubproject.class.getClassLoader("resources/images/subproject.jpg"));
			GridBagConstraints gbc_lblUser = new GridBagConstraints();
			gbc_lblUser.gridheight = 3;
			gbc_lblUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblUser.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblUser.gridx = 1;
			gbc_lblUser.gridy = 3;
			postSubprojectPanel.add(userLabel, gbc_lblUser);	
		}
		
		
	}

}
