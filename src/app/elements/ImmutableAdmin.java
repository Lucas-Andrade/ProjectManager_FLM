package app.elements;

import app.commands.BasePostCommand;

/**
 * Class that represents the {@code User} created by the time the method
 * {@link InMemoryUserRepo#removeAll()} is used. {@code AppProjectManager} needs
 * at least one {@code User} in the {@link UserRepository} or it won't be
 * possible to use {@link BasePostCommand}s.
 */
public class ImmutableAdmin extends Admin
{

	/**
	 * The constructor of {@code ImmutableAdmin}.
	 */
	public ImmutableAdmin()
	{
		super("admin", "admin");
	}

}
