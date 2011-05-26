

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
		
		Subject subject = new Subject("STR080",EducationLevel.GRADUATE,"STARS 101");
		
		Subject subject2 = subjectDao.getSubjectById("STR080");
		
		assertTrue(subject.equals(subject2));
	}

	@Test
	public void getListOfAllSubjects() throws DAOException {
		
		Collection<Subject> allSubjects = new ArrayList<Subject>();
		
		allSubjects.add(new Subject("STR080", EducationLevel.GRADUATE, "STARS 101"));
		allSubjects.add(new Subject("FIL889", EducationLevel.GRADUATE, "ADVANCE FILIPINO I"));
		allSubjects.add(new Subject("NUR661", EducationLevel.GRADUATE, "ADVANCE NURSING III"));
		allSubjects.add(new Subject("LAW101", EducationLevel.GRADUATE, "BASIC LAW I"));
		allSubjects.add(new Subject("ROY105", EducationLevel.GRADUATE, "ROYAL FAMILY III"));
		allSubjects.add(new Subject("MATH657", EducationLevel.UNDERGRADUATE, "ALGEBRA II"));
		allSubjects.add(new Subject("MATH311", EducationLevel.UNDERGRADUATE, "BASIC TRIGONOMETRY"));
		allSubjects.add(new Subject("MOR888", EducationLevel.UNDERGRADUATE, "MORALITY I"));
		allSubjects.add(new Subject("MOV002", EducationLevel.UNDERGRADUATE, "MOVIE BASICS II"));
		allSubjects.add(new Subject("MOV001", EducationLevel.UNDERGRADUATE, "MOVIE BASICS I"));
		allSubjects.add(new Subject("CHIN656", EducationLevel.GRADUATE, "CHINA CULTURE I"));
		allSubjects.add(new Subject("PHIST003", EducationLevel.GRADUATE, "PHILIPPINE HISTORY I"));
		allSubjects.add(new Subject("MATH999", EducationLevel.GRADUATE, "QUANTUM PHYSICS"));
		allSubjects.add(new Subject("LIFE333", EducationLevel.GRADUATE, "LIFE LESSONS 2"));
		allSubjects.add(new Subject("PE101", EducationLevel.GRADUATE, "PHYSICAL EDUCATION I"));
		allSubjects.add(new Subject("MATH667", EducationLevel.UNDERGRADUATE, "CALCULUS II"));
		allSubjects.add(new Subject("MATH666", EducationLevel.UNDERGRADUATE, "CALCULUS I"));
		allSubjects.add(new Subject("MATH122", EducationLevel.UNDERGRADUATE, "BASIC ALGEBRA"));
		allSubjects.add(new Subject("LIFE101", EducationLevel.UNDERGRADUATE, "LIFE LESSONS"));
		allSubjects.add(new Subject("PHL5", EducationLevel.UNDERGRADUATE, "PHILOSOPHY"));
		assertTrue(allSubjects.equals(subjectDao.getAllSubjects()));
		
	}
	
}
