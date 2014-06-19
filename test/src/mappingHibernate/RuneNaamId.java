package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class RuneNaamId {
	private int id;
	private String name;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param userName the userName to set
	 */
	public void setName(String name) {
		try {
			if (name.length()<=100)
				this.name = name;
			else{
				throw new ToLargeVariableExeption("You have a to long name");
			}
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		try {
			if (String.valueOf(id).length()<=11)
				this.id = id;
			else
				throw new ToLargeVariableExeption("You have a to long id");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
	
}
