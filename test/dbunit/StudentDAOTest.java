package dbunit;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.junit.Test;

import database.DAOException;
import database.StudentDAO;
import database.impl.StudentDaoJDBC;
import domain.Schedule;
import domain.Student;

public class StudentDAOTest {
	
	private final String STUDENT_TABLE = "Student";
	
	protected IDatabaseConnection getConnection() throws SQLException, ClassNotFoundException, DatabaseUnitException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost/schoolregistration?user=root&password=");
		return new DatabaseConnection(jdbcConnection);
	}
	

	@Test
	public void testGetStudentById() throws SQLException, DAOException, ClassNotFoundException, DatabaseUnitException{
		
		int idNo=1;
		
		StudentDAO student = new StudentDaoJDBC();
		Student actualStudent = student.getStudentById(1);
		
		QueryDataSet expectedDataSet = new QueryDataSet(getConnection());
		expectedDataSet.addTable("Student","Select * from Student where idNo="+idNo);
		
		Student expectedStudent = new Student(Integer.parseInt(expectedDataSet.getTable(STUDENT_TABLE).getValue(0, "idNo").toString()),
				expectedDataSet.getTable(STUDENT_TABLE).getValue(0, "firstName").toString(),
				expectedDataSet.getTable(STUDENT_TABLE).getValue(0, "lastName").toString()
				);
		
		
		assertEquals(expectedStudent, actualStudent);
		
	}
	
	@Test
	public void testGetAllStudents() throws SQLException, DAOException, NumberFormatException, ClassNotFoundException, DatabaseUnitException{			
	
	    StudentDAO studentDAO = new StudentDaoJDBC();
		
	    Collection<Student> actualListOfStudents = new ArrayList<Student>();
	    actualListOfStudents = studentDAO.getAllStudents();
	    
	    Collection<Student> expectedListOfStudents = new ArrayList<Student>();
	    
		QueryDataSet expectedDataSet = new QueryDataSet(getConnection());
		expectedDataSet.addTable("Student","Select * from Student order by idNo");
		
        for(int i = 0; i < expectedDataSet.getTable(STUDENT_TABLE).getRowCount(); i++)
        {
        	expectedListOfStudents.add(new Student(
        				Integer.parseInt(expectedDataSet.getTable(STUDENT_TABLE).getValue(i, "idNo").toString()), 
        				expectedDataSet.getTable(STUDENT_TABLE).getValue(i, "firstName").toString(), 
        				expectedDataSet.getTable(STUDENT_TABLE).getValue(i, "lastName").toString())
        	);
        }
	    
	    assertEquals(expectedListOfStudents, actualListOfStudents);
		
	}
	
}
