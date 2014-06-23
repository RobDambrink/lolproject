package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class MatchHistory {
	private Long id;
	private Long summonerId;
	private Long gameId;
	

	/**
	 * @return the summonerId
	 */
	public Long getSummonerId() {
		return summonerId;
	}

	/**
	 * @param summonerId the summonerId to set
	 */
	public void setSummonerId(Long summonerId) {
		try {
			if (String.valueOf(summonerId).length()<=20)
				this.summonerId = summonerId;
			else
				throw new ToLargeVariableExeption("You have a to long summonerId");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}

	/**
	 * @return the gameId
	 */
	public Long getGameId() {
		return gameId;
	}

	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(Long gameId) {
		try {
			if (String.valueOf(gameId).length()<=20)
				this.gameId = gameId;
			else
				throw new ToLargeVariableExeption("You have a to long gameId");
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
		this.id = id;
	}
	
}
