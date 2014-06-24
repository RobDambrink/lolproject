package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class RunePageDatabase {
	private Long id;
	private String name;
	private byte[] runes;
	private Long accountId;
	private Long championId;
	
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		try {
			if (name.length()<=55)
				this.name = name;
			else
				throw new ToLargeVariableExeption("You have a to long championId");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
	/**
	 * @return the runes
	 */
	public byte[] getRunes() {
		return runes;
	}
	/**
	 * @param runes the runes to set
	 */
	public void setRunes(byte[] runes) {
		this.runes = runes;
	}
	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		try {
			if (String.valueOf(accountId).length()<=11)
				this.accountId = accountId;
			else
				throw new ToLargeVariableExeption("You have a to long accountId");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
	/**
	 * @return the championId
	 */
	public Long getChampionId() {
		return championId;
	}
	/**
	 * @param championId the championId to set
	 */
	public void setChampionId(Long championId) {
		try {
			if (String.valueOf(championId).length()<=11)
				this.championId = championId;
			else
				throw new ToLargeVariableExeption("You have a to long championId");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
}
	