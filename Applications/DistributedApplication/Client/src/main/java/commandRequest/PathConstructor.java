package commandRequest;

import java.util.Map;

public class PathConstructor {

	
	
	public static String getUserPath(Map<String, String> params){
		String path ="GET /users/"+params.get("username")+" accept=application/json";
		return path;
	}
	
	public static String postUserPath(Map<String, String> params){
		String path ="POST /users {parameter list}"+params.get("username")+" accept=application/json";
		return path;
	}
	
	public static String portConsultantPath(Map<String, String> params){
		String path ="POST /consultants {parameter list}"+params.get("username")+" accept=application/json";
		return path;
	}
	
	public static String postWorkerInProjectPath(Map<String, String> params){
		String path ="POST /projects/"+params.get("pid")+"/{type} {parameter list}"+" accept=application/json";
		return path;
	}
	
	public static String postSubprojectPath(Map<String, String> params){
		String path ="POST /projects/" +params.get("pid")+"/subproject {parameter list}"+" accept=application/json";
		return path;
	}
	
	public static String getUsersPath(Map<String, String> params){
		String path ="GET /users/"+params.get("username")+" accept=application/json";
		return path;
	}
	
	public static String getWorkersInProjectPath(Map<String, String> params){
		String path ="GET /projects/"+params.get("pid")+"/"+params.get("type")+" accept=application/json";
		return path;
	}
	
	public static String getProjectPath(Map<String, String> params){
		String path ="GET /projects/"+params.get("pid")+" accept=application/json";
		return path;
	}
	
	public static String getSubprojectPath(Map<String, String> params){
		String path ="GET /projects/"+params.get("pid")+"/subproject accept=application/json";
		return path;
	}
	
	public static String patchUsersPath(Map<String, String> params){
		String path ="PATCH /users/"+ params.get("username")+" accept=application/json";
		return path;
	}
	
	public static String patchProjectsPath(Map<String, String> params){
		String path ="PATCH /projects/"+params.get("pid")+" accept=application/json";
		return path;
	}
	
	public static String patchConsultantPath(Map<String, String> params){
		String path ="PATCH /consultants/"+params.get("cid") +" {parameter list}"+" accept=application/json";
		return path;
	}
	
	public static String deleteProjectPath(Map<String, String> params){
		String path ="DELETE /projects/"+params.get("pid")+" accept=application/json";
		return path;
	}

	
	
	
	
}
