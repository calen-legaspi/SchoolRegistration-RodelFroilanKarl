
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import database.DAOException;
import database.StudentDAO;
import database.impl.StudentDaoJDBC;
import domain.Student;

public class StudentDAOTest {

	@Test
	public void testGetStudentById() throws SQLException, DAOException{
		
		StudentDAO student = new StudentDaoJDBC();
		Student myStudent = student.getStudentById(1);
		
		Student testStudent = new Student(1, "MINNY", "HILLER");
		
		assertTrue(myStudent.equals(testStudent));
		
	}
	
	@Test
	public void testGetAllStudents() throws SQLException, DAOException{			
	
	    StudentDAO student = new StudentDaoJDBC();
		
	    Collection<Student> collectionOfAllStudents = new ArrayList<Student>();
	    collectionOfAllStudents.add(new Student(1, "MINNY", "MILLER"));
	    collectionOfAllStudents.add(new Student(2, "SWEET", "HONEY"));
	    collectionOfAllStudents.add(new Student(3, "MIN", "HO"));
	    
	    assertTrue(collectionOfAllStudents.equals(student.getAllStudents()));
		
	}
	
}
