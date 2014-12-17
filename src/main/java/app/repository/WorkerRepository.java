package app.repository;

import utils.AWorker;
import utils.Consultant;
import utils.Leader;
import app.elements.DatabaseElement;

/**
 * The interface to be implemented by all {@link AWorker}s {@link Repository}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface WorkerRepository extends Repository<DatabaseElement>
{

	/**
	 * @return The next available CID ({@link AWorker} ID, there can't be more
	 *         than one {@code AWorker} with the same CID).
	 */
	public long nextCID();

	/**
	 * Method that adds a Manager {@code Leader} to the repository.
	 * 
	 * @param manager
	 *            The Manager to add.
	 * @return True if successful, False if not.
	 */
	public boolean addManager(Leader manager);

	/**
	 * Method that adds a {@code Consultant} to the repository.
	 * 
	 * @param consultant
	 *            The Consultant to add.
	 * @return True if successful, False if not.
	 */
	public boolean addConsultant(Consultant consultant);

	/**
	 * Get's the {@code Consultant} with the supplied CID from the repository.
	 * 
	 * @param cid
	 *            The CID of the Consultant to search.
	 * @return The Consultant with the supplied CID, or null if none.
	 */
	public Consultant getConsultantByID(long cid);

	/**
	 * Get's the Manager ({@link Leader}) with the supplied CID from the
	 * repository.
	 * 
	 * @param cid
	 *            The CID of the Manager to search.
	 * @return The Manager with the supplied CID, or null if none.
	 */
	public Leader getManagerByID(long cid);
	
	/**
	 * Get's the Worker ({@link AWorker}) with the supplied CID from the
	 * repository.
	 * 
	 * @param cid
	 *            The CID of the Worker to search.
	 * @return The Worker with the supplied CID, or null if none.
	 */
	public AWorker getAWorkerByID(long cid);

}
