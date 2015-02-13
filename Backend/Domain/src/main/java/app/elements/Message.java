package app.elements;

import org.json.JSONObject;

import app.AppElement;

/**
 * Allows the commands to return a message. Instances of this class are used to
 * contain a {@code String}, that can be returned by the {@code internalCall} of
 * any command, given that {@code Message} implements {@code AppElement}. All
 * {@code Message} instances are thread-safe.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 *
 */
public class Message implements AppElement {

	private String messageContent;
	private String title;

	/**
	 * The lock to be used inside {@code this} object.
	 */
	private Object lockMessage = new Object();

	/**
	 * Constructs a new {@code Message}, with the {@code String} passed as
	 * parameter, as it's content. The title of the message is set to
	 * {@code "Message"}
	 * 
	 * @param message
	 */
	public Message(String message) {
		synchronized (lockMessage) {
			this.title = "Message";
			this.messageContent = message;
		}
	}

	/**
	 * Constructs a new {@code Message}, with the {@code message} parameter as
	 * it's content. The title of the message is the parameter {@code title}
	 * 
	 * @param title
	 * @param message
	 */
	public Message(String title, String message) {
		synchronized (lockMessage) {
			this.title = title;
			this.messageContent = message;
		}
	}

	/**
	 * @return a {@code String} with the content of the message only (no title).
	 */
	public String read() {
		synchronized (lockMessage) {
			return messageContent;
		}
	}

	@Override
	public JSONObject getJson() {
		synchronized (lockMessage) {
			JSONObject json = new JSONObject();
			json.put(title, messageContent);
			return json;
		}
	}
}
