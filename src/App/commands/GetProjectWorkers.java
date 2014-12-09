package App.commands;

import java.util.Map;

import App.model.Projects.Project;
import App.model.Projects.UsersRepository;
import App.model.Projects.WorkersRepository;


/**
 * GET /projects/{pid}/{type} - retorna o(s) consultor(es) do tipo type (manager
 * ou consultant), do projecto identificado por pid.
 */
public class GetProjectWorkers implements Command      
{

	/**
	 * Class that implements the {@link GetProjectWorkers} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

		private final WorkersRepository repository;
		
		public Factory (WorkersRepository repository)
		{
			this.repository = repository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return null;
			
		}	
	}

	
	
	private GetProjectWorkers (WorkersRepository repository, long id)
	{
		
	}
	
	@Override
	public void execute() 
	{
		
		
	}


}

