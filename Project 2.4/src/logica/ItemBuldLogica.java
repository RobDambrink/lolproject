package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import mappingHibernate.Build;
import databaseConnection.Hibernate;
import exeption.ItemNotExist;

public class ItemBuldLogica {
	private List<ItemBuldBlock> itemBlockList = new ArrayList<ItemBuldBlock>();
	private Hibernate hib;
	
	public ItemBuldLogica(Hibernate hib){
		this.hib=hib;
		/*itemBlockList.add(new ItemBuldBlock("test", new Long[] {1001l,1002l}));
		makeItemBuldPage("Namepage", 1l, 1l, itemBlockList);
		editItemBuldPage(1l, "NamepageEdit", itemBlockList);
		/*System.out.println(getItemBuldByItemBuldId(1l));
		System.out.println(getItemBuldByChampionId(1l));
		System.out.println(getItemBuldByAccountId(1l));
		System.out.println(getItemBuldByAccountIdChampionId(1l,1L));*/
	}

	public JSONObject getItemBuldByItemBuldId(Long id){
		return getItemBuldByQuery("FROM Build WHERE id = " + id + "");
	}
	
	public JSONObject getItemBuldByChampionId(Long championId){
		return getItemBuldByQuery("FROM Build WHERE championId = " + championId + "");
	}
	
	public JSONObject getItemBuldByAccountId(Long accountId){
		return getItemBuldByQuery("FROM Build WHERE accountId =" + accountId +"");
	}
	
	public JSONObject getItemBuldByAccountIdChampionId(Long accountId, Long championId){
		return getItemBuldByQuery("FROM Build WHERE accountId =" + accountId +" AND championId = " + championId + "");
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getItemBuldByQuery(String query){
		List<?> list = hib.getDataFromDatabase(query);
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
			return obj;
		}
		return null;
	}
	
	public void makeItemBuldPage(String namePage, Long accountId, Long championId, List<ItemBuldBlock> itemBuldBlock){
		this.itemBlockList=itemBuldBlock;
		Build build = new Build();
		build.setAccountId(accountId);
		build.setChampionId(championId);
		build.setName(namePage);
		build.setItemPages(ObjectToByteConvert.ObjectToByteArray(itemBlockList));
		hib.addToDatabase(build);
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param itemName
	 * @param items
	 * @param championId
	 * @throws ItemNotExist
	 */
	public void editItemBuldPage(Long id, String name, List<ItemBuldBlock> itemBuldBlock){
		Build build = getBuild(id);
		if (build!=null){
			build.setItemPages(ObjectToByteConvert.ObjectToByteArray(itemBuldBlock));
			build.setName(name);
			hib.updateToDatabse(build);
		}
	}	
	
	public void removeItemBuldPage(Long id){
		hib.deleteFromDatabase("FROM Build WHERE id =" + id + "");
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
