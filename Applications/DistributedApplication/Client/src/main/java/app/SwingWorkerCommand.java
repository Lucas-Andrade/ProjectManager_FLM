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
//			System.out.println("does the swing worker get a null connection? " + (connection == null));
			connection.setConnectTimeout(60000);
			connection.connect();

			publish("Waiting for the server's response...");
			toReturn = httpRequest.receiveRequest();
//			System.out.println("Mas sera que chegou aqui??");
//			System.out.println("retorno: " + toReturn);

		} catch (SocketTimeoutException e) {
			errorPublisher.publish("Connection timeout. The server did not answer.");
			return null;
		} catch (IOException | NumberFormatException e) {
			errorPublisher.publish(e.getCause().getMessage()); //This should improve, should show http response status code and message."Could not understand server's answer.");
			return null;
		} catch(IllegalArgumentException e) {
			errorPublisher.publish("Could not process. Please review your data.");
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

//		System.out.println("ele chega aqui");
		if (toReturn.contains("Message")) {
			errorPublisher.publishJsonFormatString(toReturn);
			return null;
		}
		
//		System.out.println("ele chega aqui2");
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
		
//		System.out.println("ele chega ao done");
		String toPublish = null;

		try {
			toPublish = get();
//			System.out.println("ele faz o get");
		} catch (NullPointerException e) {
			System.out.println("a null pointer exception foi apanhada");
			return;
		} catch (InterruptedException e) {
			errorPublisher.publish("Unexpected interruption.");
			return;
		} catch (ExecutionException e) {
//			System.out.println(e.toString());
			errorPublisher.publish("Unexpected error with data received from server."); //This should improve, should show http response status code and message.
			return;
		}

//		System.out.println("ele sai do tri cach");
		if (toPublish != null) {
			System.out.println("ele vai publicar");
			publisher.publish(toPublish);
			System.out.println("ele acabou de publicar");
		}
	}

}
