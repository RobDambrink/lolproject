package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class Accounts {
	private Long id;
	private String name;
	private String password;
	private Long summonerID;
	
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
			if (name.length()<=55)
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
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		try {
			if (String.valueOf(id).length()<=11)
				this.id = id;
			else
				throw new ToLargeVariableExeption("You have a to long id");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		try {
			if (password.length()<=32)
				this.password = password;
			else{
				throw new ToLargeVariableExeption("You have a to long password");
			}
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}

	/**
	 * @return the summonerID
	 */
	public Long getSummonerID() {
		return summonerID;
	}

	/**
	 * @param summonerID the summonerID to set
	 */
	public void setSummonerID(Long summonerID) {
		if (summonerID!=null){
			try {
				if (String.valueOf(summonerID).length()<=11)
					this.summonerID = summonerID;
				else
					throw new ToLargeVariableExeption("You have a to long summoner id");
			} catch (ToLargeVariableExeption e) {
				e.getMessage();
			}
		}
		else{
			this.summonerID=null;
		}
	}
	
}
