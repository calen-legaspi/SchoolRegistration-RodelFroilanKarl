package database.mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import database.DAOException;
import database.StudentDAO;
import domain.SchoolClass;
import domain.Student;

public class FakeStudentDAO implements StudentDAO {

	@Override
	public Student getStudentById(int studentNo) throws DAOException {
		
		return new Student(studentNo,"Jose","Rizal");
	}

	@Override
	public Collection<Student> getAllStudents() throws DAOException {
		
		Collection<Student> students = new ArrayList<Student>();
		students.add(new Student(1,"test","test"));
		
		return students;
	}

	@Override
	public void enrollStudentIntoClass(Student student, SchoolClass schoolClass)
			throws DAOException {
		
		//NOTHING

	}

}
