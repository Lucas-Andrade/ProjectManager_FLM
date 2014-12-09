package App.commands;

import java.util.Map;

import App.repository.UsersRepository;

/**
 * POST /consultants - cria um novo consultor, dados os seguintes parâmetros:
 * name - nome do consultor
 * priceHour - preço/hora do consultor
 */
public class PostConsultant implements Command {

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
