package mainFrame;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


@SuppressWarnings("serial")
public class LoadingDialog extends JDialog{
	
	JLabel label;
	
	public void setMessage(String message){
		label.setText(message);
		label.updateUI();
	}
	
	public LoadingDialog(){
		this.setTitle("Loading...");
		this.setSize(220, 170);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{24, 10, 156, 0};
		gridBagLayout.rowHeights = new int[]{132, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		getContentPane().setBackground(new Color(176, 196, 222));
		
		JLabel logo = new JLabel();
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.anchor = GridBagConstraints.WEST;
		gbc_logo.fill = GridBagConstraints.VERTICAL;
		gbc_logo.insets = new Insets(0, 0, 0, 5);
		gbc_logo.gridx = 0;
		gbc_logo.gridy = 0;
		getContentPane().add(logo, gbc_logo);
		logo.setIcon(new ImageIcon(LoadingDialog.class.getClassLoader()
				.getResource("images/loading.png")));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 2;
		gbc.gridy = 0;
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(label, gbc);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
}
