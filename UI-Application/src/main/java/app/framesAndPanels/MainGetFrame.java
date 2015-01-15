package app.framesAndPanels;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class MainGetFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGetFrame frame = new MainGetFrame();
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
	public MainGetFrame() {
		setBounds(100, 100, 450, 300);

	}

}
