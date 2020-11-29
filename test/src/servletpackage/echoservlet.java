package servletpackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class echoservlet
 */
@WebServlet("/echoservlet")
public class echoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public echoservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html") ;
		
		String prenom = request.getParameter("fname");
		String nom = request.getParameter("lname");
		String langage = request.getParameter("lang");
		PrintWriter out = response.getWriter() ;
		out.println("<HTML>\n");
		out.println("<BODY>\n");
		out.println("<H1>Bonjour</H1>\n");
		
		if(prenom == null || prenom.trim().length() == 0 ){
			out.println("Prenom : MISSING <br/>");
		}else {
			out.println("Prenom : " + prenom + "</br>");
		}
		
		out.println("Nom :" + nom + "</br>");
		out.println("Language fav : " + langage + "</br>");
		out.println("</BODY>\n");
		out.println("</HTML>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
