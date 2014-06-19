package logica;

import net.sf.json.JSONObject;

import com.fourspaces.couchdb.Document;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;

public class StaticDataGet {
	
	private Hibernate hib;
	private CouchDB couch;
	
	public StaticDataGet(Hibernate hib, CouchDB couch){
		this.hib=hib;
		this.couch=couch;
		System.out.println(getChampionByName("AnnIe"));
		System.out.println(getItemByName("Boots of Speed"));
		System.out.println(getMasteryByName("Double-Edged Sword"));
		System.out.println(getRuneByName("Lesser Mark of Attack Damage"));
		System.out.println(getSummonerSpelByName("Cleanse"));
		System.out.println(getChampionByID(1L));
		System.out.println(getItemByID(3105L));
		System.out.println(getMasteryByID(4214L));
		System.out.println(getRuneByID(5063L));
		System.out.println(getSummonerSpelByID(7L));
	}
	
	public JSONObject getChampionByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM ChampionNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getChampionByID(id);
	}
	
	public JSONObject getChampionByID(Long id){
		Document champion = couch.getDataFromDatabase(CouchDB.CHAMPION_ID+id.toString());
		champion.remove("_id");
		champion.remove("_rev");
		return champion.getJSONObject();
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
