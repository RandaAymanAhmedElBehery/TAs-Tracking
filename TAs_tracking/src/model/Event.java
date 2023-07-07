package model;

import java.util.Date;

import utils.DateUtils;

public abstract class Event {
	
	String type;
	Date date; //start date if an event has from and to
	
	public Event() {
		this.type = this.getClass().getCanonicalName();
	} 
	
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
	public void setDate(String date) {
//	    Date date1 = null;
//		try {
//			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//		this.date = date1;
		this.date= DateUtils.stringtoDate(date);
	}

}
