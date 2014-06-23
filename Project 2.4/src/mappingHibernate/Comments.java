package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class Comments {
	private Long id;
	private Long accountId;
	private String comment;
	
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
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
