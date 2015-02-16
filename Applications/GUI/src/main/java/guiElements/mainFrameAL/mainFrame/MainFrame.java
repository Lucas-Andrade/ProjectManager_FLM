package guiElements.mainFrameAL.mainFrame;

import guiElements.Authentication;
import guiElements.commandWindowsAL.commandWindows.AuthenticationDialog;
import guiElements.mainFrameAL.DeleteProjectAL;
import guiElements.mainFrameAL.GetProjectsAL;
import guiElements.mainFrameAL.GetSubprojectsAL;
import guiElements.mainFrameAL.GetUserAL;
import guiElements.mainFrameAL.GetWorkersInProjectAL;
import guiElements.mainFrameAL.PatchConsultantAL;
import guiElements.mainFrameAL.PatchProjectAL;
import guiElements.mainFrameAL.PatchUserAL;
import guiElements.mainFrameAL.PostConsultantAL;
import guiElements.mainFrameAL.PostProjectAL;
import guiElements.mainFrameAL.PostSubprojectAL;
import guiElements.mainFrameAL.PostUserAL;
import guiElements.mainFrameAL.PostWorkerAL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * This class defines the main frame of the main application.
 * Here are define all the main application components
 * This is the place where the repositories are storage.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1406565561867998589L;
	public static final int PANEL_DIVIDER_LOCATION = 120;
	private static JSplitPane splitPane;
	
	/**
	 * @return splitPane  so we can format it later
	 */
	public static JSplitPane getSplitPane(){
		return splitPane;
	}

	/**
	 * Set cursor to wait mode
	 */
	public static void setWaitCursor() {
		splitPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	/**
	 * Set cursor to wait mode
	 */
	public static void setNormalCursor() {
		splitPane.setCursor(Cursor.getDefaultCursor());
	}
	
	private JLabel logo;
	JMenu mnFile;
	JMenuItem mntmLogin;
	JMenuItem mntmLogout;
	
	public void setLoginButton() {
		mntmLogin.setVisible(true);
		mntmLogout.setVisible(false);
		mntmLogin.updateUI();
	}
	
	public void setLogoutButton() {
		mntmLogin.setVisible(false);
		mntmLogout.setVisible(true);
		mntmLogin.updateUI();
	}

	public MainFrame(){

		MainFrame.splitPane = new JSplitPane();
		this.setBackground(new Color(176, 196, 222));
		
		this.setTitle("Project Manager");
		try {
			this.setIconImage(ImageIO.read(MainFrame.class.getClassLoader().getResource("images/Pm48x48.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(850, 500);
		splitPane.setContinuousLayout(true);
		splitPane.setBackground(new Color(176, 196, 222));

		//horizontal menu bar
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menuBar.setBackground(new Color(102, 153, 204));

		mnFile = new JMenu("File");
		mnFile.setBackground(new Color(176, 196, 222));
		menuBar.add(mnFile);

		mntmLogin = new JMenuItem("Login");
		mntmLogin.setBackground(new Color(176, 196, 222));
		mnFile.add(mntmLogin);
		mntmLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AuthenticationDialog().setVisible(true);
			}
		});
	
		mntmLogout = new JMenuItem("Logout");
		mntmLogout.setBackground(new Color(176, 196, 222));
		mnFile.add(mntmLogout);
		mntmLogout.setVisible(false);
		mntmLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Authentication.unauthenticate();
			}
		});

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setBackground(new Color(176, 196, 222));
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setBackground(new Color(176, 196, 222));
		menuBar.add(mnHelp);

		JMenuItem mntmHelpMe = new JMenuItem("Help me!");
		mntmHelpMe.setBackground(new Color(176, 196, 222));
		mnHelp.add(mntmHelpMe);
		mntmHelpMe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpDialog().setVisible(true);
			}
		});

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setBackground(new Color(176, 196, 222));
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutDialog().setVisible(true);
			}
		});

		this.getContentPane().setLayout(new BorderLayout(0, 0));

		this.getContentPane().add(splitPane, BorderLayout.CENTER);

		
		// result panel
		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));

		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[] { this.getWidth()-this.getWidth()/4 };
		gbl_rightPanel.rowHeights = new int[] { this.getHeight()/3 , this.getHeight()/3 };
		rightPanel.setLayout(gbl_rightPanel);
		
		JLabel lblWelcomeToProject = new JLabel("Welcome to Project Manager");
		lblWelcomeToProject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_WelcomeToProject = new GridBagConstraints();
		gbc_WelcomeToProject.fill = GridBagConstraints.NONE;
		gbc_WelcomeToProject.anchor = GridBagConstraints.NORTHEAST;
		gbc_WelcomeToProject.gridx = 0;
		gbc_WelcomeToProject.gridy = 0;
		rightPanel.add(lblWelcomeToProject, gbc_WelcomeToProject);

		logo = new JLabel("");
		logo.setIcon(new ImageIcon(MainFrame.class.getClassLoader()
				.getResource("images/Logo400_.png")));
		logo.setOpaque(false);
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.fill = GridBagConstraints.NONE;
		gbc_WelcomeToProject.anchor = GridBagConstraints.EAST;
		gbc_logo.gridx = 0;
		gbc_logo.gridy = 1;
		rightPanel.add(logo, gbc_logo);

		rightPanel.setBackground(new Color(176, 196, 222));

		JPanel leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[] { 119, 0 };
		gbl_leftPanel.rowHeights = new int[] { 284, 179, 0 };
		gbl_leftPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_leftPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		leftPanel.setLayout(gbl_leftPanel);
		leftPanel.setBackground(new Color(176, 196, 222));

		ClassLoader cl = getClass().getClassLoader();
		Image main = new ImageIcon(cl.getResource("images/Main.png"))
				.getImage();

		
		// vertical menu bar
		JMenuBar vert = new VerticalMenuBar();
		vert.setBackground(new Color(102, 153, 204));

		GridBagConstraints gbc_vert = new GridBagConstraints();
		gbc_vert.fill = GridBagConstraints.BOTH;
		gbc_vert.insets = new Insets(0, 0, 5, 0);
		gbc_vert.gridx = 0;
		gbc_vert.gridy = 0;
		leftPanel.add(vert, gbc_vert);

		JMenu projectsMenu = new JMenu("Projects");
		vert.add(projectsMenu);

		JMenuItem newProjectItem = new JMenuItem("New project");
		newProjectItem.setBackground(new Color(176, 196, 222));
		projectsMenu.add(newProjectItem);
		newProjectItem.addActionListener(new PostProjectAL());

		JMenuItem editProjectItem = new JMenuItem("Edit project");
		editProjectItem.setBackground(new Color(176, 196, 222));
		projectsMenu.add(editProjectItem);
		editProjectItem.addActionListener(new PatchProjectAL());

		JMenuItem mntmDeleteProject = new JMenuItem("Delete project");
		mntmDeleteProject.setBackground(new Color(176, 196, 222));
		projectsMenu.add(mntmDeleteProject);
		mntmDeleteProject.addActionListener(new DeleteProjectAL());

		JMenu mnGet = new JMenu("Get");
		mnGet.setOpaque(true);
		mnGet.setBackground(new Color(176, 196, 222));
		projectsMenu.add(mnGet);

		JMenuItem mntmGetProjects = new JMenuItem("Get project");
		mntmGetProjects.setBackground(new Color(176, 196, 222));
		mnGet.add(mntmGetProjects);
		mntmGetProjects.addActionListener(new GetProjectsAL());
		
		JMenuItem mntmGetSubprojects = new JMenuItem("Get subprojects");
		mntmGetSubprojects.setBackground(new Color(176, 196, 222));
		mnGet.add(mntmGetSubprojects);
		mntmGetSubprojects.addActionListener(new GetSubprojectsAL());

		JMenuItem mntmGetTeam = new JMenuItem("Get Team");
		mntmGetTeam.setBackground(new Color(176, 196, 222));
		mnGet.add(mntmGetTeam);
		mntmGetTeam.addActionListener(new GetWorkersInProjectAL());

		JMenuItem mntmGetManager = new JMenuItem("Get Manager");
		mntmGetManager.setBackground(new Color(176, 196, 222));
		mnGet.add(mntmGetManager);
		mntmGetManager.addActionListener(new GetWorkersInProjectAL());

		JMenu mnAddToProject = new JMenu("Add to Project");
		mnAddToProject.setOpaque(true);
		mnAddToProject.setBackground(new Color(176, 196, 222));
		projectsMenu.add(mnAddToProject);

		JMenuItem mntmAddConsultant = new JMenuItem("Add Consultant");
		mntmAddConsultant.setBackground(new Color(176, 196, 222));
		mnAddToProject.add(mntmAddConsultant);
		mntmAddConsultant.addActionListener(new PostWorkerAL());

		JMenuItem mntmAddManager = new JMenuItem("Add Manager");
		mntmAddManager.setBackground(new Color(176, 196, 222));
		mnAddToProject.add(mntmAddManager);
		mntmAddManager.addActionListener(new PostWorkerAL());

		JMenuItem mntmAddSubproject = new JMenuItem("Add Subproject");
		mntmAddSubproject.setBackground(new Color(176, 196, 222));
		mnAddToProject.add(mntmAddSubproject);
		mntmAddSubproject.addActionListener(new PostSubprojectAL());

		JMenu consultantsMenu = new JMenu("Consultants");
		consultantsMenu.setBackground(new Color(176, 196, 222));
		vert.add(consultantsMenu);

		JMenuItem newConsultantItem = new JMenuItem("New consultant/Manager");
		newConsultantItem.setBackground(new Color(176, 196, 222));
		consultantsMenu.add(newConsultantItem);
		newConsultantItem.addActionListener(new PostConsultantAL());

		JMenuItem mntmEditWorker = new JMenuItem("Edit worker");
		mntmEditWorker.setBackground(new Color(176, 196, 222));
		consultantsMenu.add(mntmEditWorker);
		mntmEditWorker.addActionListener(new PatchConsultantAL());

		JMenuItem mntmAddWorker = new JMenuItem("Add worker to project");
		mntmAddWorker.setBackground(new Color(176, 196, 222));
		consultantsMenu.add(mntmAddWorker);
		mntmAddWorker.addActionListener(new PostWorkerAL());

		JMenuItem mntmGetConsultant = new JMenuItem("Get worker(s) in project");
		mntmGetConsultant.setBackground(new Color(176, 196, 222));
		consultantsMenu.add(mntmGetConsultant);
		mntmGetConsultant.addActionListener(new GetWorkersInProjectAL());

		JMenu usersMenu = new JMenu("Users");
		usersMenu.setBackground(new Color(176, 196, 222));
		vert.add(usersMenu);

		JMenuItem mntmNewUser = new JMenuItem("New user");
		mntmNewUser.setBackground(new Color(176, 196, 222));
		usersMenu.add(mntmNewUser);
		mntmNewUser.addActionListener(new PostUserAL());

		JMenuItem mntmGetUser = new JMenuItem("Get user");
		mntmGetUser.setBackground(new Color(176, 196, 222));
		usersMenu.add(mntmGetUser);
		mntmGetUser.addActionListener(new GetUserAL());

		JMenuItem mntmEditUser = new JMenuItem("Edit user");
		mntmEditUser.setBackground(new Color(176, 196, 222));
		usersMenu.add(mntmEditUser);
		mntmEditUser.addActionListener(new PatchUserAL());

		JMenu searchMenu = new JMenu("Search");
		searchMenu.setBackground(new Color(176, 196, 222));
		vert.add(searchMenu);
		
		JMenuItem mntmProjects = new JMenuItem("Subprojects in project");
		mntmProjects.setBackground(new Color(176, 196, 222));
		searchMenu.add(mntmProjects);
		mntmProjects.addActionListener(new GetSubprojectsAL());

		JMenuItem mntmConsultants = new JMenuItem("Consultants/Manager in project");
		mntmConsultants.setBackground(new Color(176, 196, 222));
		searchMenu.add(mntmConsultants);
		mntmConsultants.addActionListener(new GetWorkersInProjectAL());

		JMenuItem mntmUsers = new JMenuItem("User");
		mntmUsers.setBackground(new Color(176, 196, 222));
		searchMenu.add(mntmUsers);
		mntmUsers.addActionListener(new GetUserAL());

		JLabel randstadGirlImageLabel = new JLabel("");
		randstadGirlImageLabel.setIcon(new ImageIcon(main));
		GridBagConstraints gbc_randstadGirlImageLabel = new GridBagConstraints();
		gbc_randstadGirlImageLabel.anchor = GridBagConstraints.NORTH;
		gbc_randstadGirlImageLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_randstadGirlImageLabel.gridx = 0;
		gbc_randstadGirlImageLabel.gridy = 1;
		leftPanel.add(randstadGirlImageLabel, gbc_randstadGirlImageLabel);

		pack();
	}

	

}
