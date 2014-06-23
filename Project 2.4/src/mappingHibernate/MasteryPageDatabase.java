package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class MasteryPageDatabase {
	private Long id;
	private String name;
	private byte[] masteriesPages;
	private Long accountId;
	
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
	 * @return the masteriesPages
	 */
	public byte[] getMasteriesPages() {
		return masteriesPages;
	}

	/**
	 * @param masteriesPages the masteriesPages to set
	 */
	public void setMasteriesPages(byte[] masteriesPages) {
		this.masteriesPages = masteriesPages;
	}
	
}
