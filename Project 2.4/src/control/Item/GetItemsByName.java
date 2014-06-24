package control.Item;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.scripts.JS;
import util.JSONUtility;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.StaticDataGet;

/**
 * Servlet implementation class GetItemsByName
 */
@WebServlet(description = "Get items by their (partial) name", urlPatterns = { "/Item/GetByName" })
public class GetItemsByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetItemsByName() {
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
		String name = request.getParameter("name");
		StaticDataGet sdg = new StaticDataGet(new Hibernate(), new CouchDB());
		JSONObject json = sdg.getItemByParselName(name);
		if(json != null) 
			JSONUtility.sendJSON(response, json);
		else 
			JSONUtility.sendError(response, "Item not found.");
	}

}
