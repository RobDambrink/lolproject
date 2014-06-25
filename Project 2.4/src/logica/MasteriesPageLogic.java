package logica;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import mappingHibernate.MasteryPageDatabase;
import databaseConnection.Hibernate;
import exeption.MasteryNotExist;

public class MasteriesPageLogic {
	private Hibernate hib;
	
	public MasteriesPageLogic(Hibernate hib){
		this.hib=hib;
		/*HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		hm.put(4111, 1);
		hm.put(4112, 2);
		hm.put(4113, 3);
		makeMasteryPage("testPage",hm,1l,1l);
		editMasteryPage(1L, "testPageEdit2", hm);*/
		/*System.out.println(getMasteryBuldByItemBuldId(1l));
		System.out.println(getMasteryBuldByChampionId(1l));
		System.out.println(getMasteryBuldByAccountId(1l));
		System.out.println(getMasteryBuldByAccountIdChampionId(1l,1l));*/
	}
	
	public JSONObject getMasteryBuldByItemBuldId(Long id){
		return getMasteryBuldByQuery("FROM MasteryPageDatabase WHERE id = " + id + "");
	}
	
	
	public JSONObject getMasteryBuldByChampionId(Long championId){
		return getMasteryBuldByQuery("FROM MasteryPageDatabase WHERE championId = " + championId + "");
	}
	
	public JSONObject getMasteryBuldByAccountId(Long accountId){
		return getMasteryBuldByQuery("FROM MasteryPageDatabase WHERE accountId =" + accountId +"");
	}
	
	public JSONObject getMasteryBuldByAccountIdChampionId(Long accountId, Long championId){
		return getMasteryBuldByQuery("FROM MasteryPageDatabase WHERE accountId =" + accountId +" AND championId = " + championId + "");
	}
	
	public JSONObject getMasteryBuldByQuery(String query){
		List<?> list = hib.getDataFromDatabase(query);
		if (list!=null && !list.isEmpty()){
			JSONObject obj = new JSONObject();
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj2 = new JSONObject();
				MasteryPageDatabase build = (MasteryPageDatabase)list.get(i);
				obj2.put("name", build.getName());
				obj2.put("accountId", build.getAccountId());
				obj2.put("championId", build.getChampionId());
				obj2.put("id", build.getId());
				MasteryPage page = (MasteryPage)(ObjectToByteConvert.ByteToObject(build.getMasteriesPages()));
				obj2.put("pages", page.getJSON());
				obj.put(build.getId().toString(), obj2);
			}
			return obj;
		}
		return null;
	}
	
	public void editMasteryPage(Long id, String name, HashMap<Integer,Integer> masteries){
		MasteryPageDatabase page =  getMasteryPage(id);
		page.setName(name);
		page.setMasteriesPages(ObjectToByteConvert.ObjectToByteArray(masteries));
		hib.updateToDatabse(page);
	}
	
	@SuppressWarnings("rawtypes")
	public MasteryPageDatabase getMasteryPage(Long id){
		List list = hib.getDataFromDatabase("FROM MasteryPageDatabase WHERE id=" + id + "");
		MasteryPageDatabase page=null;
		if (list!=null && !list.isEmpty()){
			page = (MasteryPageDatabase) list.get(0);
		}
		return page;
	}
	
	
	/**
	 * Make a new MasteryPage
	 * @param name
	 * @param masteries <id,rank>
	 * @throws MasteryNotExist 
	 */
	public void makeMasteryPage(String name, HashMap<Integer,Integer> masteries, Long championId, Long accountId){		
		MasteryPageDatabase mastery = new MasteryPageDatabase();
		mastery.setName(name);
		mastery.setAccountId(accountId);
		mastery.setMasteriesPages(ObjectToByteConvert.ObjectToByteArray(masteries));
		mastery.setChampionId(championId);
		hib.addToDatabase(mastery);
	}
	
	public void removeMasteryPage(Long id){
		hib.deleteFromDatabase("FROM MasteryPageDatabase WHERE id = " + id + "");
	}
}
