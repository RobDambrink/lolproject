package logica;

import databaseConnection.Hibernate;
import mappingHibernate.BuildComment;
import mappingHibernate.Comments;
import mappingHibernate.MasteryComment;
import mappingHibernate.RuneComment;

public class CommentLogic {
	public static final String ITEMBULD ="item";
	public static final String RUNESBULD ="rune";
	public static final String MASTERYBULD ="mastery";
	
	private Hibernate hib;
	public CommentLogic(Hibernate hib){
		this.hib=hib;
		addComment(ITEMBULD, "bla bla test item", 1L, 1L);
		addComment(RUNESBULD, "bla bla test rune", 1L, 2L);
		addComment(MASTERYBULD, "bla bla test mastery", 1L, 3L);
	}
	
	/**
	 * 
	 * @param kind this is the kind of object where is comment on. for example item,mastery etc
	 * @param accountId the accountId
	 * @param Id this is the id of the page you are commenting on
	 * @param comment the comment
	 */
	public void addComment(String kind, String comment, Long accountId, Long id){
		 switch (kind) {
         case ITEMBULD:	
        	 Comments com = addCommentOnly(comment, accountId);
        	 BuildComment build = new BuildComment();
        	 build.setBuildid(id);
        	 build.setCommentid(com.getId());
        	 hib.addToDatabase(build);
        	 // TODO CONTROLEREN OF HET BESTAAD?
        	 break;
         case RUNESBULD:	
        	 com = addCommentOnly(comment, accountId);
        	 RuneComment rune = new RuneComment();
        	 rune.setCommentid(com.getId());
        	 rune.setRuneid(id);
        	 hib.addToDatabase(rune);
        	 // TODO CONTROLEREN OF HET BESTAAD?
        	 break;
         case MASTERYBULD:	
        	 com = addCommentOnly(comment, accountId);
        	 MasteryComment mas = new MasteryComment();
        	 mas.setCommentid(com.getId());
        	 mas.setMasteryid(id);
        	 hib.addToDatabase(mas);
        	 // TODO CONTROLEREN OF HET BESTAAD?
        	 break;
		 }
	}
	
	private Comments addCommentOnly(String comment, Long accountId){
		Comments com = new Comments();
		com.setAccountId(accountId);
		com.setComment(comment);
		hib.addToDatabase(com);
		return com;
	}
}
