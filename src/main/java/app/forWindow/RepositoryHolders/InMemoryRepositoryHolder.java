package app.forWindow.RepositoryHolders;

import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

/**
 * Implements {@code RepositoryHolder} and uses the instances of {@code InMemoryRepo} to
 * construct a {@code RepositoryHolder}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 11/01/2015
 *
 */
public class InMemoryRepositoryHolder extends RepositoryHolder{

	/**
	 * constructs a {@code InMemoryRepositoryHolder} using new instances of 
	 * {@code InMemoryRepo}
	 */
	public InMemoryRepositoryHolder() {
		super(new InMemoryUserRepo(), 
				new InMemoryProjectRepo(), 
				new InMemoryWorkerRepo());
	}
}
