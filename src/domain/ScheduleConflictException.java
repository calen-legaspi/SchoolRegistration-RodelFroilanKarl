package domain;

public class ScheduleConflictException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8234716375414466275L;

	String myMessage;
	
	public ScheduleConflictException(String message) {
		myMessage = message;
	}
	
	@Override
	public String getMessage(){
		return myMessage;
	}
}
