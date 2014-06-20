package logica;

import mappingHibernate.MasterypageSummoner;
import mappingHibernate.RunepageSummoner;
import mappingHibernate.Summoner;

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
	public SummonerLogica(Hibernate hib) throws ResponseException{
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
				// TODO misschien kiezen voor update ipv delete???
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
				// TODO misschien kiezen voor update ipv delete???
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
	
	public String getRunesByID(Long id){
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
				// TODO misschien kiezen voor update ipv delete???
			}
			// TODO OPGELET IK PAK EEN JSONARRAY NAAR STRING EN DIE WEER NAAR BYTE ARRAY ZO WORDT HET OPGESLAGEN
			JSONObject runes = (JSONObject) j.get(id.toString());
			value = runes.get(RUNESPAGES).toString();
			byte[] b = value.getBytes();
			RunepageSummoner rune = new RunepageSummoner();
			rune.setId(id);
			rune.setPages(b);
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
		String value="";
		if (j!=null){
			Long check = (Long) hib.getOneValueFromTheDatabase("SELECT id FROM MasterypageSummoner WHERE id=" + id + "");
			if (check!=null){
				hib.deleteFromDatabase("FROM MasterypageSummoner WHERE id=" + id + "");
				// TODO misschien kiezen voor update ipv delete???
			}
			// TODO OPGELET IK PAK EEN JSONARRAY NAAR STRING EN DIE WEER NAAR BYTE ARRAY ZO WORDT HET OPGESLAGEN
			JSONObject runes = (JSONObject) j.get(id.toString());
			value = runes.get(RUNESPAGES).toString();
			byte[] b = value.getBytes();
			MasterypageSummoner rune = new MasterypageSummoner();
			rune.setId(id);
			rune.setPages(b);
			hib.addToDatabase(rune);
		}
		return null;
	}
}
