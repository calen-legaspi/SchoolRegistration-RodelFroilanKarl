package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.EnrollmentService;
import services.EnrollmentServiceImpl;
import beans.EnrolledClassBean;
import database.DAOException;
import database.impl.SchoolClassDaoJDBC;
import database.impl.StudentDaoJDBC;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Student;

public class StudentViewEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentViewEnrollServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		enrollStudent(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		enrollStudent(request, response);
	}

	private void enrollStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Student loggedStudent = (Student) session
				.getAttribute("loggedInStudent");

		ArrayList<EnrolledClassBean> tableData = new ArrayList<EnrolledClassBean>();
		EnrolledClassBean bean;
		
		EnrollmentService service = new EnrollmentServiceImpl(
				new StudentDaoJDBC(), new SchoolClassDaoJDBC());
		
		Collection<SchoolClass> availableClasses;
		try {
			availableClasses = service.returnListOfAvailableClasses(loggedStudent);
		} catch (DAOException e1) {
			throw new ServletException("Data Access error: "+e1.getMessage());
		} catch (ScheduleConflictException e1) {
			throw new ServletException("Data Population Error: "+e1.getMessage());
		}
		
		try {

			for (SchoolClass sc : availableClasses) {
				bean = new EnrolledClassBean();
				bean.code = sc.getClassSubject().getCourseCode();
				bean.name = sc.getClassSubject().getCourseName();
				bean.level = sc.getClassSubject().getCourseLevel().toString();
				bean.day = sc.getClassSchedule().getClassDay().commonName;
				bean.start = sc.getClassSchedule().getClassStartTime()
						.toString();
				bean.end = sc.getClassSchedule().getClassEndTime().toString();
				bean.teacher = sc.getClassTeacher().getTeacherFirstName()
						.substring(0, 1)
						+ ". " + sc.getClassTeacher().getTeacherLastName();
				tableData.add(bean);
				bean.classNo = service.getSchoolClassNo(sc.getClassSubject(),
						sc.getClassSchedule(), sc.getClassTeacher()).toString();
			}

		} catch (DAOException e) {
			throw new ServletException("Class No Extraction Error.");
		}
		
		request.setAttribute("availableClasses", tableData);
		RequestDispatcher view = request
				.getRequestDispatcher("student-enroll-page");
		view.forward(request, response);
	}

}
