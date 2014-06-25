package control.Rune;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.RunePageLogic;
import logica.SummonerLogica;
import util.JSONUtility;

/**
 * Servlet implementation class GetRuneBySummonerId
 */
@WebServlet("/Rune/GetBySummonerId")
public class GetRuneBySummonerId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRuneBySummonerId() {
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
			JSONObject json = new SummonerLogica(new Hibernate(), new CouchDB()).getRunesByID(id);
			if(json != null)
				JSONUtility.sendJSON(response, json);
			else 
				JSONUtility.sendError(response, "Summoner or runepages not found.");
		} catch(NumberFormatException e) {
			JSONUtility.sendError(response, "Invalid id format.");
		}
	}

}
