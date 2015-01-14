package app.framesAndPanels;

import java.awt.event.ActionListener;

import javax.swing.JDialog;

import app.commands.Authentication;

public class AuthenticationDialog extends JDialog{

	private static final long serialVersionUID = 9190969392304934338L;
	
	public AuthenticationDialog() {
		ActionListener coisa = new Authentication.AuthenticateActionListener("tjzgf", "fykhj");
	}

}
