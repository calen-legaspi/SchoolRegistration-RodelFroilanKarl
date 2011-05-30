package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Subject;
import domain.Teacher;

/**
 * Servlet implementation class AdminGetAllInfo
 */
public class AdminGetAllInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminGetAllInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getAllClassData(request,response);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScheduleConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getAllClassData(request,response);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScheduleConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getAllClassData(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException, ScheduleConflictException{
		
		SchoolClassDAO schoolClassDao = new SchoolClassDaoJDBC();
		SubjectDAO subjectDao = new SubjectDaoJDBC();
		ScheduleDAO scheduleDao = new ScheduleDaoJDBC();
		TeacherDAO teacherDao = new TeacherDaoJDBC();
		AdminService adminService = new AdminServiceImpl(scheduleDao, teacherDao, subjectDao, schoolClassDao);
		
		request.setAttribute("ListOfSchedule",getAllSchedules(adminService));
		
		request.setAttribute("ListOfSubject",getAllSubjects(adminService));
		
		request.setAttribute("ListOfTeacher",getAllTeachers(adminService));
		
		request.setAttribute("allSchoolClass", adminService.getAllSchoolClass());
		
		for(SchoolClass schoolClass : adminService.getAllSchoolClass()){
			System.out.println(schoolClass);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminAddSchoolClass");
		dispatcher.forward(request, response);
	}

	private Collection<Teacher> getAllTeachers(AdminService adminService)
			throws DAOException {
		Collection<Teacher> teacherList = new ArrayList<Teacher>();
		for(Teacher teacher : adminService.getAllTeachers()){
			teacherList.add(teacher);
		}
		return teacherList;
	}

	private Collection<Subject> getAllSubjects(AdminService adminService) throws DAOException {
		Collection<Subject> subjectList = new ArrayList<Subject>();
		for(Subject subject : adminService.getAllSubjects()){
			subjectList.add(subject);
		}
		return subjectList;
	}

	private Collection<Schedule> getAllSchedules(AdminService adminService) throws DAOException {
		Collection<Schedule> scheduleList = new ArrayList<Schedule>();
		for(Schedule schedule : adminService.getAllSchedules()){
			scheduleList.add(schedule);
		}
		return scheduleList;
	}
}
