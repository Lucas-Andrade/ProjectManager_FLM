package App.model.Projects;

/**
 * Class that will represent a container of projects. Extends AContainer<AWorker>.
 * 
 * Implements the Interface {@link IName}.
 */
public class Team extends AContainer<AWorker> implements IName {
	
	/**
	 * @field name - the name of the team.
	 */
	private String name;

//	/**
//	 * Team constructor that will receive as parameters the team's name and leader.
//	 * 
//	 * Thows {@link IllegalArgumentException} if any of the given parameters are null.
//	 * 
//	 * @param name - the name of the team.
//	 * @param leader - the leader of the team.
//	 */
//	public Team(String name, Leader leader) {
//		
//		if(name == null || leader == null)
//			throw new IllegalArgumentException();
//		
//		this.name = name;
//		addElement(leader);
//	}
		
	

	/**
	 * Override of the method {@code getELementByName()} from {@code AContainer}.
	 */
	public Element getElementByName(String name) {
		
		if(name == null)
			throw new IllegalArgumentException();
		
		for( Element element : this.getElementsList())
			if( element.getName().equals(name) )
				return element;
			
		return null;
	}
	
	/**
	 * Override of the method {@code getName()} from the {@code IName} Interface.
	 */
	@Override
	public String getName() {
		return name;
	}
}