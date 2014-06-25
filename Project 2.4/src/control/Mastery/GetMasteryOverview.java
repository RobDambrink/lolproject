package control.Mastery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import logica.StaticDataGet;
import util.JSONUtility;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;

/**
 * Servlet implementation class GetMasteryOverview
 */
@WebServlet("/Mastery/Overview")
public class GetMasteryOverview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMasteryOverview() {
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
		JSONObject json = new StaticDataGet(new Hibernate(), new CouchDB()).getAllMasteriesNameId();
		if(json != null)
			JSONUtility.sendJSON(response, json);
		else 
			JSONUtility.sendError(response, "Something went wrong while loading masteries.");
	}

}
