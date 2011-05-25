package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import domain.Student;

public class StudentDaoJDBC implements StudentDAO {

	private static String SELECT_BY_ID = "SELECT * FROM Student WHERE idNo = ?";
	private static String SELECT_ALL = "SELECT * FROM Student ORDER BY idNo";


	@Override
	public Student getStudentById(int studentNo) throws DAOException {

		Student result = null;

		DBConnectionFactoryImpl myFactory = DBConnectionFactoryImpl
				.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				String sql = SELECT_BY_ID;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, studentNo);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					result = new Student(rs.getInt("idNo"),
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


	@Override
	public Collection<Student> getAllStudents() throws DAOException {

		Collection<Student> allStudents = new ArrayList<Student>();

		DBConnectionFactoryImpl myFactory = DBConnectionFactoryImpl
				.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				String sql = SELECT_ALL;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					allStudents
							.add(new Student(rs.getInt("idNo"),
									rs.getString("firstName"),
									rs.getString("lastName")));
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}

		return allStudents;
	}
}
