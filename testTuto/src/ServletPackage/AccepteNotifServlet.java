package ServletPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import BeanPackage.Notification;
import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class AccepteNotifServlet
 */
@WebServlet("/AccepteNotifServlet")
//@RequestMapping("/WEB-INF/bean.jsp", method= RequestMethode.POST) 
public class AccepteNotifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccepteNotifServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");	

		//on recupere le numero de la notif que l'on veut accepter
		
		String ligneNotif = request.getParameter("expe1") ;
		System.out.print("ligne recup "+ ligneNotif) ;

		int nbLigne = Integer.parseInt(ligneNotif);
		
		
		HttpSession session = request.getSession();
		UserBean current_user = (UserBean) session.getAttribute("current_user");
		
		if(current_user == null) {
			request.getRequestDispatcher( "/WEB-INF/bean.jsp" ).forward( request, response );
		}
		else{
			SQLConnector sc = new SQLConnector();
			List list = sc.getNotifAttenteAmi(current_user.getLogin()); 
			Notification notif = (Notification) list.get(nbLigne);
			sc.accepteNotifUser(notif);
			request.getRequestDispatcher( "/WEB-INF/notifications.jsp" ).forward( request, response );

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
