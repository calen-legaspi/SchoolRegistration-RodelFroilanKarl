package beans;

public class TeacherBean implements java.io.Serializable {

	private static final long serialVersionUID = -3062485784568093424L;

	public String idNo;
	public String firstName;
	public String lastName;

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
