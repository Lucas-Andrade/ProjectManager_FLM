package app.commands;

import java.io.IOException;
import java.util.Map;

import utils.Local;
import utils.Project;
import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * POST /projects - cria uma novo projecto, dados os seguintes parâmetros:
 * latitude - latitude do local de trabalho
 * longitude - longitude do local de trabalho
 * name - nome do local de trabalho
 * price - preço do local de trabalho
 * Este comando retorna o identificador do projecto (pid).
 */

public class PostProject extends BasePostCommand {
	
	private final ProjectRepository repository;
	
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "password";
	private static final String NAME = "name";
	private static final String PRICE = "price";
	private static final String[] DEMANDING_PARAMETERS = {LATITUDE, LONGITUDE, NAME, PRICE};
	
	/**
	 * Class that implements the {@link PostProject} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final ProjectRepository repository;
		private final UserRepository uRepository;
		
		public Factory(UserRepository uRepository, ProjectRepository repository )
		{
			this.repository = repository;
			this.uRepository = uRepository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostProject(uRepository, repository, parameters);
		}
	}

	public PostProject(UserRepository uRepository, ProjectRepository repository, Map<String, String> parameters) {
		super(uRepository, parameters);
		this.repository = repository; 
	}

	@Override
	protected String[] getMandatoryParametres() {
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected void internalPostExecute(ResultOutputMethod out) throws CommandException, IOException {
		double latitude = getParameterAsDouble(LATITUDE);
		double longitude = getParameterAsDouble(LONGITUDE);
		String name = getParameterAsString(NAME);
		double price = getParameterAsDouble(PRICE);
		
		Local local = new Local(latitude, longitude, name, price);
		long pid = repository.getNextPID();
		
		Project project = new Project(local, pid);
		
		repository.addProject(project);
		out.giveResults(pid);
	}

}
