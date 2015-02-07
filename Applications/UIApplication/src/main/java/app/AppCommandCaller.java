package app;

import app.domainCommands.AddConsultantToRepo;
import app.domainCommands.AddProjectToRepo;
import app.domainCommands.AddSubprojectToRepo;
import app.domainCommands.AddUserToRepo;
import app.domainCommands.AddWorkerToProjectInRepo;
import app.domainCommands.AuthenticateUser;
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
import app.publisher.ErrorPublisher;
import app.publisher.InternalAuthenticationPublish;
import app.publisher.PublishTeamToGetPanel;
import app.publisher.PublishTeamToMainFrame;
import app.publisher.PublishToErrorDialog;
import app.publisher.PublishToGetPanel;
import app.publisher.PublishToMainFrame;
import app.publisher.PublishUsersToGetPanel;
import app.publisher.PublishUsersToMainFrame;
import app.publisher.ResultsPublisher;
import app.publisher.SwingWorkerCommand;
import app.repositoryHolders.InMemoryRepositoryHolder;
import app.repositoryHolders.RepositoryHolder;
import guiElements.ICommandCaller;

public class AppCommandCaller implements ICommandCaller{

	private RepositoryHolder repositories;
	private ErrorPublisher errorPublisher;
	private ResultsPublisher resultsPublisher;
	
	public AppCommandCaller() {
		repositories = new InMemoryRepositoryHolder();
		errorPublisher = new PublishToErrorDialog();
		resultsPublisher = new PublishToMainFrame();
	}

	@Override
	public void callDeleteProject(String pidString) {
		Command command = new RemoveProjectToRepo(repositories.getProjectsRepo(), pidString);
		executeSWC(command);
	}

	@Override
	public void callGetProject(String pidString) {
		Command command = new GetProjectFromRepo(repositories.getProjectsRepo(), pidString);
		executeSWC(command, new PublishToGetPanel());
	}

	@Override
	public void callGetSubprojects(String pidString) {
		Command command = new GetSubprojectsFromRepo(repositories.getProjectsRepo(), pidString);
		executeSWC(command, new PublishToGetPanel());
	}

	@Override
	public void callGetUsers() {
		Command command = new GetAllUsersFromRepo(repositories.getUsersRepo());
		executeSWC(command, new PublishUsersToGetPanel());
	}
	
	@Override
	public void callGetUser(String username) {
		Command command = new GetUserFromRepo(repositories.getUsersRepo(), username);
		executeSWC(command, new PublishUsersToGetPanel());
	}

	@Override
	public void callGetWorkersInProject(String pid, String workerOpt) {
		Command command = new GetProjectWorkersFromRepo(repositories.getProjectsRepo(), pid, workerOpt);
		executeSWC(command, new PublishTeamToGetPanel());
	}

	@Override
	public void callPatchConsultant(String consultantId, String consultantName, String priceHour) {
		Command command = new SetConsultantPropertiesFromRepo(repositories.getWorkersRepo(), consultantId, consultantName, priceHour);
		executeSWC(command, new PublishTeamToMainFrame());
	}

	@Override
	public void callPatchProject(String pidString, String longitude, String latitude, String price, String localName) {
		Command command = new SetProjectPropertiesFromRepo(repositories.getProjectsRepo(), pidString, longitude,
				latitude, price, localName);
		executeSWC(command);
	}

	@Override
	public void callPatchUser(String userName, String oldPassword, String newPassword) {
		Command command = new SetUserPropertiesFromRepo(repositories.getUsersRepo(), userName, oldPassword,	newPassword);
		executeSWC(command, new PublishUsersToMainFrame());
	}

	@Override
	public void callPostConsultant(String name, String priceHour, String bonus) {
		Command command = new AddConsultantToRepo(repositories.getWorkersRepo(), name, priceHour, bonus);
		executeSWC(command, new PublishTeamToMainFrame());
	}

	@Override
	public void callPostProject(String latitude, String longitude, String name, String price) {
		Command command = new AddProjectToRepo(repositories.getProjectsRepo(), latitude, longitude, name, price);
		executeSWC(command);
	}

	@Override
	public void callPostUser(String username, String password, String email, String fullname) {
		Command command = new AddUserToRepo(repositories.getUsersRepo(), username, password, email, fullname);
		executeSWC(command, new PublishUsersToMainFrame());
	}

	@Override
	public void callPostSubproject(String pid, String subprojectId) {
		Command command = new AddSubprojectToRepo(repositories.getProjectsRepo(), pid, subprojectId);
		executeSWC(command);
	}

	@Override
	public void callPostWorkerInProject(String pid, String cid, String worker) {
		Command command = new AddWorkerToProjectInRepo(repositories.getProjectsRepo(), repositories.getWorkersRepo(), pid, cid, worker);
		executeSWC(command);
	}

	@Override
	public void callAuthenticateUser(String name, String password) {
		Command command = new AuthenticateUser(repositories.getUsersRepo(), name, password);
		executeSWC(command, new InternalAuthenticationPublish());
	}
	
	private void executeSWC(Command command, ResultsPublisher resultsPublisher) {
		new SwingWorkerCommand(command, resultsPublisher, errorPublisher).execute();
	}
	
	private void executeSWC(Command command){
		executeSWC(command, resultsPublisher);
	}

}
