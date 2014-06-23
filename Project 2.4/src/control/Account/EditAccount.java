package control.Account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.riot.ResponseException;

import util.JSONUtility;
import databaseConnection.Hibernate;
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String summoner = request.getParameter("summonername");
		try {
			Hibernate h = new Hibernate();
			AccountLogica al = new AccountLogica(h);
			SummonerLogica sl = new SummonerLogica(h);
			sl.getSummonerByName(summoner);
			
		} catch (ResponseException e) {
			JSONUtility.sendError(response, "Time out while retrieving data... Try again later.");
		}
		
	}

}
