package App.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import App.commands.exceptions.CommandException;
import App.repository.UsersRepository;
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
	


	private GetSubproject(UsersRepository repository, long id)
	{
		
	}

	@Override
	public void execute()   // alterar para que imprimir para a consola não seja responsabilidade sua
	{
		
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
