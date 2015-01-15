package app.framesAndPanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public abstract class MainDialogFrame extends JDialog {  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int Y_BOUND = 100;
	private static final int X_BOUND =100;
	private static final int WIDTH_BOUND =636;
	private static final int HEIGHT_BOUND =387;
	private final MainPanel mainDialogPanel;
	
	

	/**
	 * Method that allows the user to insert different images in each child class
	 * @param imagePath
	 */
	public void setImage(String imagePath){
		mainDialogPanel.setImage(imagePath);
	}
	
	public void setTitleLabel(String title){
		mainDialogPanel.setTitleLabel(title);
	}
	
	public void setHelpTip(String help){
		mainDialogPanel.setHelpTip(help);
	}
	
	/**
	 * Create the dialog.
	 * @param image,  
	 */
	public MainDialogFrame() {
		
		mainDialogPanel = new MainPanel();
				
		setBounds(X_BOUND,Y_BOUND,WIDTH_BOUND, HEIGHT_BOUND);   //Definição da Caixa de Diálogo
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getMainDialogPanel(), BorderLayout.CENTER);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/Ok.png")));
				okButton.setActionCommand("OK");
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
	}

	/**
	 * @return the mainDialogPanel
	 */
	public JPanel getMainDialogPanel() {
		return mainDialogPanel;
	}
}

