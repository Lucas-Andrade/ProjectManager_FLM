package app;

import guiElements.Authentication;
import guiElements.ICommandCaller;

import java.text.MessageFormat;

import publishers.ErrorPublisher;
import publishers.InternalAuthenticationPublish;
import publishers.PublishToErrorDialog;
import publishers.PublishToGetPanelAsTable;
import publishers.PublishToGetPanelAsTree;
import publishers.PublishToMainFrameAsTable;
import publishers.PublishToMainFrameAsTree;
import commandRequest.DeleteHttpRequest;
import commandRequest.GetHttpRequest;
import commandRequest.PatchHttpRequest;
import commandRequest.PostHttpRequest;

/**
 * Class whose methods build the path needed to make the request to the server
 * for each available command.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class AppCommandCaller implements ICommandCaller {

	private String LOCALHOST = null;
	private String PORT;
	private String requestURL;
	private ErrorPublisher errorPublisher;

	public AppCommandCaller(String host, String port) {
		LOCALHOST = host;
		PORT = port;
		errorPublisher = new PublishToErrorDialog();
	}

	private String requestURL() {
		requestURL = MessageFormat.format("http://{0}:{1}", LOCALHOST, PORT);

		return requestURL;

	}

	/**
	 * Method responsible for build the path of {@code DeleteProject} command,
	 * with the parameters received at the user interface and needed to make the
	 * request to the server. DELETE /projects/{pid} {loginName, loginPassord}.
	 * This command will delete the project with the specify {@code ProjectId}.
	 * 
	 * @param pidString
	 *            {@code String} with the {@code Project}ID argument's name.
	 */
	@Override
	public void callDeleteProject(String pidString) {
		StringBuilder path = new StringBuilder();
		path.append("/projects/").append(pidString).append("?")
				.append(loginAndFormat());

		new SwingWorkerCommand(new DeleteHttpRequest(requestURL(),
				path.toString()), new PublishToMainFrameAsTree(),
				errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code GetProject} command, with
	 * the parameters received at the user interface and needed to make the
	 * request to the server. GET /projects/{pid} {loginName, loginPassord}.
	 * This command will return the information of the project with the specify
	 * {@code ProjectId}.
	 * 
	 * @param pidString
	 *            {@code String} with the {@code Project}ID argument's name.
	 */
	@Override
	public void callGetProject(String pidString) {
		StringBuilder path = new StringBuilder();
		path.append("/projects/").append(pidString).append("?")
				.append(loginAndFormat());

		new SwingWorkerCommand(
				new GetHttpRequest(requestURL(), path.toString()),
				new PublishToGetPanelAsTree(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code GetSubprojects}command,
	 * with the parameters received at the user interface and needed to make the
	 * request to the server. GET /projects/{pid}/subproject
	 * {loginName,loginPassord}. This command will return the information of all
	 * subprojects of a project with the specify {@code ProjectId}.
	 * 
	 * @param pidString
	 *            {@code String} with the {@code Project}ID argument's name.
	 */
	@Override
	public void callGetSubprojects(String pidString) {
		StringBuilder path = new StringBuilder();
		path.append("/projects/").append(pidString).append("/subproject")
				.append("?").append(loginAndFormat());

		new SwingWorkerCommand(
				new GetHttpRequest(requestURL(), path.toString()),
				new PublishToGetPanelAsTree(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code GetUsers}command, with
	 * the parameters received at the user interface and needed to make the
	 * request to the server. GET /users {loginName, loginPassord}. This command
	 * will return the information of all users in the User Repository
	 */
	@Override
	public void callGetUsers() {
		StringBuilder path = new StringBuilder();
		path.append("/users").append("?").append(loginAndFormat());

		new SwingWorkerCommand(
				new GetHttpRequest(requestURL(), path.toString()),
				new PublishToGetPanelAsTable(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code GetUser}command, needed
	 * to make the request to the server. GET /users/{username}
	 * {loginName,loginPassord} This command will return the information of the
	 * user with the specify {@code username} of the User Repository
	 * 
	 * @param username
	 *            {@code String} with the {@code User}'s Username argument.
	 */
	@Override
	public void callGetUser(String username) {
		StringBuilder path = new StringBuilder();
		path.append("/users/").append(username).append("?")
				.append(loginAndFormat());

		new SwingWorkerCommand(
				new GetHttpRequest(requestURL(), path.toString()),
				new PublishToGetPanelAsTable(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code GetWorkersInProject}
	 * command, with the parameters received at the user interface and needed to
	 * make the request to the server. GET /projects/{pid}/{type}
	 * {loginName,loginPassord}: This command will return the information of all
	 * consultants or of the Manager of a project with the specify
	 * {@code ProjectId}.
	 * 
	 * @param pidString
	 *            The {@code PID} of the {@code Project} from which we want to
	 *            get the {@code Team}.
	 * @param workerOpt
	 *            The type of worker to be obtained: {@code consultant} or
	 *            {@code Leader}.
	 */
	@Override
	public void callGetWorkersInProject(String pid, String workerOpt) {
		StringBuilder path = new StringBuilder();
		path.append("/projects/").append(pid).append("/").append(workerOpt)
				.append("?").append(loginAndFormat());

		new SwingWorkerCommand(
				new GetHttpRequest(requestURL(), path.toString()),
				new PublishToGetPanelAsTable(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code PatchConsultant}command,
	 * with the parameters received at the user interface and needed to make the
	 * request to the server. PATCH /consultants/{cid} {loginName,loginPassord,
	 * name, priceHour} This command will updates the information of the
	 * consultant with the specify {@code WorkerId}.
	 * 
	 * @param consultantId
	 *            The {@code CID} of the {@code Consultant} whose properties are
	 *            to be modified.
	 * @param consultantName
	 *            The new {@code name} of the {@code Consultant}.
	 * @param priceHour
	 *            The new {@code priceHour} of the {@code Consultant}.
	 */
	@Override
	public void callPatchConsultant(String consultantId, String consultantName,
			String priceHour) {
		StringBuilder path = new StringBuilder();
		path.append("/consultants/").append(consultantId);

		StringBuilder parameters = new StringBuilder();
		parameters.append(loginAndFormat());

		if (consultantId != null && !"".equals(consultantId)) {
			parameters.append("&name=").append(consultantId);
		}
		if (priceHour != null && !"".equals(priceHour)) {
			parameters.append("&priceHour=").append(priceHour);
		}

		new SwingWorkerCommand(new PatchHttpRequest(requestURL(),
				path.toString(), parameters.toString()),
				new PublishToMainFrameAsTable(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code PatchProject}command,
	 * with the parameters received at the user interface and needed to make the
	 * request to the server. PATCH /projects/{pid} {loginName, loginPassord,
	 * longitude, latitude, price, name}: This command will update the
	 * information of the project identified by the specify {@code ProjectId}.
	 * 
	 * @param pidString
	 *            The {@code PID} of the {@code Project} which properties are to
	 *            be altered
	 * @param longitudeString
	 *            The new {@code longitude}
	 * @param latitudeString
	 *            The new {@code latitude}
	 * @param priceString
	 *            The new {@code Local}'s {@code price}
	 * @param localName
	 *            The new {@code Local}'s {@code name}
	 */
	@Override
	public void callPatchProject(String pidString, String longitude,
			String latitude, String price, String localName) {
		StringBuilder path = new StringBuilder();
		path.append("/projects/").append(pidString);

		StringBuilder parameters = new StringBuilder();
		parameters.append(loginAndFormat());

		if (longitude != null && !"".equals(longitude)) {
			parameters.append("&longitude=").append(longitude);
		}

		if (latitude != null && !"".equals(latitude)) {
			parameters.append("&latitude=").append(latitude);
		}

		if (price != null && !"".equals(price)) {
			parameters.append("&price=").append(price);
		}

		if (localName != null && !"".equals(localName)) {
			parameters.append("&name=").append(localName);
		}

		new SwingWorkerCommand(new PatchHttpRequest(requestURL(),
				path.toString(), parameters.toString()),
				new PublishToMainFrameAsTree(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code PatchUser}command, with
	 * the parameters received at the user interface and needed to make the
	 * request to the server. PATCH /users/{username} {loginName, loginPassord,
	 * oldPassword, newPassword} This command will updates the password of the
	 * user identified by the specify {@code username}.
	 * 
	 * @param userName
	 *            The {@code username} of the {@code User} whose password is to
	 *            be changed
	 * @param oldPassword
	 *            The {@code User}'s current password
	 * @param newPassword
	 *            The {@code User}'s new password (must have at least 4
	 *            characters)
	 */
	@Override
	public void callPatchUser(String userName, String oldPassword,
			String newPassword) {
		StringBuilder path = new StringBuilder();
		path.append("/users/").append(userName);

		StringBuilder parameters = new StringBuilder();
		parameters.append(loginAndFormat()).append("&oldPassword=")
				.append(oldPassword).append("&newPassword=")
				.append(newPassword);

		new SwingWorkerCommand(new PatchHttpRequest(requestURL(),
				path.toString(), parameters.toString()),
				new PublishToMainFrameAsTable(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code PostConsultant}command,
	 * with the parameters received at the user interface and needed to make the
	 * request to the server. POST /consultants {loginName, loginPassord,
	 * name,priceHour,bonus}: This command will add a consultant to the Worker
	 * Repository.
	 * 
	 * @param name
	 *            The {@code name} of the new {@code AWorker}.
	 * @param priceHour
	 *            The price per hour of the new {@code AWorker}.
	 * @param bonus
	 *            The bonus of the new {@code AWorker}, if it happens to be a
	 *            {@code Leader}. If {@code null} or an empty {@code String} is
	 *            introduced in this parameter, a {@code Consultant} will be
	 *            constructed instead.
	 */
	@Override
	public void callPostConsultant(String name, String priceHour, String bonus) {
		StringBuilder path = new StringBuilder();
		path.append("/consultants");

		StringBuilder parameters = new StringBuilder();
		parameters.append(loginAndFormat()).append("&name=").append(name)
				.append("&priceHour=").append(priceHour);

		if (bonus != null && !"".equals(bonus)) {
			parameters.append("&bonus=").append(bonus);
		}

		new SwingWorkerCommand(new PostHttpRequest(requestURL(),
				path.toString(), parameters.toString()),
				new PublishToMainFrameAsTable(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code PostProject} command,
	 * with the parameters received at the user interface and needed to make the
	 * request to the server. POST /projects {loginName, loginPassord,
	 * latitude,longitude, name, price}. This command will add a Project to the
	 * Project repository.
	 * 
	 * @param latitude
	 *            The {@code latitude} of the new {@code Project}.
	 * @param longitude
	 *            The {@code longitude} of the new {@code Project}.
	 * @param name
	 *            The name of the new {@code Project}'s {@code Local}.
	 * @param price
	 *            The {@code price} of the {@code Project}'s {@code Local}.
	 */
	@Override
	public void callPostProject(String latitude, String longitude, String name,
			String price) {
		StringBuilder path = new StringBuilder();
		path.append("/projects");

		StringBuilder parameters = new StringBuilder();
		parameters.append(loginAndFormat()).append("&latitude=")
				.append(latitude).append("&longitude=").append(longitude)
				.append("&name=").append(name).append("&price=").append(price);

		new SwingWorkerCommand(new PostHttpRequest(requestURL(),
				path.toString(), parameters.toString()),
				new PublishToMainFrameAsTree(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code PostUser}command, with
	 * the parameters received at the user interface and needed to make the
	 * request to the server. POST /users/{username}
	 * {loginName,loginPassord,username, password, email, fullname}. This
	 * command will updates the password of the user identified by the specify
	 * {@code username}.
	 * 
	 * @param userName
	 *            The {@code username} of the {@code User} whose password is to
	 *            be changed
	 * @param oldPassword
	 *            The {@code User}'s current password
	 * @param newPassword
	 *            The {@code User}'s new password (must have at least 4
	 *            characters)
	 */
	@Override
	public void callPostUser(String username, String password, String email,
			String fullname) {
		StringBuilder path = new StringBuilder();
		path.append("/users");

		StringBuilder parameters = new StringBuilder();
		parameters.append(loginAndFormat()).append("&username=")
				.append(username).append("&password=").append(password)
				.append("&email=").append(email);

		if (fullname != null && !"".equals(fullname)) {
			parameters.append("&fullname=").append(fullname);
		}

		new SwingWorkerCommand(new PostHttpRequest(requestURL(),
				path.toString(), parameters.toString()),
				new PublishToMainFrameAsTable(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code PostSubproject} command,
	 * with the parameters received at the user interface and needed to make the
	 * request to the server. POST /projects/{pid}/subproject
	 * {loginName,loginPassord,subPid} : This command will add a subproject to a
	 * projects/subprojects.
	 * 
	 * @param pidString
	 *            The {@code PID} of the {@code Project} to which will be added
	 *            a subproject.
	 * @param subPidString
	 *            The {@code PID} of the {@code Project} that will be added as a
	 *            subproject.
	 */
	@Override
	public void callPostSubproject(String pid, String subprojectId) {
		StringBuilder path = new StringBuilder();
		path.append("/projects/").append(pid).append("/subproject");

		StringBuilder parameters = new StringBuilder();
		parameters.append(loginAndFormat()).append("&subPid=")
				.append(subprojectId);

		new SwingWorkerCommand(new PostHttpRequest(requestURL(),
				path.toString(), parameters.toString()),
				new PublishToMainFrameAsTree(), errorPublisher).execute();
	}

	/**
	 * Method responsible for build the path of {@code PostWorkerInProject}
	 * command, with the parameters received at the user interface and needed to
	 * make the request to the server. POST /projects/{pid}/{type}
	 * {loginName,loginPassord, cid} This command will add a consultant or
	 * Manager to a project/subproject.
	 * 
	 * @param pidString
	 *            The {@code PID} of the {@code Project}.
	 * @param cidString
	 *            The {@code CID} if the {@code AWorker}.
	 * @param workerType
	 *            The type of the worker to be added: {@code "consultant"} or
	 *            {@code "manager"}.
	 */
	@Override
	public void callPostWorkerInProject(String pid, String cid, String worker) {
		StringBuilder path = new StringBuilder();
		path.append("/projects/").append(pid).append("/").append(worker);

		StringBuilder parameters = new StringBuilder();
		parameters.append(loginAndFormat()).append("&cid=").append(cid);

		new SwingWorkerCommand(new PostHttpRequest(requestURL(),
				path.toString(), parameters.toString()),
				new PublishToMainFrameAsTree(), errorPublisher).execute();
	}

	/**
	 * Method responsible for call the application domain
	 * {@code AuthenticateUser} Command, with the required parameters.
	 * 
	 * @param username
	 *            The {@code loginName} of the new {@code User}.
	 * @param password
	 *            The {@code loginPassword} of the new {@code User}. It must
	 *            have at least four characters.
	 */
	@Override
	public void callAuthenticateUser(String name, String password) {
		Authentication.setPossibleAuthentification(name, password);
		StringBuilder path = new StringBuilder();
		path.append("/authenticate").append("?").append("loginName=")
				.append(name).append("&loginPassword=").append(password);

		new SwingWorkerCommand(
				new GetHttpRequest(requestURL(), path.toString()),
				new InternalAuthenticationPublish(), errorPublisher).execute();
	}

	private String loginAndFormat() {

		StringBuilder builder = new StringBuilder();
		builder.append("loginName=").append(Authentication.getName())
				.append("&loginPassword=").append(Authentication.getPassword());

		return builder.toString();
	}

}
