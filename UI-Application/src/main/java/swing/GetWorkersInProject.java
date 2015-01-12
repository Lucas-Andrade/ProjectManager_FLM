package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class GetWorkersInProject extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetWorkersInProject frame = new GetWorkersInProject();
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
	public GetWorkersInProject() {
		setBounds(100, 100, 450, 300);

	}

}
