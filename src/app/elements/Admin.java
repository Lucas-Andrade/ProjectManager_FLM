package app.elements;

public class Admin extends User{

	public Admin() {
		super("admin", "admin", "admin@administration.com", "Administator");
	}

	@Override
	public String getLoginPassword()
	{
		return "";
	}
}
