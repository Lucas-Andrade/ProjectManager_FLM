package App.commands;

import java.io.IOException;
import java.io.OutputStream;


// ainda vai ser alterada

/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command 
{
	void execute(OutputStream out) throws IOException;
}
