package app.elements;

import org.json.JSONObject;

/**
 * Parent interface for all Objects that can be stored in {@link Repository}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface DatabaseElement
{
	
	public JSONObject getJson();
}
