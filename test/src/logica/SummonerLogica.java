package logica;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import mappingHibernate.MasterypageSummoner;
import mappingHibernate.RunepageSummoner;
import mappingHibernate.Summoner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.riot.Main;
import org.riot.ResponseException;

import databaseConnection.Hibernate;

public class SummonerLogica {
	private Hibernate hib;
	
	public static final String SUMMONERPROFIELICON="profileIconId";
	public static final String SUMMONERREVDATE="revisionDate";
	public static final String SUMMONERNAME="name";
	public static final String SUMMONERID="id";
	public static final String SUMMONESUMLEVEL="summonerLevel";
	public static final String RUNESPAGES="pages";
	public static final String MASTERYPAGES="pages";
	public SummonerLogica(Hibernate hib) throws ResponseException, IOException{
		this.hib=hib;
		getSummonerByName("palmboom1212");
		getSummonerByID(42567292L);
		getRunesByID(42567292L);
		getMasteriesByID(42567292L);
	}
	
	public JSONObject getSummonerByName(String sumName) throws ResponseException{		
		JSONObject j = null;
		try{
			j = Main.api.getSummonersByNames(sumName);
		}
		catch(ResponseException ex){
			if (ex.getMessage().contains("404 : Not Found")){
				System.out.println("This summoner does not exist");
				// TODO exeption van maken;
			}
			else if (ex.getMessage().contains("500 :")||ex.getMessage().contains("503 :")||ex.getMessage().contains("429 :")){
				// TODO ALS RIOT DOWN IS NAAR EIGEN DATABASE KIJKEN				
			}
			
		}
		JSONObject summoner=null;
		if (j!=null){
			summoner = (JSONObject) j.get(sumName);
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
	
	public JSONObject getSummonerByID(Long id){
		JSONObject j = null;
		try{
			j = Main.api.getSummonersByIds(id.toString());
		}
		catch(ResponseException ex){
			if (ex.getMessage().contains("404 : Not Found")){
				System.out.println("This summoner does not exist");
				// TODO exeption van maken;
			}
			else if (ex.getMessage().contains("500 :")||ex.getMessage().contains("503 :")||ex.getMessage().contains("429 :")){
				// TODO ALS RIOT DOWN IS NAAR EIGEN DATABASE KIJKEN				
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
	
	// http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
	// dit voor object to byte array
	public String getRunesByID(Long id) throws IOException{
		JSONObject j = null;
		try{
			j = Main.api.getSummonersRunes(id.toString());
		}
		catch(ResponseException ex){
			if (ex.getMessage().contains("404 : Not Found")){
				System.out.println("This summoner does not exist");
				// TODO exeption van maken;
			}
			else if (ex.getMessage().contains("500 :")||ex.getMessage().contains("503 :")||ex.getMessage().contains("429 :")){
				// TODO ALS RIOT DOWN IS NAAR EIGEN DATABASE KIJKEN				
			}
			
		}
		String value="";
		if (j!=null){
			Long check = (Long) hib.getOneValueFromTheDatabase("SELECT id FROM RunepageSummoner WHERE id=" + id + "");
			if (check!=null){
				hib.deleteFromDatabase("FROM RunepageSummoner WHERE id=" + id + "");
			}
			JSONObject runes = (JSONObject) j.get(id.toString());
			JSONArray runePages = (JSONArray) runes.get(RUNESPAGES);
			List<RunePage> listRunePages = new ArrayList<RunePage>();;			
			for (int i = 0; i < runePages.length(); i++) {
				JSONObject runePageJSON = (JSONObject)runePages.get(i);
				for (int k = 0; k < runePageJSON.length(); k++) {
					RunePage runePage = new RunePage();
					runePage.setId(Long.parseLong(runePageJSON.get(RunePage.ID).toString()));
					runePage.setCurrent(Boolean.parseBoolean(runePageJSON.get(RunePage.CURRENT).toString()));
					runePage.setName(runePageJSON.get(RunePage.NAME).toString());
					JSONArray runesIdAndName = (JSONArray)runePageJSON.get(RunePage.SLOT);
					for (int l = 0; l < runesIdAndName.length(); l++) {
						JSONObject runeIdName = (JSONObject)runesIdAndName.get(l);
						runePage.addItemToSlot(Long.parseLong(runeIdName.get(RunePage.RUNEID).toString()), Integer.parseInt(runeIdName.get(RunePage.RUNESLOTID).toString()));
					}
					listRunePages.add(runePage);
				}
			}
			RunepageSummoner rune = new RunepageSummoner();
			rune.setId(id);
			rune.setPages(ObjectToByteConvert.ObjectToByteArray(listRunePages));
			hib.addToDatabase(rune);
		}
		return value;
	}
	
	public JSONObject getMasteriesByID(Long id){
		JSONObject j = null;
		try{
			j = Main.api.getSummonersMasteries(id.toString());
		}
		catch(ResponseException ex){
			if (ex.getMessage().contains("404 : Not Found")){
				System.out.println("This summoner does not exist");
				// TODO exeption van maken;
			}
			else if (ex.getMessage().contains("500 :")||ex.getMessage().contains("503 :")||ex.getMessage().contains("429 :")){
				// TODO ALS RIOT DOWN IS NAAR EIGEN DATABASE KIJKEN				
			}
			
		}
		if (j!=null){
			Long check = (Long) hib.getOneValueFromTheDatabase("SELECT id FROM MasterypageSummoner WHERE id=" + id + "");
			if (check!=null){
				hib.deleteFromDatabase("FROM MasterypageSummoner WHERE id=" + id + "");
			}
			JSONObject masterys = (JSONObject) j.get(id.toString());
			JSONArray masteryPages = (JSONArray) masterys.get(RUNESPAGES);
			List<MasteryPage> listMasteryPages = new ArrayList<MasteryPage>();;			
			for (int i = 0; i < masteryPages.length(); i++) {
				JSONObject masteryPageJSON = (JSONObject)masteryPages.get(i);
				for (int k = 0; k < masteryPageJSON.length(); k++) {
					MasteryPage masteryPage = new MasteryPage();
					masteryPage.setId(Long.parseLong(masteryPageJSON.get(MasteryPage.ID).toString()));
					masteryPage.setCurrent(Boolean.parseBoolean(masteryPageJSON.get(MasteryPage.CURRENT).toString()));
					masteryPage.setName(masteryPageJSON.get(MasteryPage.NAME).toString());
					JSONArray masteryIdAndName = (JSONArray)masteryPageJSON.get(MasteryPage.MASTERIES);
					for (int l = 0; l < masteryIdAndName.length(); l++) {
						JSONObject masteryIdName = (JSONObject)masteryIdAndName.get(l);
						masteryPage.addItemToMastery(Integer.parseInt(masteryIdName.get(MasteryPage.MASTERYID).toString()), Integer.parseInt(masteryIdName.get(MasteryPage.MASTERYRANK).toString()));
					}
					listMasteryPages.add(masteryPage);
				}
			}
			MasterypageSummoner mastery = new MasterypageSummoner();
			mastery.setId(id);
			mastery.setPages(ObjectToByteConvert.ObjectToByteArray(listMasteryPages));
			hib.addToDatabase(mastery);
		}
		return null;
	}
}
