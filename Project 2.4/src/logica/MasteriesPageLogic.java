package logica;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.JSONObject;

import mappingHibernate.MasteryPageDatabase;
import databaseConnection.Hibernate;
import exeption.MasteryNotExist;

public class MasteriesPageLogic {
	private Hibernate hib;
	private String name;
	private MasteryPage masteryPage = new MasteryPage();
	
	public MasteriesPageLogic(Hibernate hib) throws MasteryNotExist{
		this.hib=hib;
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		hm.put(4111, 1);
		hm.put(4112, 2);
		hm.put(4113, 3);
		makeMasteryPage("testPage",hm);
		addMasteryToMasteryPage(4141, 1);
		saveMasteryPage(1L,1L);
		editMasteryPage(1L, "testPageEdit2", hm,1L);
		removeMasteryPage(2L);
		getMasteryBuld(1l,1l);
	}
	
	public JSONObject getMasteryBuld(Long accoutId, Long championId){
		List<?> list = hib.getDataFromDatabase("FROM MasteryPageDatabase WHERE accountId =" + accoutId +" AND championId = " + championId + "");
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
			System.out.println(obj);
			return obj;
		}
		return null;
	}
	
	public void editMasteryPage(Long id, String name, HashMap<Integer,Integer> masteries, Long championId){
		MasteryPageDatabase page=  getMasteryPage(id);
		page.setName(name);
		MasteryPage page2= new MasteryPage();
		page2.setName(name);
		page2.setMasteries(masteries);
		page.setMasteriesPages(ObjectToByteConvert.ObjectToByteArray(masteryPage));
		page.setChampionId(championId);
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
	public void makeMasteryPage(String name, HashMap<Integer,Integer> masteries) throws MasteryNotExist{
		this.name=name;
		
		masteryPage.setName(name);
		for(Entry<Integer, Integer> entry : masteries.entrySet()) {
			Integer id = entry.getKey();
			Integer rank = entry.getValue();
			addMasteryToMasteryPage(id,rank);
		}
	}

	
	public void addMasteryToMasteryPage(Integer MasteryId, Integer MasteryRank) throws MasteryNotExist{
		if (hib.getOneValueFromTheDatabase("SELECT id FROM MasteryNameId WHERE id = " + MasteryId + ")")==null)
			throw new MasteryNotExist("This item does not exist");
		masteryPage.addItemToMastery(MasteryId, MasteryRank);
	}
	
	public void saveMasteryPage(Long accountId, Long championId){
		MasteryPageDatabase mastery = new MasteryPageDatabase();
		mastery.setName(name);
		mastery.setAccountId(accountId);
		mastery.setMasteriesPages(ObjectToByteConvert.ObjectToByteArray(masteryPage));
		mastery.setChampionId(championId);
		hib.addToDatabase(mastery);
	}
	
	public void removeMasteryPage(Long id){
		hib.deleteFromDatabase("FROM MasteryPageDatabase WHERE id = " + id + "");
	}
}
