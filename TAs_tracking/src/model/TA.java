package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.EventsConfigReader;

public class TA {
	
//	int id;
	String name;
	String mobileNo;
	String email;
	String title;
	Date hiringDate;
	boolean onVacation;
	Event lastEvent;
	List<Event> history;
	
	public TA() {
		
	}
	
	public TA(String name2, String mobileNo2, String email2, Date hiringDate, String title2, 
			String onVacation2) {
		name = name2;
		mobileNo = mobileNo2;
		email = email2;
		this.hiringDate = hiringDate;
		title = title2;
		if(onVacation2.equalsIgnoreCase(EventsConfigReader.getIsOnVacation(false)))
			onVacation = false;
		else
			onVacation = true;
	}

	public void addEventtoTA(Event event) {
		if(history == null)
			history = new ArrayList<Event>();
		history.add(event);
	}
	
	public void display() {
		System.out.println("**************** TA Info \"**************** ");
		System.out.println(name);
		System.out.println(mobileNo);
		System.out.println(email);
		System.out.println(title);
		System.out.println(hiringDate);
		System.out.println(onVacation);
		System.out.println(lastEvent);
		for(Event e: history)
			System.out.println(e);
		System.out.println("********************************************");
	}
	@Override
		public String toString() {
			return name;
		}
	
	@Override
	public TA clone() throws CloneNotSupportedException {
		
		TA clone = new TA();
		clone.name = this.name;
		clone.email = this.email;
		clone.mobileNo = this.mobileNo;
		clone.onVacation = this.onVacation;
		clone.title = this.title;
		clone.hiringDate = this.hiringDate;
		clone.lastEvent = this.lastEvent;
		clone.history = new ArrayList<Event>();
		clone.history.addAll(this.history);
		return clone;
	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isOnVacation() {
		return onVacation;
	}
	public void setOnVacation(boolean onVacation) {
		this.onVacation = onVacation;
	}
	public Event getLastEvent() {
		return lastEvent;
	}
	public void setLastEvent(Event lastEvent) {
		this.lastEvent = lastEvent;
	}
	public List<Event> getHistory() {
		return history;
	}
	public void setHistory(List<Event> history) {
		this.history = history;
	}

	public Date getHiringDate() {
		return hiringDate;
	}

	public void setHiringDate(Date hiringDate) {
		this.hiringDate = hiringDate;
	}
}
