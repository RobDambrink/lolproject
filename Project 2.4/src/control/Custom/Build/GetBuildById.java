package control.Custom.Build;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import databaseConnection.Hibernate;
import logica.ItemBuldLogica;
import util.JSONUtility;

/**
 * Servlet implementation class GetBuildById
 */
@WebServlet("/Custom/Build/GetById")
public class GetBuildById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBuildById() {
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
			JSONObject json = new ItemBuldLogica(new Hibernate()).getItemBuldByItemBuldId(id);
			if(json != null) 
				JSONUtility.sendJSON(response, json);
			else 
				JSONUtility.sendError(response, "Build not found.");
		} catch(NumberFormatException e) {
			JSONUtility.sendError(response, "Invalid id format.");
		}
	}

}
