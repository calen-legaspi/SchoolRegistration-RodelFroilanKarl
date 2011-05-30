import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import database.DAOException;
import database.ScheduleDAO;
import database.SchoolClassDAO;
import database.SubjectDAO;
import database.TeacherDAO;
import database.impl.ScheduleDaoJDBC;
import database.impl.SchoolClassDaoJDBC;
import database.impl.SubjectDaoJDBC;
import database.impl.TeacherDaoJDBC;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Student;

public class SchoolClassDAOTest {

	@Test
	public void testGetASchoolClassById() throws DAOException,
			ScheduleConflictException {

		SchoolClassDAO schoolClass = new SchoolClassDaoJDBC();
		SchoolClass thisSchoolClass = schoolClass.getClassById(1);

		TeacherDAO teacherConnect = new TeacherDaoJDBC();
		ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
		SubjectDAO subjectConnect = new SubjectDaoJDBC();

		assertTrue(thisSchoolClass.equals(new SchoolClass(subjectConnect
				.getSubjectById("STR080"), scheduleConnect.getScheduleById(1),
				teacherConnect.getTeacherById(1))));
	}

	@Test
	public void getAllSchoolClass() throws ScheduleConflictException,
			SQLException, DAOException {

		SchoolClassDAO schoolClass = new SchoolClassDaoJDBC();

		Collection<SchoolClass> collectionOfSchoolClasses = new ArrayList<SchoolClass>();

		TeacherDAO teacherConnect = new TeacherDaoJDBC();
		ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
		SubjectDAO subjectConnect = new SubjectDaoJDBC();

		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("STR080"), scheduleConnect.getScheduleById(1),
				teacherConnect.getTeacherById(1)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("FIL889"), scheduleConnect.getScheduleById(2),
				teacherConnect.getTeacherById(2)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("MATH999"), scheduleConnect.getScheduleById(3),
				teacherConnect.getTeacherById(3)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("LIFE333"), scheduleConnect.getScheduleById(4),
				teacherConnect.getTeacherById(4)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("PE101"), scheduleConnect.getScheduleById(5),
				teacherConnect.getTeacherById(5)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("PHL5"), scheduleConnect.getScheduleById(1),
				teacherConnect.getTeacherById(6)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("MATH666"), scheduleConnect.getScheduleById(7),
				teacherConnect.getTeacherById(2)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("LIFE101"), scheduleConnect.getScheduleById(8),
				teacherConnect.getTeacherById(8)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("LAW101"), scheduleConnect.getScheduleById(2),
				teacherConnect.getTeacherById(9)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("CHIN656"), scheduleConnect.getScheduleById(5),
				teacherConnect.getTeacherById(10)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("PHIST003"),
				scheduleConnect.getScheduleById(1), teacherConnect
						.getTeacherById(4)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("MATH311"),
				scheduleConnect.getScheduleById(10), teacherConnect
						.getTeacherById(7)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("MATH667"), scheduleConnect.getScheduleById(9),
				teacherConnect.getTeacherById(10)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("ROY105"), scheduleConnect.getScheduleById(7),
				teacherConnect.getTeacherById(3)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("MOR888"), scheduleConnect.getScheduleById(5),
				teacherConnect.getTeacherById(1)));

		assertTrue(collectionOfSchoolClasses
				.equals(schoolClass.getAllClasses()));

	}

	public void getClassesOfStudent() throws DAOException {

		Student newStudent = new Student(1, "MINNY", "HILLER");

		SchoolClassDAO schoolClassConnector = new SchoolClassDaoJDBC();
		Collection<SchoolClass> allClassesOfStudent = schoolClassConnector
				.getClassesOfStudent(newStudent);

		Collection<SchoolClass> collectionOfSchoolClasses = new ArrayList<SchoolClass>();

		TeacherDAO teacherConnect = new TeacherDaoJDBC();
		ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
		SubjectDAO subjectConnect = new SubjectDaoJDBC();

		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("STR080"), scheduleConnect.getScheduleById(1),
				teacherConnect.getTeacherById(1)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("FIL889"), scheduleConnect.getScheduleById(2),
				teacherConnect.getTeacherById(2)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("MATH999"), scheduleConnect.getScheduleById(3),
				teacherConnect.getTeacherById(3)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("LIFE333"), scheduleConnect.getScheduleById(4),
				teacherConnect.getTeacherById(4)));
		collectionOfSchoolClasses.add(new SchoolClass(subjectConnect
				.getSubjectById("PE101"), scheduleConnect.getScheduleById(5),
				teacherConnect.getTeacherById(5)));

		assertTrue(collectionOfSchoolClasses.equals(allClassesOfStudent));

	}

	@Test
	public void getKeyOfSchoolClass() throws DAOException {

		TeacherDAO teacherDao = new TeacherDaoJDBC();
		ScheduleDAO scheduleDao = new ScheduleDaoJDBC();
		SubjectDAO subjectDao = new SubjectDaoJDBC();

		SchoolClassDAO schoolClassDao = new SchoolClassDaoJDBC();

		assertEquals(
				new Long(1),
				schoolClassDao.getPrimaryKey(
						subjectDao.getSubjectById("STR080"),
						scheduleDao.getScheduleById(1),
						teacherDao.getTeacherById(1)));
	}

}
