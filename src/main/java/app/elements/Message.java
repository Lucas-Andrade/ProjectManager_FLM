package app.elements;

import org.json.JSONObject;

public class Message implements DatabaseElement{

	private String message;
	
	public Message(String message)
	{
		this.message = message;
	}
	
	@Override
	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		json.put("Message", message);
		return json;
	}

}
