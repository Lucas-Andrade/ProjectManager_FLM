package app.elements;

import java.util.Comparator;

import utils.Project;

/**
 * Produces a comparator that allows to compare {@code Project} objects using their {@code PID} to compare them.
 * Objects with higher {@code PID} will appear higher in a list ordered by this comparator. 
 * @author Grupo FLM
 *
 */
public class ProjectComparator implements Comparator<Project>{
	
	/**
	 * if null projects are introduced, their {@code PID} is considered to be -1
	 * @param p1 - {@code Project} 1
	 * @param p2 - {@code Project} 2
	 * @return 1 if {@code p1}'s {@code PID} is higher than {@code p2}'s {@code PID}
	 * @return -1 if {@code p1}'s {@code PID} is lower than {@code p2}'s {@code PID}
	 * @return 0 if {@code p1} and {@code p2}'s {@code PID} are equal
	 */
	@Override
	public int compare(Project p1, Project p2) 
	{
		long p1PID;
		long p2PID;
		
		try{
			p1PID = p1.getPID();
		}
		catch (NullPointerException e)
		{
			p1PID = -1;
		}
		
		try{
			p2PID = p2.getPID();
		}
		catch(NullPointerException e)
		{
			p2PID = -1;
		}
		
		return p1PID > p2PID ? -1 : p1PID < p2PID ? 1 : 0;
	}

}
