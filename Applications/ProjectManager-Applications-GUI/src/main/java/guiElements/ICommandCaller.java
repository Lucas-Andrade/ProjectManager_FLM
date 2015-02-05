package guiElements;

public interface ICommandCaller {
	
	public void callDeleteProject(String pidString);
	public void callGetProject(String pidString);
	public void callGetSubprojects(String pidString);
	public void callGetUsers();
	public void callGetUser(String username);
	public void callGetWorkersInProject(String pid, String workerOpt);
	public void callPatchConsultant(String consultantId, String consultantName, String priceHour);
	public void callPatchProject(String pidString, String longitude, String latitude, String price, String localName);
	public void callPatchUser(String userName, String oldPassword, String newPassword);
	public void callPostConsultant(String name, String priceHour, String bonus);
	public void callPostProject(String latitude, String longitude, String name, String price);
	public void callPostUser(String username, String password, String email, String fullname);
	public void callPostSubproject(String pid, String subprojectId);
	public void callPostWorkerInProject(String pid, String cid, String worker);
	public void callAuthenticateUser(String name, String password);
}
