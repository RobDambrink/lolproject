package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class MasterypageSummoner {
	private Long id;
	private byte[] pages;
	
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
	 * @return the pages
	 */
	public byte[] getPages() {
		return pages;
	}

	/**
	 * @param b the pages to set
	 */
	public void setPages(byte[] b) {
		this.pages = b;
	}
}
