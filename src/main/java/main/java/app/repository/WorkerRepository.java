package main.java.app.repository;

import main.java.app.elements.DatabaseElement;
import main.java.utils.AWorker;
import main.java.utils.Consultant;
import main.java.utils.Leader;

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

}