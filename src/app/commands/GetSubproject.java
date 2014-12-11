package app.commands;

import java.io.IOException;
import java.util.Map;

import utils.Project;
import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * GET /projects/{pid}/subprojects - retorna todos os subprojectos do projecto
 * identificado por pid, com clara distinção entre projectos e subprojectos.
 */
public class GetSubproject extends BaseCommand implements Command 
{
	private final ProjectRepository repository;
	
	public static final String pathholderParameter = "pid";

	/**
	 * Class that implements the {@link GetSubproject} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final ProjectRepository repository;
		
		public Factory(ProjectRepository repository)
		{
			this.repository = repository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new GetSubproject(repository, parameters);
		}
	}

	public GetSubproject(ProjectRepository repository, Map<String, String> parameters) {
		super(parameters);
		this.repository = repository;
	}

	@Override
	protected String[] getDemandingParametres() {
		return new String[]{pathholderParameter};
	}

	@Override
	protected void internalExecute(ResultOutputMethod out) throws CommandException, IOException 
	{
		Project project = repository.getProjectById(getParameterAsLong(pathholderParameter));
		out.giveResults(project.getContainerProject());
	}

}
