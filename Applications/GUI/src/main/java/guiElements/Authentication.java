package guiElements;

/**
 * Stores the data of an authenticated {@code User}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 *
 */
public class Authentication {
	
	/**
	 * Flag that indicates whether there is an authenticated {@code User} or not.
	 */
	private static boolean authenticated = false;
	
	/**
	 * The {@code username} of the authenticated {@code User}
	 */
	private static String name = "";
	
	/**
	 * The {@code password} of the authenticated {@code User}
	 */
	private static String password = "";
	
	/**
	 * The {@code username} of an {@code User} who tried to autenticate, but the
	 * authentication is pending on confirmation.
	 */
	private static String possibleName;
	
	/**
	 * The {@code password} of an {@code User} who tried to autenticate, but the
	 * authentication is pending on confirmation.
	 */
	private static String possiblePassword;
	
	/**
	 * Authenticates the {@code User} with the {@code username} and {@code password}
	 * passed as parameter.
	 * 
	 * @param newName
	 * 		- the {@code username} of the {@code User} to authenticate
	 * @param newPassword
	 * 		- the {@code password} of the {@code User} to authenticate
	 */
	public static void authenticate(String newName, String newPassword){
		if(!authenticated){
			authenticated = true;
			name = newName;
			password = newPassword;
			GUIUtils.setLogoutButton();
		} else {
			throw new IllegalStateException();
		}
	}
	
	/**
	 * Sets the {@code username} and {@code password} passed as parameter to the {@code possibleName}
	 * and {@code possiblePassword} fields, respectively. This merely sets those fields, and does not
	 * authenticate the {@code User}.
	 * 
	 * @param name
	 * 		- Is set as the {@code possibleName}
	 * @param password
	 * 		- Is set as the {@code possiblePassword}
	 */
	public static void setPossibleAuthentification(String name, String password) {
		possibleName = name;
		possiblePassword = password;
	}
	
	/**
	 * Authenticates the {@code User} whose {@code username} and {@code password} were
	 * saved in the {@code possibleName} and {@code possiblePassword} fields, respectively.
	 */
	public static void authenticatePossible() {
		if("".equals(possibleName) || "".equals(possiblePassword)) {
			throw new IllegalStateException();
		}
		
		if(!authenticated){
			authenticated = true;
			name = possibleName;
			password = possiblePassword;
			GUIUtils.setLogoutButton();
		}
	}
	
	/**
	 * Removes the data of the authenticated user, if any, and sets the {@code authenticated} 
	 * flag to {@code false} again.
	 */
	public static void unauthenticate(){
		if(authenticated){
			authenticated = false;
			name = "";
			password = "";
		}
		GUIUtils.setLoginButton();
	}
	
	/**
	 * Returns the value of the {@code authenticated} flag.
	 * @return {@code true} if there is an authenticated user.
	 * @return {@code false} if there is no authenticated user.
	 */
	public static boolean isAuthenticated(){
		return authenticated;
	}
	
	/**
	 * @return the {@code username} of the authenticated {@code User}. If there is no authenticated
	 * 		user an empty {@code String} will be returned.
	 */
	public static String getName() {
		return name;
	}
	
	/**
	 * @return the {@code password} of the authenticated {@code User}. If there is no authenticated
	 * 		user an empty {@code String} will be returned.
	 */
	public static String getPassword() {
		return password;
	}
}
