package app.framesAndPanels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSplitPane;

import app.AppUI;
import app.commands.Authentication;
import app.commands.PostProject;
import app.repositoryHolders.RepositoryHolder;

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1406565561867998589L;
	public static final int PANEL_DIVIDER_LOCATION = 120;
	
	public MainFrame(JSplitPane splitPane, RepositoryHolder repositories, Authentication authentication)
	{
		this.setTitle("Project Manager");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 420);
		splitPane.setContinuousLayout(true);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
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
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
		
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
		
		
		JPanel leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[]{119, 0};
		gbl_leftPanel.rowHeights = new int[]{284, 179, 0};
		gbl_leftPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_leftPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		leftPanel.setLayout(gbl_leftPanel);
		
		
		
		//load the image
		ClassLoader cl = AppUI.class.getClassLoader();
		Image main = new ImageIcon(cl.getResource("images/Main.png")).getImage();
		

		
		JMenuBar vert = new VerticalMenuBar();
		
	//	leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
		GridBagConstraints gbc_vert = new GridBagConstraints();
		gbc_vert.fill = GridBagConstraints.BOTH;
		gbc_vert.insets = new Insets(0, 0, 5, 0);
		gbc_vert.gridx = 0;
		gbc_vert.gridy = 0;
		leftPanel.add(vert, gbc_vert);
		
		
		JMenu projectsMenu = new JMenu("Projects");
		vert.add(projectsMenu);
		
		JMenuItem newProjectItem = new JMenuItem("New project");
		projectsMenu.add(newProjectItem);
		newProjectItem.addActionListener(new PostProject(splitPane, repositories, authentication));
		
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
		
		JMenu searchMenu = new JMenu("Search");
		vert.add(searchMenu);
			
		JLabel randstadGirlImageLabel = new JLabel("");
		randstadGirlImageLabel.setIcon(new ImageIcon(main));
		//leftPanel.add(randstadGirlImageLabel);
		GridBagConstraints gbc_randstadGirlImageLabel = new GridBagConstraints();
		gbc_randstadGirlImageLabel.anchor = GridBagConstraints.NORTH;
		gbc_randstadGirlImageLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_randstadGirlImageLabel.gridx = 0;
		gbc_randstadGirlImageLabel.gridy = 1;
		leftPanel.add(randstadGirlImageLabel, gbc_randstadGirlImageLabel);
	}
}
