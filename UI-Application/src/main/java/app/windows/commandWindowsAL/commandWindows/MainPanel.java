package app.windows.commandWindowsAL.commandWindows;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.authentication.Authentication;

public class MainPanel extends JPanel
{

	private static final long serialVersionUID = -4646635210489120110L;
	private JLabel mainImageLabel;
	private JLabel titleLabel;
	private JLabel helpLabel;

	public void setImage(String imagePath)
	{
		mainImageLabel.setIcon(new ImageIcon(MainDialogFrame.class
				.getClassLoader().getResource(imagePath)));
	}

	public void setTitleLabel(String title)
	{
		titleLabel.setText(title);
	}

	public void setHelpTip(String help)
	{
		helpLabel.setToolTipText(help);
	}

	/**
	 * Create the panel.
	 */
	public MainPanel()
	{
		setBackground(new Color(176, 196, 222));

		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 5, 100, 60, 100, 0, 0, 50, 0,
				75, 0 };
		gbl_mainPanel.rowHeights = new int[] { 0, 0, 50, 0, 0, 0, 0, 0, 0, 0 };
		gbl_mainPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, Double.MIN_VALUE };
		this.setLayout(gbl_mainPanel);

		mainImageLabel = new JLabel("");
		GridBagConstraints gbc_mainImageLabel = new GridBagConstraints();
		gbc_mainImageLabel.gridheight = 5;
		gbc_mainImageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_mainImageLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_mainImageLabel.gridx = 1;
		gbc_mainImageLabel.gridy = 2;
		this.add(mainImageLabel, gbc_mainImageLabel);

		titleLabel = new JLabel("");
		titleLabel.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC,
				14));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 9;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		this.add(titleLabel, gbc_titleLabel);

		helpLabel = new JLabel("");
		helpLabel.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader()
				.getResource("images/help.png")));
		GridBagConstraints gbc_helpLabel = new GridBagConstraints();
		gbc_helpLabel.insets = new Insets(0, 0, 5, 0);
		gbc_helpLabel.gridx = 8;
		gbc_helpLabel.gridy = 0;
		this.add(helpLabel, gbc_helpLabel);

		JLabel lblWellcome = new JLabel("Welcome");
		GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
		gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblWellcome.gridx = 7;
		gbc_lblWellcome.gridy = 1;
		this.add(lblWellcome, gbc_lblWellcome);

		JLabel lblRegistryuser = new JLabel(Authentication
				.getAuthenticatedUser().getFullName());
		GridBagConstraints gbcRegistryuser = new GridBagConstraints();
		gbcRegistryuser.insets = new Insets(0, 0, 5, 0);
		gbcRegistryuser.gridx = 8;
		gbcRegistryuser.gridy = 1;
		this.add(lblRegistryuser, gbcRegistryuser);
	}

}