package app.resultsOutputMethods;

import java.io.IOException;

public class testeToDelete
{

	public static void main(String[] args) throws IOException
	{
		ResultOutputAsStringToStream out = new ResultOutputAsStringToStream(System.out);
		
		boolean a = true;
		byte b = 1;
		
		Object[] c = {a, b};
		
		out.giveResults(c);
		out.giveResults(a, b);
	}

}
