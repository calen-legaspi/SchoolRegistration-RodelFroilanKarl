package services;

import java.sql.SQLException;
import java.util.Collection;

import database.DAOException;
import domain.Schedule;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Student;
import domain.Subject;
import domain.Teacher;

public interface EnrollmentService {

	public Collection<Student> returnAllStudents() throws DAOException;

	public boolean checkStudentListIsEmpty() throws DAOException;

	public Student getStudentById(Integer studentNo) throws DAOException;

	public Student getStudentEnrolledClassesById(Student myStudent)
			throws DAOException, ScheduleConflictException;

	public Collection<SchoolClass> returnListOfAvailableClasses(Student student) throws DAOException, ScheduleConflictException;

	public void enrollStudentToClass(Student studentById, SchoolClass classById) throws DAOException;

	public Long getSchoolClassNo(Subject subject, Schedule schedule, Teacher teacher) throws DAOException;

	public SchoolClass getSchoolClassById(Integer id) throws DAOException, ScheduleConflictException;

}