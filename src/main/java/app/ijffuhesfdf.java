package app;

import java.lang.reflect.Field;

import app.commands.BaseCommand;
import app.commands.Command;
import app.elements.ImmutableAdmin;
import app.elements.User;

public class ijffuhesfdf
{

	public static void main (String[] args)
	{
		
		User a = new User("w","Edddddd","Q@s.c","S");
		
		
		Class<? extends User> b = a.getClass();
		
		Field[] c = b.getDeclaredFields();
		
		for(Field d:c)
			System.out.println(d.toString());
		
	}
}
