package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.TestService;

/**
 * Servlet implementation class DBServlet
 */
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doSomething(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doSomething(request, response);
	}
	
	private void doSomething(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		   System.out.println("BLAH BLAH");
		
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        TestService test = new TestService();
        
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SampleServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SampleServlet at " + request.getContextPath () + "</h1>");                           
            out.println("<h2>Servlet SampleServlet at " + test.trial() + "</h2>");
            out.println("</body>");
            out.println("</html>");
            
           } catch (SQLException e) {
			  
     	   e.printStackTrace();
			
		   } finally { 
            out.close();
        }         
        
	}

}
