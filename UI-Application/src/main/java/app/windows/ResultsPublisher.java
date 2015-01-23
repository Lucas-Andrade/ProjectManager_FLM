package app.windows;

import utils.AWorker;
import app.AppElement;
import app.elements.IUser;

/**
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 *
 */
public abstract class ResultsPublisher extends PublishWithLoadingDialog{
	
	public abstract void publish(AppElement[] appElements);
	
	protected IUser[] castToIUser(AppElement[] elements){
		IUser[] toReturn = new IUser[elements.length];
		for(int i = 0; i < elements.length; i++) {
			toReturn[i] = (IUser) elements[i];
		}
		return toReturn;
	}
	
	protected AWorker[] castToAWorker(AppElement[] elements){
		AWorker[] toReturn = new AWorker[elements.length];
		for(int i = 0; i < elements.length; i++) {
			toReturn[i] = (AWorker) elements[i];
		}
		return toReturn;
	}
	
}
