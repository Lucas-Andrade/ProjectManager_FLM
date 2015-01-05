package app.repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import org.json.JSONObject;
import org.junit.Test;

import app.elements.AppElement;
import app.elements.WorkerComparator;
import utils.AWorker;
import utils.Consultant;
import utils.Leader;
import utils.Project;

/**
 * An {@link AWorker}s in memory {@link Repository}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class InMemoryWorkerRepo extends InMemoryRepo<AWorker> implements
		WorkerRepository
{

	/**
	 * {@code Collection} that stores the {@code AWorker}s of this repository.
	 */
	private static final Collection<AWorker> workers = new TreeSet<>(
			new WorkerComparator());

	/**
	 * The last CID attributed to an {@link AWorker} plus one.
	 */
	private static long NEXT_CID_TO_BE_USED = 1;

	/**
	 * @see WorkerRepository#nextCID()
	 */
	@Override
	public long nextCID()
	{
		return NEXT_CID_TO_BE_USED;
	}

	/**
	 * @see WorkerRepository#addManager(Leader)
	 */
	@Override
	public boolean addManager(Leader manager)
	{
		return addToRepo(manager);
	}

	/**
	 * @see WorkerRepository#addManager(Leader)
	 */
	@Override
	public boolean addConsultant(Consultant consultant)
	{
		return addToRepo(consultant);
	}

	/**
	 * Adds an {@code AWorker} to the repository if it doesn't exist already in
	 * the repository (there can't be more than one {@code AWorker} with the
	 * same CID).
	 * 
	 * @param worker
	 *            The Worker to add.
	 * @return True if successful, False if not.
	 */
	private boolean addToRepo(AWorker worker)
	{
		if (workers.add(worker))
		{
			NEXT_CID_TO_BE_USED++;
			return true;
		}
		return false;
	}

	/**
	 * @see WorkerRepository#getConsultantByID(long)
	 */
	@Override
	public Consultant getConsultantByID(long cid)
	{
		for (AWorker worker : workers)
			if (worker.getCID() == cid)
				if (!(worker instanceof Leader) && worker instanceof Consultant)
					return (Consultant) worker;
				else
					return null;
		return null;
	}

	/**
	 * @see WorkerRepository#getManagerByID(long)
	 */
	@Override
	public Leader getManagerByID(long cid)
	{
		for (AWorker worker : workers)
			if (worker.getCID() == cid)
				if (worker instanceof Leader)
					return (Leader) worker;
				else
					return null;
		return null;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for (AWorker worker : workers)
			builder.append(worker.toString()).append("\n");
		return builder.toString();
	}

	/**
	 * @see Repository#removeAll()
	 */
	@Override
	public void removeAll()
	{
		workers.clear();
	}

	/**
	 * @see Repository#getAll()
	 */
	@Override
	public AppElement[] getAll()
	{
		AppElement[] all = new AppElement[this.size()];
		int i = -1;
		for (AppElement ele : workers)
			all[++i] = ele;
		return all;
	}

	/**
	 * @see Repository#size()
	 */
	@Override
	public int size()
	{
		return workers.size();
	}

	/**
	 * @see WorkerRepository#getAWorkerByID(long)
	 */
	@Override
	public AWorker getAWorkerByID(long cid)
	{
		AWorker aWorker = null;
		for (AWorker worker : workers)
			if (worker.getCID() == cid)
				aWorker = worker;
		return aWorker;
	}

//	@Override
//	public JSONObject[] getJson() {
//		JSONObject[] json = new JSONObject[workers.size()];
//		Iterator<AWorker> iterator = workers.iterator(); 
//		int i = 0;
//		while(iterator.hasNext())
//			json[i++] = iterator.next().getJson();
//		return json;
//	}

	@Override
	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		for (AppElement ele : workers)
			json.accumulate("All workers", ele.getJson());
		
		return json;
	}
	
}
