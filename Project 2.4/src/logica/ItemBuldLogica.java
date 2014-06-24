package logica;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import mappingHibernate.Build;
import databaseConnection.Hibernate;
import exeption.ItemNotExist;

public class ItemBuldLogica {
	private String namePage;
	private Long accountId;
	private Long championId;
	private HashMap<String,Long[]> hm = new HashMap<String, Long[]>();
	private Hibernate hib;
	
	public ItemBuldLogica(Hibernate hib) throws ItemNotExist{
		this.hib=hib;
		//makeItemBuldPage("testName", "itemName", new Long[] {1004L,1001L},1L,1L);
		//saveItemBuldPage();
		//editItemBuldPage(1l, "testNaamm", "itemName", new Long[] {1004L,1001L},1L);
		//removeItemBuldPage(3L);
		//getItemBuld(1l, 1l);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getItemBuld(Long accoutId, Long championId){
		List<?> list = hib.getDataFromDatabase("FROM Build WHERE accountId =" + accoutId +" AND championId = " + championId + "");
		if (list!=null && !list.isEmpty()){
			JSONObject obj = new JSONObject();
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj2 = new JSONObject();
				Build build = (Build)list.get(i);
				obj2.put("name", build.getName());
				obj2.put("accountId", build.getAccountId());
				obj2.put("championId", build.getChampionId());
				obj2.put("id", build.getId());
				obj2.put("pages", (HashMap<String,Long[]>)ObjectToByteConvert.ByteToObject(build.getItemPages()));
				obj.put(build.getId().toString(), obj2);
			}
		}
		return null;
	}
	
	public void editItemBuldPage(Long id, String name, String itemName, Long[] items, Long championId) throws ItemNotExist{
		Build build = getBuild(id);
		addItemBlok(namePage, items);
		build.setItemPages(ObjectToByteConvert.ObjectToByteArray(hm));
		build.setChampionId(championId);
		build.setName(name);
		hib.updateToDatabse(build);
	}	
	
	public void makeItemBuldPage(String namePage, String itemName, Long[] items, Long accountId, Long championId) throws ItemNotExist{
		this.namePage=namePage;
		this.accountId=accountId;
		this.championId=championId;
		addItemBlok(namePage, items);
	}
	
	public void saveItemBuldPage(){
		Build build = new Build();
		build.setAccountId(accountId);
		build.setName(namePage);
		build.setItemPages(ObjectToByteConvert.ObjectToByteArray(hm));
		build.setChampionId(championId);
		hib.addToDatabase(build);
	}
	
	public void removeItemBuldPage(Long id){
		hib.deleteFromDatabase("FROM Build WHERE id =" + id + "");
		
	}
	
	/**
	 * 
	 * @param name The name is the name of the item block, like Startup, def etc
	 * @param items This is a long array with the id of the items
	 * @throws ItemNotExist 
	 */
	public void addItemBlok(String name, Long[] items) throws ItemNotExist{
		for (int i = 0; i < items.length; i++) {
			if (hib.getOneValueFromTheDatabase("SELECT id FROM ItemNameId WHERE id = " + items[i] + ")")==null)
				throw new ItemNotExist("This item does not exist");
		}
		hm.put(name, items);
	}
	
	@SuppressWarnings("rawtypes") 
	Build getBuild(Long id){
		List list = hib.getDataFromDatabase("FROM Build WHERE id=" + id + "");
		Build page=null;
		if (list!=null && !list.isEmpty()){
			page = (Build) list.get(0);
		}
		return page;
	}
}
