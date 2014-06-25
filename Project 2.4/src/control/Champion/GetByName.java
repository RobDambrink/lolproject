package control.Champion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import util.JSONUtility;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.StaticDataGet;

/**
 * Servlet implementation class GetByName
 */
@WebServlet(description = "Get a champion by name.", urlPatterns = { "/Champion/GetByName" })
public class GetByName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetByName() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		StaticDataGet sdg = new StaticDataGet(new Hibernate(), new CouchDB());
		JSONObject obj = sdg.getChampionByName(name);
		if (obj != null)
			JSONUtility.sendJSON(response, obj); 
		else {
			JSONUtility.sendError(response, "Champion not found.");
		}
	}

}
