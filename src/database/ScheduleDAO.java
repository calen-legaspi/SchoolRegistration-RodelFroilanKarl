package database;

import java.util.Collection;

import domain.Schedule;

public interface ScheduleDAO {

	public Schedule getScheduleById(int scheduleNo) throws DAOException;

	public Collection<Schedule> getAllSchedules() throws DAOException;

	public Long getPrimaryKey(Schedule schedule) throws DAOException;

}