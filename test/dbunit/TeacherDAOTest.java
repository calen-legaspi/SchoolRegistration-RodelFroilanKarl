package dbunit;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.junit.Test;

import database.DAOException;
import database.TeacherDAO;
import database.impl.TeacherDaoJDBC;
import domain.Student;
import domain.Teacher;


public class TeacherDAOTest {

	private final String TEACHER_TABLE = "Teacher";
	
	protected IDatabaseConnection getConnection() throws SQLException, ClassNotFoundException, DatabaseUnitException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost/schoolregistration?user=root&password=");
		return new DatabaseConnection(jdbcConnection);
	}
	
	@Test
	public void testGetTeacherById() throws DAOException, SQLException, ClassNotFoundException, DatabaseUnitException {
		
		int teacherNo=1;
		
		TeacherDAO teacherDAO = new TeacherDaoJDBC();		
		Teacher actualTeacher = teacherDAO.getTeacherById(teacherNo);				
		
		
		QueryDataSet expectedDataSet = new QueryDataSet(getConnection());
		expectedDataSet.addTable("Teacher","Select * from Teacher where teacherNo="+teacherNo);
		
		Teacher expectedTeacher = new Teacher(Integer.parseInt(expectedDataSet.getTable(TEACHER_TABLE).getValue(0, "teacherNo").toString()),
				expectedDataSet.getTable(TEACHER_TABLE).getValue(0, "firstName").toString(),
				expectedDataSet.getTable(TEACHER_TABLE).getValue(0, "lastName").toString()
				);
				
		assertEquals(expectedTeacher, actualTeacher);
		
	}
	
	@Test
	public void testGetAllTeacher() throws DAOException, SQLException, ClassNotFoundException, DatabaseUnitException
	{	
		TeacherDAO teacherDAO = new TeacherDaoJDBC();
		
		Collection<Teacher> actualCollectionOfAllTeachers = new ArrayList<Teacher>();
		actualCollectionOfAllTeachers = teacherDAO.getListOfAllTeachers();
		
		Collection<Teacher> expectedCollectionOfAllTeachers = new ArrayList<Teacher>();
		
		QueryDataSet expectedDataSet = new QueryDataSet(getConnection());
		expectedDataSet.addTable("Teacher","Select * from Teacher order by teacherNo");
		
        for(int i = 0; i < expectedDataSet.getTable(TEACHER_TABLE).getRowCount(); i++)
        {
        	expectedCollectionOfAllTeachers.add(new Teacher(
        				Integer.parseInt(expectedDataSet.getTable(TEACHER_TABLE).getValue(i, "teacherNo").toString()), 
        				expectedDataSet.getTable(TEACHER_TABLE).getValue(i, "firstName").toString(), 
        				expectedDataSet.getTable(TEACHER_TABLE).getValue(i, "lastName").toString())
        	);
        }

		
		assertEquals(expectedCollectionOfAllTeachers, actualCollectionOfAllTeachers);
		
	}
	
}
