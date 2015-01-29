package utils;

/**
 * Class that will represent a container of projects. Extends
 * AContainer<AWorker>.
 * 
 * Implements the Interface {@link IName}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class Team extends AContainer<AWorker> implements IName {

	/**
	 * @field name - the name of the team.
	 */
	private String name;

	/**
	 * Override of the method {@code getELementByName()} from {@code AContainer}
	 * .
	 */
	public UtilsElement getElementByName(String name){

		if (name == null){
			throw new IllegalArgumentException();
		}
		for (UtilsElement element : this.getElementsList()){
			if (element.getName().equals(name)){
				return element;
			}
		}

		return null;
	}

	/**
	 * Override of the method {@code getName()} from the {@code IName}
	 * Interface.
	 */
	@Override
	public String getName(){
		return name;
	}
}