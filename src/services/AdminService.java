package services;

import java.util.Collection;

import database.DAOException;
import domain.Schedule;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Subject;
import domain.Teacher;

public interface AdminService {

	public Collection<Schedule> getAllSchedules() throws DAOException;

	public Collection<Teacher> getAllTeachers() throws DAOException;

	public Collection<Subject> getAllSubjects() throws DAOException;

	public Collection<SchoolClass> getAllSchoolClass() throws DAOException, ScheduleConflictException;
	
	public void addSchoolClass(String subjectCode, Long long1,
			String teacherId) throws DAOException;

}