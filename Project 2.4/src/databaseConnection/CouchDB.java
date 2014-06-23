package databaseConnection;


import net.sf.json.JSONException;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;

public class CouchDB {
	public static final String CHAMPION_ID = "CHID";
	public static final String ITEM_ID = "ITID";
	public static final String MASTERY_ID = "MSID";
	public static final String RUNE_ID = "RUID";
	public static final String SUMMONER_SPEL_ID = "SUID";
	public static final String MATCH_HISTORY_ID = "MHID";
	
	private Session lolStaticDBSession;
	private Database lolStaticDB;
	
	public CouchDB(){
		/*Creating a session with couch db running in 5984 port*/
		lolStaticDBSession = new Session("localhost",5984);
   
		/*Selecting the lolstaticdata database from list of couch database*/
		try{
			lolStaticDB = lolStaticDBSession.getDatabase("lolstaticdata");
		}
		catch(JSONException ex){
			if (ex.getMessage().equals("JSONObject[\"error\"] is not a JSONObject.")){
				// TODO exeption van maken
				System.out.println("The selected database probably does not exist");
			}
		}
		
	 }
	
	public void addDataToDatabase(Document newdoc, String id){
		try{
			lolStaticDB.saveDocument(newdoc,id);
		}
		catch(JSONException e){
			if (e.getMessage().equals("JSONObject[\"error\"] is not a JSONObject.")){
				// TODO exeption van maken
				System.out.println("ERROR!! THIS KEY PROBABLY EXISTED ALREADY");
			}
		}
	}
	
	public Document getDataFromDatabase(String id){
		return lolStaticDB.getDocument(id);
	}
}
