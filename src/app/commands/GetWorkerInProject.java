package app.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import app.repository.ProjectRepository;
import app.repository.UsersRepository;

public class GetWorkerInProject implements Command {

	/**
	 * Class that implements the {@link GetUser} factory, according to the 
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
			return new GetWorkerInProject(repository, parameters);
		}
	}

	public GetWorkerInProject(ProjectRepository repository,
			Map<String, String> parameters) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
