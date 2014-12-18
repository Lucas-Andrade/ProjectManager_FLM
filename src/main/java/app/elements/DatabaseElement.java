package app.elements;

/**
 * Parent interface for all Objects that can be stored in {@link Repository}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface DatabaseElement
{

	/**
	 * This method represent the worker's information in HTLM format 
	 */
	String toHtml();
	
	/**
	 * This method represent the worker's information in Json's format 
	 */
	String toJson();
}
