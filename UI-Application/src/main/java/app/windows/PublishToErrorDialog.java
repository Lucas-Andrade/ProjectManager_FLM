package app.windows;

import app.windows.main_frame_al.main_frame.ErrorDialog;

public class PublishToErrorDialog implements ErrorPublisher{

	@Override
	public void publish(String message) {
		new ErrorDialog(message).setVisible(true);
	}

}
