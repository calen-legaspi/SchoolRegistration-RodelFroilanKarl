package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import domain.Schedule;

public class ScheduleDaoJDBC implements ScheduleDAO {

	private static String SELECT_BY_ID = "SELECT dayOfTheWeek, startTime, endTime FROM Schedule WHERE scheduleNo = ?";
	private static String SELECT_ALL = "SELECT dayOfTheWeek, startTime, endTime FROM Schedule ORDER BY scheduleNo";

	@Override
	public Schedule getScheduleById(int scheduleNo) throws DAOException {

		Schedule result = null;
		DBConnectionFactory myFactory = DBConnectionFactory.getInstance();

		try {
			Connection conn = myFactory.getConnection();
			try {
				String sql = SELECT_BY_ID;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, scheduleNo);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					result = new Schedule(
							
							Enum.valueOf(Schedule.Day.class,
							rs.getString("dayOfTheWeek")),
							Time.valueOf(rs.getString("startTime")), Time
									.valueOf(rs.getString("endTime")));

				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return result;
	}

	@Override
	public Collection<Schedule> getAllSchedules() throws DAOException {

		Collection<Schedule> schedules = new ArrayList<Schedule>();
		DBConnectionFactory myFactory = DBConnectionFactory.getInstance();

		try {
			Connection conn = myFactory.getConnection();
			try {
				String sql = SELECT_ALL;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					schedules.add(new Schedule(
							
							Enum.valueOf(Schedule.Day.class,
									rs.getString("dayOfTheWeek")),
							Time.valueOf(rs.getString("startTime")), 
							Time.valueOf(rs.getString("endTime"))));
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return schedules;
	}
}
