package app.framesAndPanels;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GetSubprojectsFrame extends MainGetFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GetUserPanel dialog = new GetUserPanel();
			//definimos o título da janela
			dialog.setTitle("Get Subproject");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final JPanel getSubprojectPanel = new JPanel();

	/**
	 * Create the frame.
	 */
	public GetSubprojectsFrame() {
		
		super(); 
		
		this.setTitle("Get Subprojects");    //definimos o título da janela
		this.setImage("images/subproject.jpg");
		this.setTitleLabel("Get Subprojects");
		this.setHelpTip("Return the information of all subprojects of a project with the specify Id.");
		
		
		
		
	}

}
