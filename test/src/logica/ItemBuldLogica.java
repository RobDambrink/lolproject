package logica;

import java.util.HashMap;

import mappingHibernate.Build;
import databaseConnection.Hibernate;

public class ItemBuldLogica {
	private String namePage;
	private Long accountId;
	private Long championId;
	private HashMap<String,Long[]> hm = new HashMap<String, Long[]>();
	private Hibernate hib;
	
	public ItemBuldLogica(Hibernate hib){
		this.hib=hib;
		makeItemBuldPage("testName", "itemName", new Long[] {12L,11L},1L,1L);
		saveItemBuldPage();
		// TODO edit itembuld
	}
	
	public void makeItemBuldPage(String namePage, String itemName, Long[] items, Long accountId, Long championId){
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
	
	/**
	 * 
	 * @param name The name is the name of the item block, like Startup, def etc
	 * @param items This is a long array with the id of the items
	 */
	public void addItemBlok(String name, Long[] items){
		// TODO CHECK IF ITEM EXIST
		hm.put(name, items);
	}
	
}
