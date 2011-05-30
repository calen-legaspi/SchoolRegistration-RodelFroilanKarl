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
import org.junit.Before;
import org.junit.Test;

import database.DAOException;
import database.SubjectDAO;
import database.impl.SubjectDaoJDBC;
import domain.EducationLevel;
import domain.Schedule;
import domain.Subject;


public class SubjectDAOTest {
	
	private final String SUBJECT_TABLE = "Subject";
	
	protected IDatabaseConnection getConnection() throws SQLException, ClassNotFoundException, DatabaseUnitException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost/schoolregistration?user=root&password=");
		return new DatabaseConnection(jdbcConnection);
	}
	
	@Test
	public void getSubjectByIdTest() throws DAOException, SQLException, ClassNotFoundException, DatabaseUnitException {
		
		String subjectCode = "STR080";
		
		SubjectDAO subjectDAO = new SubjectDaoJDBC();
		
		Subject actualSubject = subjectDAO.getSubjectById(subjectCode);
		
		QueryDataSet expectedDataSet = new QueryDataSet(getConnection());
		expectedDataSet.addTable("Subject","Select * from Subject where subjectCode='"+subjectCode+"'");
		
		Subject expectedSubject = new Subject(expectedDataSet.getTable(SUBJECT_TABLE).getValue(0, "subjectCode").toString(),
				Enum.valueOf(EducationLevel.class, expectedDataSet.getTable(SUBJECT_TABLE).getValue(0, "subjectLevel").toString()),
				expectedDataSet.getTable(SUBJECT_TABLE).getValue(0, "subjectName").toString()
				);
		
		
		assertEquals(expectedSubject, actualSubject);
	}

	@Test
	public void getListOfAllSubjects() throws DAOException, SQLException, ClassNotFoundException, DatabaseUnitException {
		
		SubjectDAO subjectDAO = new SubjectDaoJDBC();
		
		Collection<Subject> actualListOfSubjects = subjectDAO.getAllSubjects();
		
		Collection<Subject> expectedListOfSubjects = new ArrayList<Subject>();
		
		QueryDataSet expectedDataSet = new QueryDataSet(getConnection());
		expectedDataSet.addTable("Subject","Select * from Subject");
		
        for(int i = 0; i < expectedDataSet.getTable(SUBJECT_TABLE).getRowCount(); i++)
        {
        	expectedListOfSubjects.add(new Subject(expectedDataSet.getTable(SUBJECT_TABLE).getValue(i, "subjectCode").toString(),
    				Enum.valueOf(EducationLevel.class, expectedDataSet.getTable(SUBJECT_TABLE).getValue(i, "subjectLevel").toString()),
    				expectedDataSet.getTable(SUBJECT_TABLE).getValue(i, "subjectName").toString()));
        				
        }
        
        assertEquals(expectedListOfSubjects, actualListOfSubjects);
		
	}
	
}
