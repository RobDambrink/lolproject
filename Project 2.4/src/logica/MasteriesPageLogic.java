package logica;

import java.util.List;

import mappingHibernate.MasteryPageDatabase;
import databaseConnection.Hibernate;

public class MasteriesPageLogic {
	private Hibernate hib;
	private String name;
	private MasteryPage masteryPage = new MasteryPage();
	
	public MasteriesPageLogic(Hibernate hib){
		this.hib=hib;
		makeMasteryPage("testPage",null);
		//addMasteryToMasteryPage(1234, 1);
		saveMasteryPage(1L,1L);
		editMasteryPage(1L, "testPageEdit2", new Integer[] {1234,2},1L);
		removeMasteryPage(2L);
	}
	
	public void editMasteryPage(Long id, String name, Integer[] masteries, Long championId){
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
	 * @param masteries
	 */
	public void makeMasteryPage(String name, Integer[] masteries){
		this.name=name;
		masteryPage.setName(name);
		masteryPage.setMasteries(masteries);		
	}

	
	/*public void addMasteryToMasteryPage(Integer MasteryId, Integer MasteryRank){
		masteryPage.addItemToMastery(MasteryId, MasteryRank);
	}*/
	
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
