package app.resultsAndOutputMethods;

import java.io.IOException;

import app.elements.Admin;
import app.elements.DatabaseElement;
import app.elements.ImmutableAdmin;

public class testeResultIntroReflex
{

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, IOException
	{
		DatabaseElement c = new ImmutableAdmin();
		DatabaseElement d = new Admin("aaaaa", "bbbbb");
		DatabaseElement[] b = { c, d };
		Result a = new Result(b, "console", null );
		a.showResults();
	}

}
