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
import beans.StudentBean;
import database.DAOException;
import database.impl.SchoolClassDaoJDBC;
import database.impl.StudentDaoJDBC;
import domain.Fees;
import domain.SchoolClass;
import domain.Student;

/**
 * Servlet implementation class StudentViewCalculateTuitionServlet
 */
public class StudentViewCalculateTuitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentViewCalculateTuitionServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		getEnrolledClasses(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		getEnrolledClasses(request, response);

	}

	private void getEnrolledClasses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Student loggedStudent = (Student) session
				.getAttribute("loggedInStudent");

		Collection<SchoolClass> enrolledClasses = loggedStudent
				.getEnrolledClasses();

		ArrayList<EnrolledClassBean> tableData = new ArrayList<EnrolledClassBean>();
		EnrolledClassBean bean;

		EnrollmentService service = new EnrollmentServiceImpl(
				new StudentDaoJDBC(), new SchoolClassDaoJDBC());

		try {

			for (SchoolClass sc : enrolledClasses) {
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
				bean.balance = Fees.getCost(sc.getClassSubject().getCourseLevel()).toString();
			}

		} catch (DAOException e) {
			throw new ServletException("Class No Extraction Error.");
		}
		
		request.setAttribute("enrolledClasses", tableData);
		
		StudentBean studentInfo = new StudentBean();
		studentInfo.setMisc_fee(Fees.MISC.getCost().toString());
		studentInfo.setTotal_tuition(loggedStudent.calculateTuition().toString());		
		
		request.setAttribute("studentInfo", studentInfo);
		
		RequestDispatcher view = request
				.getRequestDispatcher("student-tuition-page");
		view.forward(request, response);
	}

}
