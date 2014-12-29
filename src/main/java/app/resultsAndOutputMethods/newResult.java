package app.resultsAndOutputMethods;

import java.io.IOException;
import java.io.OutputStream;

import app.elements.DatabaseElement;
import app.ideiaParaTraduzirAsCoisas.TextParser;
import app.resultsAndOutputMethods.outputDestination.Writable;


public class newResult
{

	private Writable destination;
	private TextParser format;
	
	private String teste;
	
	public newResult(Writable destination, String format) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		//String classFormatPath = format.getClass().getPackage().getName() + ".To" + format.substring(0, 1).toUpperCase() + format.substring(1, format.length()).toLowerCase();
		String classFormatPath = "app.ideiaParaTraduzirAsCoisas" + ".To" + format.substring(0, 1).toUpperCase() + format.substring(1, format.length()).toLowerCase();
		
		Class<TextParser> classFormat = (Class<TextParser>) Class.forName(classFormatPath);
		this.format=classFormat.newInstance();
		
		teste=classFormatPath;
		
		this.destination=destination;
	}
	
	public void showTeste()
	{
		System.out.println(format.parse(null));
		//System.out.println(teste);
	}

}