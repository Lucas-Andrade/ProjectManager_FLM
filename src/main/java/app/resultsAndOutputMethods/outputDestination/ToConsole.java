package app.resultsAndOutputMethods.outputDestination;

public class ToConsole implements Writable{

	@Override
	public void write(String info) {
		System.out.println(info);
	}
}
