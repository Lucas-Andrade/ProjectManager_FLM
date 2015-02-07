package app.swingWorkerAndPublisher;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import commandRequest.GetHttpRequest;

public class SwingWorkerCommand extends SwingWorker<String, String>{

	private GetHttpRequest getHttpRequest;
	private ResultsPublisher publisher;
	private ErrorPublisher errorPublisher;

	public SwingWorkerCommand(GetHttpRequest getHttpRequest, ResultsPublisher publisher, ErrorPublisher errorPublisher) {
		this.getHttpRequest = getHttpRequest;
		this.publisher = publisher;
		this.errorPublisher = errorPublisher;
	}

	@Override
	protected String doInBackground() {
		publish("Connecting to server...");
		
		String toReturn = "";
		try {
			getHttpRequest.sendRequest();
			
			publish("Getting response from server...");
			toReturn = getHttpRequest.receiveRequest();
			
		} catch (IOException e) {
			errorPublisher.publish("Could not connect to the server.");
			return null;
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
		
		String toPublish = "";
		
		try {
			toPublish = get();
		} catch (InterruptedException e) {
			errorPublisher.publish("Unexpected interruption.");
			return;
		} catch (ExecutionException e) {
			errorPublisher.publish("An error occurred.");
		}
		
		if(toPublish != null) {
			publisher.publish(toPublish);
		}
	}
	
}
