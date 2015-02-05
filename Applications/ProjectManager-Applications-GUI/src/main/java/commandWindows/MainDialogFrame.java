package commandWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Instances of this abstract class define the main Dialog frames of all the non GET
 * {@code command} windows
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */

public abstract class MainDialogFrame extends JDialog {  

	private static final long serialVersionUID = 1L;
	private static final int Y_BOUND = 100;
	private static final int X_BOUND =100;
	private JButton saveButton;
	private final MainPanel mainDialogPanel;
	
	/**
	 * Method that allows the user to insert different images in each child class
	 * @param imagePath
	 */
	public void setImage(String imagePath){
		mainDialogPanel.setImage(imagePath);
	}
	
	/**
	 * Method that allows the user to insert different Titles in each child class
	 * @param title
	 */
	public void setTitleLabel(String title){
		mainDialogPanel.setTitleLabel(title);
	}
	
	/**
	 * Method that allows the user to insert different help tip in each child class
	 * @param help
	 */
	public void setHelpTip(String help){
		mainDialogPanel.setHelpTip(help);
	}
	
	/**
	 * @return saveButton
	 */
	public JButton getSaveButton(){
		return saveButton;
	}

	
	/**
	 * Create the dialog.
	 * @param image,  
	 */
	public MainDialogFrame() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mainDialogPanel = new MainPanel();
		mainDialogPanel.setBackground(new Color(176, 196, 222));
		this.setLocationRelativeTo(null);
		
		setBounds(X_BOUND,Y_BOUND,661, 341);   //Definição da Caixa de Diálogo
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getMainDialogPanel(), BorderLayout.CENTER);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(102, 153, 204));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveButton = new JButton("Save");
				saveButton.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/Ok.png")));
				saveButton.setActionCommand("Save");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/cancel.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new CancelActionListener());
			}
		}
		this.getRootPane().setDefaultButton( saveButton );
		pack();  
	}

	/**
	 * @return the mainDialogPanel
	 */
	public JPanel getMainDialogPanel() {
		return mainDialogPanel;
	}
	
	private class CancelActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	
	public void setFrameUser() {
		mainDialogPanel.setFrameUser();
	}

	/**
	 * This method when called, reset all text fields
	 */
	public abstract void resetAllFields();
}

