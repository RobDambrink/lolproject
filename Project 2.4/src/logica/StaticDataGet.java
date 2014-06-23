package logica;

import java.util.List;

import net.sf.json.JSONObject;
import mappingHibernate.ChampionNameId;
import mappingHibernate.ItemNameId;
import mappingHibernate.MasteryNameId;
import mappingHibernate.RuneNameId;
import mappingHibernate.SummonerSpelNameId;

import com.fourspaces.couchdb.Document;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;

public class StaticDataGet {
	
	private Hibernate hib;
	private CouchDB couch;
	
	public StaticDataGet(Hibernate hib, CouchDB couch){
		this.hib=hib;
		this.couch=couch;
		//getChampionByParselName("ann' and 1=1");
		//getItemByParselName("boot");
		//getMasteryByParselName("cer");
		//getRuneByParselName("lesser");
		//getSummonerSpelByParselName("ig");
		//System.out.println(getChampionByName("Annie"));
		/*System.out.println(getChampionByName("AnnIe"));
		System.out.println(getItemByName("Boots of Speed"));
		System.out.println(getMasteryByName("Double-Edged Sword"));
		System.out.println(getRuneByName("Lesser Mark of Attack Damage"));
		System.out.println(getSummonerSpelByName("Cleanse"));
		System.out.println(getChampionByID(1L));
		System.out.println(getItemByID(3105L));
		System.out.println(getMasteryByID(4214L));
		System.out.println(getRuneByID(5063L));
		System.out.println(getSummonerSpelByID(7L));*/
	}
	
	@SuppressWarnings("rawtypes")
	public List getChampionByParselName(String name){
		List list = hib.getDataFromDatabase("from ChampionNameId where LOWER(name) LIKE LOWER('%" + name + "%')");
		if (list!=null && !list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				ChampionNameId champ = (ChampionNameId) list.get(i);
				System.out.println(champ.getName());
				// TODO een array returnen met id/naam is misschien handiger voor web
			}			
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getItemByParselName(String name){
		List list = hib.getDataFromDatabase("from ItemNameId where LOWER(name) LIKE LOWER('%" + name + "%')");
		if (list!=null &&!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				ItemNameId champ = (ItemNameId) list.get(i);
				System.out.println(champ.getName());
				// TODO een array returnen met id/naam is misschien handiger voor web
			}
			
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getMasteryByParselName(String name){
		List list = hib.getDataFromDatabase("from MasteryNameId where LOWER(name) LIKE LOWER('%" + name + "%')");
		if (list!=null &&!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				MasteryNameId champ = (MasteryNameId) list.get(i);
				System.out.println(champ.getName());
				// TODO een array returnen met id/naam is misschien handiger voor web
				
				// TODO JSONObject maken
			}
			
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getRuneByParselName(String name){
		List list = hib.getDataFromDatabase("from RuneNameId where LOWER(name) LIKE LOWER('%" + name + "%')");
		if (list!=null &&!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				RuneNameId champ = (RuneNameId) list.get(i);
				System.out.println(champ.getName());
				// TODO een array returnen met id/naam is misschien handiger voor web
			}
			
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getSummonerSpelByParselName(String name){
		List list = hib.getDataFromDatabase("from SummonerSpelNameId where LOWER(name) LIKE LOWER('%" + name + "%')");
		if (list!=null &&!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				SummonerSpelNameId champ = (SummonerSpelNameId) list.get(i);
				System.out.println(champ.getName());
				// TODO een array returnen met id/naam is misschien handiger voor web
			}
			
		}
		return list;
	}
	
	public JSONObject getChampionByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM ChampionNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getChampionByID(id);
	}
	
	public JSONObject getChampionByID(Long id){
		try {
			Document champion = couch.getDataFromDatabase(CouchDB.CHAMPION_ID+id.toString());
			champion.remove("_id");
			champion.remove("_rev");
			return champion.getJSONObject();
		} catch(NullPointerException e) { return null;}
	}
	
	public JSONObject getItemByID(Long id){
		Document item = couch.getDataFromDatabase(CouchDB.ITEM_ID+id.toString());
		item.remove("_id");
		item.remove("_rev");
		return item.getJSONObject();
	}
	
	public JSONObject getItemByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM ItemNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getItemByID(id);
	}
	
	public JSONObject getMasteryByID(Long id){
		Document mastery = couch.getDataFromDatabase(CouchDB.MASTERY_ID+id.toString());
		mastery.remove("_id");
		mastery.remove("_rev");
		return mastery.getJSONObject();
	}
	
	public JSONObject getMasteryByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM MasteryNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getMasteryByID(id);
	}
	
	public JSONObject getRuneByID(Long id){
		Document rune = couch.getDataFromDatabase(CouchDB.RUNE_ID+id.toString());
		rune.remove("_id");
		rune.remove("_rev");
		return rune.getJSONObject();
	}
	
	public JSONObject getRuneByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM RuneNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getRuneByID(id);
	}
	
	public JSONObject getSummonerSpelByID(Long id){
		Document summonerSpel = couch.getDataFromDatabase(CouchDB.SUMMONER_SPEL_ID+id.toString());
		summonerSpel.remove("_id");
		summonerSpel.remove("_rev");
		return summonerSpel.getJSONObject();
	}
	
	public JSONObject getSummonerSpelByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM SummonerSpelNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getSummonerSpelByID(id);
	}
}
