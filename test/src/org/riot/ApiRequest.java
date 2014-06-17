package org.riot;

import org.json.JSONArray;
import org.json.JSONObject;
import org.riot.ApiEnums.*;

public class ApiRequest {
	HttpsClient client;
	public ApiRequest(HttpsClient c) {
		this.client = c;
	}

	//champion-v1.2
	
	/**
	 * Retrieve all champions
	 * @return ChampionListDto
	 * @throws ResponseException
	 */
	public JSONObject getChampions() throws ResponseException {
		return client.requestData("v1.2/champion");
				
//		if (ah.has("champions")) {
//			System.out.println("champion data found");
//			JSONArray champions = ah.getJSONArray("champions");
//			for (int i = 0; i< champions.length(); i++) {
//				JSONObject champion = champions.getJSONObject(i);
//				System.out.print("Id: " + champion.get("id") + "\t");
//				System.out.print("Ranked: " + champion.get("rankedPlayEnabled") + "\t");
//				System.out.print("Bot: " + champion.get("botEnabled") + "\t");
//				System.out.print("BotMm: " + champion.get("botMmEnabled") + "\t");
//				System.out.print("Active: " + champion.get("active") + "\t");
//				System.out.println("Free: " + champion.get("freeToPlay"));
//				
//				//System.out.println(champions.get(i));
//			}
//		}
	}
	
	/**
	 * Retrieve all champions
	 * @param freeToPlay
	 * @return ChampionListDto
	 * @throws ResponseException
	 */
	public JSONObject getChampions(boolean freeToPlay) throws ResponseException {
		return client.requestData("v1.2/champion?freeToPlay=" + Boolean.toString(freeToPlay));
				
//		if (ah.has("champions")) {
//			System.out.println("champion data found");
//			JSONArray champions = ah.getJSONArray("champions");
//			for (int i = 0; i< champions.length(); i++) {
//				JSONObject champion = champions.getJSONObject(i);
//				System.out.print("Id: " + champion.get("id") + "\t");
//				System.out.print("Ranked: " + champion.get("rankedPlayEnabled") + "\t");
//				System.out.print("Bot: " + champion.get("botEnabled") + "\t");
//				System.out.print("BotMm: " + champion.get("botMmEnabled") + "\t");
//				System.out.print("Active: " + champion.get("active") + "\t");
//				System.out.println("Free: " + champion.get("freeToPlay"));
//				
//				//System.out.println(champions.get(i));
//			}
//		}
	}
	
	/**
	 *Retrieve champion by ID
	 * @param id Champion ID
	 * @return ChampionDto
	 * @throws ResponseException
	 */
	public JSONObject getChampionInfo(Integer id) throws ResponseException {
		JSONObject champion = client.requestData("v1.2/champion/" + id);
		
		System.out.print("Id: " + champion.get("id") + "\t");
		System.out.print("Ranked: " + champion.get("rankedPlayEnabled") + "\t");
		System.out.print("Bot: " + champion.get("botEnabled") + "\t");
		System.out.print("BotMm: " + champion.get("botMmEnabled") + "\t");
		System.out.print("Active: " + champion.get("active") + "\t");
		System.out.println("Free: " + champion.get("freeToPlay"));

		return null;
	}
	
	//game-v1.3
	/**
	 * Get recent games by summoner ID
	 * @param summonerId ID of the summoner for which to retrieve recent games.
	 * @return RecentGamesDto
	 * @throws ResponseException
	 */
	public JSONObject getRecentGames(Long summonerId) throws ResponseException {
		return client.requestData("v1.3/game/by-summoner/" + Long.toString(summonerId) + "/recent");
	}
	
	//league-v2.4
	/**
	 * Get leagues mapped by summoner ID for a given list of summoner ID
	 * @param summonerId Comma-separated list of summoner IDs. Maximum allowed at once is 40.
	 * @return Returns league entries for summoner and summoner's teams.
	 * @throws ResponseException
	 */
	public JSONObject getLeagues(String summonerIds) throws ResponseException {
		return client.requestData("v2.4/league/by-summoner/" + summonerIds);
	}

	//Get league entries mapped by summoner ID for a given list of summoner IDs
	//same?
	public JSONObject getLeaguesEntries(String summomerIds) throws ResponseException {
		return client.requestData("v2.4/league/by-summoner/" + summomerIds + "/entry");
	}
	
	//Get leagues mapped by team ID for a given list of team IDs
	public JSONObject getLeaguesTeams(String teamIds) throws ResponseException {
		return client.requestData("v2.4/league/by-team/" + teamIds);
	}
	
	//Get league entries mapped by team ID for a given list of team IDs
	//same?
	public JSONObject getLeaguesTeamsEntries(String teamIds) throws ResponseException {
		return client.requestData("v2.4/league/by-team/" + teamIds + "/entry");
	}
	
	//Get challenger tier leagues
	public JSONObject getChallengerTierLeagues(RankedType r) throws ResponseException {
		return client.requestData("v2.4/league/challenger?type=" + r.toString());
	}
	
	
	//lol-static-data-v1.2
		//no rate limit
	
	//Retrieves champion list
	//champdata paramter?
	public JSONObject getChampionList() throws ResponseException {
		return client.staticRequestData("v1.2/champion");
	}
	public JSONObject getChampionList(boolean dataById) throws ResponseException {
		return client.staticRequestData("v1.2/champion?dataById=" + Boolean.toString(dataById));
	}
	public JSONObject getChampionList(ChampData c) throws ResponseException {
		return client.staticRequestData("v1.2/champion?champData=" + c.toString());
	}
	public JSONObject getChampionList(boolean dataById, ChampData c) throws ResponseException {
		return client.staticRequestData("v1.2/champion?dataById=" + Boolean.toString(dataById) + "&champData=" + c.toString());
	}
	
	//Retrieves a champion by its id
	//champdata paramter?
	public JSONObject getChampion(Integer id) throws ResponseException {
		return client.staticRequestData("v1.2/champion/" + id);
	}
	public JSONObject getChampion(Integer id, ChampData c) throws ResponseException {
		return client.staticRequestData("v1.2/champion/" + id + "?champData=" + c.toString());
	}
	
    //Retrieves item list
	//itemListData parameter?
	public JSONObject getItemList() throws ResponseException {
		return client.staticRequestData("v1.2/item");
	}
	public JSONObject getItemList(ItemListData i) throws ResponseException {
		return client.staticRequestData("v1.2/item?itemListData=" + i.toString());
	}
	
    //Retrieves item by its unique id
	public JSONObject getItem(Integer id) throws ResponseException {
		return client.staticRequestData("v1.2/item/" + id);
	}
	public JSONObject getItem(Integer id, ItemData i) throws ResponseException {
		return client.staticRequestData("v1.2/item/" + id + "?itemData=" + i.toString());
	}
	
    //Retrieves mastery list
	public JSONObject getMasteryList() throws ResponseException {
		return client.staticRequestData("v1.2/mastery");
	}
	public JSONObject getMasteryList(MasteryListData m) throws ResponseException {
		return client.staticRequestData("v1.2/mastery?masteryListData=" + m.toString());
	}
	
    //Retrieves mastery item by its unique id
	public JSONObject getMastery(Integer id) throws ResponseException {
		return client.staticRequestData("v1.2/mastery/" + id);
	}
	public JSONObject getMastery(Integer id, MasteryData m) throws ResponseException {
		return client.staticRequestData("v1.2/mastery/" + id + "?masteryData=" + m.toString());
	}

    //Retrieve realm data
	public JSONObject getRealm() throws ResponseException {
		return client.staticRequestData("v1.2/realm");
	}
	
	//Retrieves rune list
	public JSONObject getRuneList() throws ResponseException {
		return client.staticRequestData("v1.2/rune");
	}
	public JSONObject getRuneList(RuneListData r) throws ResponseException {
		return client.staticRequestData("v1.2/rune?runeListData=" + r.toString());
	}
	
	//Retrieves rune by its unique id
	public JSONObject getRune(Integer id) throws ResponseException {
		return client.staticRequestData("v1.2/rune/" + id);
	}
	public JSONObject getRune(Integer id, RuneData r) throws ResponseException {
		return client.staticRequestData("v1.2/rune/" + id + "?runeData=" + r.toString());
	}
	
	//Retrieves summoner spell list
	public JSONObject getSummomerSpellList() throws ResponseException {
		return client.staticRequestData("v1.2/summoner-spell");
	}
	public JSONObject getSummomerSpellList(boolean dataById) throws ResponseException {
		return client.staticRequestData("v1.2/summoner-spell?dataById=" + Boolean.toString(dataById));
	}
	public JSONObject getSummomerSpellList(SpellData s) throws ResponseException {
		return client.staticRequestData("v1.2/summoner-spell?spellData=" + s.toString());
	}
	public JSONObject getSummomerSpellList(boolean dataById, SpellData s) throws ResponseException {
		return client.staticRequestData("v1.2/summoner-spell?dataById=" + Boolean.toString(dataById) + "&spellData=" + s.toString());
	}
	
	//Retrieves summoner spell by its unique i
	public JSONObject getSummomerSpell(Integer id) throws ResponseException {
		return client.staticRequestData("v1.2/summoner-spell/" + id);
	}
	public JSONObject getSummomerSpell(Integer id, SpellData s) throws ResponseException {
		return client.staticRequestData("v1.2/summoner-spell/" + id + "?spellData=" + s.toString());
	}
	
	//Retrieve version data
	public JSONArray getVersion() throws ResponseException {
		return client.staticRequestDataArray("v1.2/versions");
	}
	
	//stats-v1.3
	
	//Get ranked stats by summoner ID
	public JSONObject getRankedStats(Long summonerId) throws ResponseException {
		return client.requestData("v1.3/stats/by-summoner/" + summonerId + "/ranked");
	}
	public JSONObject getRankedStats(Long summonerId, Season s) throws ResponseException {
		return client.requestData("v1.3/stats/by-summoner/" + summonerId + "/ranked?season=" + s.toString());
	}
	
	
	//Get player stats summaries by summoner ID
	public JSONObject getRankedSummary(Long summonerId) throws ResponseException {
		return client.requestData("v1.3/stats/by-summoner/" + summonerId + "/ranked");
	}
	public JSONObject getRankedSummary(Long summonerId, Season s) throws ResponseException {
		return client.requestData("v1.3/stats/by-summoner/" + summonerId + "/ranked?season=" + s.toString());
	}
	
	//summomer-v1.4
	
	//Get summoner objects mapped by standardized summoner name for a given list of summoner names
	public JSONObject getSummonersByNames(String summonerNames) throws ResponseException {
		return client.requestData("v1.4/summoner/by-name/" + summonerNames);
	}
	
	//Get summoner objects mapped by summoner ID for a given list of summoner IDs
	public JSONObject getSummonersByIds(String summonerIds) throws ResponseException {
		return client.requestData("v1.4/summoner/" + summonerIds);
	}
	
	//Get mastery pages mapped by summoner ID for a given list of summoner IDs
	public JSONObject getSummonersMasteries(String summonerIds) throws ResponseException {
		return client.requestData("v1.4/summoner/" + summonerIds + "/masteries");
	}
	
	//Get summoner names mapped by summoner ID for a given list of summoner IDs
	public JSONObject getSummonersNames(String summonerIds) throws ResponseException {
		return client.requestData("v1.4/summoner/" + summonerIds + "/name");
	}

    //Get rune pages mapped by summoner ID for a given list of summoner IDs
	public JSONObject getSummonersRunes(String summonerIds) throws ResponseException {
		return client.requestData("v1.4/summoner/" + summonerIds + "/runes");
	}
	
	//team-v2.3
	
	//Get teams mapped by summoner ID for a given list of summoner IDs
	public JSONObject getTeamsFromSummoner(String summonerIds) throws ResponseException {
		return client.requestData("v2.3/team/by-summoner/" + summonerIds);
	}
	
	//Get teams mapped by team ID for a given list of team IDs
	public JSONObject getTeamsFromId(String teamIds) throws ResponseException {
		return client.requestData("v2.3/team/" + teamIds);
	}
}