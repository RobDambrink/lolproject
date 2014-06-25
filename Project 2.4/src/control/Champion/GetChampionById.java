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
import net.sf.json.JSONObject;
import util.JSONUtility;

/**
 * Servlet implementation class GetChampionById
 */
@WebServlet(description = "Get a JSON based on champion", urlPatterns = { "/Champion/GetById" })
public class GetChampionById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetChampionById() {
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
			JSONObject json = new StaticDataGet(new Hibernate(), new CouchDB()).getChampionByID(id);
			if(json != null) 
				JSONUtility.sendJSON(response, json);
			else 
				JSONUtility.sendError(response, "No champion found.");
		} catch(NumberFormatException e) {
			JSONUtility.sendError(response, "Invalid id format.");
		}
	}

}
