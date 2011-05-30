package servlet;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AdminService;
import services.AdminServiceImpl;
import database.DAOException;
import database.ScheduleDAO;
import database.SchoolClassDAO;
import database.SubjectDAO;
import database.TeacherDAO;
import database.impl.ScheduleDaoJDBC;
import database.impl.SchoolClassDaoJDBC;
import database.impl.SubjectDaoJDBC;
import database.impl.TeacherDaoJDBC;
import domain.Schedule;

/**
 * Servlet implementation class AdminAddSchoolClassServlet
 */
public class AdminAddSchoolClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddSchoolClassServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		addNewClass(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		addNewClass(request,response);
	}

	private void addNewClass(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SchoolClassDAO schoolClassDao = new SchoolClassDaoJDBC();
		SubjectDAO subjectDao = new SubjectDaoJDBC();
		ScheduleDAO scheduleDao = new ScheduleDaoJDBC();
		TeacherDAO teacherDao = new TeacherDaoJDBC();
		AdminService adminService = new AdminServiceImpl(scheduleDao,
				teacherDao, subjectDao, schoolClassDao);

		try {
			String subjectCode = (String) request.getAttribute("subjectCode");
			String scheduleData = (String) request.getParameter("scheduleData");
			String teacherId = (String) request.getParameter("teacherId");
			System.out.println((String) request.getParameter("scheduleData"));
			System.out.println((String) request.getParameter("subjectCode"));
			System.out.println(getScheduleIdOf(scheduleData));
			adminService.addSchoolClass((String) request.getParameter("subjectCode"), getScheduleIdOf(scheduleData), teacherId);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-add-class");
		dispatcher.forward(request, response);
		} catch (DAOException e) {
			throw new ServletException(e.getMessage());
		}
	}

	public Long getScheduleIdOf(String scheduleData) throws DAOException {
		ScheduleDAO scheduleDao = new ScheduleDaoJDBC();
		String[] data = scheduleData.split(",");
		return scheduleDao.getPrimaryKey(new Schedule(Enum.valueOf(Schedule.Day.class, data[0]), Time.valueOf(data[1]),Time.valueOf(data[2])));
	}
}
