package app.commands;

import java.io.IOException;
import java.util.Map;

import utils.AWorker;
import utils.Leader;
import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.resultsOutputMethods.ResultOutputMethod;


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

		private final ProjectRepository pRepository;
		
		public Factory (ProjectRepository pRepository)
		{
			this.pRepository = pRepository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new GetProjectWorkers(pRepository, parameters);
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
	private GetProjectWorkers (ProjectRepository pRepository, Map<String, String> parameters)
	{
		super(parameters);
		this.projectRepository = pRepository;
	}
	
	/**
	 * @see app.commands.BaseCommand#getDemandingParametres()
	 */
	@Override
	protected String[] getDemandingParametres() 
	{
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected void internalExecute(ResultOutputMethod out) throws CommandException, IOException {
		
		projectId = getParameterAsLong(PID);
		this.typeWorker = getParameterAsString(WTYPE);
		
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
			out.giveResults("Unrecognised type of worker.");
	}

}



