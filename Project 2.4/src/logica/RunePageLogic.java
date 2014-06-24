package logica;

import java.util.List;

import org.json.JSONObject;

import mappingHibernate.RunePageDatabase;
import databaseConnection.Hibernate;

public class RunePageLogic {
	private Hibernate hib;
	public RunePageLogic(Hibernate hib){
		this.hib=hib;
		//makeNewRunePage("testNaam",1L,1L,new Long[] {1l,22l});
		System.out.println(getRuneBuld(1l,1l));
	}
	
	public JSONObject getRuneBuld(Long accoutId, Long championId){
		List<?> list = hib.getDataFromDatabase("FROM RunePageDatabase WHERE accountId =" + accoutId +" AND championId = " + championId + "");
		if (list!=null && !list.isEmpty()){
			JSONObject obj = new JSONObject();
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj2 = new JSONObject();
				RunePageDatabase build = (RunePageDatabase)list.get(i);
				obj2.put("name", build.getName());
				obj2.put("accountId", build.getAccountId());
				obj2.put("championId", build.getChampionId());
				obj2.put("id", build.getId());
				RunePage page = (RunePage)(ObjectToByteConvert.ByteToObject(build.getRunes()));
				obj2.put("pages", page.getJSON());
				obj.put("pages", obj2);
			}
			return obj;
		}
		return null;
	}
	
	/**
	 * 
	 * @param name
	 * @param accountId
	 * @param championId
	 * @param slots this must be in the format [1,22] when the first digit the place is where the rune is placed
	 * the seccond digit is the rune itself
	 */
	public void makeNewRunePage(String name, Long accountId, Long championId, Long[] slots){
		RunePage page = new RunePage();
		page.setName(name);
		page.setSlots(slots);
		RunePageDatabase pageDatabase = new RunePageDatabase();
		pageDatabase.setName(name);
		pageDatabase.setAccountId(accountId);
		pageDatabase.setChampionId(championId);
		pageDatabase.setRunes(ObjectToByteConvert.ObjectToByteArray(page));
		hib.addToDatabase(pageDatabase);
	}
	
	public void editRunePage(Long id,String name, Long championId, Long[] slots){
		RunePageDatabase runePage = getRunePage(id);
		runePage.setChampionId(championId);
		runePage.setName(name);
		RunePage page = new RunePage();
		page.setName(name);
		page.setSlots(slots);
		runePage.setRunes(ObjectToByteConvert.ObjectToByteArray(page));
		hib.updateToDatabse(runePage);
	}
	
	public RunePageDatabase getRunePage(Long id){
		List<?> list = hib.getDataFromDatabase("FROM RunePageDatabase WHERE id=" + id + "");
		RunePageDatabase page=null;
		if (list!=null && !list.isEmpty()){
			page = (RunePageDatabase) list.get(0);
		}
		return page;
	}
	
	public void removeRunePage(Long id){
		hib.deleteFromDatabase("FROM RunePage WHERE id =" + id + "");
	}
	
	
}
