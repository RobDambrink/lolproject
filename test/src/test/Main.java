package test;

import org.json.*;

import test.ApiEnums.*;

public class Main {
	public static void main(String[] args) {
		ApiRequest api = new ApiRequest(new HttpsClient(new String[] {"bf9782d6-8d7f-424a-bbfb-1b2dc389d2dc"}));
		
		try {
			//tests
			JSONObject j = api.getChallengerTierLeagues(RankedType.RANKED_SOLO_5x5);
			System.out.println(j.length());
			for (int i = 0; i< j.length(); i++) {
				String name = j.names().get(i).toString();
				String value = j.get(name).toString();
				System.out.println(name + ": " + value);
			}
			
			// means tested
			/// means not tested
			
			//api.getChampions();
			//api.getChampions(true);
			//api.getChampionInfo(266);
			//api.getRecentGames(Long.valueOf("42567292"));
			
			///api.getLeagues("42567292"); //not ranked yet.. returns 404 not found
			///api.getLeaguesEntries("42567292");
			///api.getLeaguesTeams("");
			///api.getLeaguesTeamsEntries("");
			
			//api.getChallengerTierLeagues(RankedType.RANKED_SOLO_5x5);
			
			//api.getChampionList();
			//api.getChampionList(ChampData.all);
			//api.getChampionList(true);
			//api.getChampionList(true, ChampData.all);
			//api.getChampion(266);
			//api.getChampion(266, ChampData.all);
			//api.getItemList();
			//api.getItemList(ItemListData.all);
			//api.getItem(3083);
			//api.getItem(3083, ItemData.all);
			//api.getMasteryList();
			//api.getMasteryList(MasteryListData.all);
			//api.getMastery(4353);
			//api.getMastery(4353, MasteryData.all);
			//api.getRealm();
			//api.getRuneList();
			//api.getRuneList(RuneListData.all);
			//api.getRune(5235);
			//api.getRune(5235, RuneData.all);
			//api.getSummomerSpellList();
			//api.getSummomerSpellList(SpellData.all);
			//api.getSummomerSpellList(true);
			//api.getSummomerSpellList(true, SpellData.all);
			//api.getSummomerSpell(1);
			//api.getSummomerSpell(1, SpellData.all);
			//api.getVersion();
			
			///api.getRankedStats(Long.valueOf("42567292"));
			///api.getRankedStats(Long.valueOf("42567292"), Season.SEASON3);
			///api.getRankedSummary(Long.valueOf("42567292"));
			///api.getRankedSummary(Long.valueOf("42567292"), Season.SEASON3);
			
			//api.getSummonersByNames("palmboom1212");
			//api.getSummonersByIds("42567292");
			//api.getSummonersMasteries("42567292");
			
			//api.getSummonersNames("42567292");
			//api.getSummonersRunes("42567292");
			
			//api.getTeamsFromSummoner("42567292");
			///api.getTeamsFromId("");
		} catch (ResponseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}