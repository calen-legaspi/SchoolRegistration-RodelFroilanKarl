import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import database.DAOException;
import database.ScheduleDAO;
import database.ScheduleDaoJDBC;
import domain.Schedule;

public class ScheduleDAOTest {

	@Test 
	public void testGetScheduleById() throws SQLException, DAOException{
		
		ScheduleDAO schedule = new ScheduleDaoJDBC();
		Schedule newSchedule = schedule.getScheduleById(1);
		
		Schedule thisSchedule = new Schedule(Schedule.Day.MONDAY, Time.valueOf("7:00:00"), Time.valueOf("8:00:00"));
		
		assertTrue(newSchedule.equals(thisSchedule));
		
	}
	
	@Test 
	public void testGetAllSchedules() throws SQLException, DAOException{
		
		ScheduleDAO schedule = new ScheduleDaoJDBC();
		
		Collection<Schedule> allSchedules = schedule.getAllSchedules();
		
		Collection<Schedule> collectionOfAllSchedules = new ArrayList<Schedule>();
		collectionOfAllSchedules.add(new Schedule(Schedule.Day.MONDAY, Time.valueOf("7:00:00"), Time.valueOf("8:00:00")));
		collectionOfAllSchedules.add(new Schedule(Schedule.Day.MONDAY, Time.valueOf("8:00:00"), Time.valueOf("9:00:00")));
		collectionOfAllSchedules.add(new Schedule(Schedule.Day.MONDAY, Time.valueOf("9:00:00"), Time.valueOf("10:00:00")));
		collectionOfAllSchedules.add(new Schedule(Schedule.Day.MONDAY, Time.valueOf("10:00:00"), Time.valueOf("11:00:00")));
		collectionOfAllSchedules.add(new Schedule(Schedule.Day.MONDAY, Time.valueOf("11:00:00"), Time.valueOf("12:00:00")));
		collectionOfAllSchedules.add(new Schedule(Schedule.Day.MONDAY, Time.valueOf("12:00:00"), Time.valueOf("13:00:00")));
		collectionOfAllSchedules.add(new Schedule(Schedule.Day.MONDAY, Time.valueOf("13:00:00"), Time.valueOf("14:00:00")));
		collectionOfAllSchedules.add(new Schedule(Schedule.Day.MONDAY, Time.valueOf("14:00:00"), Time.valueOf("15:00:00")));
		collectionOfAllSchedules.add(new Schedule(Schedule.Day.MONDAY, Time.valueOf("15:00:00"), Time.valueOf("16:00:00")));
		collectionOfAllSchedules.add(new Schedule(Schedule.Day.MONDAY, Time.valueOf("16:00:00"), Time.valueOf("17:00:00")));
		
		assertTrue(allSchedules.equals(collectionOfAllSchedules));
		
	}
	
	
	
	
}
