import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import database.DAOException;
import database.ScheduleDAO;
import database.ScheduleDaoJDBC;
import database.SchoolClassDAO;
import database.SchoolClassDaoJDBC;
import database.SubjectDAO;
import database.SubjectDaoJDBC;
import database.TeacherDAO;
import domain.ScheduleConflictException;
import domain.SchoolClass;


public class SchoolClassDAOTest {
	
	@Test
	public void testGetASchoolClassById() throws DAOException, ScheduleConflictException{
		
		SchoolClassDAO schoolClass = new SchoolClassDaoJDBC();
		SchoolClass thisSchoolClass = schoolClass.getClassById(1);
		
		TeacherDAO teacherConnect = new TeacherDAO();
		ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
		SubjectDAO subjectConnect = new SubjectDaoJDBC();
		
		assertTrue(thisSchoolClass.equals(
				new SchoolClass(
						subjectConnect.getSubjectById("STR080"), 
						scheduleConnect.getScheduleById(1), 
						teacherConnect.getTeacherById(1)
				)));		
	}
	
	@Test
	public void getAllSchoolClass() throws ScheduleConflictException, SQLException, DAOException{
		
		SchoolClassDAO schoolClass = new SchoolClassDaoJDBC();
		
		Collection<SchoolClass> collectionOfSchoolClasses 
		  = new ArrayList<SchoolClass>(); 
		
		TeacherDAO teacherConnect = new TeacherDAO();
		ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
		SubjectDAO subjectConnect = new SubjectDaoJDBC();
		
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("STR080"), 
						scheduleConnect.getScheduleById(1), 
						teacherConnect.getTeacherById(1)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("FIL889"), 
						scheduleConnect.getScheduleById(2), 
						teacherConnect.getTeacherById(2)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("MATH999"), 
						scheduleConnect.getScheduleById(3), 
						teacherConnect.getTeacherById(3)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("LIFE333"), 
						scheduleConnect.getScheduleById(4), 
						teacherConnect.getTeacherById(4)
				));
		collectionOfSchoolClasses.add(
				new SchoolClass(
						subjectConnect.getSubjectById("PE101"), 
						scheduleConnect.getScheduleById(5), 
						teacherConnect.getTeacherById(5)
				));
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
		
		assertTrue(collectionOfSchoolClasses.equals(schoolClass.getAllClasses()));
		
	}

}
