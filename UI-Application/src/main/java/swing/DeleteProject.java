package swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DeleteProject extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel deleteProjectPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DeleteProject dialog = new DeleteProject();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DeleteProject() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		deleteProjectPanel.setLayout(new FlowLayout());
		deleteProjectPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(deleteProjectPanel, BorderLayout.CENTER);
		
		
	    //Inserir imagem : project -> Path e localização no Painel
		{ 
			JLabel delProjectLabel = new JLabel("");
			delProjectLabel.setIcon(new ImageIcon(DeleteProject.class.getClassLoader().getResource("images/project-delete.png")));;
			GridBagConstraints gbc_lblDelProject = new GridBagConstraints();
			gbc_lblDelProject.gridheight = 2;
			gbc_lblDelProject.insets = new Insets(0, 0, 5, 5);
			gbc_lblDelProject.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblDelProject.gridx = 1;
			gbc_lblDelProject.gridy = 3;
			deleteProjectPanel.add(delProjectLabel, gbc_lblDelProject);

		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
