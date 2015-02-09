package app;

import app.swingWorkerAndPublisher.ErrorPublisher;
import app.swingWorkerAndPublisher.ErrorPublisherWithLoadingDialog;
import app.swingWorkerAndPublisher.PublishToGetPanel;
import app.swingWorkerAndPublisher.PublishToMainFrame;
import app.swingWorkerAndPublisher.ResultsPublisher;
import app.swingWorkerAndPublisher.SwingWorkerCommand;
import commandRequest.GetHttpRequest;
import guiElements.Authentication;
import guiElements.ICommandCaller;

public class AppCommandCaller implements ICommandCaller{
	
	private String requestURL;
	private ResultsPublisher mainFramePublisher;
	private PublishToGetPanel getPanelPublisher;
	private ErrorPublisher errorPublisher;
	
	public AppCommandCaller(String requestURL) {
		this.requestURL = requestURL;
		errorPublisher = new ErrorPublisherWithLoadingDialog();
		mainFramePublisher = new PublishToMainFrame();
		getPanelPublisher = new PublishToGetPanel();
	}

	@Override
	public void callDeleteProject(String pidString) {
		StringBuilder path = new StringBuilder();
		path.append("DELETE /projects/").append(pidString)
			.append(loginAndFormat());
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), mainFramePublisher, errorPublisher);
	}

	@Override
	public void callGetProject(String pidString) {
		StringBuilder path = new StringBuilder();
		path.append("GET /projects/").append(pidString)
			.append(loginAndFormat());
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), getPanelPublisher, errorPublisher);
	}

	@Override
	public void callGetSubprojects(String pidString) {
		StringBuilder path = new StringBuilder();
		path.append("GET /projects/").append(pidString).append("/subproject")
			.append(loginAndFormat());
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), getPanelPublisher, errorPublisher);
	}

	@Override
	public void callGetUsers() {
		StringBuilder path = new StringBuilder();
		path.append("GET /users")
			.append(loginAndFormat());
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), getPanelPublisher, errorPublisher);
	}

	@Override
	public void callGetUser(String username) {
		StringBuilder path = new StringBuilder();
		path.append("GET /users/").append(username)
			.append(loginAndFormat());
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), getPanelPublisher, errorPublisher);
	}

	@Override
	public void callGetWorkersInProject(String pid, String workerOpt) {
		StringBuilder path = new StringBuilder();
		path.append("GET /projects/").append(pid).append("/").append(workerOpt)
			.append(loginAndFormat());
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), getPanelPublisher, errorPublisher);
	}

	@Override
	public void callPatchConsultant(String consultantId, String consultantName,
			String priceHour) {
		StringBuilder path = new StringBuilder();
		path.append("PATCH /consultants/").append(consultantId)
			.append(loginAndFormat());
		
		if(consultantId != null && !"".equals(consultantId)) {
			path.append("&name=").append(consultantId);
		}
		if(priceHour != null && !"".equals(priceHour)) {
			path.append("&priceHour=").append(priceHour);
		}
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), mainFramePublisher, errorPublisher);
	}

	@Override
	public void callPatchProject(String pidString, String longitude,
			String latitude, String price, String localName) {
		StringBuilder path = new StringBuilder();
		path.append("PATCH /projects/").append(pidString)
			.append(loginAndFormat());
		
		if(longitude != null && !"".equals(longitude)) {
			path.append("&longitude=").append(longitude);
		}
		
		if(latitude != null && !"".equals(latitude)) {
			path.append("&latitude=").append(latitude);
		}
		
		if(price != null && !"".equals(price)) {
			path.append("&price=").append(price);
		}
		
		if(localName != null && !"".equals(localName)) {
			path.append("&name=").append(localName);
		}
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), mainFramePublisher, errorPublisher);
	}

	@Override
	public void callPatchUser(String userName, String oldPassword,
			String newPassword) {
		StringBuilder path = new StringBuilder();
		path.append("PATCH /users/").append(userName)
			.append(loginAndFormat())
			.append("&oldPassword=").append(oldPassword)
			.append("&newPassword=").append(newPassword);
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), mainFramePublisher, errorPublisher);
	}

	@Override
	public void callPostConsultant(String name, String priceHour, String bonus) {
		StringBuilder path = new StringBuilder();
		path.append("POST /consultants")
			.append(loginAndFormat())
			.append("&name=").append(name).append("&priceHour=").append(priceHour);
		
		if(bonus != null && !"".equals(bonus)) {
			path.append("&bonus=").append(bonus);
		}
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), mainFramePublisher, errorPublisher);
	}

	@Override
	public void callPostProject(String latitude, String longitude, String name,
			String price) {
		StringBuilder path = new StringBuilder();
		path.append("POST /projects")
			.append(loginAndFormat())
			.append("&latitude=").append(latitude).append("&longitude=").append(longitude)
			.append("&name=").append(name).append("&price").append(price);
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), mainFramePublisher, errorPublisher);
	}

	@Override
	public void callPostUser(String username, String password, String email,
			String fullname) {
		StringBuilder path = new StringBuilder();
		path.append("POST /users")
			.append(loginAndFormat())
			.append("&username=").append(username).append("&password=").append(password)
			.append("&email=").append(email);
		
		if(fullname != null && !"".equals(fullname)) {
			path.append("&fullname=").append(fullname);
		}
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), mainFramePublisher, errorPublisher);
	}

	@Override
	public void callPostSubproject(String pid, String subprojectId) {
		StringBuilder path = new StringBuilder();
		path.append("POST /projects/").append(pid).append("/subproject")
			.append(loginAndFormat())
			.append("&subPid=").append(subprojectId);
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), mainFramePublisher, errorPublisher);
	}

	@Override
	public void callPostWorkerInProject(String pid, String cid, String worker) {
		StringBuilder path = new StringBuilder();
		path.append("POST /projects/").append(pid).append("/").append(worker)
			.append(loginAndFormat())
			.append("&cid=").append(cid);
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), mainFramePublisher, errorPublisher);
	}

	@Override
	public void callAuthenticateUser(String name, String password) {
		StringBuilder path = new StringBuilder();
		path.append("POST /authenticate")
			.append(" loginName=").append(name)
			.append("&loginPassword=").append(password)
			.append(jsonFormat());
		
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), mainFramePublisher, errorPublisher);
		PossibleAuthentication.setNameAndPassword(name, password);
	}

	private String loginAndFormat() {
		
		StringBuilder builder = new StringBuilder();
		builder.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword())
			.append(jsonFormat());
		
		return builder.toString();
	}
	
	private String jsonFormat() {
		return "accept=application/json";
	}
	
	private void executeCommand(StringBuilder path, ResultsPublisher publisher) {
		new SwingWorkerCommand(new GetHttpRequest(requestURL, path.toString()), publisher, errorPublisher);
	}
}
