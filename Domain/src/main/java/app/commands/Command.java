package app.commands;

import java.util.concurrent.Callable;

import app.AppElement;

public interface Command extends Callable<AppElement[]>{
	
}
