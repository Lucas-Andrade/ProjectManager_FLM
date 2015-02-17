package app.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import app.AppElement;
import app.elements.mutable.ConsultantCreationDescriptor;
import app.elements.mutable.LeaderCreationDescriptor;
import app.elements.mutable.WorkerCreationDescriptor;
import utils.AWorker;
import utils.Consultant;
import utils.Leader;

/**
 * An {@link AWorker}s in memory {@link Repository}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class InMemoryWorkerRepo extends InMemoryRepo<AWorker> implements
		WorkerRepository {

	/**
	 * Synchronized {@code Map} that stores the {@code AWorker}s of this
	 * repository.
	 */
	private static final Map<Long, AWorker> WORKERS = Collections
			.synchronizedMap(new HashMap<Long, AWorker>());

	/**
	 * The lock to be used in instructions where
	 * {@code this#NEXT_CID_TO_BE_USED} cannot be modified by concurrent
	 * threads. This lock CANNOT be used after a synchronized block with the
	 * lock {@code this#cidResetLock}.
	 */
	private final Object cidLock;

	/**
	 * The lock to be used when {@code this#NEXT_CID_TO_BE_USED} is reset, by
	 * all instructions that associate a Worker to a CID in the repository, thus
	 * preventing the possibility of a Worker being added to this repository
	 * with a CID higher than the {@code this#NEXT_CID_TO_BE_USED}.
	 */
	private final Object cidResetLock;

	/**
	 * The last CID attributed to an {@link AWorker} plus one.
	 */
	private volatile static long NEXT_CID_TO_BE_USED;

	/**
	 * Constructs a new empty {@code InMemoryWorkerRepo}, in which the next
	 * {@code CID} to be used is set to 1.
	 */
	public InMemoryWorkerRepo() {
		NEXT_CID_TO_BE_USED = 1;
		cidLock = new Object();
		cidResetLock = new Object();
	}

	/**
	 * @see WorkerRepository#addManager(LeaderCreationDescriptor)
	 */
	@Override
	public Long addManager(LeaderCreationDescriptor creationDescriptor) {
		return this.addWorker(creationDescriptor);
	}

	/**
	 * Method that creates and adds an {@code AWorker} to the repository.
	 * 
	 * @param manager
	 *            The {@code WorkerCreationDescriptor} with the information
	 *            necessary to create the {@code AWorker} to add.
	 * @return The CID if successful, null if not.
	 */
	@SuppressWarnings("rawtypes")
	private Long addWorker(WorkerCreationDescriptor creationDescriptor) {
		Long newWorkerCID;
		AWorker newWorker;

		synchronized (cidLock) {
			newWorkerCID = NEXT_CID_TO_BE_USED++;
			newWorker = creationDescriptor.build(newWorkerCID);
			if (newWorker == null) {
				NEXT_CID_TO_BE_USED--;
				return null;
			}
		}
		synchronized (cidResetLock) {
			if (NEXT_CID_TO_BE_USED > newWorkerCID
					&& WORKERS.putIfAbsent(newWorkerCID, newWorker) == null)
				return newWorkerCID;
		}

		return null;
	}
	
	/**
	 * @see WorkerRepository#addConsultant(consultantCreationDescriptor)
	 */
	@Override
	public Long addConsultant(
			ConsultantCreationDescriptor creationDescriptor) {
		return this.addWorker(creationDescriptor);
	}

	/**
	 * @see WorkerRepository#getConsultantByID(long)
	 */
	@Override
	public Consultant getConsultantByID(long cid) {
		AWorker worker = WORKERS.get(cid);
		if (worker instanceof Consultant) {
			return (Consultant) worker;
		} else {
			return null;
		}
	}

	/**
	 * @see WorkerRepository#getManagerByID(long)
	 */
	@Override
	public Leader getManagerByID(long cid) {
		AWorker worker = WORKERS.get(cid);
		if (worker instanceof Leader) {
			return (Leader) worker;
		} else {
			return null;
		}
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Iterable<AWorker> workers = WORKERS.values();
		for (AWorker worker : workers) {
			builder.append(worker.toString()).append("\n");
		}
		return builder.toString();
	}

	/**
	 * @see Repository#removeAll()
	 */
	@Override
	public void removeAll() {
		synchronized (cidResetLock) {
			WORKERS.clear();
			NEXT_CID_TO_BE_USED = 1;
		}
	}

	/**
	 * @see Repository#getAll()
	 */
	@Override
	public AWorker[] getAll() {
		Collection<AWorker> workers = WORKERS.values();
		AWorker[] all = new AWorker[workers.size()];
		int i = -1;
		for (AWorker ele : workers) {
			all[++i] = ele;
		}
		return all;
	}

	/**
	 * @see Repository#size()
	 */
	@Override
	public int size() {
		return WORKERS.size();
	}

	/**
	 * @see WorkerRepository#getAWorkerByID(long)
	 */
	@Override
	public AWorker getAWorkerByID(long cid) {
		return WORKERS.get(cid);
	}

	@Override
	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		Iterable<AWorker> workers = WORKERS.values();
		for (AppElement ele : workers) {
			json.accumulate("All workers", ele.getJson());
		}
		return json;
	}

}
