package app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import publishers.ErrorPublisher;
import publishers.ResultsPublisher;
import commandRequest.HttpConnectionException;
import commandRequest.HttpRequest;

public class SwingWorkerCommand extends SwingWorker<String, String>{

	private HttpRequest httpRequest;
	private ResultsPublisher publisher;
	private ErrorPublisher errorPublisher;

	public SwingWorkerCommand(HttpRequest httpRequest, ResultsPublisher publisher, ErrorPublisher errorPublisher) {
		this.httpRequest = httpRequest;
		this.publisher = publisher;
		this.errorPublisher = errorPublisher;
	}

	@Override
	protected String doInBackground() throws HttpConnectionException {
		publish("Connecting to server...");
		
		String toReturn = "";
		HttpURLConnection connection = null;
		
		try {
			connection = httpRequest.sendRequest();
			connection.connect();
			
			publish("Waiting for the server's response...");
			toReturn = httpRequest.receiveRequest();
			
		} catch (IOException e) {
			errorPublisher.publish("Could not connect to the server.");
			return null;
		
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
		if(toReturn.contains("Message")) {
			errorPublisher.publishJsonFormatString(toReturn);
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
			errorPublisher.publish("Server exception.");
		}
		
		if(toPublish != null) {
			publisher.publish(toPublish);
		}
	}
	
}
