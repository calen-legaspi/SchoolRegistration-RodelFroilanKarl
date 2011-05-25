package services;

import java.math.BigDecimal;

import database.StudentDAO;
import database.StudentDaoJDBC;

public class StudentService {
	
	public static final BigDecimal miscFees = new BigDecimal("2000.00");
	public static final BigDecimal undergraduateFees = new BigDecimal("2000.00");
	public static final BigDecimal graduateFees = new BigDecimal("4000.00");
	
	private StudentDAO studentDao;
	
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	
	public StudentService(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	
}
