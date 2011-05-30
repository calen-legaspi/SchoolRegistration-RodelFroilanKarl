package database;

import java.util.Collection;

import domain.Teacher;

public interface TeacherDAO {

	public Teacher getTeacherById(int teacherNo) throws DAOException;

	public Collection<Teacher> getListOfAllTeachers() throws DAOException;

}