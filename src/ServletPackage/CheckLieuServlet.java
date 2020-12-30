package ServletPackage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class CheckLieuServlet
 */
@WebServlet("/CheckLieuServlet")
public class CheckLieuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLieuServlet() {
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
			String lieu = request.getParameter("lieu") ;		//	public boolean  {
			String dateActivite = request.getParameter("dateActivite") ;
			String debut = request.getParameter("debut") ;
			String fin = request.getParameter("fin") ;
			String login = current_user.getLogin() ;
			
			System.out.print("date : "+ dateActivite) ;

			SQLConnector sc = new SQLConnector() ;
			if(sc.existLieu(lieu, dateActivite, debut, fin, login)) {
				sc.createActivite(dateActivite, debut, fin , lieu, login) ;
			}
			else {
				sc.createActivite(dateActivite, debut, fin , lieu, login) ;
				request.getRequestDispatcher( "/WEB-INF/lieu.jsp" ).forward( request, response );

			}
			//	public void createActivite(Date date, Time debut, Time fin, String nomLieu, String login) {


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
