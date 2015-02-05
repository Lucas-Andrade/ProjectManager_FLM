package guiElements.mainFrameAL;

import guiElements.Authentication;
import guiElements.commandWindowsAL.commandWindows.AuthenticationDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Class responsible for implementing the {@link ActionListener}s for the
 * {@link MainFrame}. Checks if there is an authenticated {@code User} in
 * {@code Authentication}, if yes calls the method {@code this#action()}, if not
 * calls the {@code AuthenticationDialog}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public abstract class MainFrameActionListener implements ActionListener {

	/**
	 * Checks if there is an authenticated {@code User} in
	 * {@code Authentication}, if yes calls the method {@code this#action()}, if
	 * not calls the {@code AuthenticationDialog}.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		if (Authentication.isAuthenticated()){
			this.action();
		} else {
			new AuthenticationDialog().setVisible(true);
		}
	}

	/**
	 * The action to perform if an {@code User} is authenticated.
	 */
	abstract void action();

}