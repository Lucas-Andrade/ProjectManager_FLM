package app.commands;

import java.util.Map;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void internalExecute(ResultOutputMethod out)
			throws CommandException {
		// TODO Auto-generated method stub
		
	}

}
