package app;

import publishers.ErrorPublisher;
import publishers.InternalAuthenticationPublish;
import publishers.PublishToErrorDialog;
import publishers.PublishToGetPanelAsTable;
import publishers.PublishToGetPanelAsTree;
import publishers.PublishToMainFrameAsTable;
import publishers.PublishToMainFrameAsTree;
import publishers.ResultsPublisher;
import app.domainCommands.AddConsultantToRepo;
import app.domainCommands.AddProjectToRepo;
import app.domainCommands.AddSubprojectToRepo;
import app.domainCommands.AddUserToRepo;
import app.domainCommands.AddWorkerToProjectInRepo;
import app.domainCommands.UserAuthentication;
import app.domainCommands.Command;
import app.domainCommands.GetAllUsersFromRepo;
import app.domainCommands.GetProjectFromRepo;
import app.domainCommands.GetProjectWorkersFromRepo;
import app.domainCommands.GetSubprojectsFromRepo;
import app.domainCommands.GetUserFromRepo;
import app.domainCommands.RemoveProjectToRepo;
import app.domainCommands.SetConsultantPropertiesFromRepo;
import app.domainCommands.SetProjectPropertiesFromRepo;
import app.domainCommands.SetUserPropertiesFromRepo;
import app.repositoryHolders.InMemoryRepositoryHolder;
import app.repositoryHolders.RepositoryHolder;
import guiElements.Authentication;
import guiElements.ICommandCaller;

/**
 * Class which methods call of the available commands on the
 * userInterface.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class AppCommandCaller implements ICommandCaller{

	private RepositoryHolder repositories;
	private ErrorPublisher errorPublisher;
	
	public AppCommandCaller() {
		repositories = new InMemoryRepositoryHolder();
		errorPublisher = new PublishToErrorDialog();
	}

	/**
	 * {@see guiElements.ICommandCaller#callDeleteProject(String)}
	 */
	@Override
	public void callDeleteProject(String pidString) {
		Command command = new RemoveProjectToRepo(repositories.getProjectsRepo(), pidString);
		executeSWC(command, new PublishToMainFrameAsTree());
	}

	/**
	 * {@see guiElements.ICommandCaller#callGetProject(String)}
	 */
	@Override
	public void callGetProject(String pidString) {
		Command command = new GetProjectFromRepo(repositories.getProjectsRepo(), pidString);
		executeSWC(command, new PublishToGetPanelAsTree());
	}

	/**
	 * {@see guiElements.ICommandCaller#callGetSubprojects(String)} 
	 */
	@Override
	public void callGetSubprojects(String pidString) {
		Command command = new GetSubprojectsFromRepo(repositories.getProjectsRepo(), pidString);
		executeSWC(command, new PublishToGetPanelAsTree());
	}

	/**
	 * {@see guiElements.ICommandCaller#callGetUsers()}
	 */
	@Override
	public void callGetUsers() {
		Command command = new GetAllUsersFromRepo(repositories.getUsersRepo());
		executeSWC(command, new PublishToGetPanelAsTable());
	}
	
	/**
	 * {@see guiElements.ICommandCaller#callGetUser(String)}
	 */
	@Override
	public void callGetUser(String username) {
		Command command = new GetUserFromRepo(repositories.getUsersRepo(), username);
		executeSWC(command, new PublishToGetPanelAsTable());
	}

	/**
	 * {@see guiElements.ICommandCaller#callGetWorkersInProject(String, String)}
	 */
	@Override
	public void callGetWorkersInProject(String pid, String workerOpt) {
		Command command = new GetProjectWorkersFromRepo(repositories.getProjectsRepo(), pid, workerOpt);
		executeSWC(command, new PublishToGetPanelAsTable());
	}

	/**
	 * {@see guiElements.ICommandCaller#callPatchConsultant(String, String, String)}
	 */
	@Override
	public void callPatchConsultant(String consultantId, String consultantName, String priceHour) {
		Command command = new SetConsultantPropertiesFromRepo(repositories.getWorkersRepo(), consultantId, consultantName, priceHour);
		executeSWC(command, new PublishToMainFrameAsTable());
	}

	/**
	 * {@see guiElements.ICommandCaller#callPatchProject(String, String, String, String, String)}
	 */
	@Override
	public void callPatchProject(String pidString, String longitude, String latitude, String price, String localName) {
		Command command = new SetProjectPropertiesFromRepo(repositories.getProjectsRepo(), pidString, longitude,
				latitude, price, localName);
		executeSWC(command, new PublishToMainFrameAsTree());
	}

	/**
	 * {@see guiElements.ICommandCaller#callPatchUser(String, String, String)}
	 */
	@Override
	public void callPatchUser(String userName, String oldPassword, String newPassword) {
		Command command = new SetUserPropertiesFromRepo(repositories.getUsersRepo(), userName, oldPassword,	newPassword);
		executeSWC(command, new PublishToMainFrameAsTable());
	}

	/**
	 * {@see guiElements.ICommandCaller#callPostConsultant(String, String, String)}
	 */
	@Override
	public void callPostConsultant(String name, String priceHour, String bonus) {
		Command command = new AddConsultantToRepo(repositories.getWorkersRepo(), name, priceHour, bonus);
		executeSWC(command, new PublishToMainFrameAsTable());
	}

	/**
	 * {@see guiElements.ICommandCaller#callPostProject(String, String, String, String)}
	 */
	@Override
	public void callPostProject(String latitude, String longitude, String name, String price) {
		Command command = new AddProjectToRepo(repositories.getProjectsRepo(), latitude, longitude, name, price);
		executeSWC(command, new PublishToMainFrameAsTree());
	}

	/**
	 * {@see guiElements.ICommandCaller#callPostUser(String, String, String, String)}
	 */
	@Override
	public void callPostUser(String username, String password, String email, String fullname) {
		Command command = new AddUserToRepo(repositories.getUsersRepo(), username, password, email, fullname);
		executeSWC(command, new PublishToMainFrameAsTable());
	}

	/**
	 * {@see guiElements.ICommandCaller#callPostSubproject(String, String)}
	 */
	@Override
	public void callPostSubproject(String pid, String subprojectId) {
		Command command = new AddSubprojectToRepo(repositories.getProjectsRepo(), pid, subprojectId);
		executeSWC(command, new PublishToMainFrameAsTree());
	}

	/**
	 * {@see guiElements.ICommandCaller#callPostWorkerInProject(String, String, String)}
	 */
	@Override
	public void callPostWorkerInProject(String pid, String cid, String worker) {
		Command command = new AddWorkerToProjectInRepo(repositories.getProjectsRepo(), repositories.getWorkersRepo(), pid, cid, worker);
		executeSWC(command, new PublishToMainFrameAsTree());
	}

	/**
	 * {@see guiElements.ICommandCaller#callAuthenticateUser(String, String)}
	 */
	@Override
	public void callAuthenticateUser(String name, String password) {
		Authentication.setPossibleAuthentification(name, password);
		Command command = new UserAuthentication(repositories.getUsersRepo(), name, password);
		executeSWC(command, new InternalAuthenticationPublish());
	}
	
	/**
	 * Method responsible for instantiating the {@code Command} and for
	 * executing it in a new {@link SwingWorkerCommand} with the publisher
	 * {@link ResultsPublisher} that publish information about the expected
	 * results (when it does not occur any errors of any kind).
	 * 
	 * @param command
	 * @param resultsPublisher
	 */
	private void executeSWC(Command command, ResultsPublisher resultsPublisher) {
		new SwingWorkerCommand(command, resultsPublisher, errorPublisher).execute();
	}

}
