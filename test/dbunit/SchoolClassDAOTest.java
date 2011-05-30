package dbunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.junit.Ignore;
import org.junit.Test;

import database.DAOException;
import database.ScheduleDAO;
import database.SchoolClassDAO;
import database.SubjectDAO;
import database.TeacherDAO;
import database.impl.ScheduleDaoJDBC;
import database.impl.SchoolClassDaoJDBC;
import database.impl.SubjectDaoJDBC;
import database.impl.TeacherDaoJDBC;
import domain.EducationLevel;
import domain.Schedule;
import domain.ScheduleConflictException;
import domain.SchoolClass;
import domain.Student;
import domain.Subject;
import domain.Teacher;

public class SchoolClassDAOTest {
	
	protected IDatabaseConnection getConnection() throws SQLException, ClassNotFoundException, DatabaseUnitException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost/schoolregistration?user=root&password=");
		return new DatabaseConnection(jdbcConnection);
	}

	@Test
	public void testGetASchoolClassById() throws DAOException,
			ScheduleConflictException, SQLException, ClassNotFoundException, DatabaseUnitException {

		int schoolClassNo=1, teacherNo=1, scheduleNo=1;
		String subjectCode = "STR080";
		
		SchoolClassDAO schoolClass = new SchoolClassDaoJDBC();
		SchoolClass actualSchoolClass = schoolClass.getClassById(schoolClassNo);

		QueryDataSet teacherDataSet = new QueryDataSet(getConnection());
		teacherDataSet.addTable("Teacher","Select * from Teacher where teacherNo="+teacherNo);
		
		QueryDataSet scheduleDataSet = new QueryDataSet(getConnection());
		scheduleDataSet.addTable("Schedule","Select * from Schedule where scheduleNo="+scheduleNo);
		
		QueryDataSet subjectDataSet = new QueryDataSet(getConnection());
		subjectDataSet.addTable("Subject","Select * from Subject where subjectCode='"+subjectCode+"'");
		
		Teacher expectedTeacher = new Teacher(Integer.parseInt(teacherDataSet.getTable("Teacher").getValue(0, "teacherNo").toString()),
				teacherDataSet.getTable("Teacher").getValue(0, "firstName").toString(),
				teacherDataSet.getTable("Teacher").getValue(0, "lastName").toString()
				);
		
		Subject expectedSubject = new Subject(subjectDataSet.getTable("Subject").getValue(0, "subjectCode").toString(),
				Enum.valueOf(EducationLevel.class, subjectDataSet.getTable("Subject").getValue(0, "subjectLevel").toString()),
				subjectDataSet.getTable("Subject").getValue(0, "subjectName").toString()
				);
		
		Schedule expectedSchedule = new Schedule(
				Enum.valueOf(Schedule.Day.class, scheduleDataSet.getTable("Schedule").getValue(0, "dayOfTheWeek").toString()), 
				Time.valueOf(scheduleDataSet.getTable("Schedule").getValue(0, "startTime").toString()), 
				Time.valueOf(scheduleDataSet.getTable("Schedule").getValue(0, "endTime").toString())
				);
		
		SchoolClass expectedSchoolClass = new SchoolClass(expectedSubject,expectedSchedule,expectedTeacher);

		assertEquals(expectedSchoolClass, actualSchoolClass);
		
	}

	@Test
	public void getAllSchoolClass() throws ScheduleConflictException,
			SQLException, DAOException, ClassNotFoundException, DatabaseUnitException {

		SchoolClassDAO schoolClass = new SchoolClassDaoJDBC();

		Collection<SchoolClass> actualCollectionOfSchoolClasses = schoolClass.getAllClasses();
		
		Collection<SchoolClass> expectedCollectionOfSchoolClasses = new ArrayList<SchoolClass>();
		Subject expectedSubject = null;
		Teacher expectedTeacher = null;
		Schedule expectedSchedule = null;
		SchoolClass expectedSchoolClass = null;
		
		QueryDataSet teacherDataSet = new QueryDataSet(getConnection());
		teacherDataSet.addTable("Teacher","Select * from Teacher order by teacherNo");
		
		QueryDataSet scheduleDataSet = new QueryDataSet(getConnection());
		scheduleDataSet.addTable("Schedule","Select * from Schedule order by scheduleNo");
		
		QueryDataSet subjectDataSet = new QueryDataSet(getConnection());
		subjectDataSet.addTable("Subject","Select * from Subject");
		
		QueryDataSet schoolClassDataSet = new QueryDataSet(getConnection());
		schoolClassDataSet.addTable("SchoolClass","Select * from SchoolClass order by classNo");

		for(int i=0; i<schoolClassDataSet.getTable("SchoolClass").getRowCount(); i++){
			for(int j=0; j<subjectDataSet.getTable("Subject").getRowCount();j++){
				if( schoolClassDataSet.getTable("SchoolClass").getValue(i, "subjectCode")
						.equals(subjectDataSet.getTable("Subject").getValue(j, "subjectCode") ) ){
					 			expectedSubject = new Subject(subjectDataSet.getTable("Subject").getValue(j, "subjectCode").toString(),
					 						Enum.valueOf(EducationLevel.class, subjectDataSet.getTable("Subject").getValue(j, "subjectLevel").toString()),
					 						subjectDataSet.getTable("Subject").getValue(j, "subjectName").toString()
					 						);
				}
			}	
			for (int k=0; k<teacherDataSet.getTable("Teacher").getRowCount();k++){
				if( schoolClassDataSet.getTable("SchoolClass").getValue(i, "teacherNo")
						.equals(teacherDataSet.getTable("Teacher").getValue(k, "teacherNo") ) ){
							   expectedTeacher = new Teacher(Integer.parseInt(teacherDataSet.getTable("Teacher").getValue(k, "teacherNo").toString()),
									   teacherDataSet.getTable("Teacher").getValue(k, "firstName").toString(),
									   teacherDataSet.getTable("Teacher").getValue(k, "lastName").toString()
							   			);
					}
			}
			for (int l=0; l<scheduleDataSet.getTable("Schedule").getRowCount();l++){
				if( schoolClassDataSet.getTable("SchoolClass").getValue(i, "scheduleNo")
						.equals(scheduleDataSet.getTable("Schedule").getValue(l, "scheduleNo") ) ){
								expectedSchedule = new Schedule(
											Enum.valueOf(Schedule.Day.class, scheduleDataSet.getTable("Schedule").getValue(l, "dayOfTheWeek").toString()), 
											Time.valueOf(scheduleDataSet.getTable("Schedule").getValue(l, "startTime").toString()), 
											Time.valueOf(scheduleDataSet.getTable("Schedule").getValue(l, "endTime").toString())
								);
					}
						
			}
			expectedSchoolClass = new SchoolClass(expectedSubject,expectedSchedule,expectedTeacher);
			expectedCollectionOfSchoolClasses.add(expectedSchoolClass);
			}

		assertEquals(expectedCollectionOfSchoolClasses,actualCollectionOfSchoolClasses);

	}

	@Test
	public void getClassesOfStudent() throws DAOException, SQLException, ClassNotFoundException, DatabaseUnitException {

		Student newStudent = new Student(1, "MINNY", "HILLER");

		SchoolClassDAO schoolClassConnector = new SchoolClassDaoJDBC();
		Collection<SchoolClass> actualClassesOfStudent = schoolClassConnector
				.getClassesOfStudent(newStudent);

		Collection<SchoolClass> expectedClassesOfStudent = new ArrayList<SchoolClass>();
		
		int studentNo = 1;
		Subject expectedSubject = null;
		Teacher expectedTeacher = null;
		Schedule expectedSchedule = null;
		SchoolClass expectedSchoolClass = null;
		
		QueryDataSet teacherDataSet = new QueryDataSet(getConnection());
		teacherDataSet.addTable("Teacher","Select * from Teacher order by teacherNo");
		
		QueryDataSet scheduleDataSet = new QueryDataSet(getConnection());
		scheduleDataSet.addTable("Schedule","Select * from Schedule order by scheduleNo");
		
		QueryDataSet subjectDataSet = new QueryDataSet(getConnection());
		subjectDataSet.addTable("Subject","Select * from Subject");
		
		QueryDataSet schoolClassDataSet = new QueryDataSet(getConnection());
		schoolClassDataSet.addTable("SchoolClass","Select * from SchoolClass order by classNo");
		
		QueryDataSet studentSchoolClassesDataSet = new QueryDataSet(getConnection());
		studentSchoolClassesDataSet.addTable("Student_SchoolClass","Select * from Student_SchoolClass where studentNo="+studentNo);
		
		
		for(int m=0; m<studentSchoolClassesDataSet.getTable("Student_SchoolClass").getRowCount(); m++){
			for(int i=0; i<schoolClassDataSet.getTable("SchoolClass").getRowCount(); i++){
				if(studentSchoolClassesDataSet.getTable("Student_SchoolClass").getValue(m, "classNo")
						.equals(schoolClassDataSet.getTable("SchoolClass").getValue(i, "classNo"))){
					for(int j=0; j<subjectDataSet.getTable("Subject").getRowCount();j++){
						if( schoolClassDataSet.getTable("SchoolClass").getValue(i, "subjectCode")
								.equals(subjectDataSet.getTable("Subject").getValue(j, "subjectCode") ) ){
							 			expectedSubject = new Subject(subjectDataSet.getTable("Subject").getValue(j, "subjectCode").toString(),
							 						Enum.valueOf(EducationLevel.class, subjectDataSet.getTable("Subject").getValue(j, "subjectLevel").toString()),
							 						subjectDataSet.getTable("Subject").getValue(j, "subjectName").toString()
							 						);
						}
					}	
					for (int k=0; k<teacherDataSet.getTable("Teacher").getRowCount();k++){
						if( schoolClassDataSet.getTable("SchoolClass").getValue(i, "teacherNo")
								.equals(teacherDataSet.getTable("Teacher").getValue(k, "teacherNo") ) ){
									   expectedTeacher = new Teacher(Integer.parseInt(teacherDataSet.getTable("Teacher").getValue(k, "teacherNo").toString()),
											   teacherDataSet.getTable("Teacher").getValue(k, "firstName").toString(),
											   teacherDataSet.getTable("Teacher").getValue(k, "lastName").toString()
									   			);
							}
					}
					for (int l=0; l<scheduleDataSet.getTable("Schedule").getRowCount();l++){
						if( schoolClassDataSet.getTable("SchoolClass").getValue(i, "scheduleNo")
								.equals(scheduleDataSet.getTable("Schedule").getValue(l, "scheduleNo") ) ){
										expectedSchedule = new Schedule(
													Enum.valueOf(Schedule.Day.class, scheduleDataSet.getTable("Schedule").getValue(l, "dayOfTheWeek").toString()), 
													Time.valueOf(scheduleDataSet.getTable("Schedule").getValue(l, "startTime").toString()), 
													Time.valueOf(scheduleDataSet.getTable("Schedule").getValue(l, "endTime").toString())
										);
							}
								
					}
					expectedSchoolClass = new SchoolClass(expectedSubject,expectedSchedule,expectedTeacher);
					expectedClassesOfStudent.add(expectedSchoolClass);
				}
			}
		}
		
		assertEquals(expectedClassesOfStudent,actualClassesOfStudent);
	}

	@Test
	public void getKeyOfSchoolClass() throws DAOException, SQLException, ClassNotFoundException, DatabaseUnitException
	{
		int scheduleNo=1, teacherNo=1, expectedClassNo=0;
		String subjectCode = "STR080";
		
		TeacherDAO teacherDao = new TeacherDaoJDBC();
		ScheduleDAO scheduleDao = new ScheduleDaoJDBC();
		SubjectDAO subjectDao = new SubjectDaoJDBC();

		SchoolClassDAO schoolClassDao = new SchoolClassDaoJDBC();
		Long actualKeyOfSchoolClass = Long.valueOf(schoolClassDao.getPrimaryKey(
				subjectDao.getSubjectById(subjectCode),
				scheduleDao.getScheduleById(scheduleNo),
				teacherDao.getTeacherById(teacherNo)));

		QueryDataSet schoolClassDataSet = new QueryDataSet(getConnection());
		schoolClassDataSet.addTable("SchoolClass","Select * from SchoolClass order by classNo");
		
		Long expectedKeyOfSchoolClass = Long.valueOf(schoolClassDataSet.getTable("SchoolClass").getValue(expectedClassNo, "classNo").toString());
		
		assertEquals(expectedKeyOfSchoolClass, actualKeyOfSchoolClass);
	}

}
