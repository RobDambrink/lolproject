package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class RuneComment {
	private Long commentid;
	private Long runeid;
	
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
	 * @return the runeid
	 */
	public Long getRuneid() {
		return runeid;
	}
	/**
	 * @param runeid the runeid to set
	 */
	public void setRuneid(Long runeid) {
		try {
			if (String.valueOf(runeid).length()<=20)
				this.runeid = runeid;
			else
				throw new ToLargeVariableExeption("You have a to long buildid");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
}
	