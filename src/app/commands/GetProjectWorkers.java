package app.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.resultsOutputMethods.ResultOutputMethod;
import utils.AWorker;
import utils.Leader;


/**
 * Class whose instances are commands that return .....
 * GET /projects/{pid}/{type} - retorna o(s) consultor(es) do tipo type (manager
 * ou consultant), do projecto identificado por pid.
 */
public class GetProjectWorkers extends BaseCommand       
{

	/**
	 * Class that implements the {@link GetProjectWorkers} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

		private final ProjectRepository repository;
		
		public Factory (ProjectRepository repository)
		{
			this.repository = repository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new GetProjectWorkers(repository, parameters);
		}
	}

	
	private final ProjectRepository projectRepository;
	private String typeWorker;
	private long projectId;
	public static final String PID = "pid";
	public static final String WTYPE = "type";
	private static final String[] DEMANDING_PARAMETERS = {PID, WTYPE };
	
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetProjectWorkers (ProjectRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.projectRepository = repository;
	}
	
	/**
	 * @see app.commands.BaseCommand#getDemandingParametres()
	 */
	@Override
	protected String[] getDemandingParametres() 
	{
		return DEMANDING_PARAMETERS;
	}

	
	//
	@Override
	protected void internalExecute(ResultOutputMethod out) throws CommandException, IOException {
		
		projectId = getParameterAsLong(PID);
		this.typeWorker = parameters.get(WTYPE);
		
		if (typeWorker.equals("Manager"))
		{
			Leader manager = projectRepository.getProjectById(projectId).getManager();
			out.giveResults(manager);
			return;
		}
		else if(typeWorker.equals("Consultant"))  
		{
			Iterable<AWorker> workers = projectRepository.getProjectById(projectId).getTeam();
			out.giveResults(workers);
			
			return;
		}
		else
			throw new IllegalArgumentException();
	}

}



