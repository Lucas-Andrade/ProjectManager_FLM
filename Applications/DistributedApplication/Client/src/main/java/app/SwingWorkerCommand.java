package app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import publishers.ErrorPublisher;
import publishers.ResultsPublisher;
import commandRequest.HttpConnectionException;
import commandRequest.HttpRequest;

public class SwingWorkerCommand extends SwingWorker<String, String> {

	private HttpRequest httpRequest;
	private ResultsPublisher publisher;
	private ErrorPublisher errorPublisher;

	public SwingWorkerCommand(HttpRequest httpRequest,
			ResultsPublisher publisher, ErrorPublisher errorPublisher) {
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
			connection.setConnectTimeout(60000);
			connection.connect();

			publish("Waiting for the server's response...");
			toReturn = httpRequest.receiveRequest();

		} catch (SocketTimeoutException e) {
			errorPublisher.publish("Connection timeout. The server did not answer.");
			return null;
		} catch (IOException | NumberFormatException e) {
			errorPublisher.publish("<html> Could not understand server's answer. Error:<hr>" + e.getCause().getMessage() + "</http>"); //This should improve, should show http response status code and message.);
			return null;
		} catch(IllegalArgumentException e) {
			errorPublisher.publish("Could not process. Please review your data.");
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

		if (toReturn.contains("Message")) {
			errorPublisher.publishJsonFormatString(toReturn);
			return null;
		}
		
		publisher.publish(toReturn);
		return toReturn;
	}

	/**
	 * Publishes into the {@code ResultsPublisher} object.
	 * 
	 * @param chunks
	 */
	@Override
	protected void process(List<String> chunks) {
		publisher.publish(chunks);
	}

	/**
	 * Publishes the result of {@code doInBackground} into the
	 * {@code ResultsPublisher} object. If the result is null, nothing is
	 * published.
	 */
	@Override
	protected void done() {
		
		String toPublish = null;

		try {
			toPublish = get();
		} catch (NullPointerException e) {
			System.out.println("a null pointer exception foi apanhada");
			return;
		} catch (InterruptedException e) {
			errorPublisher.publish("Unexpected interruption.");
			return;
		} catch (ExecutionException e) {
			errorPublisher.publish("Unexpected error with data received from server."); //This should improve, should show http response status code and message.
			return;
		}

		if (toPublish != null) {
			publisher.publish(toPublish);
		}
	}

}
