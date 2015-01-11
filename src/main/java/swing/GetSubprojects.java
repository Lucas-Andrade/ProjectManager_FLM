package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class GetSubprojects extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetSubprojects frame = new GetSubprojects();
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
	public GetSubprojects() {
		setBounds(100, 100, 450, 300);

	}

}
