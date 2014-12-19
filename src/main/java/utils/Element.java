package utils;

import org.json.JSONObject;

/**
 * Abstract class Element that will serve as an abstraction layer representing
 * all the objects that can be kept in a container.
 * 
 * Implements the Interfaces {@link ICost}, {@link IName}, {@link Comparable}.
 */
public abstract class Element implements ICost, IName, Comparable<Element>
{
	
	public abstract String toString();
	public abstract String toHtml();
	protected abstract String toHtml(int indent);
	public abstract String toJson();
	protected abstract JSONObject getJson();
}