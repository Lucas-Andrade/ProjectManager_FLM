package app.windows.mainFrameAL.mainFrame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.elements.IUser;
import app.repositoryHolders.InMemoryRepositoryHolder;
import app.repositoryHolders.RepositoryHolder;
import app.windows.commandWindowsAL.commandWindows.AuthenticationDialog;
import app.windows.commandWindowsAL.commandWindows.MainDialogFrame;
import app.windows.mainFrameAL.DeleteProjectAL;
import app.windows.mainFrameAL.GetSubprojectsAL;
import app.windows.mainFrameAL.GetUserAL;
import app.windows.mainFrameAL.GetWorkersInProjectAL;
import app.windows.mainFrameAL.PatchConsultantAL;
import app.windows.mainFrameAL.PatchProjectAL;
import app.windows.mainFrameAL.PatchUserAL;
import app.windows.mainFrameAL.PostConsultantAL;
import app.windows.mainFrameAL.PostProjectAL;
import app.windows.mainFrameAL.PostSubprojectAL;
import app.windows.mainFrameAL.PostUserAL;
import app.windows.mainFrameAL.PostWorkerAL;

public class MainFrame extends JFrame implements Authentication.AuthenticationActionListener
{

	private static final long serialVersionUID = 1406565561867998589L;
	public static final int PANEL_DIVIDER_LOCATION = 120;
	private static RepositoryHolder repositories;
	private static JSplitPane splitPane;
	
	public static void main(String[] args)
	{
		new MainFrame(new InMemoryRepositoryHolder()).setVisible(true);
	}
	
	public static JSplitPane getSplitPane(){
		return splitPane;
	}
	
	public static RepositoryHolder getRepositories(){
		return repositories;
	}

	private JLabel logo;

	@SuppressWarnings("deprecation")
	public MainFrame(RepositoryHolder repositories){

		MainFrame.splitPane = new JSplitPane();
		MainFrame.repositories = repositories;
		
		this.setTitle("Project Manager");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 420);
		splitPane.setContinuousLayout(true);

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmLogin = new JMenuItem("Login");
		mnFile.add(mntmLogin);
		mntmLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AuthenticationDialog(repositories).setVisible(true);
			}
		});

		mntmLogout = new JMenuItem("Logout");
		mnFile.add(mntmLogout);
		mntmLogout.setVisible(false);
		mntmLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Authentication.unauthenticate();
			}
		});

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelpMe = new JMenuItem("Help me!");
		mnHelp.add(mntmHelpMe);
		mntmHelpMe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpDialog().setVisible(true);
			}
		});

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutDialog().setVisible(true);
			}
		});

		this.getContentPane().setLayout(new BorderLayout(0, 0));

		this.getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblWelcomeToProject = new JLabel("  Welcome to Project Manager");
		lblWelcomeToProject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rightPanel.add(lblWelcomeToProject, BorderLayout.NORTH);

		JLabel lblStatus = new JLabel("Status: Ready");
		rightPanel.add(lblStatus, BorderLayout.SOUTH);
		
		logo = new JLabel("");
		logo.setIcon(new ImageIcon(MainDialogFrame.class.getClassLoader()
				.getResource("images/ProjectLogo400.png")));
		logo.setOpaque(false);

		rightPanel.add(logo, BorderLayout.CENTER);

		JPanel leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[] { 119, 0 };
		gbl_leftPanel.rowHeights = new int[] { 284, 179, 0 };
		gbl_leftPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_leftPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		leftPanel.setLayout(gbl_leftPanel);

		ClassLoader cl = getClass().getClassLoader();
		Image main = new ImageIcon(cl.getResource("images/Main.png"))
				.getImage();

		JMenuBar vert = new VerticalMenuBar();

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
		newProjectItem.addActionListener(new PostProjectAL(repositories));

		JMenuItem editProjectItem = new JMenuItem("Edit project");
		projectsMenu.add(editProjectItem);
		editProjectItem.addActionListener(new PatchProjectAL(repositories));

		JMenuItem mntmDeleteProject = new JMenuItem("Delete project");
		projectsMenu.add(mntmDeleteProject);
		mntmDeleteProject.addActionListener(new DeleteProjectAL(repositories));

		JMenu mnGet = new JMenu("Get");
		projectsMenu.add(mnGet);

		JMenuItem mntmGetSubprojects = new JMenuItem("Get subprojects");
		mnGet.add(mntmGetSubprojects);
		mntmGetSubprojects.addActionListener(new GetSubprojectsAL(repositories));

		JMenuItem mntmGetTeam = new JMenuItem("Get Team");
		mnGet.add(mntmGetTeam);
		mntmGetTeam.addActionListener(new GetWorkersInProjectAL(
				repositories));

		JMenuItem mntmGetManager = new JMenuItem("Get Manager");
		mnGet.add(mntmGetManager);
		mntmGetManager.addActionListener(new GetWorkersInProjectAL(
				repositories));

		JMenu mnAddToProject = new JMenu("Add to Project");
		projectsMenu.add(mnAddToProject);

		JMenuItem mntmAddConsultant = new JMenuItem("Add Consultant");
		mnAddToProject.add(mntmAddConsultant);
		mntmAddConsultant.addActionListener(new PostWorkerAL(repositories));

		JMenuItem mntmAddManager = new JMenuItem("Add Manager");
		mnAddToProject.add(mntmAddManager);
		mntmAddManager.addActionListener(new PostWorkerAL(repositories));

		JMenuItem mntmAddSubproject = new JMenuItem("Add Subproject");
		mnAddToProject.add(mntmAddSubproject);
		mntmAddSubproject.addActionListener(new PostSubprojectAL(repositories));

		JMenu consultantsMenu = new JMenu("Consultants");
		vert.add(consultantsMenu);

		JMenuItem newConsultantItem = new JMenuItem("New consultant/Manager");
		consultantsMenu.add(newConsultantItem);
		newConsultantItem.addActionListener(new PostConsultantAL(repositories));

		JMenuItem mntmEditWorker = new JMenuItem("Edit worker");
		consultantsMenu.add(mntmEditWorker);
		mntmEditWorker.addActionListener(new PatchConsultantAL(repositories));

		JMenuItem mntmAddWorker = new JMenuItem("Add worker to project");
		consultantsMenu.add(mntmAddWorker);
		mntmAddWorker.addActionListener(new PostWorkerAL(repositories));

		JMenuItem mntmGetConsultant = new JMenuItem("Get worker(s) in project");
		consultantsMenu.add(mntmGetConsultant);
		mntmGetConsultant.addActionListener(new GetWorkersInProjectAL(
				repositories));

		JMenu usersMenu = new JMenu("Users");
		vert.add(usersMenu);

		JMenuItem mntmNewUser = new JMenuItem("New user");
		usersMenu.add(mntmNewUser);
		mntmNewUser.addActionListener(new PostUserAL(repositories));

		JMenuItem mntmGetUser = new JMenuItem("Get user");
		usersMenu.add(mntmGetUser);
		mntmGetUser.addActionListener(new GetUserAL(repositories));

		JMenuItem mntmEditUser = new JMenuItem("Edit user");
		usersMenu.add(mntmEditUser);
		mntmEditUser.addActionListener(new PatchUserAL(repositories));

		JMenu searchMenu = new JMenu("Search");
		vert.add(searchMenu);
		JMenuItem mntmProjects = new JMenuItem("Subprojects in project");
		
		searchMenu.add(mntmProjects);
		mntmProjects.addActionListener(new GetSubprojectsAL(repositories));

		JMenuItem mntmConsultants = new JMenuItem("Consultants/Manager in project");
		searchMenu.add(mntmConsultants);
		mntmConsultants.addActionListener(new GetWorkersInProjectAL(repositories));

		JMenuItem mntmUsers = new JMenuItem("User");
		searchMenu.add(mntmUsers);
		mntmUsers.addActionListener(new GetUserAL(repositories));

		JLabel randstadGirlImageLabel = new JLabel("");
		randstadGirlImageLabel.setIcon(new ImageIcon(main));
		GridBagConstraints gbc_randstadGirlImageLabel = new GridBagConstraints();
		gbc_randstadGirlImageLabel.anchor = GridBagConstraints.NORTH;
		gbc_randstadGirlImageLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_randstadGirlImageLabel.gridx = 0;
		gbc_randstadGirlImageLabel.gridy = 1;
		leftPanel.add(randstadGirlImageLabel, gbc_randstadGirlImageLabel);

		Authentication.addActionListener(this);
	}

	JMenu mnFile;
	JMenuItem mntmLogin;
	JMenuItem mntmLogout;

	@Override
	public void actionPerformed(boolean isAuthenticated, IUser authenticatedUser)
	{
		if (isAuthenticated)
		{
			mntmLogin.setVisible(false);
			mntmLogout.setText("Logout " + authenticatedUser.getLoginName());
			mntmLogout.setVisible(true);
		}
		else
		{
			mntmLogin.setVisible(true);
			mntmLogout.setVisible(false);
			mntmLogout.setText("Logout");
		}
	}

}