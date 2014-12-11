package app.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import utils.AWorker;
import utils.Consultant;
import utils.Leader;

public class InMemoryWorkerRepo extends InMemoryRepo<AWorker> implements WorkerRepository{

	private static final Collection<AWorker> workers = new HashSet<>();

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
	
	
	public static Collection<AWorker> getProjects() {
		return Collections.unmodifiableCollection(workers);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (AWorker worker: workers)
			builder.append(worker.toString()).append("\n");
		return builder.toString();
	}
}