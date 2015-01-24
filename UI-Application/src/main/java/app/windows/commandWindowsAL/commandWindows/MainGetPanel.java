package app.windows.commandWindowsAL.commandWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class MainGetPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int Y_BOUND = 100;
	private static final int X_BOUND = 100;
	private static final int WIDTH_BOUND = 636;
	private static final int HEIGHT_BOUND = 387;
	private final MainPanel mainGetPanel;
	private JButton getButton;
	protected static JPanel results;
	
	public static void setResults(JComponent component) {
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
//			getRootPane().setDefaultButton(getButton);
		}
		  
	}

	/**
	 * @return the mainDialogPanel
	 */
	public JPanel getMainGetPanel() {
		return mainGetPanel;
	}
	
}

	
