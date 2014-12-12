package app.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;
import app.elements.DatabaseElements;
import app.elements.WorkerComparator;
import utils.AWorker;
import utils.Consultant;
import utils.Leader;

public class InMemoryWorkerRepo extends InMemoryRepo<AWorker> implements WorkerRepository{

	private static final Collection<AWorker> workers = new TreeSet<>(new WorkerComparator());

	private static long nextCIDToBeUsed = 1;
	
	@Override
	public long nextCID() {
		return nextCIDToBeUsed;
	}

	@Override
	public boolean addManager(Leader manager) {
		return addToRepo(manager);
	}

	@Override
	public boolean addConsultant(Consultant consultant) {
		return addToRepo(consultant);
	}
	
	private boolean addToRepo(AWorker worker)
	{
		if (workers.add(worker))
		{
			nextCIDToBeUsed++;
			return true;
		}
		return false;
	}
	
	@Override
	public Consultant getConsultantByID(long cid) {
		for(AWorker worker : workers)
			if (worker.getCID() == cid)
				if (!(worker instanceof Leader) && worker instanceof Consultant)
					return (Consultant) worker;
				else
					return null;
		return null;
	}

	@Override
	public Leader getManagerByID(long cid) {
		for(AWorker worker : workers)
			if (worker.getCID() == cid)
				if (worker instanceof Leader)
					return (Leader) worker;
				else
					return null;
		return null;
	}
	
	
	public static Collection<AWorker> getWorkers() {
		return Collections.unmodifiableCollection(workers);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (AWorker worker: workers)
			builder.append(worker.toString()).append("\n");
		return builder.toString();
	}
	
	@Override
	public void removeAll()
	{
		workers.clear();
	}

	@Override
	public DatabaseElements[] getAll() {
		Collection<AWorker> col = getWorkers();
		DatabaseElements[] dataArr = new DatabaseElements[col.size()];
		int index = 0;
		
		for(DatabaseElements elem : col)
			dataArr[index++] = elem;
		
		return dataArr;
	}
}
