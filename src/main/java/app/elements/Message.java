package app.elements;

import org.json.JSONObject;

public class Message implements AppElement{

	private String message;
	private String msg;
	
	public Message(String message)
	{
		this.msg = "Message";
		this.message = message;
	}
	
	public Message(String msg, String message)
	{
		this.msg = msg;
		this.message = message;
	}
	
	
	@Override
	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		json.put(msg, message.replaceAll("\n", ""));
		return json;
	}

}
