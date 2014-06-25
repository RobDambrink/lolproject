package control.Spell;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.StaticDataGet;
import util.JSONUtility;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;

/**
 * Servlet implementation class GetSpellOverview
 */
@WebServlet("/Spell/Overview")
public class GetSpellOverview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSpellOverview() {
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
		JSONUtility.sendJSON(response, new StaticDataGet(new Hibernate(), new CouchDB()).getAllSummonerSpelNameId());
	}

}
