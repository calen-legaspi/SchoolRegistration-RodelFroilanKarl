package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogInServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String myName = request.getParameter("myText");
		
        response.setContentType("text/html;charset=UTF-8");
        /*PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SampleServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SampleServlet at " + request.getContextPath () + "</h1>");
            out.println("<h2>Servlet SampleServlet at " + myName + "</h2>");
            out.println("</body>");
            out.println("</html>");
            
        } finally { 
            out.close();
        }*/
        response.sendRedirect("testing.jsp");
		
	}

}
