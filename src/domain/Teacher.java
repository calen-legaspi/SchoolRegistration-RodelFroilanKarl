package domain;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends SystemUser {
	
	private long teacherIdNo;
	private String teacherFirstName;
	private String teacherLastName;
	private List<SchoolClass> teacherClassList;
	
	
	public Teacher(long idNo, String firstName, String lastName){
		teacherIdNo = idNo;
		teacherFirstName = firstName;
		teacherLastName = lastName;
		teacherClassList = new ArrayList<SchoolClass>();
	}
	
	public void assignSchoolClass(SchoolClass schoolClass) throws ScheduleConflictException {
		
		if(conflictsWithClass(schoolClass)){
			throw new ScheduleConflictException("This class conflicts with teacher's assigned classes."); 
		} else {
			teacherClassList.add(schoolClass);		
		}
	}
	
	@Override
	public String toString(){
		return teacherIdNo+" "+teacherFirstName+" "+teacherLastName+"\n";
	}

	public boolean isTeachingThisClass(SchoolClass schoolClass) {
		
		return teacherClassList.contains(schoolClass);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (teacherIdNo ^ (teacherIdNo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (teacherIdNo != other.teacherIdNo)
			return false;
		return true;
	}

	public boolean conflictsWithClass(SchoolClass schoolClass) {
		
		for (SchoolClass enrolled : teacherClassList) {

			if (enrolled.getClassSchedule().conflictsWith(
					schoolClass.getClassSchedule())) {
				return true;
			}

		}

		return false;
	}

	public boolean conflictsWithSchedule(Schedule schedule) {
		
		for (SchoolClass enrolled : teacherClassList) {

			if (enrolled.getClassSchedule().conflictsWith(
					schedule)) {
				return true;
			}

		}

		return false;
	}

	public long getTeacherIdNo() {
		return teacherIdNo;
	}

	public String getTeacherFirstName() {
		return teacherFirstName;
	}

	public String getTeacherLastName() {
		return teacherLastName;
	}

}
