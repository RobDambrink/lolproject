package control.Matches;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import org.riot.ResponseException;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.SummonerLogica;
import util.JSONUtility;

/**
 * Servlet implementation class GetMatchesFromSummoner
 */
@WebServlet(description = "Get all matches from a summoner", urlPatterns = { "/Summoner/GetMatchesBySummonerId" })
public class GetMatchesFromSummoner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMatchesFromSummoner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			long id = Long.parseLong(request.getParameter("id"));
			SummonerLogica sl = new SummonerLogica(new Hibernate(), new CouchDB());
			JSONObject json = sl.getMatchHistory(id);
			if(json != null)
				JSONUtility.sendJSON(response,json);
			else 
				JSONUtility.sendError(response, "No matches found.");
		} catch(NumberFormatException e) {
			JSONUtility.sendError(response, "Invalid format.");
		} catch (ResponseException e) {
			JSONUtility.sendError(response, "Something went wrong.");
		}
	}

}
