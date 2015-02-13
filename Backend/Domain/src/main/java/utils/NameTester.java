package utils;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Abstract class whose purpose will be to validate if a {@code Project} with a
 * given name already exists as a Sub{@code Project} of another {@code Project}.
 */
public abstract class NameTester {

	/**
	 * A synchronized sorted TreeSet to store used names.
	 */
	private static final Collection<String> USED_NAMES = Collections
			.synchronizedSortedSet(new TreeSet<>());

	public static boolean addName(String name) {
		return USED_NAMES.add(name);
	}

	public static boolean removeName(String name) {
		return USED_NAMES.remove(name);
	}

	public static void removeAll() {
		USED_NAMES.clear();
	}

	public static Collection<String> getUsedNames() {
		return Collections.unmodifiableCollection(USED_NAMES);
	}

	@SuppressWarnings("unused")
	@Override
	public String toString() {
		String names = "";
		String[] usedNames = null;
		if (USED_NAMES.size() > 0)
			USED_NAMES.toArray(usedNames);
		if (usedNames != null)
			for (String name : usedNames) {
				names += name.toString();
			}
		return names;
	}

}