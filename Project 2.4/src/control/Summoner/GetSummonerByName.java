package control.Summoner;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.riot.ResponseException;

import util.JSONUtility;
import org.json.JSONObject;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.SummonerLogica;

/**
 * Servlet implementation class GetSummonerByName
 */
@WebServlet("/Summoner/GetByName")
public class GetSummonerByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSummonerByName() {
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
		String name = request.getParameter("name");
		try {
			JSONObject json = new SummonerLogica(new Hibernate(), new CouchDB()).getSummonerByName(name);
			if(json != null)
				JSONUtility.sendJSON(response, json);
			else 
				JSONUtility.sendError(response, "Summoner " + name + " not found.");
		} catch(ResponseException e) {
			JSONUtility.sendError(response, "Something went wrong.");
		}
	}

}
