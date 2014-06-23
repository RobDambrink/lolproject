package mappingHibernate;

import exeption.ToLargeVariableExeption;

public class BuildComment {
	private Long commentid;
	private Long buildid;
	
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
	 * @return the buildid
	 */
	public Long getBuildid() {
		return buildid;
	}
	/**
	 * @param buildid the buildid to set
	 */
	public void setBuildid(Long buildid) {
		try {
			if (String.valueOf(buildid).length()<=20)
				this.buildid = buildid;
			else
				throw new ToLargeVariableExeption("You have a to long buildid");
		} catch (ToLargeVariableExeption e) {
			e.getMessage();
		}
	}
}
	