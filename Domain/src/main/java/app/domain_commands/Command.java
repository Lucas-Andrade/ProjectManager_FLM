package app.domain_commands;

import java.util.concurrent.Callable;

import app.AppElement;
import app.domain_commands.exceptions.CommandExecutionException;

public interface Command extends Callable<AppElement[]>{
	
	@Override
	public AppElement[] call() throws CommandExecutionException;
}
