package control.Mastery;

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
 * Servlet implementation class GetMasteryById
 */
@WebServlet(description = "Get a mastery by id", urlPatterns = { "/Mastery/GetById" })
public class GetMasteryById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMasteryById() {
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
			JSONObject json = new StaticDataGet(new Hibernate(), new CouchDB()).getMasteryByID(id);
			if(json != null) 
				JSONUtility.returnJSON(response, json);
			else
				JSONUtility.sendError(response, "Mastery not found.");
		} catch (NumberFormatException e) {
			JSONUtility.sendError(response, "Id not in correct format.");
		}
	}

}
