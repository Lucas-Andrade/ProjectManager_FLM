package app.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import app.commands.exceptions.CommandException;
import utils.Project;

/**
 * GET /projects/{pid}/subprojects - retorna todos os subprojectos do projecto
 * identificado por pid, com clara distinção entre projectos e subprojectos.
 */
public class GetSubproject extends BaseCommand implements Command 
{
	

	/**
	 * Class that implements the {@link GetSubproject} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
//	public static class Factory implements CommandFactory 
//	{
//		
//		
//		public Factory(UsersRepository repository)
//		{
//			this.repository = repository;
//		}
//		
//		@Override
//		public Command newInstance(Map<String, String> parameters) 
//		{
//			return;
//			
//		}
//		
//	}
	

	public GetSubproject(Map<String, String> parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void execute(OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		
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
