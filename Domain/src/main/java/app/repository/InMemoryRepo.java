package app.repository;

import org.json.JSONObject;

import app.AppElement;

/**
 * Abstract class whose purpose will be to store all {link DatabaseElements} in
 * the memory.
 * 
 * @param <T>
 *            The elements that can be stored in the Repository, extends
 *            {@code DatabaseElements}.
 *            
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public abstract class InMemoryRepo<T extends AppElement> implements
		Repository<AppElement> {
	
	public abstract JSONObject getJson();
}
