package app.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import utils.AWorker;
import utils.Leader;


/**
 * GET /projects/{pid}/{type} - retorna o(s) consultor(es) do tipo type (manager
 * ou consultant), do projecto identificado por pid.
 */
public class GetProjectWorkers extends BaseCommand implements Command      
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
	private final long projectId;
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetProjectWorkers (ProjectRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.projectRepository = repository;
		final String ID = "id";
		this.projectId = Long.parseLong(parameters.get("{pid}"));
		this.typeWorker = parameters.get("{type}");
	}
	
	@Override
	public void execute(OutputStream out) throws IOException 
	{
		if (typeWorker.equals("Manager"))
		{
			Leader manager = projectRepository.getProjectById(projectId).getManager();
			out.write(manager.toString().getBytes());
			out.close();
			return;
		}
		else if(typeWorker.equals("Consultant"))  
		{
			Iterable<AWorker> workers = projectRepository.getProjectById(projectId).getTeam();
			StringBuilder builder = new StringBuilder();
			for(AWorker c : workers)
			{
				builder.append(c.toString()).append("\n");
				
			}
			out.write(builder.toString().getBytes());
			out.close();
			return;
		}
		else
			throw new IllegalArgumentException();
			
	}

	@Override
	protected String[] getDemandingParametres() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void internalExecute() throws CommandException {
		// TODO Auto-generated method stub
		
	}
}



