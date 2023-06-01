package model;

import java.util.Date;

public abstract class Event {
	
	String type;
	Date date; //start date if an event has from and to
	
	@Override
	public String toString() {
		
		return "[type: " + type + " , date: " +date + "]";
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
