package app.repository;

import app.elements.DatabaseElements;
import app.elements.User;
import app.elements.UserInterface;

public class InMemoryUserRepo extends InMemoryRepo<User> implements UserRepository {
	
	@Override
	public UserInterface getUserByUsername(String loginName)
	{
		for (DatabaseElements user : getDatabaseElements())
			if (((User) user).getLoginName().equals(loginName))
				return (UserInterface) user;

		return null;
	}

	@Override
	public UserInterface[] getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}
