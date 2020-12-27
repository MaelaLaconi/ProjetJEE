package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Utilisateur; 

/**
 * Servlet implementation class Utilisateur
 */
@WebServlet("/Utilisateur")
public class UtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");	
		
		HttpSession session = request.getSession();
		
		Utilisateur current_user = (Utilisateur) session.getAttribute("current_user");
		
		if(current_user == null) {
			
			request.getRequestDispatcher( "/JSP_pages/bean.jsp" ).forward( request, response );
		}
		else{
			if(current_user.getRang().trim().equals("basic_user")) {
				request.getRequestDispatcher( "/JSP_pages/logged.jsp" ).forward( request, response );
			}
			else {
				if(current_user.getRang().trim().equals("admin")) {
					request.getRequestDispatcher( "/JSP_pages/admin.jsp" ).forward( request, response );
				}
			}
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
