package logica;

import net.sf.json.JSONObject;

import com.fourspaces.couchdb.Document;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;

public class StaticDataGet {
	
	private Hibernate hib = new Hibernate();
	private CouchDB couch = new CouchDB();
	
	public StaticDataGet(Hibernate hib, CouchDB couch){
		this.hib=hib;
		this.couch=couch;
		System.out.println(getChampionByName("Annie"));
		System.out.println(getItemByID(3105L));
		System.out.println(getMasterysByID(4214L));
		System.out.println(getRuneByID(5063L));
		System.out.println(getSummonerSpelByID(7));
	}
	
	public JSONObject getChampionByName(String name){
		// TODO NU HOOFDLETTER GEVOELIG, WIJZIGEN OF NIET?
		Integer id = (Integer)hib.getOneValueFromTheDatabase("SELECT id FROM ChampionNaamId WHERE name='Annie'");
		return getChampionByID(id);
	}
	
	public JSONObject getChampionByID(Integer id){
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
	
	public void getItemByName(){
		// TODO miss als we sql gaan gebruiken, want het halen uit couch is een beetje vervelend
	}
	
	public JSONObject getMasterysByID(Long id){
		Document mastery = couch.getDataFromDatabase(CouchDB.MASTERY_ID+id.toString());
		mastery.remove("_id");
		mastery.remove("_rev");
		return mastery.getJSONObject();
	}
	
	public void getMasteryByName(){
		// TODO miss als we sql gaan gebruiken, want het halen uit couch is een beetje vervelend
	}
	
	public JSONObject getRuneByID(Long id){
		Document rune = couch.getDataFromDatabase(CouchDB.RUNE_ID+id.toString());
		rune.remove("_id");
		rune.remove("_rev");
		return rune.getJSONObject();
	}
	
	public void getRuneByName(){
		// TODO miss als we sql gaan gebruiken, want het halen uit couch is een beetje vervelend
	}
	
	public JSONObject getSummonerSpelByID(Integer id){
		Document summonerSpel = couch.getDataFromDatabase(CouchDB.SUMMONER_SPEL_ID+id.toString());
		summonerSpel.remove("_id");
		summonerSpel.remove("_rev");
		return summonerSpel.getJSONObject();
	}
	
	public void getSummonerSpelByName(){
		// TODO miss als we sql gaan gebruiken, want het halen uit couch is een beetje vervelend
	}
}
