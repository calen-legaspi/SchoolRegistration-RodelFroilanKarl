package domain;

import java.sql.Time;

public class Schedule {

	public enum Day {
		MONDAY("Monday", "MON"), TUESDAY("Tuesday", "TUE"), WEDNESDAY("Wednesday",
				"WED"), THURSDAY("Thursday", "THU"), FRIDAY("Friday", "FRI"), SATURDAY(
				"Saturday", "SAT"), SUNDAY("Sunday", "SUN");

		public String commonName;
		public String abbreviation;

		Day(String commonName, String abbreviation) {
			this.commonName = commonName;
			this.abbreviation = abbreviation;
		}
		
		
	}
	
	public static Day returnEnumValue(String string){
		if("MON".equals(string)){ return Day.MONDAY; }
		else if("TUE".equals(string)){ return Day.TUESDAY; }
		else if("WED".equals(string)){ return Day.WEDNESDAY; }
		else if("THU".equals(string)){ return Day.THURSDAY; }
		else if("FRI".equals(string)){ return Day.FRIDAY; }
		else if("SAT".equals(string)){ return Day.SATURDAY; }
		else if("SUN".equals(string)){ return Day.SUNDAY; }
		else {
			throw new IllegalArgumentException("Invalid Day of the Week.");
		}
	}

	private Schedule.Day classDay;
	private Time classStartTime;
	private Time classEndTime;

	public Schedule(Schedule.Day day, Time startTime, Time endTime) {
		classDay = day;
		classStartTime = startTime;
		classEndTime = endTime;
	}

	@Override
	public String toString() {
		return classDay.commonName + " " + classStartTime + " " + classEndTime+"\n";
	}

	public Schedule.Day getClassDay() {
		return classDay;
	}

	public Time getClassStartTime() {
		return classStartTime;
	}

	public Time getClassEndTime() {
		return classEndTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((classDay == null) ? 0 : classDay.hashCode());
		result = prime * result
				+ ((classEndTime == null) ? 0 : classEndTime.hashCode());
		result = prime * result
				+ ((classStartTime == null) ? 0 : classStartTime.hashCode());
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
		Schedule other = (Schedule) obj;
		if (classDay != other.classDay)
			return false;
		if (classEndTime == null) {
			if (other.classEndTime != null)
				return false;
		} else if (!classEndTime.equals(other.classEndTime))
			return false;
		if (classStartTime == null) {
			if (other.classStartTime != null)
				return false;
		} else if (!classStartTime.equals(other.classStartTime))
			return false;
		return true;
	}

	public boolean conflictsWith(Schedule schedule) {
		
		if(this.equals(schedule)){
			return true;
		}
		
		if(!classDay.equals(schedule.getClassDay())){
			return false;
		} else {
				
			if(classStartTime.before(schedule.classStartTime) && classEndTime.before(schedule.classStartTime)){
				return false;
			} else if(classStartTime.after(schedule.classEndTime) && classEndTime.after(schedule.classEndTime)){
				return false;
			} else {
				return true;
			}
		
		}
		
	}

}
