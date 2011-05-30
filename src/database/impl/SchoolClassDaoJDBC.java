package database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import database.DAOException;
import database.DBConnectionFactoryImpl;
import database.ScheduleDAO;
import database.SchoolClassDAO;
import database.SubjectDAO;
import database.TeacherDAO;
import domain.Schedule;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Student;
import domain.Subject;
import domain.Teacher;

public class SchoolClassDaoJDBC implements SchoolClassDAO {

	private final static String SELECT_BY_ID = "SELECT subjectCode, scheduleNo, teacherNo FROM SchoolClass WHERE classNo = ?";
	private final static String SELECT_ALL = "SELECT subjectCode, scheduleNo, teacherNo FROM SchoolClass S ORDER BY classNo ";
	private final static String SELECT_ALL_CLASSES_OF_STUDENT = "SELECT S.subjectCode, S.scheduleNo, S.teacherNo FROM SchoolClass S, Student_SchoolClass SC WHERE S.classNo = SC.classNo AND SC.studentNo = ? ORDER BY S.classNo";
	private final static String SELECT_KEY = "SELECT classNo FROM SchoolClass S WHERE subjectCode = ? and scheduleNo = ? and teacherNo = ?";
	private final static String INSERT_NEW = "Insert into SchoolClass (subjectCode,scheduleNo,teacherNo) values (?,?,?)";
	
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

				TeacherDAO teacherConnect = new TeacherDaoJDBC();
				ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
				SubjectDAO subjectConnect = new SubjectDaoJDBC();

				while (rs.next()) {
					thisClass = new SchoolClass(
							subjectConnect.getSubjectById(rs
									.getString("subjectCode")),
							scheduleConnect.getScheduleById(rs
									.getInt("scheduleNo")),
							teacherConnect.getTeacherById(rs
									.getInt("teacherNo")));
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
	public Collection<SchoolClass> getAllClasses() throws DAOException,
			ScheduleConflictException {

		Collection<SchoolClass> allClasses = new ArrayList<SchoolClass>();

		String sql = SELECT_ALL;

		DBConnectionFactoryImpl myFactory = DBConnectionFactoryImpl
				.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				TeacherDAO teacherConnect = new TeacherDaoJDBC();
				ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
				SubjectDAO subjectConnect = new SubjectDaoJDBC();

				while (rs.next()) {
					allClasses.add(new SchoolClass(subjectConnect
							.getSubjectById(rs.getString("subjectCode")),
							scheduleConnect.getScheduleById(rs
									.getInt("scheduleNo")), teacherConnect
									.getTeacherById(rs.getInt("teacherNo"))));
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return allClasses;
	}

	@Override
	public Collection<SchoolClass> getClassesOfStudent(Student student)
			throws DAOException {

		Collection<SchoolClass> allClasses = new ArrayList<SchoolClass>();

		String sql = SELECT_ALL_CLASSES_OF_STUDENT;

		DBConnectionFactoryImpl myFactory = DBConnectionFactoryImpl
				.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, student.getMyIdNo());
				ResultSet rs = pstmt.executeQuery();

				TeacherDAO teacherConnect = new TeacherDaoJDBC();
				ScheduleDAO scheduleConnect = new ScheduleDaoJDBC();
				SubjectDAO subjectConnect = new SubjectDaoJDBC();

				while (rs.next()) {
					allClasses.add(new SchoolClass(subjectConnect
							.getSubjectById(rs.getString("subjectCode")),
							scheduleConnect.getScheduleById(rs
									.getInt("scheduleNo")), teacherConnect
									.getTeacherById(rs.getInt("teacherNo"))));
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return allClasses;
	}

	@Override
	public Long getPrimaryKey(Subject subjectById, Schedule scheduleById,
			Teacher teacherById) throws DAOException {

		ScheduleDAO scheduleDao = new ScheduleDaoJDBC();

		String sql = SELECT_KEY;

		Long key = 0L;

		DBConnectionFactoryImpl myFactory = DBConnectionFactoryImpl
				.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, subjectById.getCourseCode());
				pstmt.setLong(2, scheduleDao.getPrimaryKey(scheduleById));
				pstmt.setLong(3, teacherById.getTeacherIdNo());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					key = rs.getLong("classNo");
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}

		return key;
	}

	@Override
	public void addNewClass(String subjectCode, String scheduleId,
			String teacherId) throws DAOException {
		String sql = INSERT_NEW;
		DBConnectionFactoryImpl myFactory = DBConnectionFactoryImpl
				.getInstance();
		try {
			Connection conn = myFactory.getConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, subjectCode);
				pstmt.setInt(2, Integer.valueOf(scheduleId));
				pstmt.setInt(3, Integer.valueOf(teacherId));
				pstmt.execute();

			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

}
