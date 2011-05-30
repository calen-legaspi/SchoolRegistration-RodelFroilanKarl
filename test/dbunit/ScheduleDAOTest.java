package dbunit;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.junit.Test;

import database.DAOException;
import database.ScheduleDAO;
import database.impl.ScheduleDaoJDBC;
import domain.Schedule;

public class ScheduleDAOTest {

	private final String SCHEDULE_TABLE = "Schedule";
	
	protected IDatabaseConnection getConnection() throws SQLException, ClassNotFoundException, DatabaseUnitException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost/schoolregistration?user=root&password=");
		return new DatabaseConnection(jdbcConnection);
	}
	
	@Test
	public void testGetScheduleById() throws DAOException, SQLException, ClassNotFoundException, DatabaseUnitException {
	
		int scheduleNo=1;
		
		ScheduleDAO schedule = new ScheduleDaoJDBC();
		Schedule newSchedule = schedule.getScheduleById(scheduleNo);

		QueryDataSet expectedDataSet = new QueryDataSet(getConnection());
		expectedDataSet.addTable("Schedule","Select * from Schedule where scheduleNo="+scheduleNo);
		
		Schedule expectedSchedule = new Schedule(
				Enum.valueOf(Schedule.Day.class, expectedDataSet.getTable(SCHEDULE_TABLE).getValue(0, "dayOfTheWeek").toString()), 
				Time.valueOf(expectedDataSet.getTable(SCHEDULE_TABLE).getValue(0, "startTime").toString()), 
				Time.valueOf(expectedDataSet.getTable(SCHEDULE_TABLE).getValue(0, "endTime").toString())
				);

		assertEquals(expectedSchedule,newSchedule);
	}

	@Test
	public void testGetAllSchedules() throws SQLException, DAOException, ClassNotFoundException, DatabaseUnitException {
		
		ScheduleDAO schedule = new ScheduleDaoJDBC();

		Collection<Schedule> expectedListOfSchedule = new ArrayList<Schedule>();
		
		Collection<Schedule> actualSchedules = schedule.getAllSchedules();
		
		QueryDataSet scheduleTable = new QueryDataSet(getConnection());
		scheduleTable.addTable("Schedule","Select * from Schedule order by scheduleNo");
		
        for(int i = 0; i < scheduleTable.getTable(SCHEDULE_TABLE).getRowCount(); i++)
        {
        	expectedListOfSchedule.add(new Schedule(
        				Enum.valueOf(Schedule.Day.class, scheduleTable.getTable(SCHEDULE_TABLE).getValue(i, "dayOfTheWeek").toString()), 
        				Time.valueOf(scheduleTable.getTable(SCHEDULE_TABLE).getValue(i, "startTime").toString()), 
        				Time.valueOf(scheduleTable.getTable(SCHEDULE_TABLE).getValue(i, "endTime").toString()))
        				);
        }

        assertEquals(expectedListOfSchedule, actualSchedules);
        
	}

	@Test
	public void getKeyOfSchedule() throws DAOException, SQLException, ClassNotFoundException, DatabaseUnitException {

		ScheduleDAO schedule = new ScheduleDaoJDBC();	
		
		String dayOfTheWeek = "MONDAY";
		String startTime = "9:00:00";
		String endTime = "10:00:00";
		
		QueryDataSet scheduleTable = new QueryDataSet(getConnection());
		scheduleTable.addTable("Schedule","Select * from Schedule where dayOfTheWeek='"+dayOfTheWeek+"' and " +
				"startTime='"+startTime+"' " +
				"and endTime='"+endTime+"'");
		
		Long expectedKey = Long.valueOf(""+scheduleTable.getTable(SCHEDULE_TABLE).getValue(0,"scheduleNo"));
		
		assertEquals("Primary keys taken not equal to expected ",expectedKey,schedule.getPrimaryKey(new Schedule(Schedule.Day.MONDAY, Time
				.valueOf(startTime), Time.valueOf(endTime))));

	}

}
