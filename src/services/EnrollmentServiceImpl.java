package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import database.DAOException;
import database.SchoolClassDAO;
import database.StudentDAO;
import domain.Schedule;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Student;
import domain.Subject;
import domain.Teacher;

public class EnrollmentServiceImpl implements EnrollmentService {

	private StudentDAO studentDao = null;
	private SchoolClassDAO schoolClassDao = null;

	// public void setStudentDao(StudentDAO studentDao) {
	// this.studentDao = studentDao;
	// }

	public EnrollmentServiceImpl(StudentDAO studentDao, SchoolClassDAO schoolClassDao) {
		this.studentDao = studentDao;
		this.schoolClassDao = schoolClassDao;
	}

	@Override
	public Collection<Student> returnAllStudents() throws DAOException {
		return studentDao.getAllStudents();
	}

	@Override
	public boolean checkStudentListIsEmpty() throws DAOException {
		return studentDao.getAllStudents().isEmpty();
	}

	@Override
	public Student getStudentById(Integer studentNo) throws DAOException {
		return studentDao.getStudentById(studentNo);
	}

	@Override
	public Student getStudentEnrolledClassesById(Student myStudent)
			throws DAOException, ScheduleConflictException {

		Student newStudent = myStudent;
		Collection<SchoolClass> allEnrolledClasses = schoolClassDao
				.getClassesOfStudent(myStudent);
		if (!allEnrolledClasses.isEmpty()) {
			for (SchoolClass schoolClass : allEnrolledClasses) {
				
				newStudent.enrollIntoSchoolClass(schoolClass);
				
			}
		}
		
		return newStudent;
	}

	@Override
	public Collection<SchoolClass> returnListOfAvailableClasses(Student student) throws DAOException, ScheduleConflictException {
		
		Collection<SchoolClass> availableClasses = new ArrayList<SchoolClass>();

		for(SchoolClass classOnRecord : schoolClassDao.getAllClasses()){
			if(!student.getEnrolledClasses().contains(classOnRecord)){
				availableClasses.add(classOnRecord);
			}
		}
		
		return availableClasses;
	}

	@Override
	public void enrollStudentToClass(Student student, SchoolClass schoolClass) throws DAOException {
		
		studentDao.enrollStudentIntoClass(student, schoolClass);
		
	}
	
	@Override
	public Long getSchoolClassNo(Subject subject, Schedule schedule, Teacher teacher) throws DAOException{
		
		return schoolClassDao.getPrimaryKey(subject, schedule, teacher);
		
	}
	
	@Override
	public SchoolClass getSchoolClassById(Integer id) throws DAOException, ScheduleConflictException{
		
		return schoolClassDao.getClassById(id);
	}

	

}
