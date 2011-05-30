package services;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import database.DAOException;
import database.ScheduleDAO;
import database.SchoolClassDAO;
import database.StudentDAO;
import database.SubjectDAO;
import database.TeacherDAO;
import database.impl.ScheduleDaoJDBC;
import database.impl.SchoolClassDaoJDBC;
import database.impl.StudentDaoJDBC;
import database.impl.SubjectDaoJDBC;
import database.impl.TeacherDaoJDBC;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Student;

import database.mock.*;

public class EnrollmentServiceTest {

	
	@Test
	public void testReturnAvailableClasses() throws DAOException, ScheduleConflictException{
		
		EnrollmentService service = new EnrollmentServiceImpl(new StudentDaoJDBC(), new SchoolClassDaoJDBC());
		
		TeacherDAO teacherConnect = new TeacherDaoJDBC();
		ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
		SubjectDAO subjectConnect = new SubjectDaoJDBC();
				
		Student student = new FakeStudentDAO().getStudentById(1);
		
		SchoolClassDAO scd = new SchoolClassDaoJDBC();
		for(SchoolClass c : scd.getClassesOfStudent(student)){
			student.enrollIntoSchoolClass(c);
		}
		
		Collection<SchoolClass> availableClasses = service.returnListOfAvailableClasses(student);
		
		Collection<SchoolClass> collectionOfSchoolClasses = new ArrayList<SchoolClass>();
		
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("PHL5"), 
						scheduleConnect.getScheduleById(1), 
						teacherConnect.getTeacherById(6)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("MATH666"), 
						scheduleConnect.getScheduleById(7), 
						teacherConnect.getTeacherById(2)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("LIFE101"), 
						scheduleConnect.getScheduleById(8), 
						teacherConnect.getTeacherById(8)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("LAW101"), 
						scheduleConnect.getScheduleById(2), 
						teacherConnect.getTeacherById(9)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("CHIN656"), 
						scheduleConnect.getScheduleById(5), 
						teacherConnect.getTeacherById(10)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("PHIST003"), 
						scheduleConnect.getScheduleById(1), 
						teacherConnect.getTeacherById(4)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("MATH311"), 
						scheduleConnect.getScheduleById(10), 
						teacherConnect.getTeacherById(7)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("MATH667"), 
						scheduleConnect.getScheduleById(9), 
						teacherConnect.getTeacherById(10)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("ROY105"), 
						scheduleConnect.getScheduleById(7), 
						teacherConnect.getTeacherById(3)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("MOR888"), 
						scheduleConnect.getScheduleById(5), 
						teacherConnect.getTeacherById(1)
				));
		
		assertTrue(collectionOfSchoolClasses.equals(availableClasses));
		
	}
	
	@Test
	public void testEnrollIntoClass() throws DAOException, ScheduleConflictException, SQLException{
		
		StudentDAO studentDao = new StudentDaoJDBC();
		SchoolClassDAO schoolClassDao = new SchoolClassDaoJDBC();
		
		EnrollmentService service = new EnrollmentServiceImpl(studentDao, schoolClassDao);
		
		Student testStudent = studentDao.getStudentById(1);
		
		service.enrollStudentToClass(testStudent,schoolClassDao.getClassById(6));
		
	}
	
	
	
}
