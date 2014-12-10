package app.commands;

import java.util.Map;

import app.repository.UsersRepository;

/**
 * POST /projects/{pid}/subprojects - adiciona ao projecto identificado por pid
 * um subprojecto, dados os seguintes parâmetros:
 * 
 * subproject - identificador do subprojecto a inserir 
 * Este comando retorna o sucesso ou insucesso da operação. Em caso de insucesso indica o motivo.
 */
public class PostSubproject implements Command {

	/**
	 * Class that implements the {@link GetUser} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final UsersRepository repository;
		
		public Factory(UsersRepository repository)
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
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
