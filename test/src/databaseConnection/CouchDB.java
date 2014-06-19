package databaseConnection;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;
import com.fourspaces.couchdb.ViewResults;

public class CouchDB {
	public static final String CHAMPION_ID = "CHID";
	public static final String ITEM_ID = "ITID";
	public static final String MASTERY_ID = "MSID";
	public static final String RUNE_ID = "RUID";
	public static final String SUMMONER_SPEL_ID = "SUID";
	
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
		/*Creating a new Document*/
		//Document newdoc = new Document();
		   
		/*Map for list of properties for the new document*/
		//Map<String , String> properties = new HashMap<String,String>();
		
		/*properties.put(STUDENT_KEY_NAME, "saana");
		properties.put(STUDENT_KEY_MARKS, "67");
		properties.put(STUDENT_KEY_ROLL, "12");
		properties.put(STUDENT_KEY_CONTACT, "+31513216549879");
		   
		   
		/*Adding all the properties to the new document*/
		//newdoc.putAll(properties);
		   
		/*Saving the new document in the 'student' database */
		//studentCouchDb.saveDocument(newdoc);
		// DIT KUN JE DOEN ALS JE ZELF EEN ID WILT TOEVOEGEN
		/*String id = "RUNESid12";
		try{
			studentCouchDb.getDocument(id);
			System.out.println("hij is er");
		}
		catch(JSONException e){
			if (e.getMessage().equals("JSONObject[\"error\"] is not a JSONObject.")){
				studentCouchDb.saveDocument(newdoc,id);
			}
			else{
				e.printStackTrace();
			}
		}*/
	}
}
