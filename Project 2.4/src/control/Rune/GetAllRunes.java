package control.Rune;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JSONUtility;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.StaticDataGet;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetAllRunes
 */
@WebServlet("/Rune/All")
public class GetAllRunes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllRunes() {
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
		JSONObject json = new StaticDataGet(new Hibernate(), new CouchDB()).getAllRunesInfo();
		if(json != null)
			JSONUtility.sendJSON(response, json);
		else 
			JSONUtility.sendError(response, "Error while rendering runes.");
	}

}
