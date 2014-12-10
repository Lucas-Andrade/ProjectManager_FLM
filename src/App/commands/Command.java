package App.commands;


// ainda vai ser alterada

/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command 
{
	void execute();
	// TODO em vez dum OutputStream, não seria melhor um Writer? Nós só queremos
	// uma stream de caracteres, ou no futuro queremos mais?
}
