package app.ideiaParaEscolherParaOndeMandarImprimirAsCoisas;

public class ToConsole implements Writable{

	@Override
	public void write(String info) {
		System.out.println(info);
	}
}
