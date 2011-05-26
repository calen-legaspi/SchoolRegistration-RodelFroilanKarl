package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import domain.Teacher;

public class TeacherDAO {

	private final static String SELECT_BY_ID = "Select * from Teacher where teacherNo = ?";
	private final static String SELECT_ALL = "Select * from Teacher order by teacherNo";

	public Teacher getTeacherById(int teacherNo) throws DAOException {

		Teacher result = null;

		DBConnectionFactoryImpl myFactory = DBConnectionFactoryImpl
				.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				String sql = SELECT_BY_ID;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, teacherNo);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					result = new Teacher(rs.getInt("teacherNo"),
									rs.getString("firstName"),
									rs.getString("lastName"));
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return result;

	}

	public Collection<Teacher> getListOfAllTeachers() throws DAOException {

		Collection<Teacher> result = new ArrayList<Teacher>();

		DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				String sql = SELECT_ALL;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					result.add(new Teacher(rs.getInt("teacherNo"),
							rs.getString("firstName"), rs.getString("lastName")));
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
