package app.resultsAndOutputMethods.outputFormat;

import org.json.JSONObject;

public class ToApplicationJson implements TextParser{

	@Override
	public String parse(JSONObject jsonObject) 
	{
		return jsonObject.toString();
	}

	@Override
	public String parse(JSONObject[] toWrite) 
	{
		if (toWrite.length == 1)
			return parse(toWrite[0]);
		else
		{
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (int i = 0; i < toWrite.length - 1; i++)
			builder.append(parse(toWrite[i])).append(",");
		builder.append(parse(toWrite[toWrite.length - 1])).append("]");
		return builder.toString();
		}
	}

}
