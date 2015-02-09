package app;

/**
 * Allows to save the username and password that the user tried to use in order
 * to authenticate. If the answer from the server is a positive one, this data can and 
 * will be used to authenticate the user.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 09/02/2015
 *
 */
public class PossibleAuthentication {

	private static String name;
	private static String password;

	/**
	 * Allows to set a name and password for later use.
	 * @param newName
	 * @param newPassword
	 */
	public static void setNameAndPassword(String newName, String newPassword) {
		name = newName;
		password = newPassword;
	}
	
	/**
	 * Allows to get the stored username. If this method is called before any username
	 * has been set, an {@code IllegalStateException} will be thrown.
	 * @return the username
	 * @throws IllegalStateException
	 */
	public static String getName() {
		if (name == null) {
			throw new IllegalStateException();
		}
		return name;
	}
	
	/**
	 * Allows to get the stored password. If this method is called before any password
	 * has been set, an {@code IllegalStateException} will be thrown.
	 * @return the password
	 * @throws IllegalStateException
	 */
	public static String getPassword() {
		if (password == null) {
			throw new IllegalStateException();
		}
		return password;
	}
	
}
