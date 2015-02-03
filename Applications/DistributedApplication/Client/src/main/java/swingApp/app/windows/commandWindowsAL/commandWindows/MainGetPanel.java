package swingApp.app.windows.commandWindowsAL.commandWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import app.windows.commandWindowsAL.commandWindows.MainDialogFrame;
import app.windows.commandWindowsAL.commandWindows.MainPanel;

/**
 * Instances of this abstract class define the main Panel of all the GET
 * {@code command} windows
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public abstract class MainGetPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int Y_BOUND = 100;
	private static final int X_BOUND = 100;
	private static final int WIDTH_BOUND = 636;
	private static final int HEIGHT_BOUND = 387;
	private final MainPanel mainGetPanel;
	private JButton getButton;
	protected JPanel results;
	
	public void setResults(JComponent component) {
		results.removeAll();
		results.setLayout(new BorderLayout(0, 0));
		results.add(component);
		results.updateUI();
	}
	

	/**
	 * Method that allows the user to insert different images in each child class
	 * @param imagePath
	 */
	public void setImage(String imagePath){
		mainGetPanel.setImage(imagePath);
	}
	
	/**
	 * Method that allows the user to insert different Titles in each child class
	 * @param title
	 */
	public void setTitleLabel(String title){
		mainGetPanel.setTitleLabel(title);
	}
	
	/**
	 * Method that allows the user to insert different help tip in each child class
	 * @param help
	 */
	public void setHelpTip(String help){
		mainGetPanel.setHelpTip(help);
	}

	/**
	 * @return getButton
	 */
	public JButton getGetButton(){
		return getButton;
	}
	
	

	/**
	 * Create the frame.
	 */
	public MainGetPanel() {
		this.setVisible(true);
		
		mainGetPanel = new MainPanel();
		mainGetPanel.setBackground(new Color(176, 196, 222));
		
		setBounds(X_BOUND,Y_BOUND,WIDTH_BOUND, HEIGHT_BOUND);   //Definição da Caixa de Diálogo
		this.setLayout(new BorderLayout());
		this.add(mainGetPanel, BorderLayout.CENTER);
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(102, 153, 204));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(buttonPane, BorderLayout.SOUTH);
		{
			getButton = new JButton("GET");
			getButton.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader().getResource("images/Ok.png")));
			getButton.setActionCommand("GET");
			buttonPane.add(getButton);
		}	  
	}

	/**
	 * @return the mainDialogPanel
	 */
	public JPanel getMainGetPanel() {
		return mainGetPanel;
	}
	
	
	public void setFrameUser() {
		mainGetPanel.setFrameUser();
	}
	
	/**
	 * This method when called, reset all text fields
	 */
	public abstract void resetAllFields();
	
}

	
