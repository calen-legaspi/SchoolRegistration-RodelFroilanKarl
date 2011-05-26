package services;

import java.math.BigDecimal;
import java.util.Collection;

import database.DAOException;
import database.StudentDAO;
import domain.Student;

public class StudentService {
	
	public static final BigDecimal miscFees = new BigDecimal("2000.00");
	public static final BigDecimal undergraduateFees = new BigDecimal("2000.00");
	public static final BigDecimal graduateFees = new BigDecimal("4000.00");
	
	private StudentDAO studentDao = null;
	
	//public void setStudentDao(StudentDAO studentDao) {
	//	this.studentDao = studentDao;
	//}
	
	public StudentService(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	
	public Collection<Student> returnAllStudents() throws DAOException{
		return studentDao.getAllStudents();
	}
	
	public boolean checkStudentListIsEmpty() throws DAOException{
		return studentDao.getAllStudents().isEmpty();
	}
	
	
}
