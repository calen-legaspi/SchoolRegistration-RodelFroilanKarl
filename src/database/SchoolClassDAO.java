package database;

import java.util.Collection;

import domain.Schedule;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Student;
import domain.Subject;
import domain.Teacher;

public interface SchoolClassDAO {

	public SchoolClass getClassById(int classId) throws DAOException,
			ScheduleConflictException;

	public Collection<SchoolClass> getAllClasses() throws DAOException,
			ScheduleConflictException;

	public Collection<SchoolClass> getClassesOfStudent(Student newStudent) throws DAOException;

	public Long getPrimaryKey(Subject subjectById, Schedule scheduleById,
			Teacher teacherById) throws DAOException;
	
	public void addNewClass(String subjectCode, String scheduleId, String teacherId) throws DAOException;

}