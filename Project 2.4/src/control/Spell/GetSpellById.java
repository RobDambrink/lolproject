package control.Spell;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.StaticDataGet;
import net.sf.json.JSONObject;
import util.JSONUtility;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;

/**
 * Servlet implementation class GetSpellById
 */
@WebServlet(description = "Get a spell by Id", urlPatterns = { "/Spell/GetById" })
public class GetSpellById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSpellById() {
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
			JSONObject json = new StaticDataGet(new Hibernate(), new CouchDB()).getSummonerSpelByID(id);
			if(json != null) 
				JSONUtility.sendJSON(response, json);
			else
				JSONUtility.sendError(response, "Spell not found.");
		} catch (NumberFormatException e) {
			JSONUtility.sendError(response, "Id not in correct format.");
		}
	}

}
