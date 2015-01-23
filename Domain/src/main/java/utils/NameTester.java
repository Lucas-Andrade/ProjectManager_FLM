package utils;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Abstract class whose purpose will be to validate if a {@code Project} with a
 * given name already exists as a Sub{@code Project} of another {@code Project}.
 */
public abstract class NameTester{
	private static final Collection<String> USED_NAMES = new TreeSet<>();

	public static boolean addName(String name){
		return USED_NAMES.add(name);
	}

	public static boolean removeName(String name){
		return USED_NAMES.remove(name);
	}

	public static void removeAll(){
		USED_NAMES.clear();
	}

	public static Collection<String> getUsedNames(){
		return Collections.unmodifiableCollection(USED_NAMES);
	}

	@Override
	public String toString(){

		String names = "";

		for (String name : USED_NAMES){
			names += name.toString();
		}
		return names;
	}
}
