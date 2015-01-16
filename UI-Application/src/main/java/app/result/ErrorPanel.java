package app.result;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import app.framesAndPanels.MainDialogFrame;

/**
 * Class responsible for showing the errors from the computation of the results
 * in a JPanel.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 16/01/2014
 */
public class ErrorPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor for a JPanel that shows the errors.
	 * 
	 * @param results
	 *            The results for showing in the JPanel.
	 */
	public ErrorPanel(String message)
	{

		JPanel panel = new JPanel();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 25, 100 };
		gridBagLayout.columnWidths = new int[] { 50, 200 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0 };
		panel.setLayout(gridBagLayout);

		JLabel mainImageLabel = new JLabel("");
		mainImageLabel.setIcon(new ImageIcon(MainDialogFrame.class
				.getClassLoader().getResource("images/error.png")));
		GridBagConstraints gbc_mainImageLabel = new GridBagConstraints();
		gbc_mainImageLabel.gridheight = 2;
		gbc_mainImageLabel.insets = new Insets(0, 0, 0, 5);
		gbc_mainImageLabel.gridx = 0;
		gbc_mainImageLabel.gridy = 0;
		panel.add(mainImageLabel, gbc_mainImageLabel);

		JLabel titleLabel = new JLabel("Error Message");
		titleLabel.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC,
				12));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		panel.add(titleLabel, gbc_titleLabel);

		JLabel errorMessage = new JLabel(message);
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessage.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_ErrorMessage = new GridBagConstraints();
		gbc_ErrorMessage.fill = GridBagConstraints.BOTH;
		gbc_ErrorMessage.gridx = 1;
		gbc_ErrorMessage.gridy = 1;
		panel.add(errorMessage, gbc_ErrorMessage);

	}

}