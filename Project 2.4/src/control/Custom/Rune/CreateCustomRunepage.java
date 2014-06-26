package control.Custom.Rune;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseConnection.Hibernate;
import logica.RunePageLogic;
import util.JSONUtility;

/**
 * Servlet implementation class CreateCustomRunepage
 */
@WebServlet("/Custom/Runepage/Create")
public class CreateCustomRunepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public CreateCustomRunepage() {
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
			String name = request.getParameter("name");
			Long account= Long.parseLong(request.getParameter("account"));
			Long champion = Long.parseLong(request.getParameter("champion"));
			String[] runes = request.getParameterValues("runes");
			System.out.println(runes.length);
			Long[] runeId = new Long[runes.length];
			for(int i = 0; i < runeId.length; i++)
				runeId[i] = Long.parseLong(runes[i]);
			RunePageLogic rpl = new RunePageLogic(new Hibernate());
			rpl.makeNewRunePage(name, account, champion, runeId);
		} catch(NumberFormatException e) {
			JSONUtility.sendError(response, "Something went wrong while creating runepage<br>Try logging out and in again.");
		}
		
	}

}
