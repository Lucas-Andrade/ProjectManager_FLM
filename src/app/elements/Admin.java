package app.elements;

public class Admin extends User{

	public Admin(String username, String password) {
		super(username, password, "admin_" + username + "@administration.com", "Administator");
	}
}
