package database;

import java.util.Collection;

import domain.Student;

public interface StudentDAO {

	public Student getStudentById(int studentNo) throws DAOException;

	public Collection<Student> getAllStudents() throws DAOException;

}