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
import domain.Student;

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogInServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		extractUserData(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		extractUserData(request, response);

	}

	private void extractUserData(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String type = request.getParameter("type");
		HttpSession session = request.getSession();

		if (type == null) {

			response.sendRedirect("main");

		} else {

			if ("admin".equals(type)) {

				session.setAttribute("adminIsLoggedIn", "true");
				
				response.sendRedirect("admin-view");

			} else if ("student".equals(type)) {

				if (request.getParameter("studentNo") == null) {
					response.sendRedirect("main");
				} else {

					int studentNo = Integer.valueOf(request
							.getParameter("studentNo"));

					EnrollmentService studentService = new EnrollmentServiceImpl(
							new StudentDaoJDBC(), new SchoolClassDaoJDBC());
					Student thisStudent = null;
					try {
						thisStudent = studentService.getStudentById(studentNo);
						thisStudent = studentService
								.getStudentEnrolledClassesById(thisStudent);
					} catch (DAOException e) {
						response.sendRedirect("main");
						e.printStackTrace(); //TODO: throw new ServletException()
					} catch (ScheduleConflictException e) {
						response.sendRedirect("main");
						e.printStackTrace();
					} 
					session.setAttribute("loggedInStudent", thisStudent);
					response.sendRedirect("student-account");
				}

			} else {

				response.sendRedirect("main");

			}

		}

	}
}
