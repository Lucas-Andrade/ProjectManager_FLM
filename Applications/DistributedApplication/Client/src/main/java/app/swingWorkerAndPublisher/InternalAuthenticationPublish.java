package app.swingWorkerAndPublisher;

import app.PossibleAuthentication;
import java.lang.IllegalStateException;

import guiElements.Authentication;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

/**
 * A publisher class with the express purpose of not publishing.
 * Instead, the {@code Authentication} class is updated.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 05/02/2015
 *
 */
public class InternalAuthenticationPublish extends ResultsPublisherWithLoadingDialog{

	/**
	 * Will not publish in the interface. This is just used to publish the authentication
	 * to the {@code Authentication} class.
	 */
	@Override
	public void publish(String elementString)
	{
		if(resultWasError(elementString, "The username or password you introduced was wrong.")) {
			return;
		}
		
		try {
			String name = PossibleAuthentication.getName();
			String pass = PossibleAuthentication.getPassword();
			Authentication.authenticate(name, pass);
			
		} catch (IllegalStateException e) {
			new ErrorDialog("Could not authenticate.").setVisible(true);
		}
		
		disposeLoadingDialog();
	}
}
