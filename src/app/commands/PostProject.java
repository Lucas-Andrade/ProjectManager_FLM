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
	
	/**
	 * Class that implements the {@link PostProject} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final ProjectRepository repository;
		private final UserRepository uRepository;
		
		public Factory(ProjectRepository repository, UserRepository uRepository)
		{
			this.repository = repository;
			this.uRepository = uRepository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostProject(repository, uRepository, parameters);
		}
	}

	public PostProject(ProjectRepository repository, UserRepository uRepository, Map<String, String> parameters) {
		super(uRepository, parameters);
		this.repository = repository; 
	}

	@Override
	protected String[] getDemandingParametres() {
		return new String[]{"latitude", "longitude", "name", "price"};
	}

	@Override
	protected void internalPostExecute(ResultOutputMethod out) throws CommandException, IOException {
		double latitude = getParameterAsDouble("latitude");
		double longitude = getParameterAsDouble("longitude");
		String name = getParameterAsString("name");
		double price = getParameterAsDouble("price");
		
		Local local = new Local(latitude, longitude, name, price);
		long pid = repository.getNextPID();
		
		Project project = new Project(local, pid);
		
		repository.addProject(project);
		out.giveResults(pid);
	}

}
