package app;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import utils.Team;

/**
 * Class for Project Manager. Create a container of projects (named MainProject) how store all the
 * Main projects existent in the Company.
 * 
 * <p>
 * Commands:
 * <li>ADD_PROJECT: add a Main Project.
 * <li>ADD_SUBPROJECT: add a subproject, to a project/subproject.
 * <li>ADD_CONSULTANT: add a consultant to a project/subproject.
 * <li>REMOVE_PROJECT: remove a subproject from a project/subproject.
 * <li>REMOVE_CONSULTANT: remove a consultant from a project/subproject.
 * <li>PROJECT_COSTS: calculate and print in the command line the costs of a project/subproject.
 * <li>WRITE_INFO: write in a .txt file all the information of project/subproject. The name of the
 * file is the name of that project/subproject. Contains the information of the Manager, Local,
 * Team, and all of its subprojects.
 * <li>END_APP: terminates the application.
 * 
 * 
 * <p>
 * Public methods:
 * 
 * <li>execute: Ask for a command and execute it, till the END_APP command is called.
 * <li>main: run the app.
 * 
 * <p>
 * Private methods:
 * 
 * <li>ReadProject: ask all project information (manager, local, team).
 * <li>ReadTeam: ask for the Team name, Leader name, payment per hour, working hours and bonus.
 * <li>ReadManager: ask for the Manager name, payment per hour, working hours and bonus.
 * <li>ReadLocal : ask for the Localization, Address and Costs.
 * <li>ReadConsultant : ask for the Consult name , payment per hour, working hours.
 * <li>writeInfo: responsible for print project information into a text file.
 * <li>GetProjectCosts: Print to console the information about project costs.
 * <li>removeConsultant: Responsible to Remove a Consultant how belong to a Team
 * <li>removeProject: Responsible to Remove a project or a subproject.
 * <li>addConsultant: Add a Consultant in a team
 * <li>addSubProject: Add a subproject
 * <li>findProject:Find a Main Project contained in a container.
 * 
 * @author Filipa E., Filipa G., Gonçalo C., José O. -> First Version
 * @author Daniel G., Filipe M., Pedro A. -> Second Version
 * 
 * @since 6/11/2014
 */

public class App {

	private Scanner scanner = new Scanner(System.in);

	private int numberProjects = 0;
	private int numberSubProjects = 0;

	private Collection<Project> Mainprojects = new TreeSet<Project>();

	private static final String ADD_PROJECT = "Add a Main Project -> Select 1";
	private static final String ADD_SUBPROJECT = "Add a Subproject -> Select 2";
	private static final String ADD_CONSULTANT = "Add a Consultant -> Select 3";
	private static final String REMOVE_PROJECT = "Remove a Project -> Select 4";
	private static final String REMOVE_CONSULTANT = "Remove a Consultant -> Select 5";
	private static final String PROJECT_COSTS = "Get costs -> Select 6";
	private static final String WRITE_INFO = "info -> Select 7";
	private static final String END_APP = "exit -> Select 8";

	private String[] commands = {ADD_PROJECT, ADD_SUBPROJECT, ADD_CONSULTANT, REMOVE_PROJECT,
			REMOVE_CONSULTANT, PROJECT_COSTS, WRITE_INFO, END_APP};

	/**
	 * Ask for a command and execute it, till the END_APP command is called.
	 */
	public void execute() throws FileNotFoundException {

		System.out.println("*********************************");
		System.out.println("********** JAVA COMPANY *********");
		System.out.println("*********************************");

		do {

			System.out.println("*********************************");
			System.out.println("nº Main Projects ->" + numberProjects);
			System.out.println("nº Subprojects -> " + numberSubProjects);
			System.out.println("*********************************");

			System.out.println("   **** Select an Option **** ");
			System.out.println("*********************************");

			for (String element : commands)
				System.out.println("->" + element);

			switch (scanner.nextInt()) {

				case 1:
					scanner.nextLine();
					Mainprojects.add(ReadProject());
					++numberProjects;
					break;
				case 2:
					scanner.nextLine();
					addSubProject();
					break;
				case 3:
					scanner.nextLine();
					addConsultant();
					break;
				case 4:
					scanner.nextLine();
					removeProject();
					break;
				case 5:
					scanner.nextLine();
					removeConsultant();
					break;
				case 6:
					scanner.nextLine();
					GetProjectCosts();
					break;
				case 7:
					scanner.nextLine();
					writeInfo();
					break;
				case 8:
					scanner.nextLine();
					return;
				default:
					System.out.println("Invalid Command. Please Insert a Valid Command");
			}

		} while (true);

	}

	/**
	 * run the app.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		App app = new App();
		app.execute();
	}

	/**
	 * This method is responsible for print in one text file all information of one project.
	 */
	private void writeInfo() throws FileNotFoundException {

		System.out.print("Which Main Project are you interested ?");
		String MainProject = scanner.nextLine();

		System.out.print("Write all information of the Main project/subproject with the name: ");
		String projectName = scanner.nextLine();

		try (PrintWriter writer = new PrintWriter(projectName + ".txt")) {
			writer.println((findProject(MainProject).getSubProjectByName(projectName))
					.toString());
		} catch (NullPointerException npe) {
			System.out.println("The project/subproject do not exist!");
		}

	}

	/**
	 * This method is responsible for print to the console costs of a specific project.
	 */
	private void GetProjectCosts() {

		System.out.print("Which Main Project are you interested ?");
		String MainProject = scanner.nextLine();

		System.out.print("Calculate the costs of the Main project/subproject with the name: ");
		String projectName = scanner.nextLine();

		try {
			System.out.println("Project costs: "
					+ findProject(MainProject).getSubProjectByName(projectName).getCost());
		} catch (NullPointerException npe) {
			System.out.println("The project/subproject do not exist!");
		}

	}

	/**
	 * This method is responsible for remove a Consultant of a team
	 */
	private void removeConsultant() {

		System.out.print("Remove a Consultant from the Main Project with the name: ");
		String MainProject = scanner.nextLine();

		System.out.print("Remove a Consultant from the Project/SubProject with the name: ");
		String projectName = scanner.nextLine();

		System.out.print("Consultant name -> ");
		try {
			(findProject(MainProject).getSubProjectByName(projectName)).removeAWorker(scanner
					.nextLine());
		} catch (NullPointerException npe) {
			System.out.println("The project/subproject do not exist!");
		}
	}

	/**
	 * This method is responsible for remove a project of a Sub-project / project
	 */
	private void removeProject() {

		System.out.print("Remove a SubProject from the Main Project with the name: ");
		String MainProject = scanner.nextLine();

		System.out.print("Remove a Subproject from the Project/SubProject with the name: ");
		String projectName = scanner.nextLine();

		System.out.print("Subproject name -> ");
		try {
			(findProject(MainProject).getSubProjectByName(projectName)).removeProject(scanner
					.nextLine());
		} catch (NullPointerException npe) {
			System.out.println("The project/subproject do not exist!");
		}
	}

	/**
	 * This method is responsible for adding a consultant in a team
	 */
	private void addConsultant() {

		while (true) {
			try {
				System.out.print("Add a consultant to Main Project with the name: ");
				String MainProject = scanner.nextLine();

				System.out.print("Add a consultant to the project/subproject with the name: ");
				String projectName = scanner.nextLine();

				(findProject(MainProject).getSubProjectByName(projectName))
						.addWorker(ReadConsultant());
				System.out.println();

				System.out.println("That subproject has already been added to this project!");

			} catch (NullPointerException npe) {
				System.out.println("The project/subproject do not exist!");
				break;
			}
			System.out.print("Do you want to add a new consultant? if yes write 'y' ");
			if (!scanner.nextLine().equals("y"))
				break;

		}
	}

	/**
	 * This method is responsible for adding a subproject to a Sub-project / project
	 */
	private void addSubProject() {

		while (true) {
			try {
				System.out.println("Specify the Main Project: ");
				String ProjectBase = scanner.nextLine();
				System.out.println("Add subproject to the project/subproject with the name: ");
				String addedToThisProject = scanner.nextLine();

				(findProject(ProjectBase).getSubProjectByName(addedToThisProject))
						.addProject(ReadProject());
				++numberSubProjects;

				System.out.println("That subproject has already been added to this project!");
			}

			catch (NullPointerException npe) {
				System.out.println("The project/subproject do not exist!");
				break;
			}

			System.out.print("Do you want to add a new subproject? if yes write 'y'  ");

			if (!scanner.nextLine().equals("y"))
				break;
		}
	}

	/**
	 * This method search a Main Project by the name.
	 * 
	 * @return new Project - The result of the search.
	 */
	private Project findProject(String projectToFind) {

		for (Project element : Mainprojects)
			if (element.getName().equals(projectToFind))
				return element;
		return null;
	}

	/**
	 * This method reads the information necessary to create a new object of type Consultant.
	 * 
	 * @return new Consultant - The subject of Consultant type is characterized by its name, the
	 *         value of his Payment per hour, and the number of hours of work.
	 */
	private Consultant ReadConsultant() {

		System.out.print("Consultante Name: ");
		String ConsultantName = scanner.nextLine();

		System.out.print("Consultante Cost By Hour: ");
		double ConsultantCost = scanner.nextDouble();

		System.out.print("Consultante Worked Hours: ");
		double ConsultantHours = scanner.nextDouble();

		System.out.println();
		return new Consultant(ConsultantName, ConsultantCost, ConsultantHours);

	}

	/**
	 * This method reads the information necessary to create a new object of type Project.
	 * 
	 * @return new Project - This object is characterized by its name , manager , Local and work
	 *         team
	 */
	private Project ReadProject() {

		String projectName;
		Local localization;
		Leader manager;
		Team team;

		System.out.println("-----------");
		System.out.println("New Project");
		System.out.println("-----------");
		System.out.print("Define the Project Name -> ");
		projectName = scanner.nextLine();
		localization = ReadLocal();

		System.out.println("----------------------------------");

		manager = ReadManager();

		System.out.println("----------------------------------");

		team = ReadTeam();

		return new Project(projectName, localization, manager, team);

	}

	/**
	 * This method reads the information necessary to create a new object of type Local.
	 * 
	 * @return new Local - The subject of local type is characterized by its name , location and
	 *         cost .
	 */
	private Local ReadLocal() {

		System.out.println("----------------------------------------");
		System.out.println("Specify The Localization Caractertistics");
		System.out.println("----------------------------------------");
		System.out.print("Project Localization -> ");
		String LocalName = scanner.nextLine();

		System.out.print("Project Adress -> ");
		String LocalAdress = scanner.nextLine();

		System.out.print("Local Cost -> ");
		double LocalCost = scanner.nextDouble();

		return new Local(LocalName, LocalAdress, LocalCost);

	}

	/**
	 * This method reads the information necessary to create a new object of type Leader with the
	 * all characteristics.
	 * 
	 * @return new Leader - The subject of Leader type, in this case, is characterized by its name,
	 *         the value of his Payment per hour, the number of hours of work and the value of his
	 *         bonus prize.
	 */
	private Leader ReadManager() {

		System.out.println("---------------------------------");
		System.out.println("Specify The Manager Id and salary");
		System.out.println("---------------------------------");

		scanner.nextLine();

		System.out.print("Manager Name -> ");
		String ManagerName = scanner.nextLine();

		System.out.print("Manager Cost by Hour  -> ");
		double ManagerCostsByHour = scanner.nextDouble();

		System.out.print("Manager Worked Hours -> ");
		double ManagerWorkedHours = scanner.nextDouble();

		System.out.print("Manager Bonus  -> ");
		double ManagerBonus = scanner.nextDouble();

		return new Leader(ManagerName, ManagerCostsByHour, ManagerWorkedHours, ManagerBonus);
	}

	/**
	 * This method reads the information necessary to create a new object of type Team.
	 * 
	 * @return new Tea, - The subject of local type is characterized by its name and a Leader.
	 */
	private Team ReadTeam() {

		System.out.println("---------------------------------");
		System.out.println("Specify The Team Caractertistics ");
		System.out.println("---------------------------------");
		scanner.nextLine();
		System.out.print("Team Name -> ");
		String TeamName = scanner.nextLine();

		System.out.print("Leader Name -> ");
		String LeaderName = scanner.nextLine();

		System.out.print("Leader Cost by Hour -> ");
		double LeaderCost = scanner.nextDouble();

		System.out.print("Leader Worked Hours -> ");
		double LeaderWorkedHours = scanner.nextDouble();

		System.out.print("Leader Bonus -> ");
		double LeaderBonus = scanner.nextDouble();
		scanner.nextLine();
		return new Team(TeamName,
				new Leader(LeaderName, LeaderCost, LeaderWorkedHours, LeaderBonus));

	}

}