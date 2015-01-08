package app.forWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class NewProject implements ActionListener{
	private static JTextField textField;

	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dialog = new JDialog(new JFrame(), "New project");
		dialog.setSize(640, 420);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 59, 0, 306, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		dialog.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Local");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		dialog.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		dialog.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		dialog.setVisible(true);
	}
	
	
//	public static void main(String[] args)
//	{
//		JFrame frame = new JFrame();
//		JDialog dialog = new JDialog(frame, "New project");
//		frame.setSize(640, 420);
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[]{0, 59, 0, 306, 0, 0};
//		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
//		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
//		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
//		frame.getContentPane().setLayout(gridBagLayout);
//		
//		JLabel lblNewLabel = new JLabel("Local");
//		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
//		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
//		gbc_lblNewLabel.gridx = 1;
//		gbc_lblNewLabel.gridy = 2;
//		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
//		
//		textField = new JTextField();
//		GridBagConstraints gbc_textField = new GridBagConstraints();
//		gbc_textField.insets = new Insets(0, 0, 0, 5);
//		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField.gridx = 3;
//		gbc_textField.gridy = 2;
//		frame.getContentPane().add(textField, gbc_textField);
//		textField.setColumns(10);
//		
//		frame.setVisible(true);
//	}

}
