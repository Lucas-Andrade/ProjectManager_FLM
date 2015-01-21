package app.domainCommands;

import java.util.concurrent.Callable;

import app.AppElement;
import app.domainCommands.exceptions.CommandExecutionException;

public interface Command extends Callable<AppElement[]>{
	
	@Override
	public AppElement[] call() throws CommandExecutionException;
}
