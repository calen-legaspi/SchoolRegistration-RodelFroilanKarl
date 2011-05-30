package servlet;

import java.io.IOException;
import java.util.Collection;

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
 * Servlet implementation class AdminViewSchedServlet
 */
public class AdminViewSchedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminViewSchedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getAllScedules(request, response);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getAllScedules(request, response);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getAllScedules(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException{
		
		SchoolClassDAO schoolClassDao = new SchoolClassDaoJDBC();
		SubjectDAO subjectDao = new SubjectDaoJDBC();
		ScheduleDAO scheduleDao = new ScheduleDaoJDBC();
		TeacherDAO teacherDao = new TeacherDaoJDBC();
		AdminService adminService = new AdminServiceImpl(scheduleDao, teacherDao, subjectDao, schoolClassDao);	
		
		Collection<Schedule> allSchedules = adminService.getAllSchedules();
		
		request.setAttribute("allSchedules", allSchedules);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminViewSchedules");
		dispatcher.forward(request, response);
		
		
	}
}
