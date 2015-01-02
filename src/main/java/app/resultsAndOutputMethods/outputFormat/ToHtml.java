package app.resultsAndOutputMethods.outputFormat;

import org.json.JSONObject;

public class ToHtml implements TextParser {

	@Override
	public String parse(JSONObject jsonObject) 
	{
		StringBuilder builder = new StringBuilder();
		return builder.append("<html>").append(parse(jsonObject, "</p>\n", 0)).append("</html>").toString();
	}

	private static String parse(JSONObject jsonObject, String lineSeparator, int indentation) 
	{
		Iterable<String> jsonKeys = TextParser.getOrderedKeySet(jsonObject);
		StringBuilder builder = new StringBuilder();
		
		for (String key : jsonKeys)
		{
			builder.append(indent(indentation))
				.append("<b>")
				.append(key).append("</b>: ");
			
			if ((jsonObject.get(key).getClass()).equals(new JSONObject().getClass()))
				builder.append(parse((JSONObject)jsonObject.get(key), ", ", -1))
					.append(lineSeparator);
			
			else if (jsonObject.get(key) instanceof JSONObject[])
				builder.append(lineSeparator)
					.append(parseArray((JSONObject[]) jsonObject.get(key), lineSeparator, indentation + 5));
			else
				builder.append(jsonObject.get(key))
					.append(lineSeparator);
		}
		return builder.toString();
	}

	private static String indent(int indentation) 
	{
		return indentation >= 0 ? "<p style=\"text-indent: " + indentation + "em;\">" : "";
	}

	private static String parseArray(JSONObject[] jsonArray, String lineSeparator, int indentation) 
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < jsonArray.length; i++)
			builder.append(parse(jsonArray[i], lineSeparator, indentation)).append(lineSeparator);
		return builder.toString();
	}

}
