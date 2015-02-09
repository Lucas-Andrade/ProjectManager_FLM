package app;

public class PossibleAuthentication {

	private static String name;
	private static String password;

	public static void setNameAndPassword(String newName, String newPassword) {
		name = newName;
		password = newPassword;
	}
	
	public static String getName() {
		if (name == null) {
			throw new IllegalStateException();
		}
		return name;
	}
	
	public static String getPassword() {
		if (password == null) {
			throw new IllegalStateException();
		}
		return password;
	}
	
}
