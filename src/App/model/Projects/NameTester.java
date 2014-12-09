package App.model.Projects;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Abstract class whose purpose will be to validate if a project with a given name already exists or
 * can be added to a super project at any level.
 */
public abstract class NameTester {

	private static final Collection<String> usedNames = new TreeSet<>();

	public static boolean addName(String name) {

		return usedNames.add(name);
	}

	public static boolean removeName(String name) {

		return usedNames.remove(name);
	}
	
	public static void removeAll() {
		
		usedNames.clear();
	}

	public static Collection<String> getUsedNames() {

		return Collections.unmodifiableCollection(usedNames);
	}

	@Override
	public String toString() {

		String names = "";

		for (String name : usedNames)
			names += name.toString();

		return names;
	}
}
