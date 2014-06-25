package logica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import mappingHibernate.MasterypageSummoner;
import mappingHibernate.MatchHistory;
import mappingHibernate.RunepageSummoner;
import mappingHibernate.Summoner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.riot.Main;
import org.riot.ResponseException;

import com.fourspaces.couchdb.Document;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import exeption.SummonerNotExist;

public class SummonerLogica {
	private Hibernate hib;
	private CouchDB couch;	
	public static final String SUMMONERPROFIELICON="profileIconId";
	public static final String SUMMONERREVDATE="revisionDate";
	public static final String SUMMONERNAME="name";
	public static final String SUMMONERID="id";
	public static final String SUMMONESUMLEVEL="summonerLevel";
	public static final String RUNESPAGES="pages";
	public static final String MASTERYPAGES="pages";
	public static final String GAMES="games";
	public static final String GAMEID="gameId";
	public static final String GAMESSUMMONERID="summonerId";	
	public SummonerLogica(Hibernate hib,CouchDB couch){
		this.hib=hib;
		this.couch=couch;
		/*System.out.println("summoner by name"+getSummonerByName("DemonsWill"));
		/*System.out.println("summoner by name"+getSummonerByNameFromDatabase("demonswill"));
		System.out.println("summoner by id"+getSummonerByID(37268473L));
		System.out.println("summoner by id"+getSummonerByIDFromDatabase(37268473L));
		System.out.println("runes by id"+getRunesByID(37268473L));
		System.out.println("runes by id"+getRunesByIDFromDatabase(37268473L));
		System.out.println("masteries by id"+getMasteriesByID(37268473L));
		System.out.println("masteries by id"+getMasteriesByIDFromDataBase(37268473L));
		System.out.println("match by id"+getMatchHistory(37268473L));
		System.out.println("match by id from db"+getAllMatchesFromDatabase(37268473L));*/
	}
	
	private JSONObject getSummonerByNameFromDatabase(String sumName){
		List<?> list = hib.getDataFromDatabase("FROM Summoner WHERE name = '" + sumName + "'");
		if(list!=null && !list.isEmpty()){
			JSONObject obj = new JSONObject();
			Summoner sum = (Summoner) list.get(0);
			obj.put(SUMMONERID, sum.getId());
			obj.put(SUMMONERNAME, sum.getName());
			obj.put(SUMMONERPROFIELICON, sum.getProfileIconId());
			obj.put(SUMMONERREVDATE, sum.getRevisionDate());
			obj.put(SUMMONESUMLEVEL, sum.getSummonerLevel());
			return obj;
		}
		return null;
	}
	
	public JSONObject getSummonerByName(String sumName) throws ResponseException{		
		JSONObject j = null;
		try{
			j = Main.api.getSummonersByNames(sumName);
		}
		catch(ResponseException ex){
			try{
				if (ex.getMessage().contains("404 : Not Found")){
					throw new SummonerNotExist("This summoner does not exist");
				}
				else if (ex.getMessage().contains("500 :")||ex.getMessage().contains("503 :")||ex.getMessage().contains("429 :")){
					return getSummonerByNameFromDatabase(sumName);
				}
			}
			catch(SummonerNotExist ex2){
				System.out.println(ex2.getMessage());
			}
			
		}
		JSONObject summoner=null;
		if (j!=null){
			summoner = (JSONObject) j.get(sumName.toLowerCase());
			Summoner sum = new Summoner();
			String check = (String) hib.getOneValueFromTheDatabase("SELECT name FROM Summoner WHERE id=" + Long.parseLong(summoner.get(SUMMONERID).toString()) + "");
			if (check!=null){
				hib.deleteFromDatabase("FROM Summoner WHERE id=" + Long.parseLong(summoner.get(SUMMONERID).toString()) + "");
			}				
			sum.setId(Long.parseLong(summoner.get(SUMMONERID).toString()));
			sum.setName(summoner.get(SUMMONERNAME).toString());
			sum.setProfileIconId(Long.parseLong(summoner.get(SUMMONERPROFIELICON).toString()));
			sum.setRevisionDate(Long.parseLong(summoner.get(SUMMONERREVDATE).toString()));
			sum.setSummonerLevel(Integer.parseInt(summoner.get(SUMMONESUMLEVEL).toString()));
			hib.addToDatabase(sum);
		}
		return summoner;
	}

	private JSONObject getSummonerByIDFromDatabase(Long id){
		List<?> list = hib.getDataFromDatabase("FROM Summoner WHERE id = '" + id + "'");
		if(list!=null && !list.isEmpty()){
			JSONObject obj = new JSONObject();
			Summoner sum = (Summoner) list.get(0);
			obj.put(SUMMONERID, sum.getId());
			obj.put(SUMMONERNAME, sum.getName());
			obj.put(SUMMONERPROFIELICON, sum.getProfileIconId());
			obj.put(SUMMONERREVDATE, sum.getRevisionDate());
			obj.put(SUMMONESUMLEVEL, sum.getSummonerLevel());
			return obj;
		}
		return null;
	}
	
	public JSONObject getSummonerByID(Long id){
		JSONObject j = null;
		try{
			j = Main.api.getSummonersByIds(id.toString());
		}
		catch(ResponseException ex){
			try{
				if (ex.getMessage().contains("404 : Not Found")){
					throw new SummonerNotExist("This summoner does not exist");
				}
				else if (ex.getMessage().contains("500 :")||ex.getMessage().contains("503 :")||ex.getMessage().contains("429 :")){
					return getSummonerByIDFromDatabase(id);
				}
			}
			catch(SummonerNotExist ex2){
				System.out.println(ex2.getMessage());
			}
		}
		JSONObject summoner=null;
		if (j!=null){
			summoner = (JSONObject) j.get(id.toString());
			Summoner sum = new Summoner();
			String check = (String) hib.getOneValueFromTheDatabase("SELECT name FROM Summoner WHERE id=" + Long.parseLong(summoner.get(SUMMONERID).toString()) + "");
			if (check!=null){
				hib.deleteFromDatabase("FROM Summoner WHERE id=" + id + "");
			}				
			sum.setId(Long.parseLong(summoner.get(SUMMONERID).toString()));
			sum.setName(summoner.get(SUMMONERNAME).toString());
			sum.setProfileIconId(Long.parseLong(summoner.get(SUMMONERPROFIELICON).toString()));
			sum.setRevisionDate(Long.parseLong(summoner.get(SUMMONERREVDATE).toString()));
			sum.setSummonerLevel(Integer.parseInt(summoner.get(SUMMONESUMLEVEL).toString()));
			hib.addToDatabase(sum);
		}
		return summoner;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject getRunesByIDFromDatabase(Long id){
		List<?> list = hib.getDataFromDatabase("FROM RunepageSummoner WHERE id = '" + id + "'");
		if(list!=null && !list.isEmpty()){
			RunepageSummoner runePageSum = (RunepageSummoner)list.get(0);
			List<RunePage> listRunePages = new ArrayList<RunePage>();
			listRunePages = (List<RunePage>)ObjectToByteConvert.ByteToObject(runePageSum.getPages());
			JSONObject obj = new JSONObject();		
			JSONArray ar = new JSONArray();
			for (int i = 0; i < listRunePages.size(); i++) {
				RunePage runePage = listRunePages.get(i);
				JSONObject runePageJSON = new JSONObject();
				JSONArray runesIdAndName = new JSONArray();
				Long[] slot = runePage.getSlots();
				for (int j = 0; j < 30; j++) {
					if (slot[i+1]!=null){
						JSONObject runeIdName = new JSONObject();
						runeIdName.put(RunePage.RUNESLOTID, j+1);
						runeIdName.put(RunePage.RUNEID, slot[j+1]);
						runesIdAndName.put(runeIdName);
					}
				}
				runePageJSON.put(RunePage.ID, runePage.getId());
				runePageJSON.put(RunePage.CURRENT, runePage.isCurrent());
				runePageJSON.put(RunePage.NAME, runePage.getName());
				if (!runesIdAndName.isNull(0))
					runePageJSON.put(RunePage.SLOT, runesIdAndName);
				ar.put(runePageJSON);
			}
			obj.put(RUNESPAGES, ar);
			obj.put("summonerId", id);
			return obj;
		}
		return null;
	}
	
	// http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
	// dit voor object to byte array
	public JSONObject getRunesByID(Long id) throws IOException{
		JSONObject j = null;
		try{
			j = Main.api.getSummonersRunes(id.toString());
		}
		catch(ResponseException ex){
			try{
				if (ex.getMessage().contains("404 : Not Found")){
					throw new SummonerNotExist("This summoner does not exist");
				}
				else if (ex.getMessage().contains("500 :")||ex.getMessage().contains("503 :")||ex.getMessage().contains("429 :")){
					return getRunesByIDFromDatabase(id);
				}
			}
			catch(SummonerNotExist ex2){
				System.out.println(ex2.getMessage());
			}
			
		}
		JSONObject runes=null;
		if (j!=null){
			Long check = (Long) hib.getOneValueFromTheDatabase("SELECT id FROM RunepageSummoner WHERE id=" + id + "");
			if (check!=null){
				hib.deleteFromDatabase("FROM RunepageSummoner WHERE id=" + id + "");
			}
			runes = (JSONObject) j.get(id.toString());
			// get all the pages
			/*JSONArray runePages = (JSONArray) runes.get(RUNESPAGES);
			for (int i = 0; i < runePages.length(); i++) {
				JSONObject runePageJSON = (JSONObject)runePages.get(i);
			}*/
			JSONArray runePages = (JSONArray) runes.get(RUNESPAGES);
			List<RunePage> listRunePages = new ArrayList<RunePage>();		
			for (int i = 0; i < runePages.length(); i++) {
				JSONObject runePageJSON = (JSONObject)runePages.get(i);
				//for (int k = 0; k < runePageJSON.length(); k++) {
					RunePage runePage = new RunePage();
					runePage.setId(Long.parseLong(runePageJSON.get(RunePage.ID).toString()));
					runePage.setCurrent(Boolean.parseBoolean(runePageJSON.get(RunePage.CURRENT).toString()));
					runePage.setName(runePageJSON.get(RunePage.NAME).toString());
					if (runePageJSON.has(RunePage.SLOT)){
						JSONArray runesIdAndName = (JSONArray)runePageJSON.get(RunePage.SLOT);
						for (int l = 0; l < runesIdAndName.length(); l++) {
							JSONObject runeIdName = (JSONObject)runesIdAndName.get(l);
							runePage.addItemToSlot(Long.parseLong(runeIdName.get(RunePage.RUNEID).toString()), Integer.parseInt(runeIdName.get(RunePage.RUNESLOTID).toString()));
						}
					}
					listRunePages.add(runePage);
				//}
			}
			RunepageSummoner rune = new RunepageSummoner();
			rune.setId(id);
			rune.setPages(ObjectToByteConvert.ObjectToByteArray(listRunePages));
			hib.addToDatabase(rune);
		}
		return runes;
	}

	@SuppressWarnings("unchecked")
	private JSONObject getMasteriesByIDFromDataBase(Long id){
		List<?> list = hib.getDataFromDatabase("FROM MasterypageSummoner WHERE id = '" + id + "'");
		if(list!=null && !list.isEmpty()){
			JSONObject obj = new JSONObject();
			MasterypageSummoner masterySummoner = (MasterypageSummoner) list.get(0);
			List<MasteryPage> listMasteryPages = (List<MasteryPage>) ObjectToByteConvert.ByteToObject(masterySummoner.getPages());		
			JSONArray masteryPages = new JSONArray();//(JSONArray) masterys.get(RUNESPAGES);
			for (int i = 0; i < listMasteryPages.size(); i++) {
				JSONObject masteryPageJSON = new JSONObject();
				MasteryPage masteryPage = listMasteryPages.get(i);
				JSONArray masteryIdAndName = new JSONArray();
				for(Entry<Integer, Integer> entry : masteryPage.getMasteries().entrySet()) {
					Integer masteryId = entry.getKey(); //MASTERYID
					Integer masteryRank = entry.getValue(); //MASTERYRANK
					JSONObject masteryIdName = new JSONObject();
					masteryIdName.put(MasteryPage.MASTERYID, masteryId);
					masteryIdName.put(MasteryPage.MASTERYRANK, masteryRank);
					masteryIdAndName.put(masteryIdName);
				}
				masteryPageJSON.put(MasteryPage.ID,masteryPage.getId());
				masteryPageJSON.put(MasteryPage.CURRENT,masteryPage.isCurrent());
				masteryPageJSON.put(MasteryPage.NAME,masteryPage.getName());
				masteryPage.setId(Long.parseLong(masteryPageJSON.get(MasteryPage.ID).toString()));
				masteryPage.setCurrent(Boolean.parseBoolean(masteryPageJSON.get(MasteryPage.CURRENT).toString()));
				masteryPage.setName(masteryPageJSON.get(MasteryPage.NAME).toString());
				if (!masteryIdAndName.isNull(0))
					masteryPageJSON.put(MasteryPage.MASTERIES, masteryIdAndName);
				
				masteryPages.put(masteryPageJSON);
			}
			
			
			obj.put(RUNESPAGES,masteryPages);
			obj.put("summonerId",id);
			return obj;
		}
		return null;
	}
	
	public JSONObject getMasteriesByID(Long id){
		JSONObject j = null;
		try{
			j = Main.api.getSummonersMasteries(id.toString());
		}
		catch(ResponseException ex){
			try{
				if (ex.getMessage().contains("404 : Not Found")){
					throw new SummonerNotExist("This summoner does not exist");
				}
				else if (ex.getMessage().contains("500 :")||ex.getMessage().contains("503 :")||ex.getMessage().contains("429 :")){
					return getMasteriesByIDFromDataBase(id);
				}
			}
			catch(SummonerNotExist ex2){
				System.out.println(ex2.getMessage());
			}			
		}
		JSONObject masterys = null;
		if (j!=null){
			Long check = (Long) hib.getOneValueFromTheDatabase("SELECT id FROM MasterypageSummoner WHERE id=" + id + "");
			if (check!=null){
				hib.deleteFromDatabase("FROM MasterypageSummoner WHERE id=" + id + "");
			}
			masterys = (JSONObject) j.get(id.toString());
			JSONArray masteryPages = (JSONArray) masterys.get(RUNESPAGES);
			List<MasteryPage> listMasteryPages = new ArrayList<MasteryPage>();		
			for (int i = 0; i < masteryPages.length(); i++) {
				JSONObject masteryPageJSON = (JSONObject)masteryPages.get(i);
				//for (int k = 0; k < masteryPageJSON.length(); k++) {
					MasteryPage masteryPage = new MasteryPage();
					masteryPage.setId(Long.parseLong(masteryPageJSON.get(MasteryPage.ID).toString()));
					masteryPage.setCurrent(Boolean.parseBoolean(masteryPageJSON.get(MasteryPage.CURRENT).toString()));
					masteryPage.setName(masteryPageJSON.get(MasteryPage.NAME).toString());
					if (masteryPageJSON.has(MasteryPage.MASTERIES)){
						JSONArray masteryIdAndName = (JSONArray)masteryPageJSON.get(MasteryPage.MASTERIES);
						for (int l = 0; l < masteryIdAndName.length(); l++) {
							JSONObject masteryIdName = (JSONObject)masteryIdAndName.get(l);
							masteryPage.addItemToMastery(Integer.parseInt(masteryIdName.get(MasteryPage.MASTERYID).toString()), Integer.parseInt(masteryIdName.get(MasteryPage.MASTERYRANK).toString()));
						}
					}
					listMasteryPages.add(masteryPage);
				//}
			}
			MasterypageSummoner mastery = new MasterypageSummoner();
			mastery.setId(id);
			mastery.setPages(ObjectToByteConvert.ObjectToByteArray(listMasteryPages));
			hib.addToDatabase(mastery);
		}
		return masterys;
	}
	
	public JSONObject getMatchHistory(Long id){
		JSONObject j = null;
		try{
			j = Main.api.getRecentGames(id);
		}
		catch(ResponseException ex){
			try{
				if (ex.getMessage().contains("404 : Not Found")){
					throw new SummonerNotExist("This summoner does not exist");
				}
				else if (ex.getMessage().contains("500 :")||ex.getMessage().contains("503 :")||ex.getMessage().contains("429 :")){
					return getAllMatchesFromDatabase(id);
				}
			}
			catch(SummonerNotExist ex2){
				System.out.println(ex2.getMessage());
			}
			
		}
		JSONArray gamesAll =null;
		if (j!=null){
			gamesAll = (JSONArray) j.get(GAMES);
			for (int i = 0; i < gamesAll.length(); i++) {
				JSONObject games= (JSONObject) gamesAll.get(i);
				Long gameId = Long.parseLong(games.get(GAMEID).toString());
				Long check = (Long) hib.getOneValueFromTheDatabase("SELECT id FROM MatchHistory WHERE summonerId=" + id + " AND gameId=" + gameId + "");
				if (check == null){
					// add to couch
					Document doc = new Document();
					doc.putAll(ConvertJSONToMap.toMap(games));
					// add the document to the couchdb
					couch.addDataToDatabase(doc, CouchDB.MATCH_HISTORY_ID+gameId);
					// add to mysql
					MatchHistory match = new MatchHistory();
					match.setGameId(gameId);
					match.setSummonerId(id);
					hib.addToDatabase(match);
				}
			}
		}
		return j;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONObject getAllMatchesFromDatabase(Long id){
		List list = hib.getDataFromDatabase("FROM MatchHistory WHERE summonerId = " + id + "");
		JSONArray a = new JSONArray();
		if (list!=null && !list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				MatchHistory match = (MatchHistory)list.get(i);
				Document matchData = couch.getDataFromDatabase(CouchDB.MATCH_HISTORY_ID+match.getGameId());
				matchData.remove("_id");
				matchData.remove("_rev");
				a.put(matchData);
			}
		}
		JSONObject obj = new JSONObject();
		obj.put(GAMES, a);
		obj.put(GAMESSUMMONERID, id);
		return obj;
	}
}
