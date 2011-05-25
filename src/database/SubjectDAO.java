package database;

import java.util.Collection;

import domain.Subject;

public interface SubjectDAO {

	public Subject getSubjectById(String subjectCode) throws DAOException;

	public Collection<Subject> getAllSubjects() throws DAOException;

}