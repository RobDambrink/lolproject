package control.Account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.riot.ResponseException;

import util.JSONUtility;
import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.AccountLogica;
import logica.SummonerLogica;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet(description = "Create an account with a password", urlPatterns = { "/Account/Create" })
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String summoner = request.getParameter("summonername");
		try {
			Hibernate h = new Hibernate();
			CouchDB c = new CouchDB();
			AccountLogica al = new AccountLogica(h,c);
			SummonerLogica sl = new SummonerLogica(h,c);
			JSONObject tmp = sl.getSummonerByName(summoner);
			al.createAccount(username, password, tmp.getLong(SummonerLogica.SUMMONERID));
			JSONObject json = new JSONObject();
			json.put("username", username);
			json.put("success", true);
			JSONUtility.returnJSON(response, json);
		} catch (ResponseException | NumberFormatException e) {
			JSONObject json = new JSONObject();
			json.put("success", false);
			json.put("error", e.getMessage());
			JSONUtility.returnJSON(response, json);
		} 
	}

}
