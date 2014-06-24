package logica;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;
import databaseConnection.Hibernate;
import exeption.ItemNotExist;
import exeption.MasteryNotExist;
import exeption.PageNotExist;
import mappingHibernate.BuildComment;
import mappingHibernate.Comments;
import mappingHibernate.MasteryComment;
import mappingHibernate.RuneComment;

public class CommentLogic {
	public static final String ITEMBULD ="item";
	public static final String RUNESBULD ="rune";
	public static final String MASTERYBULD ="mastery";
	
	private Hibernate hib;
	public CommentLogic(Hibernate hib) throws PageNotExist, ItemNotExist, MasteryNotExist{
		this.hib=hib;
		addComment(ITEMBULD, "bla bla test item", 1L, 1L);
		addComment(RUNESBULD, "bla bla test rune", 1L, 1L);
		addComment(MASTERYBULD, "bla bla test mastery", 1L, 1L);
		System.out.println(getComments(1l,ITEMBULD));
		System.out.println(getComments(2l,RUNESBULD));
		System.out.println(getComments(3l,MASTERYBULD));
	}
	
	/**
	 * 
	 * @param kind this is the kind of object where is comment on. for example item,mastery etc
	 * @param accountId the accountId
	 * @param Id this is the id of the page you are commenting on
	 * @param comment the comment
	 * @throws PageNotExist 
	 * @throws ItemNotExist 
	 * @throws MasteryNotExist 
	 */
	public void addComment(String kind, String comment, Long accountId, Long id) throws PageNotExist, ItemNotExist, MasteryNotExist{
		 switch (kind) {
         case ITEMBULD:	
        	 ItemBuldLogica itemBuld = new ItemBuldLogica(hib);
        	 if(itemBuld.getBuild(id)!=null){
	        	 Comments com = addCommentOnly(comment, accountId);
	        	 BuildComment build = new BuildComment();
	        	 build.setBuildid(id);
	        	 build.setCommentid(com.getId());
	        	 hib.addToDatabase(build);
        	 }
        	 else
        		 throw new PageNotExist("This itemBuldPage Does not exist");
        	 break;
         case RUNESBULD:
        	 RunePageLogic runePage = new RunePageLogic(hib);
        	 if(runePage.getRunePage(id)!=null){
        		 Comments com = addCommentOnly(comment, accountId);
	        	 RuneComment rune = new RuneComment();
	        	 rune.setCommentid(com.getId());
	        	 rune.setRuneid(id);
	        	 hib.addToDatabase(rune);
        	 }
        	 else
        		 throw new PageNotExist("This runePage does not exist");
        	 break;
         case MASTERYBULD:	
        	 MasteriesPageLogic masteryPage = new MasteriesPageLogic(hib);
        	 if(masteryPage.getMasteryPage(id)!=null){
        		 Comments com = addCommentOnly(comment, accountId);
	        	 MasteryComment mas = new MasteryComment();
	        	 mas.setCommentid(com.getId());
	        	 mas.setMasteryid(id);
	        	 hib.addToDatabase(mas);
        	 }
        	 else
        		 throw new PageNotExist("This masteryPage does not exist");
        	 break;
		 }
	}
	
	public JSONObject getComments(Long id, String name){
		HashMap<Long, Comments> hm = new HashMap<Long, Comments>();
		switch (name) {
	        case ITEMBULD:	
	        	List<?> list = hib.getDataFromDatabase("FROM BuildComment WHERE buildid =" + id + "");
	       	 	if (list!=null && !list.isEmpty()){
	       	 		for (int i = 0; i < list.size(); i++) {
	       	 			BuildComment comment = (BuildComment) list.get(i);
	       	 			hm.put(comment.getCommentid(), getCommentById(comment.getCommentid()));
	       	 		}
	       	 	}
	       	 	else
	       	 		return null;
       	 	break;
	        case RUNESBULD:	
	        	list = hib.getDataFromDatabase("FROM RuneComment WHERE runeid =" + id + "");
	       	 	if (list!=null && !list.isEmpty()){
	       	 		for (int i = 0; i < list.size(); i++) {
	       	 			RuneComment comment = (RuneComment) list.get(i);
	       	 			hm.put(comment.getCommentid(), getCommentById(comment.getCommentid()));
	       	 		}
	       	 	}
	       	 	else
	       	 		return null;
       	 	break;
	        case MASTERYBULD:	
	        	list = hib.getDataFromDatabase("FROM MasteryComment WHERE masteryid =" + id + "");
	       	 	if (list!=null && !list.isEmpty()){
	       	 		for (int i = 0; i < list.size(); i++) {
	       	 			MasteryComment comment = (MasteryComment) list.get(i);
	       	 			hm.put(comment.getCommentid(), getCommentById(comment.getCommentid()));
	       	 		}
	       	 	}
	       	 	else
	       	 		return null;
       	 	break;
		}
		JSONObject obj = new JSONObject();
		obj.putAll(hm);
		return obj;
	}
	
	@SuppressWarnings("rawtypes")
	private Comments getCommentById(Long id){
		List list = hib.getDataFromDatabase("FROM Comments WHERE id = " + id + "");
		if (list!=null && !list.isEmpty())
			return (Comments)list.get(0);
		return null;
	}
	
	private Comments addCommentOnly(String comment, Long accountId){
		Comments com = new Comments();
		com.setAccountId(accountId);
		com.setComment(comment);
		hib.addToDatabase(com);
		return com;
	}
}
