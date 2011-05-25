package database;

import java.util.Collection;

import domain.Student;

public class MockStudentDAO implements StudentDAO {

	@Override
	public Student getStudentById(int studentNo) {
		return new Student(1, "test", "test");
	}

	@Override
	public Collection<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

}
