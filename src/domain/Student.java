package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import services.StudentService;

public class Student extends SystemUser {

	private long myIdNo;
	private String myFirstName;
	private String myLastName;
	private Collection<SchoolClass> studentClassList;


	public Student(long idNo, String firstName, String lastName) {
		myIdNo = idNo;
		myFirstName = firstName;
		myLastName = lastName;
		studentClassList = new ArrayList<SchoolClass>();

	}
	
	@Override
	public String toString() {
		return myIdNo + " " + myFirstName + " " + myLastName + "\n";
	}

	public void enrollIntoSchoolClass(SchoolClass schoolClass) throws ScheduleConflictException {
		
		if(conflictsWithSchedule(schoolClass)){
			throw new ScheduleConflictException("This class conflicts with student's enrolled classes."); 
		} else {		
			studentClassList.add(schoolClass);
		}
	}

	public boolean isTakingThisClass(SchoolClass schoolClass) {

		return studentClassList.contains(schoolClass);
	}

	public Collection<SchoolClass> getEnrolledClasses() {
		return studentClassList;
	}

	public boolean conflictsWithSchedule(SchoolClass schoolClass) {

		for (SchoolClass enrolled : studentClassList) {

			if (enrolled.getClassSchedule().conflictsWith(
					schoolClass.getClassSchedule())) {
				return true;
			}

		}

		return false;
	}
	
    public BigDecimal calculateTuition(Student testStudent) {
		
		BigDecimal total = StudentService.miscFees;
		
		for(SchoolClass course : testStudent.getEnrolledClasses()){
			switch(course.getClassSubject().getCourseLevel()){
			  case UNDERGRADUATE: total = total.add(StudentService.undergraduateFees); break;
			  case GRADUATE: total = total.add(StudentService.graduateFees); break;
			}
		}
		
		return total;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (myIdNo ^ (myIdNo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (myIdNo != other.myIdNo)
			return false;
		return true;
	}

	public long getMyIdNo() {
		return myIdNo;
	}

	public String getMyFirstName() {
		return myFirstName;
	}

	public String getMyLastName() {
		return myLastName;
	}

	public Collection<SchoolClass> getStudentClassList() {
		return studentClassList;
	}
	
	


}
