package database;

import java.util.Collection;

import domain.ScheduleConflictException;
import domain.SchoolClass;

public interface SchoolClassDAO {

	public SchoolClass getClassById(int classId) throws DAOException,
			ScheduleConflictException;

	public Collection<SchoolClass> getAllClasses() throws DAOException,
			ScheduleConflictException;

}