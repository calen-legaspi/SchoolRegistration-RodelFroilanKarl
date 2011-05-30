package services;

import java.util.Collection;

import database.DAOException;
import database.ScheduleDAO;
import database.SchoolClassDAO;
import database.SubjectDAO;
import database.TeacherDAO;
import domain.Schedule;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Subject;
import domain.Teacher;

public class AdminServiceImpl implements AdminService {

	ScheduleDAO scheduleDao;
	TeacherDAO teacherDao;
	SubjectDAO subjectDao;
	SchoolClassDAO schoolClassDao;
	
	public AdminServiceImpl(ScheduleDAO scheduleDao, TeacherDAO teacherDao, SubjectDAO subjectDao, SchoolClassDAO schoolClassDao){
		this.scheduleDao = scheduleDao;
		this.teacherDao = teacherDao;
		this.subjectDao = subjectDao;
		this.schoolClassDao = schoolClassDao;
	}
	
	@Override
	public Collection<Schedule> getAllSchedules() throws DAOException {
		return scheduleDao.getAllSchedules();		
	}

	@Override
	public Collection<Teacher> getAllTeachers() throws DAOException {
		return teacherDao.getListOfAllTeachers();
	}

	@Override
	public Collection<Subject> getAllSubjects() throws DAOException {
		return subjectDao.getAllSubjects();
	}

	@Override
	public Collection<SchoolClass> getAllSchoolClass() throws DAOException,
			ScheduleConflictException {
		return schoolClassDao.getAllClasses();
	}

	@Override
	public void addSchoolClass(String subjectCode, Long scheduleId, String teacherId)
			throws DAOException {
		schoolClassDao.addNewClass(subjectCode, scheduleId.toString(), teacherId);
		
	}

}
