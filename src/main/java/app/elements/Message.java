package app.elements;

import org.json.JSONObject;

/**
 * Allows the commands to return a message.
 * Instances of this class are used to contain a {@code String}, that can be returned by
 * the {@code internalCall} of any command, given that {@code Message} implements
 * {@code AppElement}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 *
 */
public class Message implements AppElement{

	private String message;
	private String title;
	
	/**
	 * Constructs a new {@code Message}, with the {@code String} passed as parameter, as it's 
	 * content. The title of the message is set to {@code "Message"}
	 * @param message
	 */
	public Message(String message)
	{
		this.title = "Message";
		this.message = message;
	}
	
	/**
	 * Constructs a new {@code Message}, with the {@code message} parameter as it's 
	 * content. The title of the message is the parameter {@code title}
	 * @param title
	 * @param message
	 */
	public Message(String title, String message)
	{
		this.title = title;
		this.message = message;
	}
	
	/**
	 * @return a {@code String} with the content of the message only (no title).
	 */
	public String read()
	{
		return message;
	}
	
	@Override
	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		json.put(title, message);
		return json;
	}

}
