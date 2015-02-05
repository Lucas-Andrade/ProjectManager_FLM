package app;

import guiElements.Authentication;
import guiElements.ICommandCaller;

public class AppCommandCaller implements ICommandCaller{
	
	//o que estes métodos vão fazer é enviar para o servidor os dados retirados da janela
	//para cada command
	
	@Override
	public void callDeleteProject(String pidString) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE /projects/").append(pidString)
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword());
	}

	@Override
	public void callGetProject(String pidString) {
		StringBuilder builder = new StringBuilder();
		builder.append("GET /projects/").append(pidString)
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword());
	}

	@Override
	public void callGetSubprojects(String pidString) {
		StringBuilder builder = new StringBuilder();
		builder.append("GET /projects/").append(pidString).append("/subproject")
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword());
	}

	@Override
	public void callGetUsers() {
		StringBuilder builder = new StringBuilder();
		builder.append("GET /users")
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword());
	}

	@Override
	public void callGetUser(String username) {
		StringBuilder builder = new StringBuilder();
		builder.append("GET /users/").append(username)
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword());
	}

	@Override
	public void callGetWorkersInProject(String pid, String workerOpt) {
		StringBuilder builder = new StringBuilder();
		builder.append("GET /projects/").append(pid).append("/").append(workerOpt)
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword());
	}

	@Override
	public void callPatchConsultant(String consultantId, String consultantName,
			String priceHour) {
		StringBuilder builder = new StringBuilder();
		builder.append("PATCH /consultants/").append(consultantId)
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword());
		
		if(consultantId != null && !"".equals(consultantId)) {
			builder.append("&name=").append(consultantId);
		}
		if(priceHour != null && !"".equals(priceHour)) {
			builder.append("&priceHour=").append(priceHour);
		}
	}

	@Override
	public void callPatchProject(String pidString, String longitude,
			String latitude, String price, String localName) {
		StringBuilder builder = new StringBuilder();
		builder.append("PATCH /projects/").append(pidString)
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword());
		
		if(longitude != null && !"".equals(longitude)) {
			builder.append("&longitude=").append(longitude);
		}
		
		if(latitude != null && !"".equals(latitude)) {
			builder.append("&latitude=").append(latitude);
		}
		
		if(price != null && !"".equals(price)) {
			builder.append("&price=").append(price);
		}
		
		if(localName != null && !"".equals(localName)) {
			builder.append("&name=").append(localName);
		}
	}

	@Override
	public void callPatchUser(String userName, String oldPassword,
			String newPassword) {
		StringBuilder builder = new StringBuilder();
		builder.append("PATCH /users/").append(userName)
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword())
			.append("&oldPassword=").append(oldPassword)
			.append("&newPassword=").append(newPassword);
	}

	@Override
	public void callPostConsultant(String name, String priceHour, String bonus) {
		StringBuilder builder = new StringBuilder();
		builder.append("POST /consultants")
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword())
			.append("&name=").append(name).append("&priceHour=").append(priceHour);
		
		if(bonus != null && !"".equals(bonus)) {
			builder.append("&bonus=").append(bonus);
		}
	}

	@Override
	public void callPostProject(String latitude, String longitude, String name,
			String price) {
		StringBuilder builder = new StringBuilder();
		builder.append("POST /projects")
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword())
			.append("&latitude=").append(latitude).append("&longitude=").append(longitude)
			.append("&name=").append(name).append("&price").append(price);
	}

	@Override
	public void callPostUser(String username, String password, String email,
			String fullname) {
		StringBuilder builder = new StringBuilder();
		builder.append("POST /users")
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword())
			.append("&username=").append(username).append("&password=").append(password)
			.append("&email=").append(email);
		
		if(fullname != null && !"".equals(fullname)) {
			builder.append("&fullname=").append(fullname);
		}
	}

	@Override
	public void callPostSubproject(String pid, String subprojectId) {
		StringBuilder builder = new StringBuilder();
		builder.append("POST /projects/").append(pid).append("/subproject")
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword())
			.append("&subPid=").append(subprojectId);
	}

	@Override
	public void callPostWorkerInProject(String pid, String cid, String worker) {
		StringBuilder builder = new StringBuilder();
		builder.append("POST /projects/").append(pid).append("/").append(worker)
			.append(" loginName=").append(Authentication.getName())
			.append("&loginPassword=").append(Authentication.getPassword())
			.append("&cid=").append(cid);
	}

	@Override
	public void callAuthenticateUser(String name, String password) {
		StringBuilder builder = new StringBuilder();
		builder.append("POST /authenticate")
			.append(" loginName=").append(name)
			.append("&loginPassword=").append(password);
	}

}
