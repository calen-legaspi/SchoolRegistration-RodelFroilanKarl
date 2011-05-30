import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import database.DAOException;
import database.TeacherDAO;
import database.impl.TeacherDaoJDBC;
import domain.Teacher;


public class TeacherDAOTest {

	@Test
	public void testGetTeacherById() throws DAOException {
		
		TeacherDAO teacherDAO = new TeacherDaoJDBC();		
		Teacher newTeacher = teacherDAO.getTeacherById(1);				
		Teacher expectedTeacher = new Teacher(1, "JOHN", "MILLER");
		
		assertEquals(expectedTeacher, newTeacher);
		
	}
	
	@Test
	public void testGetAllTeacher() throws DAOException
	{
		Collection<Teacher> expectedCollectionOfAllTeachers = new ArrayList<Teacher>();
		
		TeacherDAO teacherDAO = new TeacherDaoJDBC();
		
		expectedCollectionOfAllTeachers.add(new Teacher(1,"JOHN", "MILLER"));
		expectedCollectionOfAllTeachers.add(new Teacher(2,"MIKHAIL", "KOSBONOK"));
		expectedCollectionOfAllTeachers.add(new Teacher(3,"GANDIE", "NOTINDIAN"));
		expectedCollectionOfAllTeachers.add(new Teacher(4,"PUN", "PAN"));
		expectedCollectionOfAllTeachers.add(new Teacher(5,"SHU", "MI"));
		expectedCollectionOfAllTeachers.add(new Teacher(6,"HENRY", "KING"));
		expectedCollectionOfAllTeachers.add(new Teacher(7,"KOONG", "FOO"));
		expectedCollectionOfAllTeachers.add(new Teacher(8,"JAMES", "HARDING"));
		expectedCollectionOfAllTeachers.add(new Teacher(9,"WINDY", "CLOUD"));
		expectedCollectionOfAllTeachers.add(new Teacher(10,"GEOWA", "SHINGTON"));
		expectedCollectionOfAllTeachers.add(new Teacher(11,"JUNE", "JULY"));
		expectedCollectionOfAllTeachers.add(new Teacher(12,"HU", "YOO"));
		expectedCollectionOfAllTeachers.add(new Teacher(13,"NAT", "YU"));
		
		Collection<Teacher> collectionOfAllTeachers = new ArrayList<Teacher>();
		collectionOfAllTeachers = teacherDAO.getListOfAllTeachers();
		
		assertEquals(expectedCollectionOfAllTeachers, collectionOfAllTeachers);
		
	}
	
}
