package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class Build {
	private Long id;
	private String name;
	private byte[] itemPages;
	private Long accountId;
	private Long championId;
	
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
			if (String.valueOf(id).length()<=20)
				this.id = id;
			else
				throw new ToLargeVariableExeption("You have a to long id");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}

	/**
	 * @return the accountid
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountid the accountid to set
	 */
	public void setAccountId(Long accountId) {
		try {
			if (String.valueOf(accountId).length()<=11)
				this.accountId = accountId;
			else
				throw new ToLargeVariableExeption("You have a to long account id");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}	
	}

	/**
	 * @return the imagepages
	 */
	public byte[] getItemPages() {
		return itemPages;
	}

	/**
	 * @param imagepages the imagepages to set
	 */
	public void setItemPages(byte[] itemPages) {
		this.itemPages = itemPages;
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
				throw new ToLargeVariableExeption("You have a to long champion id");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
	
}
