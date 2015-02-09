package guiElements;

public class Authentication {
	
	private static boolean authenticated = false;
	private static String name;
	private static String password;
	private static String possibleName;
	private static String possiblePassword;
	 
	public static void authenticate(String newName, String newPassword){
		if(!authenticated){
			authenticated = true;
			name = newName;
			password = newPassword;
			GUIUtils.setLogoutButton();
		}
	}
	
	public static void setPossibleAuthentification(String name, String password) {
		possibleName = name;
		possiblePassword = password;
	}
	
	public static void authenticatePossible() {
		if(possibleName == null || possiblePassword == null) {
			throw new IllegalStateException();
		}
		
		if(!authenticated){
			authenticated = true;
			name = possibleName;
			password = possiblePassword;
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
