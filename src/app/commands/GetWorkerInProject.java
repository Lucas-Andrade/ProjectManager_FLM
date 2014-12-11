package app.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import app.repository.UserRepository;


public class GetWorkerInProject implements Command {

	/**
	 * Class that implements the {@link GetUser} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final UserRepository repository;
		
		public Factory(UserRepository repository)
		{
			this.repository = repository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			// TODO
			return null;
		}
	}

	@Override
	public void execute(OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
