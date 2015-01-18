package app.framesAndPanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
	private JButton okButton;
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
	
	protected void setOkButtonActionListener(JTextField[] textFields) {
		okButton.addActionListener(new OkActionListener(textFields));
	}
	
	/**
	 * Create the dialog.
	 * @param image,  
	 */
	public MainDialogFrame(CommandResult result) {
		this.result = result;
		
		try {
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		
			}catch (Exception e) {
			e.printStackTrace();
		}
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
				okButton = new JButton("OK");
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
	
	protected class OkActionListener implements ActionListener{
		
		JTextField[] textFields;
		
		public OkActionListener(JTextField[] textFields) {
			this.textFields = textFields;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			result.executeResult(textFields);
			dispose();
			
		}
	}
}

