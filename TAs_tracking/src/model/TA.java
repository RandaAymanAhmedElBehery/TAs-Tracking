package model;

import java.util.ArrayList;
import java.util.List;

public class TA {
	
//	int id;
	String name;
	String mobileNo;
	String email;
	String title;
	String yearOfHiring;
	boolean onVacation;
	Event lastEvent;
	List<Event> history;
	
	public TA() {
		
	}
	
	public TA(String name2, String mobileNo2, String email2, String year, String title2, 
			String onVacation2) {
		name = name2;
		mobileNo = mobileNo2;
		email = email2;
		yearOfHiring = year;
		title = title2;
		if(onVacation2.equalsIgnoreCase("No"))
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
		System.out.println(yearOfHiring);
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
	
	public String getYearOfHiring() {
		return yearOfHiring;
	}
	public void setYearOfHiring(String yearOfHiring) {
		this.yearOfHiring = yearOfHiring;
	}
	

}
