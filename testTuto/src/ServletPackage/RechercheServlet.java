package ServletPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class RechercheServlet
 */
@WebServlet("/RechercheServlet")
public class RechercheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheServlet() {
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
		
		UserBean current_user = (UserBean) session.getAttribute("current_user");
		
		if(current_user == null) {
			
			request.getRequestDispatcher( "/WEB-INF/bean.jsp" ).forward( request, response );
		}
		else{
			String userSearch = request.getParameter("recherche") ;
			SQLConnector sc = new SQLConnector() ;
			UserBean friend = sc.showFriend(userSearch);  //la personne qu'on a cherche dans la barre de rechecher
			session.setAttribute("current_search",friend);//suppose non null ajouter condition pour verifier
			request.getRequestDispatcher( "/WEB-INF/addFriend.jsp" ).forward( request, response );

		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");	
		
		HttpSession session = request.getSession();
		
		UserBean current_user = (UserBean) session.getAttribute("current_user");
		
		if(current_user == null) {
			
			request.getRequestDispatcher( "/WEB-INF/bean.jsp" ).forward( request, response );
		}
		else{
			String userSearch = request.getParameter("recherche") ;
			SQLConnector sc = new SQLConnector() ;
			UserBean friend = sc.showFriend(userSearch);  //la personne qu'on a cherche dans la barre de rechecher
			session.setAttribute("current_search",friend);//suppose non null ajouter condition pour verifier
			request.getRequestDispatcher( "/WEB-INF/addFriend.jsp" ).forward( request, response );

		}
				}

}
