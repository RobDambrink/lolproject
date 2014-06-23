package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class Summoner {
	private Long id;
	private String name;
	private Long profileIconId;
	private Long revisionDate;
	private Integer summonerLevel;
	
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
	 * @return the profileIconId
	 */
	public Long getProfileIconId() {
		return profileIconId;
	}

	/**
	 * @param profileIconId the profileIconId to set
	 */
	public void setProfileIconId(Long profileIconId) {
		try {
			if (String.valueOf(profileIconId).length()<=11)
				this.profileIconId = profileIconId;
			else
				throw new ToLargeVariableExeption("You have a to long prfileIconId");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}

	/**
	 * @return the revisionDate
	 */
	public Long getRevisionDate() {
		return revisionDate;
	}

	/**
	 * @param revisionDate the revisionDate to set
	 */
	public void setRevisionDate(Long revisionDate) {
		try {
			if (String.valueOf(revisionDate).length()<=20)
				this.revisionDate = revisionDate;
			else
				throw new ToLargeVariableExeption("You have a to revisionDate");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}

	/**
	 * @return the summonerLevel
	 */
	public Integer getSummonerLevel() {
		return summonerLevel;
	}

	/**
	 * @param summonerLevel the summonerLevel to set
	 */
	public void setSummonerLevel(Integer summonerLevel) {
		try {
			if (String.valueOf(summonerLevel).length()<=2)
				this.summonerLevel = summonerLevel;
			else
				throw new ToLargeVariableExeption("You have a to revisionDate");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
	
}
