package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import domain.Subject;

public class SubjectDaoJDBC implements SubjectDAO {

	@Override
	public Subject getSubjectById(String subjectCode) throws DAOException {

		Subject result = null;

		DBConnectionFactory myFactory = DBConnectionFactory.getInstance();

		try {
			Connection conn = myFactory.getConnection();

			try {

				String sql = "SELECT * FROM Subject where subjectCode = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, subjectCode);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					result = Subject.makeNewSubject(
							rs.getString("subjectCode"),
							Subject.returnEnumValue(rs.getInt("subjectLevel")),
							"subjectName");
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
	public Collection<Subject> getAllSubjects() throws DAOException {

		Collection<Subject> allSubjects = new ArrayList<Subject>();

		DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {

				String sql = "SELECT * FROM `Subject`";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					allSubjects.add(Subject.makeNewSubject(
							rs.getString("subjectCode"),
							Subject.returnEnumValue(rs.getInt("subjectLevel")),
							"subjectName"));
				}

			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return allSubjects;
	}

}
