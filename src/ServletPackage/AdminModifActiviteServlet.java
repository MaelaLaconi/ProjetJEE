package ServletPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.Activite;
import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class AdminModifActiviteServlet
 */
@WebServlet("/AdminModifActiviteServlet")
public class AdminModifActiviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminModifActiviteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		UserBean current_user = (UserBean) session.getAttribute("current_user");
		
		if(current_user == null) {
			
			request.getRequestDispatcher( "/WEB-INF/bean.jsp" ).forward( request, response );
		}
		else{	
			SQLConnector sc = new SQLConnector() ;
			
			String ligne = request.getParameter("idUser") ;
			int nbLigne = Integer.parseInt(ligne);
			List list = sc.getAllActivites();	
			Activite act = (Activite)list.get(nbLigne) ;
			session.setAttribute("ActiviteEdit", act);
			request.getRequestDispatcher( "/WEB-INF/adminModifActivite.jsp" ).forward( request, response );
			
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
