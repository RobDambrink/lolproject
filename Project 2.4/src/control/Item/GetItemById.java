package control.Item;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.StaticDataGet;
import util.JSONUtility;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetItemById
 */
@WebServlet(description = "Get an item by id", urlPatterns = { "/Item/GetById" })
public class GetItemById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetItemById() {
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
			StaticDataGet sdg = new StaticDataGet(new Hibernate(), new CouchDB());
			JSONObject json = sdg.getItemByID(id);
			if(json != null) 
				JSONUtility.returnJSON(response, json);
			else 
				JSONUtility.sendError(response, "Item not found.");
		} catch (NumberFormatException e) {
			JSONUtility.sendError(response, "Id not in correct format.");
		}
		
	}

}
