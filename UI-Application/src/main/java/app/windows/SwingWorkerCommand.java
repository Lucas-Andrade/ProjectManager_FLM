package app.windows;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import app.AppElement;
import app.domain_commands.Command;
import app.domain_commands.exceptions.CommandExecutionException;

/**
 * Is a {@code SwingWorker<AppElement[], String>} that is used to apply changes to the database.
 * To do so, it receives the {@code Command} to be called, a {@code ResultsPublisher} to publish
 * the result's success, and a {@code ErrorPublisher} if problems were encountered.
 * 
 * Calls the {@code Command} passed as parameter in the {@code doInBackgound()} method of 
 * {@code SwingWorker}, and publishes the result into the {@code ResultsPublisher} object.
 * 
 * If an exception is caught, an error message is published into the {@code ErrorPublisher} object.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * 
 */
public class SwingWorkerCommand extends SwingWorker<AppElement[], String>{

	Command command;
	ResultsPublisher publisher;
	ErrorPublisher errorPublisher;
	
	/**
	 * Constructs a new {@code SwingWorkerCommand} passing as parameter all the objects it needs 
	 * to work correctly. 
	 * @param command
	 * @param publisher
	 * @param errorPublisher
	 */
	public SwingWorkerCommand(Command command, ResultsPublisher publisher, ErrorPublisher errorPublisher) {
		this.command = command;
		this.publisher = publisher;
		this.errorPublisher = errorPublisher;
	}

	/**
	 * Calls the {@code Command} and returns its result. Any exception this action may throw is 
	 * caught here and a message is published to the {@code ErrorPublisher}.
	 * @return The array of {@code AppElement} returned by the {@code call()} of the {@code Command}.
	 */
	@Override
	protected AppElement[] doInBackground() {
		
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
			publish("Status: Ready.");
		} catch(NumberFormatException e) {
			errorPublisher.publish("Letters were introduced in a numbers only field.");
			publish("Status: Ready.");
		} catch(IllegalArgumentException e) {
			errorPublisher.publish("Invalid parameters were entered.");
			publish("Status: Ready.");
		}
		
		return toReturn;
	}
	
	/**
	 * Publishes into the {@code ResultsPublisher} object.
	 * @param chunks
	 */
	@Override
	protected void process(List<String> chunks) {
		publisher.publish(chunks);
	}
	
	/**
	 * Publishes the result of {@code doInBackground} into the {@code ResultsPublisher} object. 
	 * If the result is null, nothing is published.
	 */
	@Override
	protected void done(){
		AppElement[] result = null;
		try {
			result = get();
		} catch (InterruptedException e) {
			errorPublisher.publish("Unexpected interruption.");
			return;
		} catch (ExecutionException e) {
			errorPublisher.publish("Unexpected execution error.");
			return;
		}
		
		if (result != null){ //because get() will return a null result if doInBackgound caught an exception
			publisher.publish(result);
		}
	}

}
