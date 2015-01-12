package app.forWindow;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollBar;

import app.forWindow.RepositoryHolders.InMemoryRepositoryHolder;
import app.forWindow.RepositoryHolders.RepositoryHolder;
import app.forWindow.commandsFrames.NewProject;

public class AppMainFrame {

	private static JSplitPane splitPane = new JSplitPane();
	public static final int PANEL_DIVIDER_LOCATION = 120;
	private static RepositoryHolder repositories = new InMemoryRepositoryHolder();
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Project Manager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 420);
		
		splitPane.setDividerLocation(PANEL_DIVIDER_LOCATION);
		
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
		
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcomeToProject = new JLabel("  Welcome to Project Manager");
		lblWelcomeToProject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rightPanel.add(lblWelcomeToProject, BorderLayout.NORTH);
		
		JScrollBar scrollBar = new JScrollBar();
		rightPanel.add(scrollBar, BorderLayout.EAST);
		
		JLabel lblStatus = new JLabel("Status: Ready");
		rightPanel.add(lblStatus, BorderLayout.SOUTH);
		
		
		
		JMenuBar vert = new VerticalMenuBar();
		splitPane.setLeftComponent(vert);
		
		JMenu projectsMenu = new JMenu("Projects");
		vert.add(projectsMenu);
		
		JMenuItem newProjectItem = new JMenuItem("New project");
		projectsMenu.add(newProjectItem);
		newProjectItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NewProject(splitPane, repositories);
			}
			
		});

		
		JMenuItem getProjectItem = new JMenuItem("Get project");
		projectsMenu.add(getProjectItem);
		
		JMenuItem editProjectItem = new JMenuItem("Edit project");
		projectsMenu.add(editProjectItem);
		
		JMenuItem mntmDeleteProject = new JMenuItem("Delete project");
		projectsMenu.add(mntmDeleteProject);
		
		JMenu consultantsMenu = new JMenu("Consultants");
		vert.add(consultantsMenu);
		
		JMenuItem newConsultantItem = new JMenuItem("New consultant");
		consultantsMenu.add(newConsultantItem);
		
		JMenu usersMenu = new JMenu("Users");
		vert.add(usersMenu);
		
		JMenuItem mntmNewUser = new JMenuItem("New user");
		usersMenu.add(mntmNewUser);
		
		JMenu searchMenu = new JMenu("Find");
		vert.add(searchMenu);
		
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
