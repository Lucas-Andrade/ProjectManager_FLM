package app.forWindow;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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

import app.forWindow.AppMainFrame.VerticalMenuBar;

public class AppExample {

JPanel rightPanel;
	
	
	
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
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcomeToProject = new JLabel("  Welcome to Project Manager");
		JLabel lblWelcomeToProject1 = new JLabel("  registry");
		lblWelcomeToProject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblWelcomeToProject, BorderLayout.NORTH);
		panel.add(lblWelcomeToProject1, BorderLayout.NORTH);
		
		JScrollBar scrollBar = new JScrollBar();
		panel.add(scrollBar, BorderLayout.EAST);
		
		JLabel lblStatus = new JLabel("Status: Ready");
		panel.add(lblStatus, BorderLayout.SOUTH);
		
		
		
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