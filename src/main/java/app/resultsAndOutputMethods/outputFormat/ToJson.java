package app.resultsAndOutputMethods.outputFormat;

import org.json.JSONObject;

public class ToJson implements TextParser{

	@Override
	public String parse(JSONObject jsonObject) {
		return jsonObject.toString();
	}

}
