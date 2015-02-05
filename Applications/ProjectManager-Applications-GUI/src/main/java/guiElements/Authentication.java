package guiElements;

public class Authentication {
	
	private static boolean authenticated = false;
	private static String name;
	private static String password;
	 
	public static void authenticate(String newName, String newPassword){
		if(!authenticated){
			authenticated = true;
			name = newName;
			password = newPassword;
			GUIUtils.setLogoutButton();
		}
	}
	
	public static void unauthenticate(){
		if(authenticated){
			authenticated = false;
			name = "";
			password = "";
		}
		GUIUtils.setLoginButton();
	}
	
	public static boolean isAuthenticated(){
		return authenticated;
	}
	
	public static String getName() {
		return name;
	}
	
	public static String getPassword() {
		return password;
	}
}
