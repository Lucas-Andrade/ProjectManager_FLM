package utils;

// ESTA CLASSE NÃO TEM UTILIZAÇÃO, TALVEZ NO FUTURO...
/**
 * Abstract class {@code PID} whose purpose will be to supply unused IDs to
 * new instances of {@code Project}. All instances of {@code Project} must
 * have a different ID.
 */
public abstract class PID
{
	private static Long nextPIDToUse;
	
	private static void updateNextPIDToUse()
	{
		++nextPIDToUse;
	}
	
	private static void restartNextPIDToUse()
	{
		nextPIDToUse=1l;
	}

	public static long getNextPIDToUseAndUpdate()
	{
		if (nextPIDToUse==null)
			restartNextPIDToUse();
		long next = nextPIDToUse;
		updateNextPIDToUse();
		return next;
	}
}