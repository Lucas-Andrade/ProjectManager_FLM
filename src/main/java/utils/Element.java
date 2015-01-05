package utils;

import org.json.JSONObject;

import app.elements.Element;


/**
 * Abstract class Element that will serve as an abstraction layer representing
 * all the objects that can be kept in a container.
 * 
 * Implements the Interfaces {@link ICost}, {@link IName}, {@link Comparable}.
 */
public abstract class Element implements ICost, IName, Comparable<Element>, Element
{	
	public abstract String toString();
	public abstract JSONObject getJson();
	
}