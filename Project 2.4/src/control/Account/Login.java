package control.Account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.riot.ResponseException;

import databaseConnection.CouchDB;
import databaseConnection.Hibernate;
import logica.AccountLogica;
import logica.MD5Hashing;
import logica.SummonerLogica;
import util.JSONUtility;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Account/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		int status = AccountLogica.ERROR;
		AccountLogica al = new AccountLogica(new Hibernate(), new CouchDB());
		status = al.login(username, password);
		if(status == AccountLogica.OK) {
			JSONObject json = new JSONObject();
			json.put("session", al.getIdByName(username));
			json.put("success", true);
			JSONUtility.sendJSON(response, json);
		} else {
			JSONUtility.sendError(response, "Username/password incorrect");
		}
	}

}
