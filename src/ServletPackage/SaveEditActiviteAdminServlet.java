package ServletPackage;

import java.io.IOException;
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
 * Servlet implementation class SaveEditActiviteAdminServlet
 */
@WebServlet("/SaveEditActiviteAdminServlet")
public class SaveEditActiviteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveEditActiviteAdminServlet() {
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
		Activite current_edit = (Activite) session.getAttribute("ActiviteEdit");

		if(current_user == null) {
			
			request.getRequestDispatcher( "/WEB-INF/bean.jsp" ).forward( request, response );
		}
		else{
			String newLieu = request.getParameter("lieu") ;
			String newDateActivite = request.getParameter("dateActivite") ;
			String newDebut = request.getParameter("debut") ;
			String newFin = request.getParameter("fin") ;
			String newLogin = request.getParameter("login") ;

			int id = current_edit.getId();
			
			current_edit.setDate(newDateActivite);
			String rqString = "UPDATE Activite SET dateActivite='"+newDateActivite+"' WHERE id="+id+"";
			SQLConnector sc = new SQLConnector() ;
			sc.updateUser(rqString);
			
			current_edit.setDebut(newDebut) ;
			rqString = "UPDATE Activite SET debut='"+newDebut+"' WHERE id="+id+"";
			sc.updateUser(rqString);
			
			current_edit.setFin(newFin) ;
			rqString = "UPDATE Activite SET fin='"+newFin+"' WHERE id="+id+"";
			sc.updateUser(rqString);
		
			
			current_edit.setNomLieu(newLieu);
			rqString = "UPDATE Activite SET nomLieu='"+newLieu+"' WHERE id="+id+"";
			sc.updateUser(rqString);
			
			current_edit.setLogin(newLogin);
			rqString = "UPDATE Activite SET login='"+newLogin+"' WHERE id="+id+"";
			sc.updateUser(rqString);
			
			request.getRequestDispatcher( "/WEB-INF/allActivites.jsp" ).forward( request, response );
		}			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
