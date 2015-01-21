package app.windows.commandWindowsAL.commandWindows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class MainGetPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int Y_BOUND = 100;
	private static final int X_BOUND = 100;
	private static final int WIDTH_BOUND = 636;
	private static final int HEIGHT_BOUND = 387;
	private final MainPanel mainGetPanel;
	private JButton getButton;
	protected static JPanel panelThatGetsTheResults;
	
	public static void setResults(JComponent component) {
		panelThatGetsTheResults.setLayout(new BorderLayout(0, 0));
		panelThatGetsTheResults.add(component);
		panelThatGetsTheResults.updateUI();
	}
	

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

	public JButton getGetButton(){
		return getButton;
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
		this.setLayout(new BorderLayout());
		this.add(mainGetPanel, BorderLayout.CENTER);
		
//		this.setLocation(null);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(buttonPane, BorderLayout.SOUTH);
		{
			getButton = new JButton("GET");
			getButton.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/Ok.png")));
			getButton.setActionCommand("GET");
			buttonPane.add(getButton);
//			getRootPane().setDefaultButton(getButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/cancel.png")));
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			cancelButton.addActionListener(new CancelActionListener());
		}
		  
	}

	/**
	 * @return the mainDialogPanel
	 */
	public JPanel getMainGetPanel() {
		return mainGetPanel;
	}
	
	private class CancelActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO
		}
		
	}
	
}

	
