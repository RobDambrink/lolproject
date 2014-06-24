package control.Summoner;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.SummonerLogica;

import org.json.JSONObject;
import org.riot.ResponseException;

import util.JSONUtility;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;

/**
 * Servlet implementation class GetSummonerById
 */
@WebServlet(description = "Get a summoner by id", urlPatterns = { "/Summoner/GetById" })
public class GetSummonerById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSummonerById() {
        super();
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
			JSONObject json = new SummonerLogica(new Hibernate(), new CouchDB()).getSummonerByID(id);
			if(json != null) 
				JSONUtility.sendJSON(response, json);
			else
				JSONUtility.sendError(response, "Summoner not found.");
		} catch (NumberFormatException e) {
			JSONUtility.sendError(response, "Id not in correct format.");
		} catch (ResponseException e) {
			JSONUtility.sendError(response, "Something went wrong.");
		}
	}

}
