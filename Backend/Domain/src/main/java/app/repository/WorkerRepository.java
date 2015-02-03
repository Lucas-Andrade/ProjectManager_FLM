package app.repository;

import utils.AWorker;
import utils.Consultant;
import utils.Leader;
import app.AppElement;
import app.elements.mutable.ConsultantCreationDescriptor;
import app.elements.mutable.LeaderCreationDescriptor;

/**
 * The interface to be implemented by all {@link AWorker}s {@link Repository}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface WorkerRepository extends Repository<AppElement> {

	/**
	 * Method that creates and adds a Manager ({@code Leader}) to the repository.
	 * 
	 * @param manager
	 *            The {@code leaderCreationDescriptor} with the information
	 *            necessary to create the Manager ({@code Leader}) to add.
	 * @return The CID if successful, null if not.
	 */
	public Long addManager(LeaderCreationDescriptor manager);

	/**
	 * Method that creates and adds a {@code Consultant} to the repository.
	 * 
	 * @param consultant
	 *            The {@code consultantCreationDescriptor} with the information
	 *            necessary to create the {@code Consultant} to add.
	 * @return The CID if successful, null if not.
	 */
	public Long addConsultant(ConsultantCreationDescriptor consultant);

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
