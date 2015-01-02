package app.resultsAndOutputMethods.outputFormat;

import org.json.JSONObject;

public class ToHtml implements TextParser {

	@Override
	public String parse(JSONObject jsonObject) 
	{
		return parse(jsonObject, "\n", 0);
	}

	private static String parse(JSONObject jsonObject, String lineSeparator, int indentation) 
	{
		Iterable<String> jsonKeys = TextParser.getOrderedKeySet(jsonObject);
		StringBuilder builder = new StringBuilder();
		
		for (String key : jsonKeys)
		{
			builder.append(indent(indentation)).append(key).append(": ");
			
			if ((jsonObject.get(key).getClass()).equals(new JSONObject().getClass()))
				builder.append(parse((JSONObject)jsonObject.get(key), ", ", 0))
					.append(lineSeparator);
			
			else if (jsonObject.get(key) instanceof JSONObject[])
				builder.append("\n")
					.append(parseArray((JSONObject[]) jsonObject.get(key), lineSeparator, indentation + 5));
			else
				builder.append(jsonObject.get(key))
					.append(lineSeparator);
		}
		return builder.toString();
	}

	private static String indent(int indentation) 
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < indentation; i++)
			builder.append(" ");
		return builder.toString();
	}

	private static String parseArray(JSONObject[] jsonArray, String lineSeparator, int indentation) 
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < jsonArray.length; i++)
			builder.append(parse(jsonArray[i], "\n", indentation)).append("\n");
		return builder.toString();
	}

}
