package app.ideiaParaTraduzirAsCoisas;

import java.util.Iterator;

import org.json.JSONObject;

public class ToPlainText implements TextParser{

	@Override
	public String parse(JSONObject jsonObject) 
	{
		Iterator<String> jsonKeys = jsonObject.keys();
		StringBuilder builder = new StringBuilder();
		
		while (jsonKeys.hasNext())
		{
			String key = jsonKeys.next();
			builder.append(key).append(": ").append(jsonObject.get(key)).append("\n");
		}
			
			
		return builder.toString();
	}

}
