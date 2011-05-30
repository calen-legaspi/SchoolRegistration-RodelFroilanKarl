package beans;

import java.io.Serializable;

public class SubjectBean implements Serializable {

	private static final long serialVersionUID = -2454610740026052176L;

	public String code;
	public String name;
	public String level;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
