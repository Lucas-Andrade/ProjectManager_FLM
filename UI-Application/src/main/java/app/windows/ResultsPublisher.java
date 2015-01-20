package app.windows;

import java.util.List;

import app.AppElement;

public interface ResultsPublisher {
	
	public void publish(AppElement[] appElements);

	public void publish(List<String> chunks);
}
