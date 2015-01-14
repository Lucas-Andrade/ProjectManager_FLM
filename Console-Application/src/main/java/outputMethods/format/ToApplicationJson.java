package outputMethods.format;

import org.json.JSONObject;

/**
 * Class that converts results to JSON format.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public class ToApplicationJson implements TextParser{

	/**
	 * @see TextParser#parse(JSONObject)
	 */
	@Override
	public String parse(JSONObject jsonObject) {
		return jsonObject.toString();
	}

	/**
	 * @see TextParser#parse(JSONObject[])
	 */
	@Override
	public String parse(JSONObject[] toWrite){
		if (toWrite.length == 1){
			return parse(toWrite[0]);
		}else{
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			for (int i = 0; i < toWrite.length - 1; i++){
				builder.append(parse(toWrite[i])).append(",");
			}
			builder.append(parse(toWrite[toWrite.length - 1])).append("]");
			return builder.toString();
		}
	}
}
