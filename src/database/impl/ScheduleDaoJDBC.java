package database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import database.DAOException;
import database.DBConnectionFactory;
import database.ScheduleDAO;
import domain.Schedule;

public class ScheduleDaoJDBC implements ScheduleDAO {

	private static String SELECT_BY_ID = "SELECT dayOfTheWeek, startTime, endTime FROM Schedule WHERE scheduleNo = ?";
	private static String SELECT_ALL = "SELECT dayOfTheWeek, startTime, endTime FROM Schedule ORDER BY scheduleNo";
	private static String SELECT_KEY = "SELECT scheduleNo FROM Schedule WHERE dayOfTheWeek = ? and startTime = ? and endTime = ?";

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

	@Override
	public Long getPrimaryKey(Schedule schedule) throws DAOException {
		
		
		Long result = 0L;
		DBConnectionFactory myFactory = DBConnectionFactory.getInstance();

		try {
			Connection conn = myFactory.getConnection();
			try {
				String sql = SELECT_KEY;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, schedule.getClassDay().toString());
				pstmt.setString(2, schedule.getClassStartTime().toString());
				pstmt.setString(3, schedule.getClassEndTime().toString());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					result = rs.getLong("scheduleNo");
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		
		return result;
		
	}
}
