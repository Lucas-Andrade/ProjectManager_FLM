package app.forWindow;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JMenuItem;

import app.forWindow.app.VerticalMenuBar;

import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JLayeredPane;

public class AppExample {

JPanel rightPanel;
private static JTextField textField;
private static JTextField textField_1;
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Project Manager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 420);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelpMe = new JMenuItem("Help me!");
		mnHelp.add(mntmHelpMe);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JMenuBar vert = new VerticalMenuBar();
		splitPane.setLeftComponent(vert);
		
		JMenu projectsMenu = new JMenu("Projects");
		vert.add(projectsMenu);
		
		JMenu mnNew = new JMenu("New");
		projectsMenu.add(mnNew);
		
		JMenuItem mntmProject = new JMenuItem("Project");
		mnNew.add(mntmProject);
		
		JMenuItem mntmSubproject = new JMenuItem("Subproject");
		mnNew.add(mntmSubproject);
		
		JMenuItem mntmWorker = new JMenuItem("Worker");
		mnNew.add(mntmWorker);
		
		JMenu mnEdit = new JMenu("Edit");
		projectsMenu.add(mnEdit);
		
		JMenuItem mntmProject_1 = new JMenuItem("Project");
		mnEdit.add(mntmProject_1);
		
		JMenu mnGet = new JMenu("Get");
		projectsMenu.add(mnGet);
		
		JMenuItem mntmProject_2 = new JMenuItem("Project");
		mnGet.add(mntmProject_2);
		
		JMenuItem mntmSubproject_2 = new JMenuItem("Subproject");
		mnGet.add(mntmSubproject_2);
		
		JMenuItem mntmWorkers = new JMenuItem("Workers");
		mnGet.add(mntmWorkers);
		
		JMenu mnDelete = new JMenu("Delete");
		projectsMenu.add(mnDelete);
		
		JMenuItem mntmProject_3 = new JMenuItem("Project");
		mnDelete.add(mntmProject_3);
		
		JMenu consultantsMenu = new JMenu("Consultants");
		vert.add(consultantsMenu);
		
		JMenuItem newConsultantItem = new JMenuItem("New Consultant");
		consultantsMenu.add(newConsultantItem);
		
		JMenuItem mntmEditConsultant = new JMenuItem("Edit Consultant");
		consultantsMenu.add(mntmEditConsultant);
		
		JMenu usersMenu = new JMenu("Users");
		vert.add(usersMenu);
		
		JMenuItem mntmNewUser = new JMenuItem("New User");
		usersMenu.add(mntmNewUser);
		
		JMenuItem mntmEditUser = new JMenuItem("Edit User");
		usersMenu.add(mntmEditUser);
		
		JMenuItem mntmGetUser = new JMenuItem("Get User");
		usersMenu.add(mntmGetUser);
		
		JMenu searchMenu = new JMenu("Search");
		vert.add(searchMenu);
		
		JPanel photoPanel = new JPanel();
		vert.add(photoPanel);
		
		BufferedImage photoMain;
		try {
		photoMain = ImageIO.read(new File("src\\main\\java\\swing\\imagens\\Main.jpg"));
		JLabel mainLogo = new JLabel(new ImageIcon(photoMain));
		
		GridBagConstraints gbc_lblMain = new GridBagConstraints();
		gbc_lblMain.gridheight = 7;
		gbc_lblMain.insets = new Insets(0, 0, 1, 1);
		gbc_lblMain.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblMain.gridx = 1;
		gbc_lblMain.gridy = 1;
		photoPanel.add(mainLogo, gbc_lblMain);
	
	} catch (IOException e) 
	{
		e.printStackTrace();
	}
		
		
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcomeToProject = new JLabel("  Welcome to Project Manager");
		lblWelcomeToProject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblWelcomeToProject, BorderLayout.NORTH);

		
		JScrollBar scrollBar = new JScrollBar();
		panel.add(scrollBar, BorderLayout.EAST);
		
		JLabel lblStatus = new JLabel("Status: Ready");
		panel.add(lblStatus, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 50, 50, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblResgistry = new JLabel("Resgistry:");
		GridBagConstraints gbc_lblResgistry = new GridBagConstraints();
		gbc_lblResgistry.anchor = GridBagConstraints.EAST;
		gbc_lblResgistry.insets = new Insets(0, 0, 0, 5);
		gbc_lblResgistry.gridx = 7;
		gbc_lblResgistry.gridy = 0;
		panel_1.add(lblResgistry, gbc_lblResgistry);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 0, 5);
		gbc_lblUsername.gridx = 8;
		gbc_lblUsername.gridy = 0;
		panel_1.add(lblUsername, gbc_lblUsername);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 9;
		gbc_textField.gridy = 0;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 11;
		gbc_lblPassword.gridy = 0;
		panel_1.add(lblPassword, gbc_lblPassword);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 12;
		gbc_textField_1.gridy = 0;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		
		
		int panelSize = 120;
		splitPane.setDividerLocation(panelSize);
		
		frame.setVisible(true);
	}

	public static class VerticalMenuBar extends JMenuBar
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -651471821217764757L;
		
		private static final LayoutManager grid = new GridLayout(10,1);
		
		public VerticalMenuBar() 
		{
			setLayout(grid);
		}
	}
}