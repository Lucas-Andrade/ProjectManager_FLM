package publishers;

import utils.AWorker;
import app.AppElement;
import app.elements.IUser;

/**
 * Super class of all publishers that publish information about the expected
 * results (when it does not occur any errors of any kind), and will present
 * a {@code LoadingDialog} while lengthy work in background occurs.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public abstract class ResultsPublisherWithLoadingDialog extends PublishWithLoadingDialog implements ResultsPublisher {

	/**
	 * Casts the array of {@code AppElement}s passed as parameter to an array of
	 * {@code IUser}s. If the objects on the array passed as parameter are not
	 * compatible with {@code IUser}, a ClassCastException will be thrown.
	 * 
	 * @param elements
	 *            The array of {@code AppElement}s that is to be cast to
	 *            {@code IUser}.
	 * @return An array of {@code IUser}s.
	 * 
	 * @throws ClassCastException
	 */
	protected IUser[] castToIUser(AppElement[] elements)
			throws ClassCastException {
		IUser[] toReturn = new IUser[elements.length];
		for (int i = 0; i < elements.length; i++) {
			toReturn[i] = (IUser) elements[i];
		}
		return toReturn;
	}

	/**
	 * Casts the array of {@code AppElement}s passed as parameter to an array of
	 * {@code AWorker}s. If the objects on the array passed as parameter are not
	 * compatible with {@code AWorker}, a ClassCastException will be thrown.
	 * 
	 * @param elements
	 *            The array of {@code AppElement}s that is to be cast to
	 *            {@code AWorker}.
	 * @return An array of {@code AWorker}s.
	 * 
	 * @throws ClassCastException
	 */
	protected AWorker[] castToAWorker(AppElement[] elements)
			throws ClassCastException {
		AWorker[] toReturn = new AWorker[elements.length];
		for (int i = 0; i < elements.length; i++) {
			toReturn[i] = (AWorker) elements[i];
		}
		return toReturn;
	}

}
