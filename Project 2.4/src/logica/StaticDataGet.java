package logica;

import java.util.HashMap;
import java.util.List;

import mappingHibernate.ChampionNameId;
import mappingHibernate.ItemNameId;
import mappingHibernate.MasteryNameId;
import mappingHibernate.RuneNameId;
import mappingHibernate.SummonerSpelNameId;
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
		/*System.out.println(getChampionByID(12323334l));
		System.out.println(getAllChampionNameId());
		System.out.println(getAllItemsNameId());
		System.out.println(getAllMasteriesNameId());
		System.out.println(getAllRunesNameId());
		System.out.println(getAllSummonerSpelNameId());
		System.out.println(getChampionByParselName("ann"));
		getItemByParselName("boot");
		getMasteryByParselName("cer");
		getRuneByParselName("lesser");
		getSummonerSpelByParselName("ig");
		System.out.println(getChampionByName("AnnIe"));
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
	public JSONObject getAllChampionNameId(){
		List list = hib.getDataFromDatabase("FROM ChampionNameId");
		JSONObject obj = new JSONObject();
		if (list!=null && !list.isEmpty()){
			HashMap<Long,String> hm = new HashMap<Long,String>();
			for (int i = 0; i < list.size(); i++) {
				ChampionNameId champ = (ChampionNameId) list.get(i);
				hm.put(champ.getId(), champ.getName());		
			}
			obj.putAll(hm);	
		}
		return obj;
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject getAllItemsNameId(){
		List list = hib.getDataFromDatabase("FROM ItemNameId");
		JSONObject obj = new JSONObject();
		if (list!=null && !list.isEmpty()){
			HashMap<Long,String> hm = new HashMap<Long,String>();
			for (int i = 0; i < list.size(); i++) {
				ItemNameId champ = (ItemNameId) list.get(i);
				hm.put(champ.getId(), champ.getName());		
			}
			obj.putAll(hm);	
		}
		return obj;
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject getAllRunesNameId(){
		List list = hib.getDataFromDatabase("FROM RuneNameId");
		JSONObject obj = new JSONObject();
		if (list!=null && !list.isEmpty()){
			HashMap<Long,String> hm = new HashMap<Long,String>();
			for (int i = 0; i < list.size(); i++) {
				RuneNameId champ = (RuneNameId) list.get(i);
				hm.put(champ.getId(), champ.getName());		
			}
			obj.putAll(hm);	
		}
		return obj;
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject getAllMasteriesNameId(){
		List list = hib.getDataFromDatabase("FROM MasteryNameId");
		JSONObject obj = new JSONObject();
		if (list!=null && !list.isEmpty()){
			HashMap<Long,String> hm = new HashMap<Long,String>();
			for (int i = 0; i < list.size(); i++) {
				MasteryNameId champ = (MasteryNameId) list.get(i);
				hm.put(champ.getId(), champ.getName());		
			}
			obj.putAll(hm);	
		}
		return obj;
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject getAllSummonerSpelNameId(){
		List list = hib.getDataFromDatabase("FROM SummonerSpelNameId");
		JSONObject obj = new JSONObject();
		if (list!=null && !list.isEmpty()){
			HashMap<Long,String> hm = new HashMap<Long,String>();
			for (int i = 0; i < list.size(); i++) {
				SummonerSpelNameId champ = (SummonerSpelNameId) list.get(i);
				hm.put(champ.getId(), champ.getName());		
			}
			obj.putAll(hm);	
		}
		return obj;
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject getChampionByParselName(String name){
		List list = hib.getDataFromDatabase("from ChampionNameId where LOWER(name) LIKE LOWER('%" + name + "%')");
		JSONObject obj = new JSONObject();
		if (list!=null && !list.isEmpty()){
			HashMap<Long,String> hm = new HashMap<Long,String>();
			for (int i = 0; i < list.size(); i++) {
				ChampionNameId champ = (ChampionNameId) list.get(i);
				hm.put(champ.getId(), champ.getName());		
			}
			obj.putAll(hm);
			return obj;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject getItemByParselName(String name){
		List list = hib.getDataFromDatabase("from ItemNameId where LOWER(name) LIKE LOWER('%" + name + "%')");
		JSONObject obj = new JSONObject();
		HashMap<Long,String> hm = new HashMap<Long,String>();
		if (list!=null &&!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				ItemNameId champ = (ItemNameId) list.get(i);
				hm.put(champ.getId(), champ.getName());
			}
			obj.putAll(hm);
			return obj;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject getMasteryByParselName(String name){
		List list = hib.getDataFromDatabase("from MasteryNameId where LOWER(name) LIKE LOWER('%" + name + "%')");
		JSONObject obj = new JSONObject();
		HashMap<Long,String> hm = new HashMap<Long,String>();
		if (list!=null &&!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				MasteryNameId champ = (MasteryNameId) list.get(i);
				hm.put(champ.getId(), champ.getName());
			}
			obj.putAll(hm);
			return obj;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject getRuneByParselName(String name){
		List list = hib.getDataFromDatabase("from RuneNameId where LOWER(name) LIKE LOWER('%" + name + "%')");
		JSONObject obj = new JSONObject();
		HashMap<Long,String> hm = new HashMap<Long,String>();
		if (list!=null &&!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				RuneNameId champ = (RuneNameId) list.get(i);
				hm.put(champ.getId(), champ.getName());
			}
			obj.putAll(hm);
			return obj;
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public JSONObject getSummonerSpelByParselName(String name){
		List list = hib.getDataFromDatabase("from SummonerSpelNameId where LOWER(name) LIKE LOWER('%" + name + "%')");
		JSONObject obj = new JSONObject();
		HashMap<Long,String> hm = new HashMap<Long,String>();
		if (list!=null &&!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				SummonerSpelNameId champ = (SummonerSpelNameId) list.get(i);
				hm.put(champ.getId(), champ.getName());
			}
			obj.putAll(hm);
			return obj;
		}
		return null;
	}
	
	public JSONObject getChampionByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM ChampionNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getChampionByID(id);
	}
	
	public JSONObject getChampionByID(Long id){
		Document champion = couch.getDataFromDatabase(CouchDB.CHAMPION_ID+id.toString());
		if (champion!=null){
			champion.remove("_id");
			champion.remove("_rev");
			return champion.getJSONObject();
		}
		return null;
	}
	
	public JSONObject getItemByID(Long id){
		Document item = couch.getDataFromDatabase(CouchDB.ITEM_ID+id.toString());
		if (item!=null){
			item.remove("_id");
			item.remove("_rev");
			return item.getJSONObject();
		}
		return null;
	}
	
	public JSONObject getItemByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM ItemNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getItemByID(id);
	}
	
	public JSONObject getMasteryByID(Long id){
		Document mastery = couch.getDataFromDatabase(CouchDB.MASTERY_ID+id.toString());
		if (mastery!=null){
			mastery.remove("_id");
			mastery.remove("_rev");
			return mastery.getJSONObject();
		}
		return null;
	}
	
	public JSONObject getMasteryByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM MasteryNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getMasteryByID(id);
	}
	
	public JSONObject getRuneByID(Long id){
		Document rune = couch.getDataFromDatabase(CouchDB.RUNE_ID+id.toString());
		if (rune!=null){
			rune.remove("_id");
			rune.remove("_rev");
			return rune.getJSONObject();
		}
		return null;
	}
	
	public JSONObject getRuneByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM RuneNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getRuneByID(id);
	}
	
	public JSONObject getSummonerSpelByID(Long id){
		Document summonerSpel = couch.getDataFromDatabase(CouchDB.SUMMONER_SPEL_ID+id.toString());
		if (summonerSpel!=null){
			summonerSpel.remove("_id");
			summonerSpel.remove("_rev");
			return summonerSpel.getJSONObject();
		}
		return null;
	}
	
	public JSONObject getSummonerSpelByName(String name){
		Long id = (Long)hib.getOneValueFromTheDatabase("SELECT id FROM SummonerSpelNameId WHERE LOWER(name) = LOWER('" + name + "')");
		return getSummonerSpelByID(id);
	}
}
