package commands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import app.elements.AppElement;
import app.elements.User;
import app.repository.UserRepository;

/**
 * Class whose instances are {@link Command}s that return the {@link User}s.
 * Caller {@code String}: GET /users
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class GetUsers extends BaseCommandResultsOutputMethod
{

	/**
	 * The {@link UserRepository} with the {@code User}s. This
	 * {@code UserRepository} is accessed to get the {@code User}s.
	 */
	private final UserRepository repository;

	/**
	 * Class that implements the {@link GetUsers} factory, according to the
	 * {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory
	{

		/**
		 * The {@link UserRepository} with the {@code User}s. This
		 * {@code UserRepository} is accessed to get the {@code User}s.
		 */
		private final UserRepository repository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param uRepository
		 *            The {@code UserRepository}.
		 */
		public Factory(UserRepository repository)
		{
			this.repository = repository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters)
		{
			return new GetUsers(repository, parameters);
		}

	}

	/**
	 * The constructor for {@code GetUsers}.
	 * 
	 * @param repository
	 *            The {@code UserRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public GetUsers(UserRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.repository = repository;
	}

	/**
	 * Gets all the {@code User}s in the {@code UserRepository}.
	 * 
	 * @return An array of {@code DatabaseElement} with all the {@code User}s in the
	 * {@code UserRepository}.
	 */
	@Override
	protected AppElement[] internalCall() throws Exception
	{
		return repository.getAll();
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters()
	{
		return new String[] {};
	}

}
