package app.resultsAndOutputMethods.outputDestination;

/**
 * Class that sends the results to console.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public class ToConsole implements Writable
{

	/**
	 * @see Writable#write(String)
	 */
	@Override
	public void write(String info)
	{
		System.out.println(info);
	}

}