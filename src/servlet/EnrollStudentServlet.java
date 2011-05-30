package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.EnrollmentService;
import services.EnrollmentServiceImpl;
import database.DAOException;
import database.impl.SchoolClassDaoJDBC;
import database.impl.StudentDaoJDBC;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Student;

/**
 * Servlet implementation class EnrollStudentServlet
 */
public class EnrollStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EnrollStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		enrollStudent(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		enrollStudent(request, response);		
		
	}

	private void enrollStudent(HttpServletRequest request,
			HttpServletResponse response) throws 
			IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		Student student = Student.class.cast(session.getAttribute("loggedInStudent"));
		String enrollInto = request.getParameter("subject");
		
		if(enrollInto == null || student == null){
			throw new ServletException("You are not allowed to access this page.");
		} else {
			
			EnrollmentService service = new EnrollmentServiceImpl(new StudentDaoJDBC(),new SchoolClassDaoJDBC());
			
			try {
				SchoolClass classToEnroll = service.getSchoolClassById(Integer.valueOf(enrollInto));
				service.enrollStudentToClass(student, classToEnroll);
				
				student.enrollIntoSchoolClass(classToEnroll);
				session.setAttribute("loggedInStudent", student);
				
			} catch (NumberFormatException e) {
				throw new ServletException("Invalid value for school Class id.");
			} catch (DAOException e) {
				throw new ServletException("Cannot retrieve this class from database.");
			} catch (ScheduleConflictException e) {
				throw new ServletException("Conflict in classes in database detected.\n"+e.getMessage());
			}
			
		}	
		
		response.sendRedirect("student-enroll");
	}

}
