package ServletPackage;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class positifServlet
 */
@WebServlet("/positifServlet")
public class PositifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositifServlet() {
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
			String adr = request.getParameter("adr") ;
			SQLConnector sc = new SQLConnector() ;
			List<String> amis = sc.getAmis(current_user.getLogin()) ;
			for(String s : amis) {
				sc.createNotification(current_user.getLogin(), s, "positif", "attente");
			}
			//toutes les activitées des 10 derniers jours
			List<Activite> activites = sc.getSameDateactivity();
			Activite act ;
			//liste de toutes les activités du current user
			List<Activite> userActivite = sc.getActivite(current_user.getLogin());
			
			List<Activite> lisNotif = new ArrayList() ;

			for(Activite user : userActivite) {
				for(Activite a : activites) {
					act = sc.getActiviteCovid(user.getNomLieu(), user.getDate(), user.getDeb(), user.getFin(), a.getId(), user.getLogin());
					if(act != null && !lisNotif.contains(act)) {
						lisNotif.add(act) ;
						sc.createNotification(current_user.getLogin(), a.getLogin(), "positif", "attente");
					}
				}
			}
			
			request.getRequestDispatcher( "/WEB-INF/logged.jsp" ).forward( request, response );

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
