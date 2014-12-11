
package app.commands;

import java.io.IOException;
import java.io.OutputStream;


// ainda vai ser alterada

/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command 
{
	void execute(OutputStream out) throws IOException;
	// TODO em vez dum OutputStream, não seria melhor um Writer? Nós só queremos
		// uma stream de caracteres, ou no futuro queremos mais?
}


