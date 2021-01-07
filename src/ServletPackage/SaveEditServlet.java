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
 * Servlet implementation class SaveEditServlet
 */
@WebServlet("/SaveEditServlet")
public class SaveEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveEditServlet() {
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
			String oldLogin = current_user.getLogin();
			String newName = request.getParameter("newName") ;
			String newLastName = request.getParameter("newLastName") ;
			String newPw = request.getParameter("newPw") ;
			String newLogin = request.getParameter("newLogin") ;
			String newNaissance = request.getParameter("newNaissance");
			int id = current_user.getId();
			
			current_user.setNom(newLastName);
			String rqString = "UPDATE User SET nom='"+newLastName+"' WHERE login='"+oldLogin+"'";
			SQLConnector sc = new SQLConnector() ;
			sc.updateUser(rqString);
			
			current_user.setPrenom(newName);
			rqString = "UPDATE User SET prenom='"+newName+"' WHERE login='"+oldLogin+"'";
			sc.updateUser(rqString);
			
			current_user.setPassword(newPw);
			rqString = "UPDATE User SET password='"+newPw+"' WHERE login='"+oldLogin+"'";
			sc.updateUser(rqString);
			
			current_user.setDate(newNaissance);
			rqString = "UPDATE User SET date_naissance='"+newNaissance+"' WHERE login='"+oldLogin+"'";
			sc.updateUser(rqString);
			
			current_user.setLogin(newLogin);
			rqString = "UPDATE User SET login='"+newLogin+"' WHERE login='"+oldLogin+"'";
			sc.updateUser(rqString);
	
			request.getRequestDispatcher( "/WEB-INF/logged.jsp" ).forward( request, response );
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
