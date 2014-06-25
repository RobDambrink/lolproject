package control.Account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;

import org.json.JSONObject;
import org.riot.ResponseException;

import util.JSONUtility;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import exeption.AccountNotExist;
import exeption.SummonerNotExist;
import logica.AccountLogica;
import logica.SummonerLogica;

/**
 * Servlet implementation class EditAccount
 */
@WebServlet(description = "Edit the password and/or summorer id", urlPatterns = { "/Account/Edit" })
public class EditAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAccount() {
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
			Long username = Long.parseLong(request.getParameter("id"));
			String password = request.getParameter("password");
			String summoner = request.getParameter("summoner");
			Long id=null;
			if (summoner!=null && summoner!="-1"){
				id = new SummonerLogica(new Hibernate(), new CouchDB()).getSummonerByName(request.getParameter("summoner")).getLong("id");
			}
			else if(summoner.contains("-1"))
				id =-1l;
			//Long summoner = new SummonerLogica(new Hibernate(), new CouchDB()).getSummonerByName(request.getParameter("summoner")).getLong("id");
			Hibernate h = new Hibernate();
			CouchDB c = new CouchDB();
			
			AccountLogica al = new AccountLogica(h,c);
			al.edditAccount(username, password, id);
			JSONObject json = new JSONObject();
			json.put("success", true);
			JSONUtility.sendJSON(response, json);
		} catch (ResponseException e) {
			JSONUtility.sendError(response, "Time out while retrieving data... Try again later.");
		} catch (AccountNotExist | SummonerNotExist e) {
			JSONUtility.sendError(response, "Account or summoner not found.");
		}
		
	}

}
