package mainFrameAL;

public class Authentication {
	
	private static boolean authenticated = false;
	private static String name;
	 
	public static void authenticate(String newName){
		if(!authenticated){
			authenticated = true;
			name = newName;
		}
	}
	
	public static void unauthenticate(){
		if(authenticated){
			authenticated = false;
			name = "";
		}
	}
	
	public static boolean isAuthenticated(){
		return authenticated;
	}
	
	public static String getName() {
		return name;
	}
}
