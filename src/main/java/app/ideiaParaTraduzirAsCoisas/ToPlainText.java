package app.ideiaParaTraduzirAsCoisas;

import java.util.Iterator;

import org.json.JSONObject;

public class ToPlainText implements TextParser{

	@Override
	public String parse(JSONObject jsonObject) 
	{
		return parse(jsonObject, "\n");
	}

	private String parse(JSONObject jsonObject, String elementSeparator) {
		Iterator<String> jsonKeys = jsonObject.keys();
		StringBuilder builder = new StringBuilder();
		
		while (jsonKeys.hasNext())
		{
			String key = jsonKeys.next();
			builder.append(key).append(": ");
			
			if ((jsonObject.get(key).getClass()).equals(new JSONObject().getClass()))
				builder.append(parse((JSONObject)jsonObject.get(key), ", ")).append(elementSeparator);
			else
				builder.append(jsonObject.get(key)).append(elementSeparator);
		}
			
		return builder.toString();
	}

}
