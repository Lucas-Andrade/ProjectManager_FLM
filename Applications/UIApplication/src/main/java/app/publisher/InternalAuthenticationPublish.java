package app.publisher;

import guiElements.Authentication;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;
import app.AppElement;
import app.elements.IUser;

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
	 * Will not publish.
	 */
	@Override
	public void publish(AppElement[] appElements) {
		disposeLoadingDialog();
		try{
			Authentication.authenticate(((IUser)appElements[0]).getFullName());
		} catch (ClassCastException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		}
	}
}
