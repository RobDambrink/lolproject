package control.Champion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.StaticDataGet;
import mappingHibernate.ChampionNameId;
import util.JSONUtility;

/**
 * Servlet implementation class GetChampionOverview
 */
@WebServlet(description = "Get a list of champions", urlPatterns = { "/Champion/Overview" })
public class GetChampionOverview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetChampionOverview() {
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
		JSONUtility.sendJSON(response, new StaticDataGet(new Hibernate(), new CouchDB()).getAllChampionNameId());
	}

}
