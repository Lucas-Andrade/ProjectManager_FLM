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

public class AppMainFrame {

	private static JPanel rightPanel = new JPanel();
	
	public static void setRightPanel(JPanel newRightPanel)
	{
		rightPanel = newRightPanel;
	}
	
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
		
		JMenuItem newProjectItem = new JMenuItem("New project");
		projectsMenu.add(newProjectItem);
		newProjectItem.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				new NewProject().execute();

				
//				JPanel panel = new JPanel();
//				splitPane.setRightComponent(panel);
//				JLabel lblStatus = new JLabel("    isto fez uma coisa    ");
//				panel.add(lblStatus, BorderLayout.SOUTH);
			}
			
		});

		
		JMenuItem getProjectItem = new JMenuItem("Get project");
		projectsMenu.add(getProjectItem);
		
		JMenuItem editProjectItem = new JMenuItem("Edit project");
		projectsMenu.add(editProjectItem);
		
		JMenu consultantsMenu = new JMenu("Consultants");
		vert.add(consultantsMenu);
		
		JMenuItem newConsultantItem = new JMenuItem("New consultant");
		consultantsMenu.add(newConsultantItem);
		
		JMenu usersMenu = new JMenu("Users");
		vert.add(usersMenu);
		
		JMenuItem mntmNewUser = new JMenuItem("New user");
		usersMenu.add(mntmNewUser);
		
		JMenu searchMenu = new JMenu("Search");
		vert.add(searchMenu);
		
		splitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcomeToProject = new JLabel("  Welcome to Project Manager");
		lblWelcomeToProject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rightPanel.add(lblWelcomeToProject, BorderLayout.NORTH);
		
		JScrollBar scrollBar = new JScrollBar();
		rightPanel.add(scrollBar, BorderLayout.EAST);
		
		JLabel lblStatus = new JLabel("Status: Ready");
		rightPanel.add(lblStatus, BorderLayout.SOUTH);
		
		
		
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
