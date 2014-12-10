package app.repository;

import app.elements.UserInterface;


/**
 * @author amiguinhos do Maia
 *
 */
public class InMemoryUserRepository extends InMemoryRepo<UserInterface> implements
		UsersRepository
{

	@Override
	public UserInterface getUserByUsername(String loginName)
	{
		for (UserInterface user : super.getDatabaseElements())
			if (user.getLoginName().equals(loginName))
				return user;

		return null;
	}

}
