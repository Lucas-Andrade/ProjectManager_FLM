package app.resultsAndOutputMethods;

import java.io.IOException;

import app.elements.Admin;
import app.elements.Element;
import app.elements.ImmutableAdmin;
import app.resultsAndOutputMethods.outputFormat.ToPlainText;

public class testeResultIntroReflex
{

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, IOException
	{
		Element c = new ImmutableAdmin();
		Element d = new Admin("aaaaa", "bbbbb");
		Element[] b = { c, d };
		Result a = new Result(b, "console", null );
		a.showResults();
		
		System.out.println(new ToPlainText()
		.getClass()
		.getName()
		.substring(ToPlainText.class.getName().lastIndexOf('.') + 3));
		String s1 ="a";
		String s2=s1;
		s2="b";
		System.out.print("");
		short l = 3;
	}

}
