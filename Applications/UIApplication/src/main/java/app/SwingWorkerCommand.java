package app;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.json.JSONArray;

import publishers.ErrorPublisher;
import publishers.ResultsPublisher;
import app.AppElement;
import app.domainCommands.Command;
import app.domainCommands.exceptions.CommandExecutionException;

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
public class SwingWorkerCommand extends SwingWorker<String, String>{

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
	 * @throws Exception 
	 */
	@Override
	protected String doInBackground() throws Exception {
		
		publish("<html>Getting information<br>from the database.<br></html>");
		AppElement[] result = command.call();
		return toString(result);
	}
	
	/**
	 * Turns the array of {@code AppElement}s into a string in the JSON format.
	 * @param result
	 * @return a string with the information contained in the array of {@code AppElement}s
	 */
	private static String toString(AppElement[] result) {
		
		String toReturn;
		if(result.length == 1) {
			return result[0].getJson().toString();
		} else {
			JSONArray array = new JSONArray();
			for(int i = 0; i < result.length; i++){
				array.put(result[i].getJson());
			}
			toReturn = array.toString();
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
		String result = null;
		try {
			result = get();
		} catch (InterruptedException e) {
			errorPublisher.publish("Unexpected interruption.");
			return;
		} catch (ExecutionException e) {
			
			Throwable caughtException = e.getCause();
			if (caughtException instanceof CommandExecutionException) {
				analyseMessage(e.getMessage());
				return;
			} else if(caughtException instanceof NumberFormatException) {
				errorPublisher.publish("Letters were introduced in a numbers only field.");
				return;
			} else if(caughtException instanceof IllegalArgumentException) {
				errorPublisher.publish("Illegal arguments were entered.");
				return;
			}else{
				errorPublisher.publish("An error occurred.");
			}
		}
		
		publisher.publish(result);
	}

	/**
	 * Checks if the message passed as parameter is null. If it is, a general message will be published
	 * to the {@code ErrorPublisher}. If it is not, a message based on the message passed as parameter 
	 * is published.
	 * 
	 * @param message
	 */
	private void analyseMessage(String message) {
		if (message == null){
			errorPublisher.publish("An error was encountered while applying the changes to the database.");
		} else {
			String[] splitMessage = message.split(":");
			StringBuilder builder = new StringBuilder();
			for(int i = 1; i < splitMessage.length; i++) {
				builder.append(splitMessage[i]);
			}
			errorPublisher.publish(builder.toString());
		}
	}

}
