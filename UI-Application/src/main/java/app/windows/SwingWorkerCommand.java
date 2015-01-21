package app.windows;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import app.AppElement;
import app.domainCommands.Command;
import app.domainCommands.exceptions.CommandExecutionException;

public class SwingWorkerCommand extends SwingWorker<AppElement[], String>{

	Command command;
	ResultsPublisher publisher;
	ErrorPublisher errorPublisher;
	
	public SwingWorkerCommand(Command command, ResultsPublisher publisher, ErrorPublisher errorPublisher) {
		this.command = command;
		this.publisher = publisher;
	}

	@Override
	protected AppElement[] doInBackground() throws Exception {
		
		publish("Status: Applying changes to database.");
		AppElement[] toReturn = null;
		
		try{
			toReturn = command.call();
		} catch(CommandExecutionException e){
			String message = e.getMessage();
			
			if (message == null){
				errorPublisher.publish("An error was encountered while applying the changes to the database.");
			} else {
				errorPublisher.publish(message);
			}
		} catch(NumberFormatException e) {
			errorPublisher.publish("Letters were introduced in a numbers only field.");
		} catch(IllegalArgumentException e) {
			errorPublisher.publish("Invalid parameters were entered");
		}
		
		return toReturn;
	}
	
	@Override
	protected void process(List<String> chunks) {
		publisher.publish(chunks);
	}
	
	@Override
	protected void done(){
		try {
			publisher.publish(get());
		} catch (InterruptedException e) {
			errorPublisher.publish("Unexpected interruption.");
		} catch (ExecutionException e) {
			errorPublisher.publish("Unexpected execution error.");
		}
	}

}
