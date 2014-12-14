package app.elements;

import java.util.Comparator;

import utils.AWorker;

/**
 * Produces a comparator that allows to compare {@code AWorker} objects using
 * their ID to compare them (CID). Objects with higher CID will
 * appear higher in a list ordered by this comparator.
 * 
 * The idea behind this is that, as the Workers are created their CID
 * increases. By showing first the Workers with higher CID, the more recent
 * Workers will be shown.
 */
public class WorkerComparator implements Comparator<AWorker>
{

	/**
	 * if null Workers are introduced, their {@code CID} is considered to be -1
	 * @param w1 - {@code AWorker} 1
	 * @param w2 - {@code AWorker} 2
	 * @return 1 if {@code w1}'s {@code CID} is higher than {@code w2}'s {@code CID}
	 * @return -1 if {@code w1}'s {@code CID} is lower than {@code w2}'s {@code CID}
	 * @return 0 if {@code w1} and {@code w2}'s {@code CID} are equal
	 * 
	 * @see Comparator#compare(Object, Object)
	 */
	@Override
	public int compare(AWorker w1, AWorker w2)
	{
		long cid1;
		long cid2;

		try
		{
			cid1 = w1.getCID();
		} catch (NullPointerException e)
		{
			cid1 = -1;
		}

		try
		{
			cid2 = w2.getCID();
		} catch (NullPointerException e)
		{
			cid2 = -1;
		}

		return cid1 > cid2 ? 1 : (cid1 < cid2) ? -1 : 0;
	}

}
