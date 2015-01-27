package app.windows.commandWindowsAL.commandWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

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
			dispose();
		}
	}
	
	public void setFrameUser() {
		mainDialogPanel.setFrameUser();
	}

	public abstract void resetAllFields();
}

