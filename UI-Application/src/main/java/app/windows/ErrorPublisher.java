package app.windows;

public abstract class ErrorPublisher extends PublishWithLoadingDialog{
	
	public abstract void publish(String message);
}
