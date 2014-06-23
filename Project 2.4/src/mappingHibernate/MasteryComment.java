package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class MasteryComment {
	private Long commentid;
	private Long masteryid;
	
	/**
	 * @return the commentid
	 */
	public Long getCommentid() {
		return commentid;
	}
	/**
	 * @param commentid the commentid to set
	 */
	public void setCommentid(Long commentid) {
		try {
			if (String.valueOf(commentid).length()<=20)
				this.commentid = commentid;
			else
				throw new ToLargeVariableExeption("You have a to long buildid");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
	/**
	 * @return the masteryid
	 */
	public Long getMasteryid() {
		return masteryid;
	}
	/**
	 * @param buildid the masteryid to set
	 */
	public void setMasteryid(Long masteryid) {
		try {
			if (String.valueOf(masteryid).length()<=20)
				this.masteryid = masteryid;
			else
				throw new ToLargeVariableExeption("You have a to long buildid");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
}
	