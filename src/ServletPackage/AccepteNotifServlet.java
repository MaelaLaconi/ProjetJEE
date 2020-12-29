package ServletPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.Notification;
import BeanPackage.UserBean;

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
		//
		String ligneNotif = request.getParameter("expe") ;
		System.out.print("L'id est "+ligneNotif) ;
		
		response.setContentType("text/html");	
		
		HttpSession session = request.getSession();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
