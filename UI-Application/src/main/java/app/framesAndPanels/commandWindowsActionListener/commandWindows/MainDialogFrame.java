package app.framesAndPanels.commandWindowsActionListener.commandWindows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.result.CommandResult;

public abstract class MainDialogFrame extends JDialog {  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int Y_BOUND = 100;
	private static final int X_BOUND =100;
	private static final int WIDTH_BOUND =636;
	private static final int HEIGHT_BOUND =387;
	private CommandResult result;
	private JButton saveButton;
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
	
	public JButton getSaveButton(){
		return saveButton;
	}

	
	/**
	 * Create the dialog.
	 * @param image,  
	 */
	public MainDialogFrame() {
		this.result = result;
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		mainDialogPanel = new MainPanel();
		
		this.setLocationRelativeTo(null);
		
		setBounds(X_BOUND,Y_BOUND,WIDTH_BOUND, HEIGHT_BOUND);   //Definição da Caixa de Diálogo
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getMainDialogPanel(), BorderLayout.CENTER);
		
		{
			JPanel buttonPane = new JPanel();
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
			dispose();
		}
		
	}
	
//	protected class SaveActionListener implements ActionListener{
//		
//		JTextField[] textFields;
//		
//		public SaveActionListener(JTextField[] textFields) {
//			this.textFields = textFields;
//		}
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			
//			dispose();
//			
//		}
//	}
}

