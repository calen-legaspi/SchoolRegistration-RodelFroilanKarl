package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import domain.ScheduleConflictException;
import domain.SchoolClass;

public class SchoolClassDaoJDBC implements SchoolClassDAO {

	private final static String SELECT_BY_ID = "SELECT subjectCode, scheduleNo, teacherNo FROM SchoolClass WHERE classNo = ?";
	private final static String SELECT_ALL = "SELECT subjectCode, scheduleNo, teacherNo FROM SchoolClass S ORDER BY classNo ";

	@Override
	public SchoolClass getClassById(int classId) throws DAOException,
			ScheduleConflictException {

		SchoolClass thisClass = null;

		String sql = SELECT_BY_ID;

		DBConnectionFactoryImpl myFactory = DBConnectionFactoryImpl
				.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, classId);
				ResultSet rs = pstmt.executeQuery();

				TeacherDAO teacherConnect = new TeacherDAO();
				ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
				SubjectDAO subjectConnect = new SubjectDaoJDBC();

				while (rs.next()) {
					thisClass = new SchoolClass(subjectConnect
							.getSubjectById(rs.getString("subjectCode")),
							scheduleConnect.getScheduleById(rs
									.getInt("scheduleNo")), teacherConnect
									.getTeacherById(rs.getInt("teacherNo")));
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return thisClass;
	}

	@Override
	public Collection<SchoolClass> getAllClasses() throws DAOException, ScheduleConflictException {

		Collection<SchoolClass> allClasses = new ArrayList<SchoolClass>();

		String sql = SELECT_ALL;

		DBConnectionFactoryImpl myFactory = DBConnectionFactoryImpl
				.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				TeacherDAO teacherConnect = new TeacherDAO();
				ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
				SubjectDAO subjectConnect = new SubjectDaoJDBC();

				while (rs.next()) {
					allClasses.add(new SchoolClass(
							subjectConnect.getSubjectById(rs
									.getString("subjectCode")), scheduleConnect
									.getScheduleById(rs.getInt("scheduleNo")),
							teacherConnect.getTeacherById(rs
									.getInt("teacherNo"))));
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return allClasses;
	}

}
