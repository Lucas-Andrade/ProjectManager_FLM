package app.framesAndPanels;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 424, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 10, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GetUserPanel internalFrame = new GetUserPanel();
		internalFrame.setResizable(true);
		GridBagConstraints gbc_internalFrame = new GridBagConstraints();
		gbc_internalFrame.gridwidth = 2;
		gbc_internalFrame.gridheight = 4;
		gbc_internalFrame.fill = GridBagConstraints.BOTH;
		gbc_internalFrame.gridx = 0;
		gbc_internalFrame.gridy = 0;
		contentPane.add(internalFrame, gbc_internalFrame);
		internalFrame.setVisible(true);
	}

}
