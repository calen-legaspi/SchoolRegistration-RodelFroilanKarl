package domain;

import java.sql.Time;

public class SchoolClass {

	private Schedule classSchedule;
	private Subject classSubject;
	private Teacher classTeacher;
	private int classLimit;
	private int classCurrentQuantity;

	public SchoolClass(Subject subject, Schedule schedule, Teacher teacher) {
		classSchedule = schedule;
		classSubject = subject;
		classTeacher = teacher;

	}

	

	@Override
	public String toString() {
		return classSubject.getCourseCode() + " "
				+ classSchedule.getClassDay().abbreviation + " "
				+ classSchedule.getClassStartTime() + " "
				+ classSchedule.getClassEndTime() + " " + classLimit+"\n";
	}

	public Schedule getClassSchedule() {
		return classSchedule;
	}

	public Subject getClassSubject() {
		return classSubject;
	}

	public Teacher getClassTeacher() {
		return classTeacher;
	}

	public int getClassLimit() {
		return classLimit;
	}

	public int getClassCurrentQuantity() {
		return classCurrentQuantity;
	}

	public String getClassSubjectName() {
		return this.getClassSubject().getCourseName();
	}
	
	public Time getClassScheduleStartTime() {
		return this.getClassSchedule().getClassStartTime();
	}
	
	public Time getClassScheduleEndTime() {
		return this.getClassSchedule().getClassEndTime();
	}
	
	public String getClassTeacherName() {
		return this.getClassTeacher().getTeacherLastName()+", "+this.getClassTeacher().getTeacherFirstName(); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + classLimit;
		result = prime * result
				+ ((classSchedule == null) ? 0 : classSchedule.hashCode());
		result = prime * result
				+ ((classSubject == null) ? 0 : classSubject.hashCode());
		result = prime * result
				+ ((classTeacher == null) ? 0 : classTeacher.hashCode());
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
		SchoolClass other = (SchoolClass) obj;
		if (classLimit != other.classLimit)
			return false;
		if (classSchedule == null) {
			if (other.classSchedule != null)
				return false;
		} else if (!classSchedule.equals(other.classSchedule))
			return false;
		if (classSubject == null) {
			if (other.classSubject != null)
				return false;
		} else if (!classSubject.equals(other.classSubject))
			return false;
		if (classTeacher == null) {
			if (other.classTeacher != null)
				return false;
		} else if (!classTeacher.equals(other.classTeacher))
			return false;
		return true;
	}



}
