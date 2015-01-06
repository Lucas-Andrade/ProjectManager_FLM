package app.resultsAndOutputMethods;

import java.io.IOException;

import app.elements.Admin;
import app.elements.AppElement;
import app.elements.ImmutableAdmin;

public class testeResultIntroReflex
{

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, IOException
	{
		AppElement c = new ImmutableAdmin();
		AppElement d = new Admin("aaaaa", "bbbbb");
		AppElement[] b = { c, d };
		Result a = new Result(b, "console", "text/html" );
		a.showResults();
	}

}
