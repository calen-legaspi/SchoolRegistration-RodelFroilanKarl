package domain;

public class SystemUser {
	
	private long userId;
	private String userName;
	private String userPassword;
	
	protected SystemUser(long userId, String userName, String userPassword){
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	protected SystemUser(){
		
	}

	public long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		SystemUser other = (SystemUser) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

}
