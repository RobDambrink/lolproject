package control.Matches;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.SummonerLogica;

import org.json.JSONObject;

import util.JSONUtility;

/**
 * Servlet implementation class GetMatchById
 */
@WebServlet("/Match/GetById")
public class GetMatchById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMatchById() {
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
			JSONObject json = new SummonerLogica(new Hibernate(), new CouchDB()).getMatchById(Long.parseLong(request.getParameter("id")));
			if(json != null)
				JSONUtility.sendJSON(response, json);
			else 
				JSONUtility.sendError(response, "Match not found.");
		} catch(NumberFormatException e) {
			JSONUtility.sendError(response,"Match ID not in correct format.");
		}
	}

}
