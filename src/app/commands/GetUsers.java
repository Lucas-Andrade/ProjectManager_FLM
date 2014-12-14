package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * Class whose instances are {@link Command}s that return the {@link User}s.
 */
public class GetUsers extends BaseCommand
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
		public Command newInstance(Map<String, String> parameters)
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
	 * Get's all the {@code User}s in the {@code UserRepository}.
	 * 
	 * @see BaseCommand#internalExecute(ResultOutputMethod)
	 */
	@Override
	public void internalExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		out.giveResults((Object[]) repository.getAll());
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters()
	{
		return null;
	}

}
