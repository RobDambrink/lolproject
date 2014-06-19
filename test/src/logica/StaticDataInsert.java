package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mappingHibernate.ChampionNaamId;
import mappingHibernate.ItemNaamId;
import mappingHibernate.MasteryNaamId;
import mappingHibernate.RuneNaamId;
import mappingHibernate.SummonerSpelNaamId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.riot.ApiRequest;
import org.riot.HttpsClient;
import org.riot.ResponseException;
import org.riot.ApiEnums.*;

import com.fourspaces.couchdb.Document;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;

public class StaticDataInsert {
	private ApiRequest api = new ApiRequest(new HttpsClient(new String[] {"bf9782d6-8d7f-424a-bbfb-1b2dc389d2dc"}));
	private Hibernate hib;
	private CouchDB couch;
	
	public StaticDataInsert(Hibernate hib, CouchDB couch) throws ResponseException{
		this.hib=hib;
		this.couch=couch;
		insertAllChampions();
		insertAllItems();
		insertAllMasterys();
		insertAllRunes();
		insertAllSummonerSpel();
		System.out.println("all done");
	}
	
	public void insertAllSummonerSpel() throws ResponseException{
		// name="data" that is all the data per summonerSpel
		JSONObject j = api.getSummomerSpellList(true, SpellData.all);
		JSONObject summonerSpels = (JSONObject) j.get("data");
		for (int i = 0; i< summonerSpels.length(); i++) {
			// get the summonerSpel key
			String summonerSpelKey = summonerSpels.names().get(i).toString();
			// make a document
			Document doc = new Document();
			JSONObject obj = summonerSpels.getJSONObject(summonerSpelKey);
			doc.putAll(toMap(obj));
			String name=obj.getString("name");
			// add the document to the couchdb
			couch.addDataToDatabase(doc, CouchDB.SUMMONER_SPEL_ID+summonerSpelKey);
			// create new object for hibernate
			SummonerSpelNaamId cham = new SummonerSpelNaamId();
			cham.setId(Integer.parseInt(summonerSpelKey));
			cham.setName(name);
			// save the item to the mysql database
			hib.addToDatabase(cham);
		}
		System.out.println("all summonerSpels inserted");
	}
	
	public void insertAllRunes() throws ResponseException{
		JSONObject j = api.getRuneList(RuneListData.all);
		// name="data" that is all the data per rune
		// name="basic" TODO uitzoeken wat dat precies is
		JSONObject runes = (JSONObject) j.get("data");
		for (int i = 0; i< runes.length(); i++) {
			// get the rune key
			String runeKey = runes.names().get(i).toString();
			// make a document
			Document doc = new Document();
			JSONObject obj = runes.getJSONObject(runeKey);
			doc.putAll(toMap(obj));
			// add the document to the couchdb
			couch.addDataToDatabase(doc, CouchDB.RUNE_ID+runeKey);
			String name=obj.getString("name");
			// create new object for hibernate
			RuneNaamId cham = new RuneNaamId();
			cham.setId(Integer.parseInt(runeKey));
			cham.setName(name);
			// save the item to the mysql database
			hib.addToDatabase(cham);
		}
		System.out.println("all runes inserted");
	}
	
	public void insertAllMasterys() throws ResponseException{
		JSONObject j = api.getMasteryList(MasteryListData.all);
		// name="tree" that is the tree where lol orders the masterys
		// name="data" that is all the data per item
		JSONObject masterys = (JSONObject) j.get("data");
		for (int i = 0; i< masterys.length(); i++) {
			// get the mastery key
			String masteryKey = masterys.names().get(i).toString();
			// make a document
			Document doc = new Document();
			JSONObject obj = masterys.getJSONObject(masteryKey);
			doc.putAll(toMap(obj));
			// add the document to the couchdb
			couch.addDataToDatabase(doc, CouchDB.MASTERY_ID+masteryKey);
			String name=obj.getString("name");
			// create new object for hibernate
			MasteryNaamId cham = new MasteryNaamId();
			cham.setId(Integer.parseInt(masteryKey));
			cham.setName(name);
			// save the item to the mysql database
			hib.addToDatabase(cham);
		}
		System.out.println("all masterys inserted");
	}
	
	public void insertAllItems() throws ResponseException{
		JSONObject j = api.getItemList(ItemListData.all);
		// name="tree" that is the tree where lol orders the items
		// name="data" that is all the data per item
		// name="groups" that is how many items you can carry arround
		// name="basic" TODO uitzoeken wat dat precies is
		// get all the items of the champion
		JSONObject items = (JSONObject) j.get("data");
		for (int i = 0; i< items.length(); i++) {
			// get the item key
			String itemKey = items.names().get(i).toString();
			// make a document
			Document doc = new Document();
			JSONObject obj = items.getJSONObject(itemKey);
			doc.putAll(toMap(obj));
			// add the document to the couchdb
			couch.addDataToDatabase(doc, CouchDB.ITEM_ID+itemKey);
			String name=obj.getString("name");
			// create new object for hibernate
			ItemNaamId cham = new ItemNaamId();
			cham.setId(Integer.parseInt(itemKey));
			cham.setName(name);
			// save the item to the mysql database
			hib.addToDatabase(cham);
		}
		System.out.println("all items inserted");
	}
	
	public void insertAllChampions() throws ResponseException{
		// get all the champion items
		JSONObject j = api.getChampionList(true, ChampData.all);
		// get all the keys of the champion
		JSONObject keys = (JSONObject) j.get("keys");
		// get all the champion data
		JSONObject data = (JSONObject) j.get("data");
		for (int k = 0; k < keys.length(); k++) {
			// get a key
			String keyChamp = keys.names().get(k).toString();
			// get a name
			String nameChamp = keys.get(keyChamp).toString();
			// create new object for hibernate
			ChampionNaamId cham = new ChampionNaamId();
			cham.setId(Integer.parseInt(keyChamp));
			cham.setName(nameChamp);
			// save the item to the mysql database
			hib.addToDatabase(cham);
			// make a new document
			Document doc = new Document();
			doc.putAll(toMap(data.getJSONObject(keyChamp)));
			// add the document to the couchdb
			couch.addDataToDatabase(doc, CouchDB.CHAMPION_ID+keyChamp);
		}
		System.out.println("all champions inserted");
	}
	
	public static Map<String, Object> getMap(JSONObject object, String key) throws JSONException {
        return toMap(object.getJSONObject(key));
    }
 
	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }
    
    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else if (json instanceof JSONObject) {
            return toMap((JSONObject) json);
        } else if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        } else {
            return json;
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(JSONArray array) throws JSONException {
        List list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }
}
