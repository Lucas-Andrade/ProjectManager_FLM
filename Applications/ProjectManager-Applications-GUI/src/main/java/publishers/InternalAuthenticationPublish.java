package publishers;

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
public class InternalAuthenticationPublish extends PublishWithLoadingDialog implements ResultsPublisher{

	/**
	 * Will not publish publish data into a frame or panel. Only authenticates the user.
	 */
	@Override
	public void publish(String element) {
		try {
			Authentication.authenticatePossible();
		} catch(IllegalStateException e) {
			new ErrorDialog("Could not authenticate.").setVisible(true);
		}
		disposeLoadingDialog();
	}

}
