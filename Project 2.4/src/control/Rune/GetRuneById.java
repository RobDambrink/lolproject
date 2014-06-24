package control.Rune;

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
 * Servlet implementation class GetRuneById
 */
@WebServlet(description = "Get a specific rune by id", urlPatterns = { "/Rune/GetById" })
public class GetRuneById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRuneById() {
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
			JSONObject json = new StaticDataGet(new Hibernate(), new CouchDB()).getRuneByID(id);
			if(json != null) 
				JSONUtility.returnJSON(response, json);
			else
				JSONUtility.sendError(response, "Rune not found.");
		} catch (NumberFormatException e) {
			JSONUtility.sendError(response, "Id not in correct format.");
		}
	}

}
