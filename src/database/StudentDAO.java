package database;

import java.sql.SQLException;
import java.util.Collection;

import domain.SchoolClass;
import domain.Student;

public interface StudentDAO {

	public Student getStudentById(int studentNo) throws DAOException;

	public Collection<Student> getAllStudents() throws DAOException;
	
	public void enrollStudentIntoClass(Student student, SchoolClass schoolClass) throws DAOException;

}