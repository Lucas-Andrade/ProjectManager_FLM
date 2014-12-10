package app.commands;

import java.util.Map;

import app.repository.UsersRepository;
import utils.Project;

/**
 * GET /projects/{pid}/subprojects - retorna todos os subprojectos do projecto
 * identificado por pid, com clara distinção entre projectos e subprojectos.
 */
public class GetSubproject implements Command 
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

}
