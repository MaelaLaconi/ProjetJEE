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
 * Servlet implementation class SaveEditUserAdminServlet
 */
@WebServlet("/SaveEditUserAdminServlet")
public class SaveEditUserAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveEditUserAdminServlet() {
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
		UserBean current_edit = (UserBean) session.getAttribute("UserEdit");

		if(current_user == null) {
			
			request.getRequestDispatcher( "/WEB-INF/bean.jsp" ).forward( request, response );
		}
		else{
			String oldLogin = current_edit.getLogin();
			String newName = request.getParameter("newName") ;
			String newLastName = request.getParameter("newLastName") ;
			String newPw = request.getParameter("newPw") ;
			String newLogin = request.getParameter("newLogin") ;
			String newNaissance = request.getParameter("newNaissance");
			
			current_edit.setNom(newLastName);
			String rqString = "UPDATE User SET nom='"+newLastName+"' WHERE login='"+oldLogin+"'";
			SQLConnector sc = new SQLConnector() ;
			sc.updateUser(rqString);
			
			current_edit.setPrenom(newName);
			rqString = "UPDATE User SET prenom='"+newName+"' WHERE login='"+oldLogin+"'";
			sc.updateUser(rqString);
			
			current_edit.setPassword(newPw);
			rqString = "UPDATE User SET password='"+newPw+"' WHERE login='"+oldLogin+"'";
			sc.updateUser(rqString);
			
			current_edit.setDate(newNaissance);
			rqString = "UPDATE User SET date_naissance='"+newNaissance+"' WHERE login='"+oldLogin+"'";
			sc.updateUser(rqString);
			
			if(!sc.existLogin(newLogin)) {
				current_edit.setLogin(newLogin);
				rqString = "UPDATE User SET login='"+newLogin+"' WHERE login='"+oldLogin+"'";
				sc.updateUser(rqString);
			}
			request.getRequestDispatcher( "/WEB-INF/allUsers.jsp" ).forward( request, response );
		}		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
