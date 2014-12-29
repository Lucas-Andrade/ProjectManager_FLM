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
		// TODO Auto-generated method stub
		return null;
	}

}
