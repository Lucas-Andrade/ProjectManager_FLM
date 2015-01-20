package app.windowsAndActionListeners.commandWindowsActionListener.commandWindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import java.awt.Color;

import app.windowsAndActionListeners.mainFrameActionListener.mainFrame.ProjectID;

public class GetSubprojectsFrame extends MainGetPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6402767430117560903L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
			new GetSubprojectsFrame();

	}

	private ProjectID projectId;

	/**
	 * Create the frame.
	 */
	public GetSubprojectsFrame() {
		GridBagLayout gridBagLayout = (GridBagLayout) getMainGetPanel().getLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 100, 50, 50, 0, 100, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 30, 20, 0, 20, 0, 0, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		
		this.setTitle("Get Subprojects");    //definimos o título da janela
		this.setImage("images/subproject.jpg");
		this.setTitleLabel("Get Subprojects");
		this.setHelpTip("Return the information of all subprojects of a project with the specify Id.");
		
		projectId = new ProjectID();
		GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
		gbc_lblProjectID.gridwidth = 5;
		gbc_lblProjectID.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectID.gridx = 2;
		gbc_lblProjectID.gridy = 2;
		getMainGetPanel().add(projectId, gbc_lblProjectID);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 5;
		gbc_panel.gridwidth = 7;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 3;
		getMainGetPanel().add(panel, gbc_panel);
		
		this.setVisible(true);
	}

}