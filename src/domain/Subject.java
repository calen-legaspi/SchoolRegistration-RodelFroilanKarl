package domain;

public class Subject {

	private String courseCode;
	private String courseName;
	private String courseDescrip;
	private EducationLevel courseLevel;

	public Subject(String code, EducationLevel level, String name) {
		courseCode = code;
		courseName = name;
		courseLevel = level;
	}
	
	@Override
	public String toString(){
		return courseCode+", "+courseName+", "+courseLevel+"\n";
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseDescrip() {
		return courseDescrip;
	}

	public EducationLevel getCourseLevel() {
		return courseLevel;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCourseDescription(String courseDescrip) {
		this.courseDescrip = courseDescrip;
	}

	public static EducationLevel returnEnumValue(int level){
		if(level == 0){
			return EducationLevel.UNDERGRADUATE;
		}
		else if(level == 1){
			return EducationLevel.GRADUATE;
		}
		else{
			throw new IllegalArgumentException("level not recognized.");
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((courseCode == null) ? 0 : courseCode.hashCode());
		result = prime * result
				+ ((courseDescrip == null) ? 0 : courseDescrip.hashCode());
		result = prime * result
				+ ((courseLevel == null) ? 0 : courseLevel.hashCode());
		result = prime * result
				+ ((courseName == null) ? 0 : courseName.hashCode());
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
		Subject other = (Subject) obj;
		if (courseCode == null) {
			if (other.courseCode != null)
				return false;
		} else if (!courseCode.equals(other.courseCode))
			return false;
		if (courseDescrip == null) {
			if (other.courseDescrip != null)
				return false;
		} else if (!courseDescrip.equals(other.courseDescrip))
			return false;
		if (courseLevel != other.courseLevel)
			return false;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		return true;
	}
}
