package ServletPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.Lieu;
import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class SaveEditLieuAdminServlet
 */
@WebServlet("/SaveEditLieuAdminServlet")
public class SaveEditLieuAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveEditLieuAdminServlet() {
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
		Lieu current_edit = (Lieu) session.getAttribute("LieuEdit");

		if(current_user == null) {
			
			request.getRequestDispatcher( "/WEB-INF/bean.jsp" ).forward( request, response );
		}
		else{
			String newNom = request.getParameter("nom") ;
			String newAdr = request.getParameter("adr") ;
		
			int id = current_edit.getId();
			
			current_edit.setNom(newNom) ;
			String rqString = "UPDATE Lieu SET nom='"+newNom+"' WHERE id="+id+"";
			SQLConnector sc = new SQLConnector() ;
			sc.updateUser(rqString);
			
			current_edit.setAdr(newAdr);
			rqString = "UPDATE Lieu SET adresse='"+newAdr+"' WHERE id="+id+"";
			sc.updateUser(rqString);
			
			
			request.getRequestDispatcher( "/WEB-INF/allLieux.jsp" ).forward( request, response );
		}			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
