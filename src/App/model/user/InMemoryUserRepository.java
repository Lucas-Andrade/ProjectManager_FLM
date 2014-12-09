package App.model.user;

import App.model.InMemoryRepo;

/**
 * @author amiguinhos do Maia
 *
 */
public class InMemoryUserRepository extends InMemoryRepo<UserInterface> implements
		UserRepository
{

	@Override
	public UserInterface getUserByLoginName(String loginName)
	{
		for (UserInterface user : super.getDatabaseElements())
			if (user.getLoginName().equals(loginName))
				return user;

		return null;
	}

}
