package guiElements;

public class Authentication {
	
	private static boolean authenticated = false;
	private static String name;
	 
	public static void authenticate(String newName){
		if(!authenticated){
			authenticated = true;
			name = newName;
			GUIUtils.setLogoutButton();
		}
	}
	
	public static void unauthenticate(){
		if(authenticated){
			authenticated = false;
			name = "";
		}
		GUIUtils.setLoginButton();
	}
	
	public static boolean isAuthenticated(){
		return authenticated;
	}
	
	public static String getName() {
		return name;
	}
}
