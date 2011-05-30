package database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import database.DAOException;
import database.DBConnectionFactoryImpl;
import database.SchoolClassDAO;
import database.StudentDAO;
import domain.SchoolClass;
import domain.Student;

public class StudentDaoJDBC implements StudentDAO {

	private static String SELECT_BY_ID = "SELECT * FROM Student WHERE idNo = ?";
	private static String SELECT_ALL = "SELECT * FROM Student ORDER BY idNo";
	private static String ASSOCIATE_STUDENT_TO_CLASS = "INSERT INTO Student_SchoolClass (studentNo,classNo) VALUES (?,?)";

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
							rs.getString("firstName"), rs.getString("lastName"));
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
					allStudents.add(new Student(rs.getInt("idNo"), rs
							.getString("firstName"), rs.getString("lastName")));
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}

		return allStudents;
	}

	public void enrollStudentIntoClass(Student student, SchoolClass schoolClass)
			throws DAOException {

		SchoolClassDAO schoolClassDao = new SchoolClassDaoJDBC();
		
		DBConnectionFactoryImpl myFactory = DBConnectionFactoryImpl
				.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				String sql = ASSOCIATE_STUDENT_TO_CLASS;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, student.getMyIdNo());
				pstmt.setLong(
						2,
						schoolClassDao.getPrimaryKey(
								schoolClass.getClassSubject(),
								schoolClass.getClassSchedule(),
								schoolClass.getClassTeacher()));
				pstmt.execute();

			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}

	}
}
