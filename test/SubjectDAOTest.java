

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import database.DAOException;
import database.SubjectDAO;
import database.SubjectDaoJDBC;
import domain.EducationLevel;
import domain.Subject;


public class SubjectDAOTest {
	
	SubjectDAO subjectDao;
	
	@Before
	public void setUp(){
		subjectDao = new SubjectDaoJDBC();
	}
	
	@Test
	public void getSubjectByIdTest() throws DAOException {
		
		Subject subject = new Subject("STR080",EducationLevel.GRADUATE,"STAR 101");
		
		Subject subject2 = subjectDao.getSubjectById("STR080");
		
		assertTrue(subject.equals(subject2));
	}

	@Test
	public void getListOfAllSubjects() throws DAOException {
		
		Collection<Subject> allSubjects = new ArrayList<Subject>();
		
		allSubjects.add(new Subject("STR080", Subject.returnEnumValue(1), "STAR 101"));
		allSubjects.add(new Subject("FIL889", Subject.returnEnumValue(1), "ADVANCE FILIPINO I"));
		allSubjects.add(new Subject("NUR661", Subject.returnEnumValue(1), "ADVANCE NURSING III"));
		allSubjects.add(new Subject("LAW101", Subject.returnEnumValue(1), "BASIC LAW I"));
		allSubjects.add(new Subject("ROY105", Subject.returnEnumValue(1), "ROYAL FAMILY III"));
		allSubjects.add(new Subject("MATH657", Subject.returnEnumValue(0), "ALGEBRA II"));
		allSubjects.add(new Subject("MATH311", Subject.returnEnumValue(0), "BASIC TRIGONOMETRY"));
		allSubjects.add(new Subject("MOR888", Subject.returnEnumValue(0), "MORALITY I"));
		allSubjects.add(new Subject("MOV002", Subject.returnEnumValue(0), "MOVIE BASICS II"));
		allSubjects.add(new Subject("MOV001", Subject.returnEnumValue(0), "MOVIE BASICS I"));
		allSubjects.add(new Subject("CHIN656", Subject.returnEnumValue(1), "CHINA CULTURE I"));
		allSubjects.add(new Subject("PHIST003", Subject.returnEnumValue(1), "PHILIPPINE HISTORY I"));
		allSubjects.add(new Subject("MATH999", Subject.returnEnumValue(1), "QUANTUM PHYSICS"));
		allSubjects.add(new Subject("LIFE333", Subject.returnEnumValue(1), "LIFE LESSONS 2"));
		allSubjects.add(new Subject("PE101", Subject.returnEnumValue(1), "PHYSICAL EDUCATION I"));
		allSubjects.add(new Subject("MATH667", Subject.returnEnumValue(0), "CALCULUS II"));
		allSubjects.add(new Subject("MATH666", Subject.returnEnumValue(0), "CALCULUS I"));
		allSubjects.add(new Subject("MATH122", Subject.returnEnumValue(0), "BASIC ALGEBRA"));
		allSubjects.add(new Subject("LIFE101", Subject.returnEnumValue(0), "LIFE LESSONS"));
		allSubjects.add(new Subject("PHL5", Subject.returnEnumValue(0), "PHILOSOPHY"));
		assertTrue(allSubjects.equals(subjectDao.getAllSubjects()));
		
	}
	
}
