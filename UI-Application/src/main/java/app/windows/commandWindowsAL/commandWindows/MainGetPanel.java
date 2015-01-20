package app.windows.commandWindowsAL.commandWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class MainGetPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int Y_BOUND = 100;
	private static final int X_BOUND =100;
	private static final int WIDTH_BOUND =636;
	private static final int HEIGHT_BOUND =387;
	private final MainPanel mainGetPanel;

	

	/**
	 * Method that allows the user to insert different images in each child class
	 * @param imagePath
	 */
	public void setImage(String imagePath){
		mainGetPanel.setImage(imagePath);
	}
	
	public void setTitleLabel(String title){
		mainGetPanel.setTitleLabel(title);
	}
	
	public void setHelpTip(String help){
		mainGetPanel.setHelpTip(help);
	}

	
	/**
	 * Create the dialog.
	 * @param image,  
	 */
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGetPanel frame = new MainGetPanel();
					//definimos o título da janela
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
	public MainGetPanel() {
	//	this.setDefaultCloseOperation(JPanel.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		
		mainGetPanel = new MainPanel();
		
		setBounds(X_BOUND,Y_BOUND,WIDTH_BOUND, HEIGHT_BOUND);   //Definição da Caixa de Diálogo
		getRootPane().setLayout(new BorderLayout());
		getRootPane().add(mainGetPanel, BorderLayout.CENTER);
		
		this.setLocation(null);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getRootPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("GET");
			okButton.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/Ok.png")));
			okButton.setActionCommand("GET");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/cancel.png")));
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
		  
	}

	/**
	 * @return the mainDialogPanel
	 */
	public JPanel getMainGetPanel() {
		return mainGetPanel;
	}
}

	