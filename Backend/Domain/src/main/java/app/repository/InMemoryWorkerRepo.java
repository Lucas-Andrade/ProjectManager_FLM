package app.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import app.AppElement;
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
	 * {@code Map} that stores the {@code AWorker}s of this repository.
	 */
	private static final Map<Long, AWorker> WORKERS = Collections
			.synchronizedMap(new HashMap<Long, AWorker>());

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
	}

	/**
	 * @see WorkerRepository#nextCID()
	 */
	@Override
	public long nextCID() {
		return NEXT_CID_TO_BE_USED;
	}

	/**
	 * @see WorkerRepository#addManager(Leader)
	 */
	@Override
	public synchronized boolean addManager(
			leaderCreationDescriptor creationDescriptor) {
		Long newManagerCID = NEXT_CID_TO_BE_USED;
		AWorker newManager = creationDescriptor.build(newManagerCID);
		if (newManager == null) {
			return false;
		}
		WORKERS.putIfAbsent(newManagerCID, newManager);
		NEXT_CID_TO_BE_USED++;
		return true;
	}

	/**
	 * @see WorkerRepository#addManager(Leader)
	 */
	@Override
	public synchronized boolean addConsultant(
			consultantCreationDescriptor creationDescriptor) {
		Long newConsultantCID = NEXT_CID_TO_BE_USED;
		AWorker newConsultant = creationDescriptor.build(newConsultantCID);
		if (newConsultant == null) {
			return false;
		}
		WORKERS.putIfAbsent(newConsultantCID, newConsultant);
		NEXT_CID_TO_BE_USED++;
		return true;
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
		for (AWorker worker : WORKERS.values()) {
			builder.append(worker.toString()).append("\n");
		}
		return builder.toString();
	}

	/**
	 * @see Repository#removeAll()
	 */
	@Override
	public synchronized void removeAll() {
		WORKERS.clear();
		NEXT_CID_TO_BE_USED = 1;
	}

	/**
	 * @see Repository#getAll()
	 */
	@Override
	public AWorker[] getAll() {
		AWorker[] all = new AWorker[this.size()];
		int i = -1;
		for (AWorker ele : WORKERS.values()) {
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
		for (AppElement ele : WORKERS.values()) {
			json.accumulate("All workers", ele.getJson());
		}
		return json;
	}

}
